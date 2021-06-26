<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="考试" prop="examId">
        <el-input
          v-model="queryParams.examId"
          placeholder="请输入考试"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代码" prop="courseCode">
        <el-input
          v-model="queryParams.courseCode"
          placeholder="请输入代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus" plain
          size="mini"
          @click="handleAdd"
          v-hasPermi="['collection:cltCourse:add']"
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
          v-hasPermi="['collection:cltCourse:edit']"
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
          v-hasPermi="['collection:cltCourse:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['collection:cltCourse:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cltCourseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column v-if="hiddenKey" label="主键" align="center" prop="courseId"/>
      <el-table-column label="考试" align="center" prop="examId"/>
      <el-table-column label="名称" align="center" prop="courseName"/>
      <el-table-column label="代码" align="center" prop="courseCode"/>
      <el-table-column label="教师" align="center" prop="teacherCode"/>
      <el-table-column label="学科" align="center" prop="subjectCode"/>
      <el-table-column label="教学班" align="center" prop="teachClassCode"/>
      <el-table-column label="单位代码" align="center" prop="deptCode"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['collection:cltCourse:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['collection:cltCourse:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改采集课程对话框 -->
    <el-dialog :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
               :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="考试" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入考试"/>
        </el-form-item>
        <el-form-item label="名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="代码" prop="courseCode">
          <el-input v-model="form.courseCode" placeholder="请输入代码"/>
        </el-form-item>
        <el-form-item label="教师" prop="teacherCode">
          <el-input v-model="form.teacherCode" placeholder="请输入教师"/>
        </el-form-item>
        <el-form-item label="学科" prop="subjectCode">
          <el-input v-model="form.subjectCode" placeholder="请输入学科"/>
        </el-form-item>
        <el-form-item label="教学班" prop="teachClassCode">
          <el-input v-model="form.teachClassCode" placeholder="请输入教学班"/>
        </el-form-item>
        <el-form-item label="单位代码" prop="deptCode">
          <el-input v-model="form.deptCode" placeholder="请输入单位代码"/>
        </el-form-item>
        <el-form-item label="单位" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入单位 关联sys_dept"/>
        </el-form-item>
        <el-form-item label="届别" prop="graduateCode">
          <el-input v-model="form.graduateCode" placeholder="请输入届别"/>
        </el-form-item>
        <el-form-item label="学年" prop="schoolYearCode">
          <el-input v-model="form.schoolYearCode" placeholder="请输入学年"/>
        </el-form-item>
        <el-form-item label="年级" prop="gradeCode">
          <el-input v-model="form.gradeCode" placeholder="请输入年级"/>
        </el-form-item>
        <el-form-item label="排序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入排序"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="删除标记">
          <el-radio-group v-model="form.delFlag">
            <el-radio
              v-for="dict in delFlagOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}
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
    addCltCourse,
    delCltCourse,
    exportCltCourse,
    getCltCourse,
    listCltCourse,
    updateCltCourse
  } from '@/api/collection/cltCourse'

  export default {
    name: 'CltCourse',
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
        // 采集课程表格数据
        cltCourseList: [],
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
          examId: null,
          courseName: null,
          courseCode: null
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
    },
    methods: {
      /** 查询采集课程列表 */
      getList() {
        this.loading = true
        listCltCourse(this.queryParams).then(response => {
          this.cltCourseList = response.rows
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
          courseId: null,
          examId: null,
          courseName: null,
          courseCode: null,
          teacherCode: null,
          subjectCode: null,
          teachClassCode: null,
          deptCode: null,
          deptId: null,
          graduateCode: null,
          schoolYearCode: null,
          gradeCode: null,
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
        this.ids = selection.map(item => item.courseId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加采集课程'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const courseId = row.courseId || this.ids
        getCltCourse(courseId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改采集课程'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.courseId != null) {
              updateCltCourse(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addCltCourse(this.form).then(response => {
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
        const courseIds = row.courseId || this.ids
        this.$confirm('是否确认删除采集课程编号为"' + courseIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delCltCourse(courseIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有采集课程数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportCltCourse(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
