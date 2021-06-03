<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="名称" prop="assembleName">
        <el-input
            v-model="queryParams.assembleName"
            placeholder="请输入名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代码" prop="assembleCode">
        <el-input
            v-model="queryParams.assembleCode"
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
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['reportOnline:reportAssemble:add']"
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
            v-hasPermi="['reportOnline:reportAssemble:edit']"
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
            v-hasPermi="['reportOnline:reportAssemble:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['reportOnline:reportAssemble:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportAssembleList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" prop="assembleId"/>
      <el-table-column label="名称" align="center" prop="assembleName"/>
      <el-table-column label="代码" align="center" prop="assembleCode"/>
      <el-table-column label="适用分析类型" align="center" prop="assembleAnalysisType"/>
      <el-table-column label="适用年级" align="center" prop="assembleGrade"/>
      <el-table-column label="适用考试类型" align="center" prop="assembleExamType"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['reportOnline:reportAssemble:edit']"
          >修改
          </el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['reportOnline:reportAssemble:remove']"
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

    <!-- 添加或修改报表管理对话框 -->
    <el-dialog :close-on-click-modal="this.$store.state.pubCon.isDialogClose" :title="title"
               :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="assembleName">
          <el-input v-model="form.assembleName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="代码" prop="assembleCode">
          <el-input v-model="form.assembleCode" placeholder="请输入代码"/>
        </el-form-item>
        <el-form-item label="适用分析类型">
          <el-checkbox-group v-model="form.assembleAnalysisType">
            <el-checkbox>请选择字典生成</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="适用年级">
          <el-checkbox-group v-model="form.assembleGrade">
            <el-checkbox>请选择字典生成</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="适用考试类型">
          <el-checkbox-group v-model="form.assembleExamType">
            <el-checkbox>请选择字典生成</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="关联表">
          <el-select v-model="form.reportResources" placeholder="请选择关联资源" clearable multiple
                     size="small">
            <el-option value="1" label="成绩1"/>
            <el-option value="2" label="成绩2"/>
            <el-option value="3" label="成绩图形1"/>
            <el-option value="4" label="增值评价1"/>
            <el-option value="5" label="zzpj002"/>
          </el-select>
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
    addReportAssemble,
    delReportAssemble,
    exportReportAssemble,
    getReportAssemble,
    listReportAssemble,
    updateReportAssemble
  } from '@/api/reportOnline/reportAssemble'

  export default {
    name: 'ReportAssemble',
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
        // 报表管理表格数据
        reportAssembleList: [],
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
          assembleName: null,
          assembleCode: null,
          assembleAnalysisType: null,
          assembleGrade: null,
          assembleExamType: null
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
      /** 查询报表管理列表 */
      getList() {
        this.loading = true
        listReportAssemble(this.queryParams).then(response => {
          this.reportAssembleList = response.rows
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
          assembleId: null,
          assembleName: null,
          assembleCode: null,
          assembleAnalysisType: [],
          assembleGrade: [],
          assembleExamType: [],
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
        this.ids = selection.map(item => item.assembleId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加报表管理'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const assembleId = row.assembleId || this.ids
        getReportAssemble(assembleId).then(response => {
          this.form = response.data
          this.form.assembleAnalysisType = this.form.assembleAnalysisType.split(',')
          this.form.assembleGrade = this.form.assembleGrade.split(',')
          this.form.assembleExamType = this.form.assembleExamType.split(',')
          this.open = true
          this.title = '修改报表管理'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            this.form.assembleAnalysisType = this.form.assembleAnalysisType.join(',')
            this.form.assembleGrade = this.form.assembleGrade.join(',')
            this.form.assembleExamType = this.form.assembleExamType.join(',')
            if (this.form.assembleId != null) {
              updateReportAssemble(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addReportAssemble(this.form).then(response => {
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
        const assembleIds = row.assembleId || this.ids
        this.$confirm('是否确认删除报表管理编号为"' + assembleIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return delReportAssemble(assembleIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有报表管理数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return exportReportAssemble(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
