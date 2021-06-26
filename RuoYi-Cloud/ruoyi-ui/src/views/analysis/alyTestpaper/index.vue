<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="科目来源" prop="alySubjectCome">
        <el-select v-model="queryParams.alySubjectCome" placeholder="请选择科目来源" clearable
                   size="small">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="科目类型" prop="alySubjectType">
        <el-select v-model="queryParams.alySubjectType" placeholder="请选择科目类型" clearable
                   size="small">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
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
          v-hasPermi="['analysis:alyTestpaper:add']"
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
          v-hasPermi="['analysis:alyTestpaper:edit']"
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
          v-hasPermi="['analysis:alyTestpaper:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['analysis:alyTestpaper:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="alyTestpaperList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" v-if="hiddenKey" prop="alyTestpaperId"/>
      <el-table-column label="分析试卷名称" align="center" prop="alyTestpaperName"/>
      <el-table-column label="科目" align="center" prop="subjectCode"/>
      <el-table-column label="科目来源" align="center" prop="alySubjectCome"
                       :formatter="fieldSourceFormat"/>
      <el-table-column label="科目类型" align="center" prop="alySubjectType"/>
      <el-table-column label="规则" align="center" prop="rule"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['analysis:alyTestpaper:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['analysis:alyTestpaper:remove']"
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

    <!-- 添加或修改分析试卷对话框 -->
    <el-dialog :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
               :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="科目" prop="subjectCode">
          <el-input v-model="form.subjectCode" placeholder="请输入科目">
          </el-input>
        </el-form-item>
        <el-form-item label="科目来源" prop="alySubjectCome">
          <el-radio-group v-model="form.alySubjectCome">
            <el-radio
              v-for="dict in fieldSources"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="科目类型" prop="alySubjectType">
          <el-select v-model="form.alySubjectType" placeholder="请选择科目类型">
            <el-option label="单科" value="single"/>
            <el-option label="总分" value="total"/>
          </el-select>
        </el-form-item>
        <el-form-item label="试卷满分" prop="fullscore">
          <el-input v-model="form.fullscore" placeholder="请输入试卷满分"/>
        </el-form-item>
        <el-form-item label="规则" prop="rule">
          <el-input v-model="form.rule" type="textarea" placeholder="请输入内容"/>
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
    addAlyTestpaper,
    delAlyTestpaper,
    exportAlyTestpaper,
    getAlyTestpaper,
    listAlyTestpaper,
    updateAlyTestpaper
  } from '@/api/analysis/alyTestpaper'

  export default {
    name: 'AlyTestpaper',
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
        // 分析试卷表格数据
        alyTestpaperList: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        fieldSources: [],
        open: false,
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectId: null,
          alySubjectCome: null,
          alySubjectType: null
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
      /** 查询分析试卷列表 */
      getList() {
        this.loading = true
        listAlyTestpaper(this.queryParams).then(response => {
          this.alyTestpaperList = response.rows
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
      fieldSourceFormat(row, column) {
        return this.selectDictLabel(this.fieldSources, row.alySubjectCome)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          alyTestpaperId: null,
          projectId: null,
          testpaperId: null,
          alySubjectCome: null,
          alySubjectType: null,
          fullscore: null,
          rule: null,
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
        this.ids = selection.map(item => item.alyTestpaperId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加分析试卷'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const alyTestpaperId = row.alyTestpaperId || this.ids
        getAlyTestpaper(alyTestpaperId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改分析试卷'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.alyTestpaperId != null) {
              updateAlyTestpaper(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addAlyTestpaper(this.form).then(response => {
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
        const alyTestpaperIds = row.alyTestpaperId || this.ids
        this.$confirm('是否确认删除分析试卷编号为"' + alyTestpaperIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delAlyTestpaper(alyTestpaperIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有分析试卷数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportAlyTestpaper(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
