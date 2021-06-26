<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item v-if="hiddenExam" label="考试" prop="examId">
        <el-input
          v-model="searchKey"
          placeholder="请输入考试Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="studentNum">
        <el-input
          v-model="queryParams.studentNum"
          placeholder="请输入学号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="准考证号" prop="studentZkzh">
        <el-input
          v-model="queryParams.studentZkzh"
          placeholder="请输入准考证号"
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
          icon="el-icon-plus" plain
          size="mini"
          @click="handleAdd"
          v-if="hiddenAdd"
        >新增
        </el-button>
        <!-- v-hasPermi="['collection:cltStudentDetail:add']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit" plain
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改
        </el-button>
        <!-- v-hasPermi="['collection:cltStudentDetail:edit']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete" plain
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
        <!-- v-hasPermi="['collection:cltStudentDetail:remove']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
        >导出
        </el-button>
        <!-- v-hasPermi="['collection:cltStudentDetail:export']" -->
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="cltStudentDetailList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        hiddern
        prop="studentId"
      />
      <el-table-column
        v-if="hiddenKey"
        label="考试主键"
        align="center"
        prop="examId"
      />
      <el-table-column label="名称" align="center" prop="studentName"/>
      <el-table-column label="学号" align="center" prop="studentNum"/>
      <el-table-column label="准考证号" align="center" prop="studentZkzh"/>
      <el-table-column label="选考科目名称" align="center" prop="subjects"/>
      <el-table-column label="班级" align="center" prop="classCode"/>
      <el-table-column label="学校" align="center" prop="schoolCode"/>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <!-- v-hasPermi="['collection:cltStudentDetail:edit']" -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
          <!-- v-hasPermi="['collection:cltStudentDetail:remove']" -->
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

    <!-- 添加或修改学生信息采集对话框 -->
    <el-dialog
      :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item v-if="hiddenUpdate" label="考试主键" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入考试主键"/>
        </el-form-item>
        <el-form-item label="名称" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="学号" prop="studentNum">
          <el-input v-model="form.studentNum" placeholder="请输入学号"/>
        </el-form-item>
        <el-form-item label="准考证号" prop="studentZkzh">
          <el-input v-model="form.studentZkzh" placeholder="请输入准考证号"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdate" label="选考类型" prop="studentCombineCode">
          <el-select
            v-model="form.studentCombineCode"
            placeholder="请选择选考类型"
          >
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item v-if="hiddenUpdate" label="外语科目" prop="foreignSubjectCode">
          <el-input
            v-model="form.foreignSubjectCode"
            placeholder="请输入外语科目"
          />
        </el-form-item>
        <el-form-item label="选考科目名称" prop="subjects">
          <el-input
            v-model="form.subjects"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="班级" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入班级"/>
        </el-form-item>
        <el-form-item label="学校" prop="schoolCode">
          <el-input v-model="form.schoolCode" placeholder="请输入学校"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdate" label="所有上级代码" prop="parentsCode">
          <el-input
            v-model="form.parentsCode"
            placeholder="请输入所有上级代码"
          />
        </el-form-item>
        <el-form-item label="扩展字段" prop="extensions">
          <el-input v-model="form.extensions" placeholder="请输入扩展字段"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdate" label="排序" prop="orderNum">
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
    addCltStudentDetail,
    delCltStudentDetail,
    exportCltStudentDetail,
    getCltStudentDetail,
    listCltStudentDetail,
    updateCltStudentDetail
  } from '@/api/collection/cltStudentDetail'

  export default {
    name: 'CltStudentDetail',
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
        searchKey: null,
        // 学生信息采集表格数据
        cltStudentDetailList: [],
        // 弹出层标题
        title: '',
        // 隐藏新增按钮
        hiddenAdd: false,
        // 隐藏修改里的条件
        hiddenUpdate: true,
        // 隐藏考试搜索条件
        hiddenExam: false,
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
          studentName: null,
          studentNum: null,
          studentZkzh: null,
          classCode: null,
          schoolCode: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      }
    },
    created() {
      this.searchKey = null
      this.queryParams.examId = this.$route.params && this.$route.params.key
      this.getList()
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.getDicts('sys_del_flag').then(response => {
        this.delFlagOptions = response.data
      })
    },
    // watch: {
    //   $route: {
    //     handler(newRouder) {
    //       this.searchKey = null
    //       // this.key = newRouder.query.key;
    //       this.queryParams.examId = newRouder.query.key;
    //       this.getList()
    //     },
    //     // 正常情况下，watch只有值发生变化的时候才会调用
    //     // 一上来会调用一次
    //     immediate: true,
    //   }
    // },
    methods: {
      /** 查询学生信息采集列表 */
      getList() {
        this.loading = true
        listCltStudentDetail(this.queryParams).then(response => {
          this.cltStudentDetailList = response.rows
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
          studentId: null,
          examId: null,
          studentName: null,
          studentNum: null,
          studentZkzh: null,
          studentSignCode: null,
          studentCombineCode: null,
          foreignSubjectCode: null,
          subjects: null,
          classCode: null,
          schoolCode: null,
          parentsCode: null,
          extensions: null,
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
        if (this.searchKey != null) {
          this.queryParams.examId = this.searchKey
        }
        this.queryParams.pageNum = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.searchKey = null
        this.resetForm('queryForm')
        this.handleQuery()
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.studentId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加学生信息采集'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        this.hiddenUpdate = false
        const studentId = row.studentId || this.ids
        getCltStudentDetail(studentId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改学生信息采集'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.studentId != null) {
              updateCltStudentDetail(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.hiddenUpdate = false
                this.getList()
              })
            } else {
              addCltStudentDetail(this.form).then(response => {
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
        const studentIds = row.studentId || this.ids
        this.$confirm('是否确认删除学生信息采集编号为"' + studentIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delCltStudentDetail(studentIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有学生信息采集数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportCltStudentDetail(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
