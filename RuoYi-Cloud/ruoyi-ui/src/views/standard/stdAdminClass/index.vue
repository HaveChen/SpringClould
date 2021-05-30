<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="名称" prop="className">
        <el-input
          v-model="queryParams.className"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代码" prop="classCode">
        <el-input
          v-model="queryParams.classCode"
          placeholder="请输入代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="届别" prop="graduateCode">
        <el-select v-model="queryParams.graduateCode" placeholder="请选择届别">
          <el-option
            v-for="item in graduates"
            :key="item.graduateCode"
            :label="item.graduateName"
            :value="item.graduateCode"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="单位" prop="deptId">
        <treeselect
          class="treeselect"
          v-model="queryParams.deptId"
          :options="deptOptions"
          :show-count="true"
          placeholder="请选择归属部门"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索
        </el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['standard:stdAdminClass:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['standard:stdAdminClass:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['standard:stdAdminClass:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['standard:stdAdminClass:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="stdAdminClassList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="classId"
      />
      <el-table-column label="名称" align="center" prop="className"/>
      <el-table-column label="代码" align="center" prop="classCode"/>
      <el-table-column
        label="班级类型"
        align="center"
        prop="classTypeCode"
        :formatter="classTypeFormat"
      />
      <el-table-column
        label="单位"
        align="center"
        prop="deptId"
      />
      <!--:formatter="deptYearFormat"-->
      <el-table-column
        label="届别"
        align="center"
        prop="graduateCode"
        :formatter="graduateFormat"
      />
      <el-table-column
        label="学年"
        align="center"
        prop="schoolYearCode"
        :formatter="schoolYearFormat"
      />
      <el-table-column
        label="年级"
        align="center"
        prop="gradeCode"
        :formatter="gradeFormat"
      />
      <el-table-column
        label="学期"
        align="center"
        prop="termCode"
        :formatter="termFormat"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['standard:stdAdminClass:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['standard:stdAdminClass:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改行政班对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="className">
              <el-input v-model="form.className" placeholder="请输入名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代码" prop="classCode">
              <el-input v-model="form.classCode" placeholder="请输入代码"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="班级类型" prop="classTypeCode">
              <el-select
                v-model="form.classTypeCode"
                placeholder="请选择班级类型"
              >
                <el-option
                  v-for="item in classTypes"
                  :key="item.classTypeCode"
                  :label="item.classTypeName"
                  :value="item.classTypeCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位" prop="deptId">
              <treeselect
                v-model="form.deptId"
                :options="deptOptions"
                :show-count="true"
                placeholder="请选择归属部门"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="届别" prop="graduateCode">
              <el-select v-model="form.graduateCode" placeholder="请选择届别">
                <el-option
                  v-for="item in graduates"
                  :key="item.graduateCode"
                  :label="item.graduateName"
                  :value="item.graduateCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学年" prop="schoolYearCode">
              <el-select v-model="form.schoolYearCode" placeholder="请选择学年">
                <el-option
                  v-for="item in schoolYears"
                  :key="item.schoolYearCode"
                  :label="item.schoolYearName"
                  :value="item.schoolYearCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="年级" prop="gradeCode">
              <el-select v-model="form.gradeCode" placeholder="请选择年级">
                <el-option
                  v-for="item in grades"
                  :key="item.gradeCode"
                  :label="item.gradeName"
                  :value="item.gradeCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学期" prop="termCode">
              <el-select v-model="form.termCode" placeholder="请选择学期">
                <el-option
                  v-for="item in terms"
                  :key="item.termCode"
                  :label="item.termName"
                  :value="item.termCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="排序" prop="orderNum">
              <el-input v-model="form.orderNum" placeholder="请输入排序"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="parseInt(dict.dictValue)"
                >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input
                v-model="form.remark"
                type="textarea"
                placeholder="请输入内容"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    addStdAdminClass,
    delStdAdminClass,
    exportStdAdminClass,
    getStdAdminClass,
    listClassType,
    listGrade,
    listGraduate,
    listSchoolYear,
    listStdAdminClass,
    listTerm,
    updateStdAdminClass
  } from "@/api/standard/stdAdminClass";
  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {depttranslation} from '@/utils/depttranslation'

  export default {
    name: "StdAdminClass",
    components: {Treeselect},
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 行政班表格数据
        stdAdminClassList: [],
        // 弹出层标题
        title: "",
        // 隐藏主键
        hiddenKey: false,
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        //单位数据
        deptOptions: [],
        //班级类型
        classTypes: [],
        //学年
        schoolYears: [],
        //年级
        grades: [],
        //学期
        terms: [],
        //届别
        graduates: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          className: null,
          classCode: null,
          graduateCode: null,
          schoolYearCode: null,
          gradeCode: null,
          termCode: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          className: [
            {required: true, message: "名称不能为空", trigger: "blur"}
          ],
          classCode: [
            {required: true, message: "代码不能为空", trigger: "blur"}
          ],
          classTypeCode: [
            {required: true, message: "班级类型不能为空", trigger: "blur"}
          ],
          deptCode: [
            {required: true, message: "单位不能为空", trigger: "blur"}
          ],
          graduateCode: [
            {required: true, message: "届别不能为空", trigger: "blur"}
          ],
          schoolYearCode: [
            {required: true, message: "学年不能为空", trigger: "blur"}
          ],
          gradeCode: [
            {required: true, message: "年级不能为空", trigger: "blur"}
          ],
          termCode: [
            {required: true, message: "学期不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getTreeselect();
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("sys_del_flag").then(response => {
        this.delFlagOptions = response.data;
      });
      listClassType().then(response => {
        this.classTypes = response.data;
      });
      listGraduate().then(response => {
        this.graduates = response.data;
      });
      listGrade().then(response => {
        this.grades = response.data;
      });
      listSchoolYear().then(response => {
        this.schoolYears = response.data;
      });
      listTerm().then(response => {
        this.terms = response.data;
      });
    },
    methods: {
      /** 查询行政班列表 */
      getList() {
        this.loading = true;
        listStdAdminClass(this.queryParams).then(response => {
          this.stdAdminClassList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.deptOptions = response.data;
        });
      },
      //班级类型翻译
      classTypeFormat(row, column) {
        var datas = this.classTypes;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].classTypeCode == row.classTypeCode) {
            return datas[i].classTypeName;
          }
        }
        return "无"
      },
      deptYearFormat(row, column) {
        let datas = this.deptOptions
        return depttranslation(row, column, datas)
      },
      //届别翻译
      graduateFormat(row, column) {
        var datas = this.graduates;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].graduateCode == row.graduateCode) {
            return datas[i].graduateName;
          }
        }
        return "无"
      },
      //学年
      schoolYearFormat(row, column) {
        var datas = this.schoolYears;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].schoolYearCode == row.schoolYearCode) {
            return datas[i].schoolYearName;
          }
        }
        return "无"
      },
      //学期
      termFormat(row, column) {
        var datas = this.terms;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].termCode == row.termCode) {
            return datas[i].termName;
          }
        }
        return "无"
      },
      //年级
      gradeFormat(row, column) {
        var datas = this.grades;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].gradeCode == row.gradeCode) {
            return datas[i].gradeName;
          }
        }
        return "无"
      },
      // 状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 删除标记字典翻译
      delFlagFormat(row, column) {
        return this.selectDictLabel(this.delFlagOptions, row.delFlag);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          classId: null,
          className: null,
          classCode: null,
          classTypeCode: null,
          deptCode: null,
          graduateCode: null,
          schoolYearCode: null,
          gradeCode: null,
          termCode: null,
          orderNum: null,
          remark: null,
          status: 0,
          delFlag: 0,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.classId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加行政班";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const classId = row.classId || this.ids
        getStdAdminClass(classId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改行政班";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.classId != null) {
              updateStdAdminClass(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addStdAdminClass(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const classIds = row.classId || this.ids;
        this.$confirm('是否确认删除行政班编号为"' + classIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delStdAdminClass(classIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有行政班数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportStdAdminClass(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
<style lang="scss" scoped>
  .treeselect {
    width: 200px;
  }
</style>
