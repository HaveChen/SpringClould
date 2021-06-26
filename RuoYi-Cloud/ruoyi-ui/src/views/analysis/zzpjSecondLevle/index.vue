<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="一级维度" prop="alyFirstLevelId">
        <el-input
          v-model="queryParams.alyFirstLevelId"
          placeholder="请输入关联一级维度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      </el-form-item>
      <el-form-item label="分析项目" prop="alyProjectId">
        <el-input
          v-model="queryParams.alyProjectId"
          placeholder="请输入分析项目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分析代码" prop="alyCode">
        <el-input
          v-model="queryParams.alyCode"
          placeholder="请输入分析代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分析类型" prop="alyType">
        <el-select v-model="queryParams.alyType" placeholder="请选择分析类型" clearable size="small">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="分析状态" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入分析状态"
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
          v-hasPermi="['analysis:zzpjSecondLevle:add']"
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
          v-hasPermi="['analysis:zzpjSecondLevle:edit']"
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
          v-hasPermi="['analysis:zzpjSecondLevle:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['analysis:zzpjSecondLevle:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="zzpjSecondLevleList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" v-if="hiddenKey" align="center" prop="alySecondLevelId"/>
      <el-table-column label="关联" v-if="hiddenKey" align="center" prop="alyFirstLevelId"/>
      <el-table-column label="分析项目" align="center" prop="alyProjectId"/>
      <el-table-column label="分析代码" align="center" prop="alyCode"/>
      <el-table-column label="包含学校" align="center" prop="includeSchoolCodes"/>
      <el-table-column label="分析类型" align="center" prop="alyLevelType"/>
      <el-table-column label="包含课程" align="center" prop="courseIds"/>
      <el-table-column label="分析类型" align="center" prop="alyType"/>
      <el-table-column label="分析状态" align="center" prop="state" :formatter="stateFormat"/>
      <el-table-column label="最后时间" align="center" prop="updateTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['analysis:zzpjSecondLevle:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['analysis:zzpjSecondLevle:remove']"
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

    <!-- 添加或修改二级分析层级 对话框 -->
    <el-dialog :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
               :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联" prop="alyFirstLevelId">
          <el-input v-model="form.alyFirstLevelId" placeholder="请输入关联"/>
        </el-form-item>
        <el-form-item label="名称" prop="alyLevelName">
          <el-input v-model="form.alyLevelName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="分析项目" prop="alyProjectId">
          <el-input v-model="form.alyProjectId" placeholder="请输入分析项目"/>
        </el-form-item>
        <el-form-item label="分析代码" prop="alyCode">
          <el-input v-model="form.alyCode" placeholder="请输入分析代码"/>
        </el-form-item>
        <el-form-item label="包含学校" prop="includeSchoolCodes">
          <el-input v-model="form.includeSchoolCodes" placeholder="请输入包含学校"/>
        </el-form-item>
        <el-form-item label="分析类型" prop="alyLevelType">
          <el-select v-model="form.alyLevelType" placeholder="请选择分析类型">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="包含课程" prop="courseIds">
          <el-input v-model="form.courseIds" placeholder="请输入包含课程"/>
        </el-form-item>
        <el-form-item label="分析类型" prop="alyType">
          <el-select v-model="form.alyType" placeholder="请选择分析类型">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="分析状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入分析状态"/>
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
    addZzpjSecondLevle,
    delZzpjSecondLevle,
    exportZzpjSecondLevle,
    getZzpjSecondLevle,
    listZzpjSecondLevle,
    updateZzpjSecondLevle
  } from '@/api/analysis/zzpjSecondLevle'

  export default {
    name: 'ZzpjSecondLevle',
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
        // 二级分析层级 表格数据
        zzpjSecondLevleList: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        analysisData: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          alyFirstLevelId: null,
          alyLevelName: null,
          alyProjectId: null,
          alyCode: null,
          includeSchoolCodes: null,
          alyLevelType: null,
          courseIds: null,
          alyType: null,
          state: null
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
      this.getDicts('aly_analysis_status').then(response => {
        this.analysisData = response.data
      })
    },
    methods: {
      /** 查询二级分析层级 列表 */
      getList() {
        this.loading = true
        listZzpjSecondLevle(this.queryParams).then(response => {
          this.zzpjSecondLevleList = response.rows
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
      // 分析状态字典翻译
      stateFormat(row, column) {
        return this.selectDictLabel(this.analysisData, row.state)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          alySecondLevelId: null,
          alyFirstLevelId: null,
          alyLevelName: null,
          alyProjectId: null,
          alyCode: null,
          includeSchoolCodes: null,
          alyLevelType: null,
          courseIds: null,
          alyType: null,
          state: null,
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
        this.ids = selection.map(item => item.alySecondLevelId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加二级分析层级 '
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const alySecondLevelId = row.alySecondLevelId || this.ids
        getZzpjSecondLevle(alySecondLevelId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改二级分析层级 '
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.alySecondLevelId != null) {
              updateZzpjSecondLevle(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addZzpjSecondLevle(this.form).then(response => {
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
        const alySecondLevelIds = row.alySecondLevelId || this.ids
        this.$confirm('是否确认删除二级分析层级 编号为"' + alySecondLevelIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delZzpjSecondLevle(alySecondLevelIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有二级分析层级 数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportZzpjSecondLevle(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
