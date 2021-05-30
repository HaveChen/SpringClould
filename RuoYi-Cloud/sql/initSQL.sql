/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.34-log : Database - ry-system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `gen_table` */

DROP TABLE IF EXISTS `gen_table`;

CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

/*Data for the table `gen_table` */

/*Table structure for table `gen_table_column` */

DROP TABLE IF EXISTS `gen_table_column`;

CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

/*Data for the table `gen_table_column` */

/*Table structure for table `std_admin_class` */

DROP TABLE IF EXISTS `std_admin_class`;

CREATE TABLE `std_admin_class` (
  `class_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `class_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `class_type_code` varchar(50) DEFAULT NULL COMMENT '班级类型 关联std_class_type',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '单位 关联sys_dept',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `graduate_code` varchar(50) DEFAULT NULL COMMENT '届别 关联std_jiebie',
  `school_year_code` varchar(50) DEFAULT NULL COMMENT '学年',
  `grade_code` varchar(50) DEFAULT NULL COMMENT '年级 关联std_grade',
  `term_code` varchar(50) DEFAULT NULL COMMENT '学期 关联std_term',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='行政班';

/*Data for the table `std_admin_class` */

insert  into `std_admin_class`(`class_id`,`class_name`,`class_code`,`class_type_code`,`dept_id`,`dept_code`,`graduate_code`,`school_year_code`,`grade_code`,`term_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'1班','A1013001','top',108,'1013','high2020','high2020','1','up',1,NULL,0,0,NULL,'2020-12-20 10:34:33',NULL,'2021-02-27 14:42:11'),(2,'2班','A1013002','other',108,'1013','high2020','high2020','1','up',2,NULL,0,0,NULL,'2021-02-27 14:42:36',NULL,'2021-02-27 14:42:36');

/*Table structure for table `std_class_type` */

DROP TABLE IF EXISTS `std_class_type`;

CREATE TABLE `std_class_type` (
  `class_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_type_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `class_type_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`class_type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='班级类型';

/*Data for the table `std_class_type` */

insert  into `std_class_type`(`class_type_id`,`class_type_name`,`class_type_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'尖子班','top',1,NULL,0,0,NULL,'2020-12-20 09:42:02',NULL,'2020-12-20 09:42:02'),(99,'其他','other',99,NULL,0,0,NULL,'2020-12-20 09:42:33',NULL,'2020-12-20 09:42:33');

/*Table structure for table `std_course` */

DROP TABLE IF EXISTS `std_course`;

CREATE TABLE `std_course` (
  `course_id` varchar(50) NOT NULL COMMENT '主键',
  `exam_id` bigint(20) DEFAULT NULL COMMENT '考试',
  `course_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `course_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `teacher_code` varchar(50) DEFAULT NULL COMMENT '教师',
  `subject_code` varchar(50) DEFAULT NULL COMMENT '学科',
  `teach_class_code` varchar(50) DEFAULT NULL COMMENT '教学班',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '单位 关联sys_dept',
  `graduate_code` varchar(50) DEFAULT NULL COMMENT '届别',
  `school_year_code` varchar(50) DEFAULT NULL COMMENT '学年',
  `grade_code` varchar(50) DEFAULT NULL COMMENT '年级',
  `extensions` text COMMENT '扩展字段',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程';

/*Data for the table `std_course` */

/*Table structure for table `std_grade` */

DROP TABLE IF EXISTS `std_grade`;

CREATE TABLE `std_grade` (
  `grade_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grade_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `grade_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `study_level_code` varchar(50) DEFAULT NULL COMMENT '学段',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='年级';

/*Data for the table `std_grade` */

insert  into `std_grade`(`grade_id`,`grade_name`,`grade_code`,`study_level_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'一年级','1','primary',1,NULL,0,0,NULL,'2020-12-20 09:27:48',NULL,'2020-12-20 09:28:03'),(2,'二年级','2','primary',2,NULL,0,0,NULL,'2020-12-20 09:37:21',NULL,'2020-12-20 09:37:21'),(3,'三年级','3','primary',3,NULL,0,0,NULL,'2020-12-20 09:37:29',NULL,'2020-12-20 09:37:29'),(4,'四年级','4','primary',4,NULL,0,0,NULL,'2020-12-20 09:37:44',NULL,'2020-12-20 09:37:44'),(5,'五年级','5','primary',5,NULL,0,0,NULL,'2020-12-20 09:37:53',NULL,'2020-12-20 09:37:53'),(6,'六年级','6','primary',7,NULL,0,0,NULL,'2020-12-20 09:38:17',NULL,'2020-12-20 09:38:17'),(7,'初一','7','middle',7,NULL,0,0,NULL,'2020-12-20 09:38:27',NULL,'2020-12-20 09:38:27'),(8,'初二','8','middle',8,NULL,0,0,NULL,'2020-12-20 09:38:42',NULL,'2020-12-20 09:38:42'),(9,'初三','9','middle',9,NULL,0,0,NULL,'2020-12-20 09:38:52',NULL,'2020-12-20 09:38:52'),(10,'高一','10','high',10,NULL,0,0,NULL,'2020-12-20 09:39:03',NULL,'2020-12-20 09:39:03'),(11,'高二','11','high',11,NULL,0,0,NULL,'2020-12-20 09:39:13',NULL,'2020-12-20 09:39:13'),(12,'高三','12','high',12,NULL,0,0,NULL,'2020-12-20 09:39:22',NULL,'2020-12-20 09:39:22');

/*Table structure for table `std_grade_leader` */

DROP TABLE IF EXISTS `std_grade_leader`;

CREATE TABLE `std_grade_leader` (
  `grade_leader_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '单位 关联sys_dept',
  `grade_code` varchar(50) DEFAULT NULL COMMENT '年级',
  `school_year_code` varchar(50) DEFAULT NULL COMMENT '学年',
  `graduate_code` varchar(50) DEFAULT NULL COMMENT '届别',
  `term_code` varchar(50) DEFAULT NULL COMMENT '学期',
  `teacher_code` varchar(50) DEFAULT NULL COMMENT '教师',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`grade_leader_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='年级组长';

/*Data for the table `std_grade_leader` */

insert  into `std_grade_leader`(`grade_leader_id`,`dept_code`,`dept_id`,`grade_code`,`school_year_code`,`graduate_code`,`term_code`,`teacher_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'1013',100,'1','high2020','high2020','up','T1013001',1,NULL,0,0,NULL,'2020-12-20 16:36:09',NULL,'2021-03-15 08:45:55');

/*Table structure for table `std_graduate` */

DROP TABLE IF EXISTS `std_graduate`;

CREATE TABLE `std_graduate` (
  `graduate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `graduate_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `graduate_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `study_level_code` varchar(50) DEFAULT NULL COMMENT '学段 关联std_grade_type',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`graduate_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='届别';

/*Data for the table `std_graduate` */

insert  into `std_graduate`(`graduate_id`,`graduate_name`,`graduate_code`,`study_level_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'高2020届','high2020','high',1,NULL,0,0,NULL,'2020-12-18 11:02:42',NULL,'2020-12-18 11:02:42'),(2,'高2021届','high2021','high',2,NULL,0,0,NULL,'2020-12-30 10:41:45',NULL,'2021-01-06 08:57:54');

/*Table structure for table `std_school_year` */

DROP TABLE IF EXISTS `std_school_year`;

CREATE TABLE `std_school_year` (
  `school_year_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_year_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `school_year_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `study_level_code` varchar(50) DEFAULT NULL COMMENT '学段',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`school_year_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学年';

/*Data for the table `std_school_year` */

insert  into `std_school_year`(`school_year_id`,`school_year_name`,`school_year_code`,`study_level_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'初2020学年','high2020','high',1,NULL,0,0,NULL,'2020-12-18 10:49:15',NULL,'2020-12-18 11:02:50');

/*Table structure for table `std_study_level` */

DROP TABLE IF EXISTS `std_study_level`;

CREATE TABLE `std_study_level` (
  `study_level_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `study_level_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `study_level_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`study_level_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学段';

/*Data for the table `std_study_level` */

insert  into `std_study_level`(`study_level_id`,`study_level_name`,`study_level_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'小学','primary',1,NULL,0,0,NULL,'2020-12-18 10:26:56','admin','2021-02-07 17:46:49'),(2,'初中','middle',2,NULL,0,0,NULL,'2020-12-18 10:27:19','admin','2021-02-07 17:47:04'),(3,'高中','high',3,NULL,0,0,NULL,'2020-12-18 10:27:35',NULL,'2020-12-18 10:27:35');

/*Table structure for table `std_subject` */

DROP TABLE IF EXISTS `std_subject`;

CREATE TABLE `std_subject` (
  `subject_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `subject_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `subject_other_name` varchar(50) DEFAULT NULL COMMENT '科目别名 用|隔开-思想品德|思品|品德',
  `subject_type_code` varchar(50) DEFAULT NULL COMMENT '科目类型 关联std_subject_type：必选，首选等等',
  `subject_sign` varchar(50) DEFAULT NULL COMMENT '科目标识 1,10,100（原则唯一，相加之后唯一）',
  `subject_classify_code` varchar(50) DEFAULT NULL COMMENT '科目分类 关联std_subject_classify:外语，综合等',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='科目';

/*Data for the table `std_subject` */

insert  into `std_subject`(`subject_id`,`subject_name`,`subject_code`,`subject_other_name`,`subject_type_code`,`subject_sign`,`subject_classify_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'语文','yuwen','','required','1',NULL,1,'1',0,0,NULL,'2020-12-18 11:34:03',NULL,'2020-12-18 11:34:03'),(2,'数学','shuxue','','required','10',NULL,2,NULL,0,0,NULL,'2020-12-18 11:52:15',NULL,'2020-12-18 11:52:15'),(3,'英语','yingyu',NULL,'required','100','foreign',3,NULL,0,0,NULL,'2020-12-18 11:52:44',NULL,'2020-12-18 11:52:44'),(4,'政治','zhengzhi','思品|思想品德','second','1000',NULL,4,NULL,0,0,NULL,'2020-12-18 11:53:31',NULL,'2021-05-14 18:33:57'),(5,'历史','lishi',NULL,'first','10000',NULL,5,NULL,0,0,NULL,'2020-12-20 09:17:37',NULL,'2021-05-14 18:34:56'),(6,'地理','dili',NULL,'second','100000',NULL,6,NULL,0,0,NULL,'2020-12-20 09:18:11',NULL,'2020-12-20 09:18:17'),(7,'物理','wuli',NULL,'first','1000000',NULL,7,NULL,0,0,NULL,'2020-12-20 09:18:48',NULL,'2021-02-27 14:53:45'),(8,'化学','huaxue',NULL,'second','10000000',NULL,8,NULL,0,0,NULL,'2020-12-20 09:19:07',NULL,'2020-12-20 09:19:12'),(9,'生物','shengwu',NULL,'second','100000000',NULL,9,NULL,0,0,NULL,'2020-12-20 09:19:51',NULL,'2020-12-20 09:19:51');

/*Table structure for table `std_subject_classify` */

DROP TABLE IF EXISTS `std_subject_classify`;

CREATE TABLE `std_subject_classify` (
  `subject_classify_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_classify_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `subject_classify_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`subject_classify_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='科目分类';

/*Data for the table `std_subject_classify` */

insert  into `std_subject_classify`(`subject_classify_id`,`subject_classify_name`,`subject_classify_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'外语','foreign',1,NULL,0,0,NULL,'2020-12-18 11:14:35',NULL,'2020-12-18 11:14:35');

/*Table structure for table `std_subject_combine` */

DROP TABLE IF EXISTS `std_subject_combine`;

CREATE TABLE `std_subject_combine` (
  `subject_combine_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_combine_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `subject_combine_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`subject_combine_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='科目组合类型';

/*Data for the table `std_subject_combine` */

insert  into `std_subject_combine`(`subject_combine_id`,`subject_combine_name`,`subject_combine_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'物化生','111000111',1,NULL,0,0,NULL,'2020-12-20 09:21:14',NULL,'2020-12-20 09:21:14'),(2,'化史地','10011111',2,NULL,0,0,NULL,'2021-03-05 09:13:54',NULL,'2021-03-05 09:13:54'),(3,'化生史','11010111',3,NULL,0,0,NULL,NULL,NULL,'2021-03-12 17:40:24');

/*Table structure for table `std_subject_type` */

DROP TABLE IF EXISTS `std_subject_type`;

CREATE TABLE `std_subject_type` (
  `subject_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_type_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `subject_type_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`subject_type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='科目类型';

/*Data for the table `std_subject_type` */

insert  into `std_subject_type`(`subject_type_id`,`subject_type_name`,`subject_type_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'必选科目','required',1,NULL,0,0,NULL,'2020-12-18 11:12:36',NULL,'2020-12-18 11:15:05'),(2,'首选科目','first',2,NULL,0,0,NULL,'2020-12-18 11:12:54',NULL,'2020-12-18 11:15:25'),(3,'次选科目','second',3,NULL,0,0,NULL,'2020-12-18 11:13:08',NULL,'2020-12-18 11:15:38'),(99,'其他','other',99,NULL,0,0,NULL,'2020-12-18 11:13:18',NULL,'2020-12-18 11:15:13');

/*Table structure for table `std_teach_class` */

DROP TABLE IF EXISTS `std_teach_class`;

CREATE TABLE `std_teach_class` (
  `teach_class_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teach_class_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `teach_class_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '单位 关联sys_dept',
  `graduate_code` varchar(50) DEFAULT NULL COMMENT '届别 关联std_jiebie',
  `school_year_code` varchar(50) DEFAULT NULL COMMENT '学年 关联std_school_year',
  `grade_code` varchar(50) DEFAULT NULL COMMENT '年级 关联std_grade',
  `term_code` varchar(50) DEFAULT NULL COMMENT '学期 关联std_term',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`teach_class_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='教学班';

/*Data for the table `std_teach_class` */

insert  into `std_teach_class`(`teach_class_id`,`teach_class_name`,`teach_class_code`,`dept_code`,`dept_id`,`graduate_code`,`school_year_code`,`grade_code`,`term_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'化学2班','1013huaxue02','1013',110,'high2020','high2020','1','up',2,NULL,0,0,NULL,'2020-12-20 14:50:36',NULL,'2021-02-27 14:44:06'),(2,'化学1班','1013huaxue01','1013',110,'high2020','high2020','1','up',1,NULL,0,0,NULL,'2020-12-30 10:48:20',NULL,'2021-02-27 14:43:50'),(3,'物理1班','1013wuli01','1013',110,'high2020','high2020','1','up',3,NULL,0,0,NULL,'2021-02-27 14:44:32',NULL,'2021-02-27 14:44:32'),(4,'物理2班','1013wuli02','1013',110,'high2020','high2020','1','up',4,NULL,0,0,NULL,'2021-02-27 14:44:47',NULL,'2021-02-27 14:44:47'),(5,'化学1班','1014huaxue01','1014',111,'high2020','high2020','1','up',5,NULL,0,0,NULL,'2021-02-27 14:45:23',NULL,'2021-02-27 14:45:23'),(6,'化学2班','1014huaxue02','1014',111,'high2020','high2020','1','up',6,NULL,0,0,NULL,'2021-02-27 14:45:42',NULL,'2021-02-27 14:45:42'),(7,'物理1班','1014wuli01','1014',111,'high2020','high2020','1','up',7,NULL,0,0,NULL,'2021-02-27 14:46:06',NULL,'2021-02-27 14:46:06'),(8,'物理2班','1014wuli02','1014',111,'high2020','high2020','1','up',8,NULL,0,0,NULL,'2021-02-27 14:46:22',NULL,'2021-02-27 14:46:22');

/*Table structure for table `std_teacher` */

DROP TABLE IF EXISTS `std_teacher`;

CREATE TABLE `std_teacher` (
  `teacher_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `teacher_other_name` varchar(50) DEFAULT NULL COMMENT '别称',
  `teacher_code` varchar(50) DEFAULT NULL COMMENT '工号',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '单位 关联sys_dept',
  `teacher_title_code` varchar(50) DEFAULT NULL COMMENT '职称 关联：std_teacher_post',
  `idcard` varchar(50) DEFAULT NULL COMMENT '身份证',
  `gender` varchar(50) DEFAULT NULL COMMENT '性别',
  `mobile_phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `landline_phone` varchar(50) DEFAULT NULL COMMENT '座机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `start_work_time` varchar(50) DEFAULT NULL COMMENT '开始工作时间',
  `subject_code` varchar(50) DEFAULT NULL COMMENT '学科',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='教师';

/*Data for the table `std_teacher` */

insert  into `std_teacher`(`teacher_id`,`teacher_name`,`teacher_other_name`,`teacher_code`,`dept_code`,`dept_id`,`teacher_title_code`,`idcard`,`gender`,`mobile_phone`,`landline_phone`,`email`,`start_work_time`,`subject_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'张三','张教授','T1013001','1013',100,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,0,0,NULL,'2020-12-20 15:21:19',NULL,'2021-02-27 14:46:45'),(2,'李四','李老师','T1013002','1013',110,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,0,0,NULL,'2021-02-27 14:47:01',NULL,'2021-02-27 14:48:49'),(3,'王二','王校长','T1013003','1013',110,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,0,0,NULL,'2021-02-27 14:47:33',NULL,'2021-02-27 14:49:00'),(4,'张天','张博士','T1014001','1014',111,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,0,0,NULL,'2021-02-27 14:47:55',NULL,'2021-02-27 14:47:55'),(5,'陈老','陈教授','T1013002','1014',111,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,NULL,0,0,NULL,'2021-02-27 14:48:24',NULL,'2021-02-27 14:48:24'),(6,'王小','王老师','T1013003','1014',111,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,NULL,0,0,NULL,'2021-02-27 14:48:43',NULL,'2021-02-27 14:48:43'),(7,'严喜宇',NULL,'1001','51090301',110,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,'2021-05-14 23:49:19',NULL,'2021-05-14 23:49:19');

/*Table structure for table `std_teacher_title` */

DROP TABLE IF EXISTS `std_teacher_title`;

CREATE TABLE `std_teacher_title` (
  `teacher_title_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_title_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `teacher_title_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`teacher_title_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='教师职称';

/*Data for the table `std_teacher_title` */

/*Table structure for table `std_term` */

DROP TABLE IF EXISTS `std_term`;

CREATE TABLE `std_term` (
  `term_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `term_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `term_code` varchar(50) DEFAULT NULL COMMENT '代码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态 0正常1停用',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记 0正常1删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`term_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学期';

/*Data for the table `std_term` */

insert  into `std_term`(`term_id`,`term_name`,`term_code`,`order_num`,`remark`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'上学期','up',1,'123',0,0,NULL,'2020-12-18 10:29:08',NULL,'2020-12-18 10:29:08'),(2,'下学期','down',2,NULL,0,0,NULL,'2020-12-18 10:29:21',NULL,'2020-12-18 10:29:21');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `config_id` varchar(64) NOT NULL COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`config_id`,`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values ('1','主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-04-25 10:58:03','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),('2','用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-04-25 10:58:03','',NULL,'初始化密码 123456'),('3','主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-04-25 10:58:03','',NULL,'深色主题theme-dark，浅色主题theme-light');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`ancestors`,`dept_name`,`order_num`,`leader`,`phone`,`email`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (100,0,'0','深圳海云天科技',0,'海云天','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:15','admin','2021-05-30 08:36:04'),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:15','admin','2021-05-30 08:36:04'),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:15','admin','2021-05-30 08:36:04'),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04'),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04'),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04'),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04'),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04'),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04'),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2021-04-25 10:57:16','admin','2021-05-30 08:36:04');

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `dict_code` varchar(64) NOT NULL COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values ('1',1,'男','0','sys_user_sex','','','Y','0','admin','2021-04-25 10:57:57','',NULL,'性别男'),('10',1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2021-04-25 10:57:58','',NULL,'默认分组'),('11',2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2021-04-25 10:57:58','',NULL,'系统分组'),('12',1,'是','Y','sys_yes_no','','primary','Y','0','admin','2021-04-25 10:57:58','',NULL,'系统默认是'),('13',2,'否','N','sys_yes_no','','danger','N','0','admin','2021-04-25 10:57:59','',NULL,'系统默认否'),('14',1,'通知','1','sys_notice_type','','warning','Y','0','admin','2021-04-25 10:57:59','',NULL,'通知'),('15',2,'公告','2','sys_notice_type','','success','N','0','admin','2021-04-25 10:57:59','',NULL,'公告'),('16',1,'正常','0','sys_notice_status','','primary','Y','0','admin','2021-04-25 10:57:59','',NULL,'正常状态'),('17',2,'关闭','1','sys_notice_status','','danger','N','0','admin','2021-04-25 10:57:59','',NULL,'关闭状态'),('18',1,'新增','1','sys_oper_type','','info','N','0','admin','2021-04-25 10:57:59','',NULL,'新增操作'),('19',2,'修改','2','sys_oper_type','','info','N','0','admin','2021-04-25 10:57:59','',NULL,'修改操作'),('2',2,'女','1','sys_user_sex','','','N','0','admin','2021-04-25 10:57:57','',NULL,'性别女'),('20',3,'删除','3','sys_oper_type','','danger','N','0','admin','2021-04-25 10:57:59','',NULL,'删除操作'),('21',4,'授权','4','sys_oper_type','','primary','N','0','admin','2021-04-25 10:57:59','',NULL,'授权操作'),('22',5,'导出','5','sys_oper_type','','warning','N','0','admin','2021-04-25 10:57:59','',NULL,'导出操作'),('23',6,'导入','6','sys_oper_type','','warning','N','0','admin','2021-04-25 10:57:59','',NULL,'导入操作'),('24',7,'强退','7','sys_oper_type','','danger','N','0','admin','2021-04-25 10:57:59','',NULL,'强退操作'),('25',8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2021-04-25 10:57:59','',NULL,'生成操作'),('26',9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2021-04-25 10:57:59','',NULL,'清空操作'),('27',1,'成功','0','sys_common_status','','primary','N','0','admin','2021-04-25 10:57:59','',NULL,'正常状态'),('28',2,'失败','1','sys_common_status','','danger','N','0','admin','2021-04-25 10:57:59','',NULL,'停用状态'),('3',3,'未知','2','sys_user_sex','','','N','0','admin','2021-04-25 10:57:57','',NULL,'性别未知'),('4',1,'显示','0','sys_show_hide','','primary','Y','0','admin','2021-04-25 10:57:58','',NULL,'显示菜单'),('5',2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2021-04-25 10:57:58','',NULL,'隐藏菜单'),('6',1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2021-04-25 10:57:58','',NULL,'正常状态'),('7',2,'停用','1','sys_normal_disable','','danger','N','0','admin','2021-04-25 10:57:58','',NULL,'停用状态'),('8',1,'正常','0','sys_job_status','','primary','Y','0','admin','2021-04-25 10:57:58','',NULL,'正常状态'),('9',2,'暂停','1','sys_job_status','','danger','N','0','admin','2021-04-25 10:57:58','',NULL,'停用状态');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'用户性别','sys_user_sex','0','admin','2021-04-25 10:57:54','admin','2021-05-28 02:53:15','用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2021-04-25 10:57:54','admin','2021-05-28 02:49:08','菜单状态列表12'),(3,'系统开关','sys_normal_disable','0','admin','2021-04-25 10:57:54','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2021-04-25 10:57:54','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2021-04-25 10:57:54','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2021-04-25 10:57:55','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2021-04-25 10:57:55','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2021-04-25 10:57:55','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2021-04-25 10:57:55','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2021-04-25 10:57:55','',NULL,'登录状态列表');

/*Table structure for table `sys_job` */

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

/*Data for the table `sys_job` */

insert  into `sys_job`(`job_id`,`job_name`,`job_group`,`invoke_target`,`cron_expression`,`misfire_policy`,`concurrent`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2021-04-25 10:58:06','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2021-04-25 10:58:06','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2021-04-25 10:58:07','',NULL,'');

/*Table structure for table `sys_job_log` */

DROP TABLE IF EXISTS `sys_job_log`;

CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

/*Data for the table `sys_job_log` */

/*Table structure for table `sys_logininfor` */

DROP TABLE IF EXISTS `sys_logininfor`;

CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示信息',
  `access_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `sys_logininfor` */

insert  into `sys_logininfor`(`info_id`,`user_name`,`ipaddr`,`status`,`msg`,`access_time`) values (100,'admin','10.35.1.201','0','登录成功','2021-04-25 11:03:17'),(101,'admin','10.35.1.201','0','退出成功','2021-04-25 12:30:11'),(102,'admin','10.35.1.201','0','登录成功','2021-04-25 12:30:14'),(103,'admin','10.35.1.201','0','登录成功','2021-05-19 07:10:08'),(104,'admin','10.35.1.201','0','登录成功','2021-05-21 02:14:04'),(105,'admin','10.35.1.201','0','登录成功','2021-05-24 00:40:48'),(106,'admin','10.35.1.201','0','登录成功','2021-05-28 02:46:56'),(107,'admin','10.35.1.201','0','登录成功','2021-05-28 03:08:28'),(108,'admin','10.35.1.201','0','登录成功','2021-05-28 03:39:05'),(109,'admin','10.35.1.201','0','登录成功','2021-05-28 06:39:04'),(110,'admin','10.35.1.201','0','登录成功','2021-05-30 06:56:14');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1343 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统管理',0,10,'system',NULL,1,0,'M','0','0','','system','admin','2021-04-25 10:57:24','admin','2021-05-30 06:56:45','系统管理目录'),(2,'系统监控',0,91,'monitor',NULL,1,0,'M','0','0','','monitor','admin','2021-04-25 10:57:24','admin','2021-05-30 06:56:38','系统监控目录'),(3,'系统工具',0,90,'tool',NULL,1,0,'M','0','0','','tool','admin','2021-04-25 10:57:24','admin','2021-05-30 06:56:30','系统工具目录'),(100,'用户管理',1,1,'user','system/user/index',1,0,'C','0','0','system:user:list','user','admin','2021-04-25 10:57:25','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index',1,0,'C','0','0','system:role:list','peoples','admin','2021-04-25 10:57:25','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index',1,0,'C','0','0','system:menu:list','tree-table','admin','2021-04-25 10:57:25','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index',1,0,'C','0','0','system:dept:list','tree','admin','2021-04-25 10:57:25','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index',1,0,'C','0','0','system:post:list','post','admin','2021-04-25 10:57:25','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index',1,0,'C','0','0','system:dict:list','dict','admin','2021-04-25 10:57:25','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index',1,0,'C','0','0','system:config:list','edit','admin','2021-04-25 10:57:25','',NULL,'参数设置菜单'),(107,'通知公告',1,9,'notice','system/notice/index',1,0,'C','0','0','system:notice:list','message','admin','2021-04-25 10:57:25','',NULL,'通知公告菜单'),(108,'日志管理',1,10,'log','',1,0,'M','0','0','','log','admin','2021-04-25 10:57:26','',NULL,'日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index',1,0,'C','0','0','monitor:online:list','online','admin','2021-04-25 10:57:26','',NULL,'在线用户菜单'),(110,'定时任务',2,2,'job','monitor/job/index',1,0,'C','0','0','monitor:job:list','job','admin','2021-04-25 10:57:26','',NULL,'定时任务菜单'),(111,'Sentinel控制台',2,3,'http://localhost:8718','',1,0,'C','0','0','monitor:sentinel:list','sentinel','admin','2021-04-25 10:57:26','',NULL,'流量控制菜单'),(112,'Nacos控制台',2,4,'http://localhost:8848/nacos','',1,0,'C','0','0','monitor:nacos:list','nacos','admin','2021-04-25 10:57:26','',NULL,'服务治理菜单'),(113,'Admin控制台',2,5,'http://localhost:9100/login','',1,0,'C','0','0','monitor:server:list','server','admin','2021-04-25 10:57:26','',NULL,'服务监控菜单'),(114,'表单构建',3,1,'build','tool/build/index',1,0,'C','0','0','tool:build:list','build','admin','2021-04-25 10:57:26','',NULL,'表单构建菜单'),(115,'代码生成',3,2,'gen','tool/gen/index',1,0,'C','0','0','tool:gen:list','code','admin','2021-04-25 10:57:26','',NULL,'代码生成菜单'),(116,'系统接口',3,3,'http://localhost:8080/swagger-ui.html','',1,0,'C','0','0','tool:swagger:list','swagger','admin','2021-04-25 10:57:26','',NULL,'系统接口菜单'),(500,'操作日志',108,1,'operlog','system/operlog/index',1,0,'C','0','0','system:operlog:list','form','admin','2021-04-25 10:57:26','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','system/logininfor/index',1,0,'C','0','0','system:logininfor:list','logininfor','admin','2021-04-25 10:57:27','',NULL,'登录日志菜单'),(1001,'用户查询',100,1,'','',1,0,'F','0','0','system:user:query','#','admin','2021-04-25 10:57:27','',NULL,''),(1002,'用户新增',100,2,'','',1,0,'F','0','0','system:user:add','#','admin','2021-04-25 10:57:27','',NULL,''),(1003,'用户修改',100,3,'','',1,0,'F','0','0','system:user:edit','#','admin','2021-04-25 10:57:27','',NULL,''),(1004,'用户删除',100,4,'','',1,0,'F','0','0','system:user:remove','#','admin','2021-04-25 10:57:27','',NULL,''),(1005,'用户导出',100,5,'','',1,0,'F','0','0','system:user:export','#','admin','2021-04-25 10:57:27','',NULL,''),(1006,'用户导入',100,6,'','',1,0,'F','0','0','system:user:import','#','admin','2021-04-25 10:57:27','',NULL,''),(1007,'重置密码',100,7,'','',1,0,'F','0','0','system:user:resetPwd','#','admin','2021-04-25 10:57:27','',NULL,''),(1008,'角色查询',101,1,'','',1,0,'F','0','0','system:role:query','#','admin','2021-04-25 10:57:27','',NULL,''),(1009,'角色新增',101,2,'','',1,0,'F','0','0','system:role:add','#','admin','2021-04-25 10:57:27','',NULL,''),(1010,'角色修改',101,3,'','',1,0,'F','0','0','system:role:edit','#','admin','2021-04-25 10:57:27','',NULL,''),(1011,'角色删除',101,4,'','',1,0,'F','0','0','system:role:remove','#','admin','2021-04-25 10:57:27','',NULL,''),(1012,'角色导出',101,5,'','',1,0,'F','0','0','system:role:export','#','admin','2021-04-25 10:57:27','',NULL,''),(1013,'菜单查询',102,1,'','',1,0,'F','0','0','system:menu:query','#','admin','2021-04-25 10:57:27','',NULL,''),(1014,'菜单新增',102,2,'','',1,0,'F','0','0','system:menu:add','#','admin','2021-04-25 10:57:28','',NULL,''),(1015,'菜单修改',102,3,'','',1,0,'F','0','0','system:menu:edit','#','admin','2021-04-25 10:57:28','',NULL,''),(1016,'菜单删除',102,4,'','',1,0,'F','0','0','system:menu:remove','#','admin','2021-04-25 10:57:28','',NULL,''),(1017,'部门查询',103,1,'','',1,0,'F','0','0','system:dept:query','#','admin','2021-04-25 10:57:28','',NULL,''),(1018,'部门新增',103,2,'','',1,0,'F','0','0','system:dept:add','#','admin','2021-04-25 10:57:28','',NULL,''),(1019,'部门修改',103,3,'','',1,0,'F','0','0','system:dept:edit','#','admin','2021-04-25 10:57:28','',NULL,''),(1020,'部门删除',103,4,'','',1,0,'F','0','0','system:dept:remove','#','admin','2021-04-25 10:57:28','',NULL,''),(1021,'岗位查询',104,1,'','',1,0,'F','0','0','system:post:query','#','admin','2021-04-25 10:57:28','',NULL,''),(1022,'岗位新增',104,2,'','',1,0,'F','0','0','system:post:add','#','admin','2021-04-25 10:57:28','',NULL,''),(1023,'岗位修改',104,3,'','',1,0,'F','0','0','system:post:edit','#','admin','2021-04-25 10:57:28','',NULL,''),(1024,'岗位删除',104,4,'','',1,0,'F','0','0','system:post:remove','#','admin','2021-04-25 10:57:28','',NULL,''),(1025,'岗位导出',104,5,'','',1,0,'F','0','0','system:post:export','#','admin','2021-04-25 10:57:28','',NULL,''),(1026,'字典查询',105,1,'#','',1,0,'F','0','0','system:dict:query','#','admin','2021-04-25 10:57:28','',NULL,''),(1027,'字典新增',105,2,'#','',1,0,'F','0','0','system:dict:add','#','admin','2021-04-25 10:57:28','',NULL,''),(1028,'字典修改',105,3,'#','',1,0,'F','0','0','system:dict:edit','#','admin','2021-04-25 10:57:29','',NULL,''),(1029,'字典删除',105,4,'#','',1,0,'F','0','0','system:dict:remove','#','admin','2021-04-25 10:57:29','',NULL,''),(1030,'字典导出',105,5,'#','',1,0,'F','0','0','system:dict:export','#','admin','2021-04-25 10:57:29','',NULL,''),(1031,'参数查询',106,1,'#','',1,0,'F','0','0','system:config:query','#','admin','2021-04-25 10:57:29','',NULL,''),(1032,'参数新增',106,2,'#','',1,0,'F','0','0','system:config:add','#','admin','2021-04-25 10:57:29','',NULL,''),(1033,'参数修改',106,3,'#','',1,0,'F','0','0','system:config:edit','#','admin','2021-04-25 10:57:29','',NULL,''),(1034,'参数删除',106,4,'#','',1,0,'F','0','0','system:config:remove','#','admin','2021-04-25 10:57:29','',NULL,''),(1035,'参数导出',106,5,'#','',1,0,'F','0','0','system:config:export','#','admin','2021-04-25 10:57:29','',NULL,''),(1041,'公告查询',107,1,'#','',1,0,'F','0','0','system:notice:query','#','admin','2021-04-25 10:57:29','',NULL,''),(1042,'公告新增',107,2,'#','',1,0,'F','0','0','system:notice:add','#','admin','2021-04-25 10:57:29','',NULL,''),(1043,'公告修改',107,3,'#','',1,0,'F','0','0','system:notice:edit','#','admin','2021-04-25 10:57:29','',NULL,''),(1044,'公告删除',107,4,'#','',1,0,'F','0','0','system:notice:remove','#','admin','2021-04-25 10:57:29','',NULL,''),(1045,'操作查询',500,1,'#','',1,0,'F','0','0','system:operlog:query','#','admin','2021-04-25 10:57:29','',NULL,''),(1046,'操作删除',500,2,'#','',1,0,'F','0','0','system:operlog:remove','#','admin','2021-04-25 10:57:29','',NULL,''),(1047,'日志导出',500,4,'#','',1,0,'F','0','0','system:operlog:export','#','admin','2021-04-25 10:57:30','',NULL,''),(1048,'登录查询',501,1,'#','',1,0,'F','0','0','system:logininfor:query','#','admin','2021-04-25 10:57:30','',NULL,''),(1049,'登录删除',501,2,'#','',1,0,'F','0','0','system:logininfor:remove','#','admin','2021-04-25 10:57:30','',NULL,''),(1050,'日志导出',501,3,'#','',1,0,'F','0','0','system:logininfor:export','#','admin','2021-04-25 10:57:30','',NULL,''),(1051,'在线查询',109,1,'#','',1,0,'F','0','0','monitor:online:query','#','admin','2021-04-25 10:57:30','',NULL,''),(1052,'批量强退',109,2,'#','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2021-04-25 10:57:30','',NULL,''),(1053,'单条强退',109,3,'#','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2021-04-25 10:57:30','',NULL,''),(1054,'任务查询',110,1,'#','',1,0,'F','0','0','monitor:job:query','#','admin','2021-04-25 10:57:30','',NULL,''),(1055,'任务新增',110,2,'#','',1,0,'F','0','0','monitor:job:add','#','admin','2021-04-25 10:57:30','',NULL,''),(1056,'任务修改',110,3,'#','',1,0,'F','0','0','monitor:job:edit','#','admin','2021-04-25 10:57:30','',NULL,''),(1057,'任务删除',110,4,'#','',1,0,'F','0','0','monitor:job:remove','#','admin','2021-04-25 10:57:30','',NULL,''),(1058,'状态修改',110,5,'#','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2021-04-25 10:57:30','',NULL,''),(1059,'任务导出',110,7,'#','',1,0,'F','0','0','monitor:job:export','#','admin','2021-04-25 10:57:30','',NULL,''),(1060,'生成查询',115,1,'#','',1,0,'F','0','0','tool:gen:query','#','admin','2021-04-25 10:57:30','',NULL,''),(1061,'生成修改',115,2,'#','',1,0,'F','0','0','tool:gen:edit','#','admin','2021-04-25 10:57:31','',NULL,''),(1062,'生成删除',115,3,'#','',1,0,'F','0','0','tool:gen:remove','#','admin','2021-04-25 10:57:31','',NULL,''),(1063,'导入代码',115,2,'#','',1,0,'F','0','0','tool:gen:import','#','admin','2021-04-25 10:57:31','',NULL,''),(1064,'预览代码',115,4,'#','',1,0,'F','0','0','tool:gen:preview','#','admin','2021-04-25 10:57:31','',NULL,''),(1065,'生成代码',115,5,'#','',1,0,'F','0','0','tool:gen:code','#','admin','2021-04-25 10:57:31','',NULL,''),(1066,'基础数据',0,1,'standard',NULL,1,0,'M','0','0','','dict','admin','2021-05-30 06:57:19','admin','2021-05-30 07:25:27',''),(1067,'基准数据',1066,1,'base',NULL,1,0,'M','0','0','','#','admin','2021-05-30 07:05:46','admin','2021-05-30 07:27:05',''),(1068,'届别',1067,3,'stdGraduate','standard/stdGraduate/index',1,0,'C','0','0','standard:stdGraduate:list','#','admin','2020-12-18 10:08:07','admin','2020-12-18 10:12:15','届别菜单'),(1069,'学年',1067,2,'stdSchoolYear','standard/stdSchoolYear/index',1,0,'C','0','0','standard:stdSchoolYear:list','#','admin','2020-12-18 10:08:14','admin','2020-12-18 10:12:07','学年菜单'),(1070,'学段',1067,1,'stdStudyLevel','standard/stdStudyLevel/index',1,0,'C','0','0','standard:stdStudyLevel:list','#','admin','2020-12-18 10:08:20','admin','2020-12-18 10:11:57','学段菜单'),(1071,'学期',1067,4,'stdTerm','standard/stdTerm/index',1,0,'C','0','0','standard:stdTerm:list','#','admin','2020-12-18 10:09:16','admin','2020-12-18 10:14:19','学期菜单'),(1072,'届别查询',1068,1,'#','',1,0,'F','0','0','standard:stdGraduate:query','#','admin','2020-12-18 10:08:07','',NULL,''),(1073,'届别新增',1068,2,'#','',1,0,'F','0','0','standard:stdGraduate:add','#','admin','2020-12-18 10:08:07','',NULL,''),(1074,'届别修改',1068,3,'#','',1,0,'F','0','0','standard:stdGraduate:edit','#','admin','2020-12-18 10:08:07','',NULL,''),(1075,'届别删除',1068,4,'#','',1,0,'F','0','0','standard:stdGraduate:remove','#','admin','2020-12-18 10:08:07','',NULL,''),(1076,'届别导出',1068,5,'#','',1,0,'F','0','0','standard:stdGraduate:export','#','admin','2020-12-18 10:08:07','',NULL,''),(1077,'学年查询',1069,1,'#','',1,0,'F','0','0','standard:stdSchoolYear:query','#','admin','2020-12-18 10:08:14','',NULL,''),(1078,'学年新增',1069,2,'#','',1,0,'F','0','0','standard:stdSchoolYear:add','#','admin','2020-12-18 10:08:14','',NULL,''),(1079,'学年修改',1069,3,'#','',1,0,'F','0','0','standard:stdSchoolYear:edit','#','admin','2020-12-18 10:08:14','',NULL,''),(1080,'学年删除',1069,4,'#','',1,0,'F','0','0','standard:stdSchoolYear:remove','#','admin','2020-12-18 10:08:14','',NULL,''),(1081,'学年导出',1069,5,'#','',1,0,'F','0','0','standard:stdSchoolYear:export','#','admin','2020-12-18 10:08:14','',NULL,''),(1082,'学段查询',1070,1,'#','',1,0,'F','0','0','standard:stdStudyLevel:query','#','admin','2020-12-18 10:08:20','',NULL,''),(1083,'学段新增',1070,2,'#','',1,0,'F','0','0','standard:stdStudyLevel:add','#','admin','2020-12-18 10:08:20','',NULL,''),(1084,'学段修改',1070,3,'#','',1,0,'F','0','0','standard:stdStudyLevel:edit','#','admin','2020-12-18 10:08:20','',NULL,''),(1085,'学段删除',1070,4,'#','',1,0,'F','0','0','standard:stdStudyLevel:remove','#','admin','2020-12-18 10:08:20','',NULL,''),(1086,'学段导出',1070,5,'#','',1,0,'F','0','0','standard:stdStudyLevel:export','#','admin','2020-12-18 10:08:20','',NULL,''),(1087,'学期查询',1071,1,'#','',1,0,'F','0','0','standard:stdTerm:query','#','admin','2020-12-18 10:09:16','',NULL,''),(1088,'学期新增',1071,2,'#','',1,0,'F','0','0','standard:stdTerm:add','#','admin','2020-12-18 10:09:16','',NULL,''),(1089,'学期修改',1071,3,'#','',1,0,'F','0','0','standard:stdTerm:edit','#','admin','2020-12-18 10:09:16','',NULL,''),(1090,'学期删除',1071,4,'#','',1,0,'F','0','0','standard:stdTerm:remove','#','admin','2020-12-18 10:09:16','',NULL,''),(1091,'学期导出',1071,5,'#','',1,0,'F','0','0','standard:stdTerm:export','#','admin','2020-12-18 10:09:16','',NULL,''),(1092,'科目相关',1066,2,'subject',NULL,1,0,'M','0','0',NULL,'#','admin','2021-05-30 07:37:32','',NULL,''),(1093,'科目分类',1092,3,'stdSubjectClassify','standard/stdSubjectClassify/index',1,0,'C','0','0','standard:stdSubjectClassify:list','#','admin','2020-12-18 10:08:26','admin','2020-12-18 10:10:57','科目分类菜单'),(1094,'科目组合',1092,4,'stdSubjectCombine','standard/stdSubjectCombine/index',1,0,'C','0','0','standard:stdSubjectCombine:list','#','admin','2020-12-18 10:08:38','admin','2020-12-18 10:19:09','科目组合类型菜单'),(1095,'科目管理',1092,1,'stdSubject','standard/stdSubject/index',1,0,'C','0','0','standard:stdSubject:list','#','admin','2020-12-18 10:08:48','admin','2020-12-18 10:10:36','科目管理菜单'),(1096,'科目类型',1092,2,'stdSubjectType','standard/stdSubjectType/index',1,0,'C','0','0','standard:stdSubjectType:list','#','admin','2020-12-18 10:08:53','admin','2020-12-18 10:10:47','科目类型菜单'),(1097,'科目分类查询',1093,1,'#','',1,0,'F','0','0','standard:stdSubjectClassify:query','#','admin','2020-12-18 10:08:26','',NULL,''),(1098,'科目分类新增',1093,2,'#','',1,0,'F','0','0','standard:stdSubjectClassify:add','#','admin','2020-12-18 10:08:26','',NULL,''),(1099,'科目分类修改',1093,3,'#','',1,0,'F','0','0','standard:stdSubjectClassify:edit','#','admin','2020-12-18 10:08:26','',NULL,''),(1100,'科目分类删除',1093,4,'#','',1,0,'F','0','0','standard:stdSubjectClassify:remove','#','admin','2020-12-18 10:08:26','',NULL,''),(1101,'科目分类导出',1093,5,'#','',1,0,'F','0','0','standard:stdSubjectClassify:export','#','admin','2020-12-18 10:08:26','',NULL,''),(1102,'科目组合类型查询',1094,1,'#','',1,0,'F','0','0','standard:stdSubjectCombine:query','#','admin','2020-12-18 10:08:38','',NULL,''),(1103,'科目组合类型新增',1094,2,'#','',1,0,'F','0','0','standard:stdSubjectCombine:add','#','admin','2020-12-18 10:08:38','',NULL,''),(1104,'科目组合类型修改',1094,3,'#','',1,0,'F','0','0','standard:stdSubjectCombine:edit','#','admin','2020-12-18 10:08:38','',NULL,''),(1105,'科目组合类型删除',1094,4,'#','',1,0,'F','0','0','standard:stdSubjectCombine:remove','#','admin','2020-12-18 10:08:38','',NULL,''),(1106,'科目组合类型导出',1094,5,'#','',1,0,'F','0','0','standard:stdSubjectCombine:export','#','admin','2020-12-18 10:08:38','',NULL,''),(1107,'科目管理查询',1095,1,'#','',1,0,'F','0','0','standard:stdSubject:query','#','admin','2020-12-18 10:08:48','',NULL,''),(1108,'科目管理新增',1095,2,'#','',1,0,'F','0','0','standard:stdSubject:add','#','admin','2020-12-18 10:08:48','',NULL,''),(1109,'科目管理修改',1095,3,'#','',1,0,'F','0','0','standard:stdSubject:edit','#','admin','2020-12-18 10:08:48','',NULL,''),(1110,'科目管理删除',1095,4,'#','',1,0,'F','0','0','standard:stdSubject:remove','#','admin','2020-12-18 10:08:48','',NULL,''),(1111,'科目管理导出',1095,5,'#','',1,0,'F','0','0','standard:stdSubject:export','#','admin','2020-12-18 10:08:48','',NULL,''),(1289,'科目类型查询',1096,1,'#','',1,0,'F','0','0','standard:stdSubjectType:query','#','admin','2020-12-18 10:08:53','',NULL,''),(1290,'科目类型新增',1096,2,'#','',1,0,'F','0','0','standard:stdSubjectType:add','#','admin','2020-12-18 10:08:53','',NULL,''),(1291,'科目类型修改',1096,3,'#','',1,0,'F','0','0','standard:stdSubjectType:edit','#','admin','2020-12-18 10:08:53','',NULL,''),(1292,'科目类型删除',1096,4,'#','',1,0,'F','0','0','standard:stdSubjectType:remove','#','admin','2020-12-18 10:08:53','',NULL,''),(1293,'科目类型导出',1096,5,'#','',1,0,'F','0','0','standard:stdSubjectType:export','#','admin','2020-12-18 10:08:53','',NULL,''),(1294,'校内数据',1066,3,'school',NULL,1,0,'M','0','0',NULL,'#','admin','2021-05-30 07:43:53','',NULL,''),(1295,'班级类型',1294,2,'stdClassType','standard/stdClassType/index',1,0,'C','0','0','standard:stdClassType:list','#','admin','2020-12-18 10:07:39','admin','2020-12-18 10:14:08','班级类型菜单'),(1296,'课程',1294,7,'stdCourse','standard/stdCourse/index',1,0,'C','0','0','standard:stdCourse:list','#','admin','2020-12-18 10:07:45','admin','2020-12-18 10:15:18','课程菜单'),(1297,'年级组长',1294,10,'stdGradeLeader','standard/stdGradeLeader/index',1,0,'C','0','0','standard:stdGradeLeader:list','#','admin','2020-12-18 10:07:52','admin','2020-12-18 10:13:58','年级组长菜单'),(1298,'年级',1294,1,'stdGrade','standard/stdGrade/index',1,0,'C','0','0','standard:stdGrade:list','#','admin','2020-12-18 10:07:58','admin','2020-12-18 10:13:04','年级菜单'),(1299,'教学班',1294,4,'stdTeachClass','standard/stdTeachClass/index',1,0,'C','0','0','standard:stdTeachClass:list','#','admin','2020-12-18 10:08:59','admin','2020-12-18 10:13:45','教学班菜单'),(1300,'教师',1294,4,'stdTeacher','standard/stdTeacher/index',1,0,'C','0','0','standard:stdTeacher:list','#','admin','2020-12-18 10:09:05','admin','2020-12-18 10:14:57','教师菜单'),(1301,'教师职称',1294,5,'stdTeacherTitle','standard/stdTeacherTitle/index',1,0,'C','0','0','standard:stdTeacherTitle:list','#','admin','2020-12-18 10:09:10','admin','2020-12-18 10:15:06','教师职称菜单'),(1302,'行政班',1294,3,'stdAdminClass','standard/stdAdminClass/index',1,0,'C','0','0','standard:stdAdminClass:list','#','admin','2020-12-18 10:17:22','admin','2020-12-18 10:18:12','行政班菜单'),(1303,'班级类型查询',1295,1,'#','',1,0,'F','0','0','standard:stdClassType:query','#','admin','2020-12-18 10:07:39','',NULL,''),(1304,'班级类型新增',1295,2,'#','',1,0,'F','0','0','standard:stdClassType:add','#','admin','2020-12-18 10:07:39','',NULL,''),(1305,'班级类型修改',1295,3,'#','',1,0,'F','0','0','standard:stdClassType:edit','#','admin','2020-12-18 10:07:39','',NULL,''),(1306,'班级类型删除',1295,4,'#','',1,0,'F','0','0','standard:stdClassType:remove','#','admin','2020-12-18 10:07:39','',NULL,''),(1307,'班级类型导出',1295,5,'#','',1,0,'F','0','0','standard:stdClassType:export','#','admin','2020-12-18 10:07:39','',NULL,''),(1308,'课程查询',1296,1,'#','',1,0,'F','0','0','standard:stdCourse:query','#','admin','2020-12-18 10:07:45','',NULL,''),(1309,'课程新增',1296,2,'#','',1,0,'F','0','0','standard:stdCourse:add','#','admin','2020-12-18 10:07:45','',NULL,''),(1310,'课程修改',1296,3,'#','',1,0,'F','0','0','standard:stdCourse:edit','#','admin','2020-12-18 10:07:45','',NULL,''),(1311,'课程删除',1296,4,'#','',1,0,'F','0','0','standard:stdCourse:remove','#','admin','2020-12-18 10:07:45','',NULL,''),(1312,'课程导出',1296,5,'#','',1,0,'F','0','0','standard:stdCourse:export','#','admin','2020-12-18 10:07:45','',NULL,''),(1313,'年级组长查询',1297,1,'#','',1,0,'F','0','0','standard:stdGradeLeader:query','#','admin','2020-12-18 10:07:52','',NULL,''),(1314,'年级组长新增',1297,2,'#','',1,0,'F','0','0','standard:stdGradeLeader:add','#','admin','2020-12-18 10:07:52','',NULL,''),(1315,'年级组长修改',1297,3,'#','',1,0,'F','0','0','standard:stdGradeLeader:edit','#','admin','2020-12-18 10:07:52','',NULL,''),(1316,'年级组长删除',1297,4,'#','',1,0,'F','0','0','standard:stdGradeLeader:remove','#','admin','2020-12-18 10:07:52','',NULL,''),(1317,'年级组长导出',1297,5,'#','',1,0,'F','0','0','standard:stdGradeLeader:export','#','admin','2020-12-18 10:07:52','',NULL,''),(1318,'年级查询',1298,1,'#','',1,0,'F','0','0','standard:stdGrade:query','#','admin','2020-12-18 10:07:59','',NULL,''),(1319,'年级新增',1298,2,'#','',1,0,'F','0','0','standard:stdGrade:add','#','admin','2020-12-18 10:07:59','',NULL,''),(1320,'年级修改',1298,3,'#','',1,0,'F','0','0','standard:stdGrade:edit','#','admin','2020-12-18 10:07:59','',NULL,''),(1321,'年级删除',1298,4,'#','',1,0,'F','0','0','standard:stdGrade:remove','#','admin','2020-12-18 10:07:59','',NULL,''),(1322,'年级导出',1298,5,'#','',1,0,'F','0','0','standard:stdGrade:export','#','admin','2020-12-18 10:07:59','',NULL,''),(1323,'教学班查询',1299,1,'#','',1,0,'F','0','0','standard:stdTeachClass:query','#','admin','2020-12-18 10:08:59','',NULL,''),(1324,'教学班新增',1299,2,'#','',1,0,'F','0','0','standard:stdTeachClass:add','#','admin','2020-12-18 10:08:59','',NULL,''),(1325,'教学班修改',1299,3,'#','',1,0,'F','0','0','standard:stdTeachClass:edit','#','admin','2020-12-18 10:08:59','',NULL,''),(1326,'教学班删除',1299,4,'#','',1,0,'F','0','0','standard:stdTeachClass:remove','#','admin','2020-12-18 10:08:59','',NULL,''),(1327,'教学班导出',1299,5,'#','',1,0,'F','0','0','standard:stdTeachClass:export','#','admin','2020-12-18 10:08:59','',NULL,''),(1328,'教师查询',1300,1,'#','',1,0,'F','0','0','standard:stdTeacher:query','#','admin','2020-12-18 10:09:05','',NULL,''),(1329,'教师新增',1300,2,'#','',1,0,'F','0','0','standard:stdTeacher:add','#','admin','2020-12-18 10:09:05','',NULL,''),(1330,'教师修改',1300,3,'#','',1,0,'F','0','0','standard:stdTeacher:edit','#','admin','2020-12-18 10:09:05','',NULL,''),(1331,'教师删除',1300,4,'#','',1,0,'F','0','0','standard:stdTeacher:remove','#','admin','2020-12-18 10:09:05','',NULL,''),(1332,'教师导出',1300,5,'#','',1,0,'F','0','0','standard:stdTeacher:export','#','admin','2020-12-18 10:09:05','',NULL,''),(1333,'教师职称查询',1301,1,'#','',1,0,'F','0','0','standard:stdTeacherTitle:query','#','admin','2020-12-18 10:09:10','',NULL,''),(1334,'教师职称新增',1301,2,'#','',1,0,'F','0','0','standard:stdTeacherTitle:add','#','admin','2020-12-18 10:09:10','',NULL,''),(1335,'教师职称修改',1301,3,'#','',1,0,'F','0','0','standard:stdTeacherTitle:edit','#','admin','2020-12-18 10:09:10','',NULL,''),(1336,'教师职称删除',1301,4,'#','',1,0,'F','0','0','standard:stdTeacherTitle:remove','#','admin','2020-12-18 10:09:10','',NULL,''),(1337,'教师职称导出',1301,5,'#','',1,0,'F','0','0','standard:stdTeacherTitle:export','#','admin','2020-12-18 10:09:10','',NULL,''),(1338,'行政班查询',1302,1,'#','',1,0,'F','0','0','standard:stdAdminClass:query','#','admin','2020-12-18 10:17:22','',NULL,''),(1339,'行政班新增',1302,2,'#','',1,0,'F','0','0','standard:stdAdminClass:add','#','admin','2020-12-18 10:17:22','',NULL,''),(1340,'行政班修改',1302,3,'#','',1,0,'F','0','0','standard:stdAdminClass:edit','#','admin','2020-12-18 10:17:22','',NULL,''),(1341,'行政班删除',1302,4,'#','',1,0,'F','0','0','standard:stdAdminClass:remove','#','admin','2020-12-18 10:17:22','',NULL,''),(1342,'行政班导出',1302,5,'#','',1,0,'F','0','0','standard:stdAdminClass:export','#','admin','2020-12-18 10:17:22','',NULL,'');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`notice_id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'温馨提醒：2018-07-01 若依新版本发布啦','2','新版本内容','0','admin','2021-04-25 10:58:10','',NULL,'管理员'),(2,'维护通知：2018-07-01 若依系统凌晨维护','1','维护内容','0','admin','2021-04-25 10:58:10','',NULL,'管理员');

/*Table structure for table `sys_oper_log` */

DROP TABLE IF EXISTS `sys_oper_log`;

CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `sys_oper_log` */

insert  into `sys_oper_log`(`oper_id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`oper_name`,`dept_name`,`oper_url`,`oper_ip`,`oper_location`,`oper_param`,`json_result`,`status`,`error_msg`,`oper_time`) values (100,'参数管理',1,'com.ruoyi.system.controller.SysConfigController.add()','POST',1,'admin',NULL,'/config','127.0.0.1','','{\"configName\":\"1231\",\"configKey\":\"321\",\"createBy\":\"admin\",\"createTime\":1619320037655,\"updateBy\":\"admin\",\"configId\":\"1386154821668687873\",\"updateTime\":1619320037655,\"configType\":\"Y\",\"configValue\":\"123\",\"params\":{}}','null',1,'\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Out of range value for column \'config_id\' at row 1\r\n### The error may exist in com/ruoyi/system/mapper/SysConfigMapper.java (best guess)\r\n### The error may involve com.ruoyi.system.mapper.SysConfigMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_config  ( config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Out of range value for column \'config_id\' at row 1\n; Data truncation: Out of range value for column \'config_id\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Out of range value for column \'config_id\' at row 1','2021-04-25 11:07:18'),(101,'参数管理',1,'com.ruoyi.system.controller.SysConfigController.add()','POST',1,'admin',NULL,'/config','127.0.0.1','','{\"configName\":\"1231\",\"configKey\":\"321\",\"createBy\":\"admin\",\"createTime\":1619320077670,\"updateBy\":\"admin\",\"configId\":\"1386154989503762433\",\"updateTime\":1619320077670,\"configType\":\"Y\",\"configValue\":\"123\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-04-25 11:07:57'),(102,'参数管理',3,'com.ruoyi.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/config/1386154989503762433','127.0.0.1','',NULL,'null',1,'内置参数【321】不能删除 ','2021-04-25 11:08:03'),(103,'参数管理',3,'com.ruoyi.system.controller.SysConfigController.remove()','DELETE',1,'admin',NULL,'/config/1386154989503762433','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-04-25 11:08:22'),(104,'菜单管理',3,'com.ruoyi.system.controller.SysMenuController.remove()','DELETE',1,'admin',NULL,'/menu/4','127.0.0.1','',NULL,'{\"msg\":\"菜单已分配,不允许删除\",\"code\":500}',0,NULL,'2021-05-19 07:10:35'),(105,'角色管理',2,'com.ruoyi.system.controller.SysRoleController.edit()','PUT',1,'admin',NULL,'/role','127.0.0.1','','{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1619319443000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1041,1042,1043,1044,108,500,1045,1046,1047,501,1048,1049,1050,2,109,1051,1052,1053,110,1054,1055,1056,1057,1058,1059,111,112,113,3,114,115,1060,1061,1063,1062,1064,1065,116],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-19 07:10:49'),(106,'菜单管理',3,'com.ruoyi.system.controller.SysMenuController.remove()','DELETE',1,'admin',NULL,'/menu/4','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-19 07:10:55'),(107,'菜单管理',2,'com.ruoyi.system.controller.SysMenuController.edit()','PUT',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"online\",\"orderNum\":\"1\",\"menuName\":\"在线用户\",\"params\":{},\"parentId\":2,\"isCache\":\"0\",\"path\":\"online\",\"component\":\"monitor/online/index\",\"children\":[],\"createTime\":1619319446000,\"isFrame\":\"0\",\"menuId\":109,\"menuType\":\"C\",\"perms\":\"monitor:online:list\",\"status\":\"0\"}','{\"msg\":\"修改菜单\'在线用户\'失败，地址必须以http(s)://开头\",\"code\":500}',0,NULL,'2021-05-19 07:11:14'),(108,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:19:44'),(109,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://127.0.0.1:9300/statics/2021/05/21/b5547fad-99a2-4e59-b819-22bb841cc034.jpeg\",\"code\":200}',0,NULL,'2021-05-21 02:20:40'),(110,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:29:26'),(111,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:29:33'),(112,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:29:49'),(113,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:30:02'),(114,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:31:07'),(115,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:32:06'),(116,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:32:09'),(117,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:32:17'),(118,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:32:27'),(119,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:33:05'),(120,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:21'),(121,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:31'),(122,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:32'),(123,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:32'),(124,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:32'),(125,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:32'),(126,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:32'),(127,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:33'),(128,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:33'),(129,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:38:46'),(130,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 02:41:54'),(131,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://10.35.1.191/group1/M00/00/00/CiMBv2CnHgCAT8OXAADOmo1tUuc640.png\",\"code\":200}',0,NULL,'2021-05-21 02:42:08'),(132,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://10.35.1.191/group1/M00/00/00/CiMBv2CnHgmAAkSRAADOmo1tUuc008.png\",\"code\":200}',0,NULL,'2021-05-21 02:42:17'),(133,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://10.35.1.191/group1/M00/00/00/CiMBv2CnH9aAJoikAADOmo1tUuc864.png\",\"code\":200}',0,NULL,'2021-05-21 02:49:59'),(134,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:48:30'),(135,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:48:45'),(136,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:50:22'),(137,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:50:23'),(138,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:50:26'),(139,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:50:27'),(140,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:50:34'),(141,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 03:50:47'),(142,'用户头像',2,'com.ruoyi.system.controller.SysProfileController.avatar()','POST',1,'admin',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2021-05-21 04:03:34'),(143,'字典类型',2,'com.ruoyi.system.controller.SysDictTypeController.edit()','PUT',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"createTime\":1619319474000,\"updateBy\":\"admin\",\"dictName\":\"用户性别\",\"remark\":\"用户性别列表\",\"dictId\":1,\"params\":{},\"dictType\":\"sys_user_sex\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-28 02:47:20'),(144,'字典类型',2,'com.ruoyi.system.controller.SysDictTypeController.edit()','PUT',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"createTime\":1619319474000,\"updateBy\":\"admin\",\"dictName\":\"菜单状态\",\"remark\":\"菜单状态列表12\",\"dictId\":2,\"params\":{},\"dictType\":\"sys_show_hide\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-28 02:49:08'),(145,'字典类型',2,'com.ruoyi.system.controller.SysDictTypeController.edit()','PUT',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"createTime\":1619319474000,\"updateBy\":\"admin\",\"dictName\":\"用户性别\",\"remark\":\"用户性别列表\",\"dictId\":1,\"params\":{},\"dictType\":\"sys_user_sex\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-28 02:53:15'),(146,'字典类型',1,'com.ruoyi.system.controller.SysDictTypeController.add()','POST',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"dictName\":\"test\",\"remark\":\"123\",\"params\":{},\"dictType\":\"teeter\",\"status\":\"0\"}','null',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.\r\n### The error may exist in file [D:\\work\\git\\SpringClould\\RuoYi-Cloud\\ruoyi-modules\\ruoyi-system\\target\\classes\\mapper\\system\\SysDictTypeMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.SysDictTypeMapper.insertDictType\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.','2021-05-28 03:17:46'),(147,'字典类型',1,'com.ruoyi.system.controller.SysDictTypeController.add()','POST',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"dictName\":\"test\",\"remark\":\"123\",\"params\":{},\"dictType\":\"teeter\",\"status\":\"0\"}','null',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.\r\n### The error may exist in file [D:\\work\\git\\SpringClould\\RuoYi-Cloud\\ruoyi-modules\\ruoyi-system\\target\\classes\\mapper\\system\\SysDictTypeMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.SysDictTypeMapper.insertDictType\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.','2021-05-28 03:17:49'),(148,'字典类型',1,'com.ruoyi.system.controller.SysDictTypeController.add()','POST',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"dictName\":\"test\",\"remark\":\"123\",\"params\":{},\"dictType\":\"teeter\",\"status\":\"0\"}','null',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.\r\n### The error may exist in file [D:\\work\\git\\SpringClould\\RuoYi-Cloud\\ruoyi-modules\\ruoyi-system\\target\\classes\\mapper\\system\\SysDictTypeMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.SysDictTypeMapper.insertDictType\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.','2021-05-28 03:19:47'),(149,'字典类型',1,'com.ruoyi.system.controller.SysDictTypeController.add()','POST',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"dictName\":\"1231\",\"remark\":\"13\",\"params\":{},\"dictType\":\"1321\",\"status\":\"0\"}','null',1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.\r\n### The error may exist in file [D:\\work\\git\\SpringClould\\RuoYi-Cloud\\ruoyi-modules\\ruoyi-system\\target\\classes\\mapper\\system\\SysDictTypeMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.SysDictTypeMapper.insertDictType\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalStateException: Type handler was null on parameter mapping for property \'dataStr\'. It was either not specified and/or could not be found for the javaType (com.alibaba.fastjson.JSONObject) : jdbcType (null) combination.','2021-05-28 03:25:17'),(150,'字典类型',1,'com.ruoyi.system.controller.SysDictTypeController.add()','POST',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"dictName\":\"1231\",\"remark\":\"13\",\"params\":{},\"dictType\":\"1321\",\"status\":\"0\"}','null',1,'nested exception is org.apache.ibatis.builder.BuilderException: Error resolving JdbcType. Cause: java.lang.IllegalArgumentException: No enum constant org.apache.ibatis.type.JdbcType.com.ruoyi.common.core.handler.JSONTypeHandler','2021-05-28 03:31:05'),(151,'字典类型',1,'com.ruoyi.system.controller.SysDictTypeController.add()','POST',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"dictName\":\"1231\",\"remark\":\"13\",\"params\":{},\"dictType\":\"1321\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-28 03:32:35'),(152,'字典类型',2,'com.ruoyi.system.controller.SysDictTypeController.edit()','PUT',1,'admin',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"admin\",\"dataStr\":{\"1\":\"2\"},\"createTime\":1622143954000,\"updateBy\":\"admin\",\"dictName\":\"1231\",\"remark\":\"131\",\"dictId\":11,\"params\":{},\"dictType\":\"1321\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-28 03:32:59'),(153,'字典类型',3,'com.ruoyi.system.controller.SysDictTypeController.remove()','DELETE',1,'admin',NULL,'/dict/type/11','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-28 03:33:17'),(154,'菜单管理',2,'com.ruoyi.system.controller.SysMenuController.edit()','PUT',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"90\",\"menuName\":\"系统工具\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1619319444000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 06:56:31'),(155,'菜单管理',2,'com.ruoyi.system.controller.SysMenuController.edit()','PUT',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"monitor\",\"orderNum\":\"91\",\"menuName\":\"系统监控\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"monitor\",\"children\":[],\"createTime\":1619319444000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 06:56:38'),(156,'菜单管理',2,'com.ruoyi.system.controller.SysMenuController.edit()','PUT',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"system\",\"orderNum\":\"10\",\"menuName\":\"系统管理\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"system\",\"children\":[],\"createTime\":1619319444000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 06:56:46'),(157,'菜单管理',1,'com.ruoyi.system.controller.SysMenuController.add()','POST',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"基础数据\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"standard\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 06:57:19'),(158,'菜单管理',1,'com.ruoyi.system.controller.SysMenuController.add()','POST',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"orderNum\":\"1\",\"menuName\":\"基础数据\",\"params\":{},\"parentId\":1066,\"isCache\":\"0\",\"path\":\"base\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:05:46'),(159,'学期',2,'com.ruoyi.standard.controller.StdTermController.edit()','PUT',1,'admin',NULL,'/standard/stdTerm','127.0.0.1','','{\"orderNum\":1,\"remark\":\"123\",\"updateTime\":1608258548000,\"delFlag\":0,\"params\":{},\"termId\":1,\"createTime\":1608258548000,\"termName\":\"上学期\",\"status\":0,\"termCode\":\"up\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:25:00'),(160,'菜单管理',2,'com.ruoyi.system.controller.SysMenuController.edit()','PUT',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"基础数据\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"standard\",\"children\":[],\"createTime\":1622329039000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1066,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:25:27'),(161,'菜单管理',2,'com.ruoyi.system.controller.SysMenuController.edit()','PUT',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"基准数据\",\"params\":{},\"parentId\":1066,\"isCache\":\"0\",\"path\":\"base\",\"children\":[],\"createTime\":1622329546000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:27:05'),(162,'菜单管理',1,'com.ruoyi.system.controller.SysMenuController.add()','POST',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"orderNum\":\"2\",\"menuName\":\"科目相关\",\"params\":{},\"parentId\":1066,\"isCache\":\"0\",\"path\":\"subject\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:37:32'),(163,'菜单管理',1,'com.ruoyi.system.controller.SysMenuController.add()','POST',1,'admin',NULL,'/menu','127.0.0.1','','{\"visible\":\"0\",\"orderNum\":\"3\",\"menuName\":\"校内数据\",\"params\":{},\"parentId\":1066,\"isCache\":\"0\",\"path\":\"school\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:43:53'),(164,'行政班',2,'com.ruoyi.standard.controller.StdAdminClassController.edit()','PUT',1,'admin',NULL,'/stdAdminClass','127.0.0.1','','{\"classCode\":\"A1013001\",\"deptId\":108,\"orderNum\":1,\"className\":\"1班\",\"updateTime\":1614408131000,\"delFlag\":0,\"params\":{},\"classId\":1,\"createTime\":1608431673000,\"gradeCode\":\"1\",\"classTypeCode\":\"top\",\"schoolYearCode\":\"high2020\",\"deptCode\":\"1013\",\"graduateCode\":\"high2020\",\"status\":0,\"termCode\":\"up\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 07:59:38'),(165,'行政班',5,'com.ruoyi.standard.controller.StdAdminClassController.export()','GET',1,'admin',NULL,'/stdAdminClass/export','127.0.0.1','',NULL,'null',0,NULL,'2021-05-30 08:09:50'),(166,'行政班',3,'com.ruoyi.standard.controller.StdAdminClassController.remove()','DELETE',1,'admin',NULL,'/stdAdminClass/4','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:19:06'),(167,'行政班',3,'com.ruoyi.standard.controller.StdAdminClassController.remove()','DELETE',1,'admin',NULL,'/stdAdminClass/3','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:19:10'),(168,'教师',2,'com.ruoyi.standard.controller.StdTeacherController.edit()','PUT',1,'admin',NULL,'/stdTeacher','127.0.0.1','','{\"teacherName\":\"张三\",\"deptId\":100,\"orderNum\":1,\"teacherCode\":\"T1013001\",\"updateTime\":1614408405000,\"delFlag\":0,\"params\":{},\"teacherOtherName\":\"张教授\",\"teacherId\":1,\"createTime\":1608448879000,\"deptCode\":\"1013\",\"status\":0}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:32:03'),(169,'年级组长',2,'com.ruoyi.standard.controller.StdGradeLeaderController.edit()','PUT',1,'admin',NULL,'/stdGradeLeader','127.0.0.1','','{\"deptId\":100,\"orderNum\":1,\"teacherCode\":\"T1013001\",\"updateTime\":1615769155000,\"gradeLeaderId\":1,\"delFlag\":0,\"params\":{},\"createTime\":1608453369000,\"gradeCode\":\"1\",\"schoolYearCode\":\"high2020\",\"deptCode\":\"1013\",\"graduateCode\":\"high2020\",\"status\":0,\"termCode\":\"up\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:33:20'),(170,'部门管理',2,'com.ruoyi.system.controller.SysDeptController.edit()','PUT',1,'admin',NULL,'/dept','127.0.0.1','','{\"deptName\":\"深圳海云天科技\",\"leader\":\"海云天\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1619319435000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0\",\"email\":\"ry@qq.com\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:36:04'),(171,'岗位管理',2,'com.ruoyi.system.controller.SysPostController.edit()','PUT',1,'admin',NULL,'/post','127.0.0.1','','{\"postSort\":\"1\",\"flag\":false,\"remark\":\"\",\"postId\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1619319440000,\"updateBy\":\"admin\",\"postName\":\"区领导\",\"postCode\":\"areaLeader\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:41:48'),(172,'岗位管理',2,'com.ruoyi.system.controller.SysPostController.edit()','PUT',1,'admin',NULL,'/post','127.0.0.1','','{\"postSort\":\"2\",\"flag\":false,\"remark\":\"\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1619319440000,\"updateBy\":\"admin\",\"postName\":\"校领导\",\"postCode\":\"schoolLeader\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:42:01'),(173,'岗位管理',2,'com.ruoyi.system.controller.SysPostController.edit()','PUT',1,'admin',NULL,'/post','127.0.0.1','','{\"postSort\":\"3\",\"flag\":false,\"remark\":\"\",\"postId\":3,\"params\":{},\"createBy\":\"admin\",\"createTime\":1619319441000,\"updateBy\":\"admin\",\"postName\":\"年级领导\",\"postCode\":\"gradeLeader\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:42:26'),(174,'岗位管理',2,'com.ruoyi.system.controller.SysPostController.edit()','PUT',1,'admin',NULL,'/post','127.0.0.1','','{\"postSort\":\"4\",\"flag\":false,\"remark\":\"\",\"postId\":4,\"params\":{},\"createBy\":\"admin\",\"createTime\":1619319441000,\"updateBy\":\"admin\",\"postName\":\"普通老师\",\"postCode\":\"teacher\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-05-30 08:42:44');

/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

/*Data for the table `sys_post` */

insert  into `sys_post`(`post_id`,`post_code`,`post_name`,`post_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'areaLeader','区领导',1,'0','admin','2021-04-25 10:57:20','admin','2021-05-30 08:41:48',''),(2,'schoolLeader','校领导',2,'0','admin','2021-04-25 10:57:20','admin','2021-05-30 08:42:01',''),(3,'gradeLeader','年级领导',3,'0','admin','2021-04-25 10:57:21','admin','2021-05-30 08:42:26',''),(4,'teacher','普通老师',4,'0','admin','2021-04-25 10:57:21','admin','2021-05-30 08:42:44','');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(11) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`menu_check_strictly`,`dept_check_strictly`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`,`tenant_id`) values (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2021-04-25 10:57:23','',NULL,'超级管理员',1),(2,'普通角色','common',2,'2',1,1,'0','0','admin','2021-04-25 10:57:23','admin','2021-05-19 07:10:49','普通角色',2);

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`role_id`,`dept_id`) values (2,100),(2,101),(2,105);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (2,1),(2,2),(2,3),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,500),(2,501),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060),(2,1061),(2,1062),(2,1063),(2,1064),(2,1065);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`user_name`,`nick_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`status`,`del_flag`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,103,'admin','超级管理员','00','152@163.com','15888888888','1','http://10.35.1.191:8888/group1/M00/00/00/CiMBv2CnHgmAAkSRAADOmo1tUuc008.png','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-04-25 10:57:19','admin','2021-04-25 10:57:19','',NULL,'管理员'),(2,105,'ry','若依','00','123@qq.com','15666666666','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-04-25 10:57:20','admin','2021-04-25 10:57:20','',NULL,'测试员');

/*Table structure for table `sys_user_post` */

DROP TABLE IF EXISTS `sys_user_post`;

CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

/*Data for the table `sys_user_post` */

insert  into `sys_user_post`(`user_id`,`post_id`) values (1,1),(2,2);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(2,2);

/*Table structure for table `tenant_info` */

DROP TABLE IF EXISTS `tenant_info`;

CREATE TABLE `tenant_info` (
  `tenant_info_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(11) DEFAULT NULL COMMENT '租户id',
  `tenant_name` varchar(500) CHARACTER SET latin1 DEFAULT NULL COMMENT '租户名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `over_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) CHARACTER SET latin1 DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`tenant_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tenant_info` */

insert  into `tenant_info`(`tenant_info_id`,`tenant_id`,`tenant_name`,`status`,`over_time`,`create_time`,`create_by`,`update_time`,`update_by`,`remark`) values (1,1,'2131231',0,'2021-09-17 14:08:58','2021-05-28 14:09:06','admin','2021-05-28 14:09:14','admin','1231231');

/*Table structure for table `tenant_info_detail` */

DROP TABLE IF EXISTS `tenant_info_detail`;

CREATE TABLE `tenant_info_detail` (
  `tenant_info_detail_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tenant_info_id` bigint(11) DEFAULT NULL COMMENT '关联Id',
  `max_user_num` int(10) DEFAULT '-1' COMMENT '最大用户数量',
  `max_login_user_num` int(10) DEFAULT '-1' COMMENT '允许最大登录用户数量',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tenant_info_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tenant_info_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
