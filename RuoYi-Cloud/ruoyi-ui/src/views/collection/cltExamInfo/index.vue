<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="名称" prop="examName">
        <el-input
          v-model="queryParams.examName"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简称" prop="examShortName">
        <el-input
          v-model="queryParams.examShortName"
          placeholder="请输入简称"
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
        </el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus" plain
          size="mini"
          @click="handleAdd"
          v-hasPermi="['collection:cltExamInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit" plain
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['collection:cltExamInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete" plain
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['collection:cltExamInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['collection:cltExamInfo:export']"
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
      :data="cltExamInfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="examId"
      />
      <el-table-column label="名称" align="center" prop="examName"/>
      <el-table-column label="简称" align="center" prop="examShortName"/>
      <el-table-column
        label="考试时间"
        align="center"
        prop="examDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.examDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="考试类型"
        align="center"
        prop="examTypeCode"
        :formatter="examTypeFormat"
      />
      <el-table-column
        label="考试模式"
        align="center"
        prop="examModeCode"
        :formatter="examModeFormat"
      />
      <el-table-column
        label="年级"
        align="center"
        prop="gradeCode"
        :formatter="gradeFormat"
      />
      <el-table-column
        label="学年"
        align="center"
        prop="schoolYear"
        :formatter="schoolYeaFormat"
      />
      <el-table-column
        label="届别"
        align="center"
        prop="graduateCode"
        :formatter="graduateFormat"
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
        min-width="140px"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-dropdown
            class="el-dropdown-link"
            @command="handleCommand($event, scope.row)"
          >
            <el-button
              icon="el-icon-view"
              size="mini"
              type="text"
              class="el-dropdown-link"
            >
              查看
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">采集字段查看</el-dropdown-item>
              <!--              <el-dropdown-item command="b">采集学生查看</el-dropdown-item>-->
              <!--              <el-dropdown-item command="c">采集试卷查看</el-dropdown-item>-->
              <!--              <el-dropdown-item command="d">采集采集查询</el-dropdown-item>-->
            </el-dropdown-menu>
          </el-dropdown>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['collection:cltExamInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['collection:cltExamInfo:remove']"
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

    <!-- 添加或修改考试信息对话框 -->
    <el-dialog
      :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="examName">
              <el-input v-model="form.examName" placeholder="请输入名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="简称" prop="examShortName">
              <el-input v-model="form.examShortName" placeholder="请输入简称"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="组织" prop="deptId">
            <treeselect
              v-model="form.deptId"
              :options="deptOptions"
              :show-count="true"
              placeholder="请选择归属部门"
            />
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="考试时间" prop="examDate">
              <el-date-picker
                clearable
                size="small"
                style="width: 100%"
                v-model="form.examDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择考试时间"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="考试类型" prop="examTypeCode">
              <el-select
                v-model="form.examTypeCode"
                placeholder="请选择考试类型"
              >
                <el-option
                  v-for="item in examinationType"
                  :key="item.examTypeCode"
                  :label="item.examTypeName"
                  :value="item.examTypeCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="考试模式" prop="examModeCode">
              <el-select
                v-model="form.examModeCode"
                placeholder="请选择考试模式"
              >
                <el-option
                  v-for="item in examinationMode"
                  :key="item.examModeCode"
                  :label="item.examModeName"
                  :value="item.examModeCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年级" prop="gradeCode">
              <el-select v-model="form.gradeCode" placeholder="请选择年级">
                <el-option
                  v-for="item in gradeList"
                  :key="item.gradeCode"
                  :label="item.gradeName"
                  :value="item.gradeCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="学年" prop="schoolYear">
              <el-select v-model="form.schoolYear" placeholder="请选择学年">
                <el-option
                  v-for="item in SchoolYearList"
                  :key="item.schoolYearCode"
                  :label="item.schoolYearName"
                  :value="item.schoolYearCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="界别" prop="graduateCode">
              <el-select v-model="form.graduateCode" placeholder="请选择界别">
                <el-option
                  v-for="item in GraduateList"
                  :key="item.graduateCode"
                  :label="item.graduateName"
                  :value="item.graduateCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="学期" prop="termCode">
              <el-select v-model="form.termCode" placeholder="请选择学期">
                <el-option
                  v-for="item in TermList"
                  :key="item.termCode"
                  :label="item.termName"
                  :value="item.termCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序" prop="orderNum">
              <el-input v-model="form.orderNum" placeholder="请输入排序"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="备注" prop="remark">
              <el-input
                v-model="form.remark"
                type="textarea"
                placeholder="请输入内容"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
    addCltExamInfo,
    delCltExamInfo,
    exportCltExamInfo,
    getCltExamInfo,
    listCltExamInfo,
    treeselect,
    updateCltExamInfo
  } from "@/api/collection/cltExamInfo";
  import {
    listExaminationMode,
    listExaminationType,
    listGrade,
    listGraduate,
    listSchoolYear,
    listTerm
  } from '@/api/collection/cltInitialization.js'
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: 'CltExamInfo',
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
        // 考试信息表格数据
        cltExamInfoList: [],
        // 考试类型初始化下拉数据
        examinationType: [],
        // 考试模式初始化下拉数据
        examinationMode: [],
        // 年级初始化下拉数据
        gradeList: [],
        // 学年初始化下拉数据
        SchoolYearList: [],
        // 届别初始化下拉数据
        GraduateList: [],
        // 学期初始化下拉数据
        TermList: [],
        // 组织下拉
        deptOptions: [],
        // 主键
        key: '',
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          examName: null,
          examShortName: null,
          examDate: null,
          examTypeCode: null,
          examModeCode: null,
          gradeCode: null,
          schoolYearName: null,
          graduateCode: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      }
    },
    created() {
      // console.log(this.examinationType);
      this.getList()
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.getDicts('sys_del_flag').then(response => {
        this.delFlagOptions = response.data
      })
      listExaminationType().then(response => {
        this.examinationType = response.data
      })
      listExaminationMode().then(response => {
        this.examinationMode = response.data
      })
      listGrade().then(response => {
        this.gradeList = response.data
      })
      listSchoolYear().then(response => {
        this.SchoolYearList = response.data
      })
      listGraduate().then(response => {
        this.GraduateList = response.data
      })
      listTerm().then(response => {
        this.TermList = response.data
      })
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    methods: {
      // 查看按钮点击菜单事件
      handleCommand(command, row) {
        const examId = row.examId || this.ids
        // 采集字段查看
        if (command == 'a') {
          this.$router.push({
            path: `/collection/exam/cltDatafieldToExam/${examId}`
          })
        }
        // 采集学生查看
        if (command == 'b') {
          console.log('b')
        }
        // 采集试卷查看
        if (command == 'c') {
          console.log('c')
        }
        // 采集采集查询
        if (command == 'd') {
          console.log('d')
        }
      },
      /** 查询考试信息列表 */
      getList() {
        this.loading = true
        listCltExamInfo(this.queryParams).then(response => {
          this.cltExamInfoList = response.rows
          this.total = response.total
          this.loading = false
        })
      },
      // 状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status)
      },
      // 删除标记字典翻译
      delFlagFormat(row, column) {
        return this.selectDictLabel(this.delFlagOptions, row.delFlag)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          examId: null,
          examName: null,
          examShortName: null,
          examDate: null,
          examTypeCode: null,
          examModeCode: null,
          gradeCode: null,
          schoolYear: null,
          schoolYearName: null,
          graduateCode: null,
          termCode: null,
          deptAllCode: null,
          deptCode: null,
          deptId: null,
          deptTypeCode: null,
          studentStatus: 0,
          itemCount: null,
          cjCount: null,
          irtCount: null,
          checkStatus: '0',
          examStatus: 0,
          orderNum: null,
          remark: null,
          status: 0,
          delFlag: 0,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null
        }
        this.resetForm('form')
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm('queryForm')
        this.handleQuery()
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.examId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加考试信息'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const examId = row.examId || this.ids
        getCltExamInfo(examId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改考试信息'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.examId != null) {
              updateCltExamInfo(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addCltExamInfo(this.form).then(response => {
                this.msgSuccess('新增成功')
                this.open = false
                this.getList()
              })
            }
          }
        })
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const examIds = row.examId || this.ids
        this.$confirm('是否确认删除考试信息编号为"' + examIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delCltExamInfo(examIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有考试信息数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportCltExamInfo(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      },
      // 考试类型翻译
      examTypeFormat(row, column) {
        var datas = this.examinationType
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].examTypeCode == row.examTypeCode) {
            return datas[i].examTypeName
          }
        }
        return '无'
      },
      // 考试模式翻译
      examModeFormat(row, column) {
        var datas = this.examinationMode
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].examModeCode == row.examModeCode) {
            return datas[i].examModeName
          }
        }
        return '无'
      },
      // 年级翻译
      gradeFormat(row, column) {
        var datas = this.gradeList
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].gradeCode == row.gradeCode) {
            return datas[i].gradeName
          }
        }
        return '无'
      },
      // 学年翻译
      schoolYeaFormat(row, column) {
        var datas = this.SchoolYearList
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].schoolYearCode == row.schoolYear) {
            return datas[i].schoolYearName
          }
        }
        return '无'
      },
      //届别翻译
      graduateFormat(row, column) {
        var datas = this.GraduateList
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].graduateCode == row.graduateCode) {
            return datas[i].graduateName
          }
        }
        return '无'
      },
      // 学期翻译
      termFormat(row, column) {
        var datas = this.TermList
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].termCode == row.termCode) {
            return datas[i].termName
          }
        }
        return '无'
      }
    }
  }
</script>
<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #409eff;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }

  .demonstration {
    display: block;
    color: #8492a6;
    font-size: 14px;
    margin-bottom: 20px;
  }

  .el-dropdown-link {
    margin-right: 5px;
  }
</style>
