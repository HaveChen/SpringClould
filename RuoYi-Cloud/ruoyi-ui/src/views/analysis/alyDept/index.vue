<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="分析项目" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入分析项目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组织名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入组织名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组织代码" prop="deptCode">
        <el-input
          v-model="queryParams.deptCode"
          placeholder="请输入组织代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组织来源" prop="deptCome">
        <el-input
          v-model="queryParams.deptCome"
          placeholder="请输入组织来源"
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
          v-hasPermi="['analysis:alyDept:add']"
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
          v-hasPermi="['analysis:alyDept:edit']"
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
          v-hasPermi="['analysis:alyDept:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['analysis:alyDept:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="alyDeptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" prop="alyDeptId"/>
      <el-table-column label="分析项目" align="center" prop="projectId"/>
      <el-table-column label="组织名称" align="center" prop="deptName"/>
      <el-table-column label="组织代码" align="center" prop="deptCode"/>
      <el-table-column label="组织类型" align="center" prop="deptTypeCode"/>
      <el-table-column label="组织来源" align="center" prop="deptCome" :formatter="fieldSourceFormat"/>
      <el-table-column label="包含学校" align="center" prop="allSchoolCode"/>
      <el-table-column label="排序" align="center" prop="orderNum"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['analysis:alyDept:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['analysis:alyDept:remove']"
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

    <!-- 添加或修改分析组织对话框 -->
    <el-dialog :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
               :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分析项目" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入分析项目"/>
        </el-form-item>
        <el-form-item label="组织名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入组织代码"/>
        </el-form-item>
        <el-form-item label="组织代码" prop="deptCode">
          <el-input v-model="form.deptCode" placeholder="请输入组织代码"/>
        </el-form-item>
        <el-form-item label="组织类型" prop="deptTypeCode">
          <el-input v-model="form.deptTypeCode" placeholder="请输入组织类型"/>
        </el-form-item>
        <el-form-item label="组织来源" prop="deptCome">
          <el-radio-group v-model="form.deptCome">
            <el-radio
              v-for="dict in fieldSources"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="包含的学校" prop="allSchoolCode">
          <el-input v-model="form.allSchoolCode" placeholder="请输入包含学校 包含的学校"/>
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
    addAlyDept,
    delAlyDept,
    exportAlyDept,
    getAlyDept,
    listAlyDept,
    updateAlyDept
  } from '@/api/analysis/alyDept'

  export default {
    name: 'AlyDept',
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
        // 分析组织表格数据
        alyDeptList: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        //数据来源
        fieldSources: [],
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectId: null,
          deptName: null,
          deptCode: null,
          deptCome: null
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
      this.getDicts('aly_field_source').then(response => {
        this.fieldSources = response.data
      })
    },
    methods: {
      /** 查询分析组织列表 */
      getList() {
        this.loading = true
        listAlyDept(this.queryParams).then(response => {
          this.alyDeptList = response.rows
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
      }, // 维度来源字典翻译
      fieldSourceFormat(row, column) {
        return this.selectDictLabel(this.fieldSources, row.deptCome)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          alyDeptId: null,
          projectId: null,
          parentCode: null,
          parentAllCode: null,
          deptName: null,
          deptCode: null,
          deptTypeCode: null,
          deptCome: null,
          allSchoolCode: null,
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
        this.ids = selection.map(item => item.alyDeptId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加分析组织'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const alyDeptId = row.alyDeptId || this.ids
        getAlyDept(alyDeptId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改分析组织'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.alyDeptId != null) {
              updateAlyDept(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addAlyDept(this.form).then(response => {
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
        const alyDeptIds = row.alyDeptId || this.ids
        this.$confirm('是否确认删除分析组织编号为"' + alyDeptIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delAlyDept(alyDeptIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有分析组织数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportAlyDept(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
