package com.ruoyi.common.core.utils.bean;

import com.ruoyi.common.core.utils.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 * 
 * @author ruoyi
 */
public class BeanUtils extends org.springframework.beans.BeanUtils
{

    /**
     * Bean方法名中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则表达式
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");
    /**
     * 类属性缓存
     */
    private static Map<Class<?>, Map<String, Field>> fieldsCache = new ConcurrentHashMap<>();

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src 源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     *
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取类中指定名称的属性，支持多层级。
     *
     * @param targetClass 类
     * @param fieldName 属性名
     * @return 返回对应的属性，如果没找到返回null。
     */
    public static Field findField(Class<?> targetClass, String fieldName) {
        if (fieldName.contains(".")) {
            return findNestedField(targetClass, fieldName);
        } else {
            return findDirectField(targetClass, fieldName);
        }
    }

    /**
     * 获取类中指定名称的多层级属性。
     *
     * @param targetClass 类
     * @param fieldName 属性名
     * @return 返回对应的属性，如果没找到返回null。
     */
    private static Field findNestedField(Class<?> targetClass, String fieldName) {
        String[] nestedFieldNames = fieldName.split("\\.");
        Field field = null;
        for (String nestedFieldName : nestedFieldNames) {
            field = findDirectField(targetClass, nestedFieldName);
            targetClass = field.getType();
        }
        return field;
    }

    /**
     * 获取类中指定名称的单层级属性。
     *
     * @param targetClass 类
     * @param fieldName 属性名
     * @return 返回对应的属性，如果没找到抛出异常。
     */
    private static Field findDirectField(Class<?> targetClass, String fieldName) {
        Field field = getAllDeclaredFields(targetClass).get(fieldName);
        if (field == null) {
            throw new RuntimeException("类[" + targetClass.getName() + "]中未找到属性[" + fieldName + "]。");
        }
        return field;
    }

    /**
     * 设置对象中指定属性的值，支持多层级。
     *
     * @param target 对象
     * @param fieldName 属性名
     * @param value 值
     */
    public static void setField(Object target, String fieldName, Object value) {
        if (fieldName.contains(".")) {
            setNestedField(target, fieldName, value);
        } else {
            setDirectField(target, fieldName, value);
        }
    }

    /**
     * 设置对象中指定多层级属性的值。
     *
     * @param target 对象
     * @param fieldName 属性名
     * @param value 值
     */
    private static void setNestedField(Object target, String fieldName, Object value) {
        String[] nestedFieldNames = StringUtils.substringBeforeLast(fieldName, ".").split("\\.");
        for (String nestedFieldName : nestedFieldNames) {
            if (target != null) {
                target = getDirectField(target, nestedFieldName);
            } else {
                throw new RuntimeException("未找到多层级属性：" + fieldName);
            }
        }
        setDirectField(target, StringUtils.substringAfterLast(fieldName, "."), value);
    }

    /**
     * 获取对象中指定单层级属性的值。
     *
     * @param target 对象
     * @param fieldName 属性名
     * @return 返回对象中指定属性的值。
     */
    private static Object getDirectField(Object target, String fieldName) {
        Field field = findDirectField(target.getClass(), fieldName);
        return getField(target, field);
    }

    /**
     * 获取对象中指定属性的值。
     *
     * @param target 对象
     * @param field 属性
     * @return 返回对象中指定属性的值。
     */
    public static Object getField(Object target, Field field) {
        if (target == null || field == null) {
            throw new RuntimeException("对象或属性不能为null。");
        }
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = field.get(target);
            field.setAccessible(accessible);
            return processHibernateLazyObject(result);
        } catch (Exception e) {
            throw new RuntimeException("获取对象的属性[" + field.getName() + "]值失败", e);
        }
    }

    /**
     * 处理Hibernate懒加载对象。
     *
     * @param lazyObject 懒加载对象
     * @return 如果是Hibernate懒加载对象则执行代理方法返回实际对象，否则直接返回。
     */
    private static Object processHibernateLazyObject(Object lazyObject) {
        try {
            Class<?> hibernateProxyClass = Class.forName("org.hibernate.proxy.HibernateProxy");
            if (hibernateProxyClass.isAssignableFrom(lazyObject.getClass())) {
                Method method = lazyObject.getClass().getMethod("getHibernateLazyInitializer");
                Object lazyInitializer = method.invoke(lazyObject);
                method = lazyInitializer.getClass().getMethod("getImplementation");
                return method.invoke(lazyInitializer);
            } else {
                return lazyObject;
            }
        } catch (Exception e) {
            return lazyObject;
        }
    }

    /**
     * 设置对象中指定单层级属性的值。
     *
     * @param target 对象
     * @param fieldName 属性名
     * @param value 值
     */
    private static void setDirectField(Object target, String fieldName, Object value) {
        Field field = findDirectField(target.getClass(), fieldName);
        setField(target, field, value);
    }

    /**
     * 设置对象中指定属性的值。
     *
     * @param target 对象
     * @param field 属性
     * @param value 值
     */
    public static void setField(Object target, Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(target, value);
            field.setAccessible(accessible);
        } catch (Exception e) {
            throw new RuntimeException("设置对象的属性[" + field.getName() + "]值失败", e);
        }
    }

    /**
     * 获取指定类所有的公共属性集合。
     *
     * @param targetClass 目标类
     * @return 返回指定类所有的公共属性集合。
     */
    public static Map<String, Field> getAllDeclaredFields(Class<?> targetClass) {
        if (fieldsCache.containsKey(targetClass)) {
            return fieldsCache.get(targetClass);
        }
        Map<String, Field> fields = new LinkedHashMap<>();
        for (Field field : targetClass.getDeclaredFields()) {
            fields.put(field.getName(), field);
        }
        Class<?> parentClass = targetClass.getSuperclass();
        if (parentClass != Object.class) {
            fields.putAll(getAllDeclaredFields(parentClass));
        }
        fieldsCache.put(targetClass, fields);
        return fields;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br> 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
