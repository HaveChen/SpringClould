<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="单位" prop="deptId">
        <treeselect
          class="treeselect"
          v-model="queryParams.deptId"
          :options="deptOptions"
          :show-count="true"
          placeholder="请选择归属部门"
        />
      </el-form-item>
      <el-form-item label="年级" prop="gradeCode">
        <el-select v-model="queryParams.gradeCode" placeholder="请选择年级">
          <el-option
            v-for="item in grades"
            :key="item.gradeCode"
            :label="item.gradeName"
            :value="item.gradeCode"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
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
      <el-form-item label="年级组长" prop="teacherCode">
        <el-input
          v-model="queryParams.teacherCode"
          placeholder="请输入年级组长"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
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
          v-hasPermi="['standard:stdGradeLeader:add']"
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
          v-hasPermi="['standard:stdGradeLeader:edit']"
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
          v-hasPermi="['standard:stdGradeLeader:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['standard:stdGradeLeader:export']"
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
      :data="stdGradeLeaderList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="gradeLeaderId"
      />
      <el-table-column
        label="单位"
        align="center"
        prop="deptId"
      />
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
        label="年级组长"
        align="center"
        prop="teacherCode"
        :formatter="teacherFormat"
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
            v-hasPermi="['standard:stdGradeLeader:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['standard:stdGradeLeader:remove']"
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

    <!-- 添加或修改年级组长对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="单位" prop="deptId">
          <treeselect
            v-model="form.deptId"
            :options="deptOptions"
            :show-count="true"
            placeholder="请选择归属部门"
          />
        </el-form-item>
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
        <el-form-item label="年级组长" prop="teacherCode">
          <el-select v-model="form.teacherCode" placeholder="请选择年级组长">
            <el-option
              v-for="item in teachers"
              :key="item.teacherCode"
              :label="item.teacherName"
              :value="item.teacherCode"
              :disabled="item.status == 1"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入排序"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
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
    addStdGradeLeader,
    delStdGradeLeader,
    exportStdGradeLeader,
    getStdGradeLeader,
    listGrade,
    listGraduate,
    listSchoolYear,
    listStdGradeLeader,
    listTeacher,
    listTerm,
    updateStdGradeLeader
  } from "@/api/standard/stdGradeLeader";
  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {depttranslation} from '@/utils/depttranslation'

  export default {
    name: "StdGradeLeader",
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
        // 隐藏主键
        hiddenKey: false,
        // 年级组长表格数据
        stdGradeLeaderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        //单位数据
        deptOptions: [],
        //学年
        schoolYears: [],
        //年级
        grades: [],
        //学期
        terms: [],
        //届别
        graduates: [],
        //年级组长
        teachers: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          deptCode: null,
          gradeCode: null,
          schoolYearCode: null,
          graduateCode: null,
          teacherCode: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          deptCode: [
            {required: true, message: "单位不能为空", trigger: "blur"}
          ],
          gradeCode: [
            {required: true, message: "年级不能为空", trigger: "blur"}
          ],
          schoolYearCode: [
            {required: true, message: "学年不能为空", trigger: "blur"}
          ],
          graduateCode: [
            {required: true, message: "届别不能为空", trigger: "blur"}
          ],
          teacherCode: [
            {required: true, message: "年级组长不能为空", trigger: "blur"}
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
      listTeacher().then(response => {
        this.teachers = response.data;
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
      /** 查询年级组长列表 */
      getList() {
        this.loading = true;
        listStdGradeLeader(this.queryParams).then(response => {
          this.stdGradeLeaderList = response.rows;
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
      // 单位翻译
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
      //年级
      teacherFormat(row, column) {
        var datas = this.teachers;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].teacherCode == row.teacherCode) {
            return datas[i].teacherName;
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
          gradeLeaderId: null,
          deptCode: null,
          gradeCode: null,
          schoolYearCode: null,
          graduateCode: null,
          teacherCode: null,
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
        this.ids = selection.map(item => item.gradeLeaderId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加年级组长";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const gradeLeaderId = row.gradeLeaderId || this.ids
        getStdGradeLeader(gradeLeaderId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改年级组长";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.gradeLeaderId != null) {
              updateStdGradeLeader(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addStdGradeLeader(this.form).then(response => {
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
        const gradeLeaderIds = row.gradeLeaderId || this.ids;
        this.$confirm('是否确认删除年级组长编号为"' + gradeLeaderIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delStdGradeLeader(gradeLeaderIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有年级组长数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportStdGradeLeader(queryParams);
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
