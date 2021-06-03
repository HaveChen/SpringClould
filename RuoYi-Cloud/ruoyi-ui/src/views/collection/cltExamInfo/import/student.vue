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
        </el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button
        >
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cltExamInfoList">
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
          label="界别"
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
          min-width="120px"
          class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-receiving"
              class="el-dropdown-link"
              @click="dataAcquisition(scope.row)"
          >采集
          </el-button>
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
              <el-dropdown-item command="a">报名库查看</el-dropdown-item>
              <el-dropdown-item command="b">试卷查看</el-dropdown-item>
              <el-dropdown-item command="c">成绩查询</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-dropdown
              class="el-dropdown-link"
              @command="handleDelete($event, scope.row)"
          >
            <el-button
                icon="el-icon-delete"
                size="mini"
                type="text"
                class="el-dropdown-link"
            >
              删除
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">删除学生</el-dropdown-item>
              <el-dropdown-item command="b">删除试卷</el-dropdown-item>
              <el-dropdown-item command="c">删除成绩</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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
  </div>
</template>

<script>
  import {
    achievementDeleteByExamId,
    listCltExamInfo,
    listExaminationMode,
    listExaminationType,
    listGrade,
    listGraduate,
    listSchoolYear,
    listTerm,
    papersDeleteByExamId,
    studentDeleteByExamId
  } from '@/api/collection/cltExamInfo'
  import {getToken} from '@/utils/auth'

  export default {
    name: 'student',
    components: {},
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
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        // 考试类型数据
        examinationType: [],
        SchoolYearList: [],
        gradeList: [],
        examinationMode: [],
        GraduateList: [],
        TermList: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        // 用户导入参数
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: '',
          examId: '',
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: {Authorization: 'Bearer ' + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + '/collection/importFile/uploadFiles'
        },
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
    },
    methods: {
      // 数据采集查看
      dataAcquisition(row) {
        const examId = row.examId || this.ids
        this.$router.push({
          path: `/collection/result/cltProgress/${examId}`
        })
      },
      // 更多操作按钮点击菜单事件
      handleCommand(command, row) {
        const examId = row.examId || this.ids
        // 报名库查看
        if (command == 'a') {
          this.$router.push({
            path: `/collection/exam/cltStudentDetail/${examId}`
          })
        }
        // 试卷查看
        if (command == 'b') {
          this.$router.push({
            path: `/collection/exam/cltTestpaper/${examId}`
          })
        }
        // 成绩查看
        if (command == 'c') {
          this.$router.push({
            path: `/collection/exam/cjDetail/${examId}`
          })
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
      /** 删除 */
      handleDelete(command, row) {
        const examId = row.examId || this.ids
        // 学生删除
        if (command == 'a') {
          this.$confirm('是否确认删除考试信息编号为"' + examId + '"的报名库信息吗?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            return studentDeleteByExamId(examId)
          }).then(() => {
            this.msgSuccess('删除成功')
            this.getList()
          })
        }
        // 试卷删除
        if (command == 'b') {
          this.$confirm('是否确认删除考试信息编号为"' + examId + '"的试卷信息吗?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            return papersDeleteByExamId(examId)
          }).then(() => {
            this.msgSuccess('删除成功')
            this.getList()
          })
        }
        // 成绩删除
        if (command == 'c') {
          this.$confirm('是否确认删除考试信息编号为"' + examId + '"的成绩信息吗?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            return achievementDeleteByExamId(examId)
          }).then(() => {
            this.msgSuccess('删除成功')
            this.getList()
          })
        }
      },
      /** 下载模板操作 */
      importTemplate() {
        alert('暂无')
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
<style lang="scss" scoped>
  .el-dropdown-link {
    margin-right: 5px;
  }
</style>
