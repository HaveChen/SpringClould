<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="分析项目" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入分析项目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分析试卷" prop="alyTestpaperId">
        <el-input
          v-model="queryParams.alyTestpaperId"
          placeholder="请输入分析试卷"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="科目代码" prop="subjectCode">
        <el-input
          v-model="queryParams.subjectCode"
          placeholder="请输入科目代码"
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
          v-hasPermi="['analysis:alyItemDetail:add']"
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
          v-hasPermi="['analysis:alyItemDetail:edit']"
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
          v-hasPermi="['analysis:alyItemDetail:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['analysis:alyItemDetail:export']"
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
      :data="alyItemDetailList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键" align="center" prop="alyItemId"/>
      <el-table-column label="分析项目" align="center" prop="projectId"/>
      <el-table-column label="分析试卷" align="center" prop="alyTestpaperId"/>
      <el-table-column label="科目代码" align="center" prop="subjectCode"/>
      <el-table-column label="给分点" align="center" prop="scorePoint"/>
      <el-table-column label="小题号" align="center" prop="littleTitleNo"/>
      <el-table-column label="大题号" align="center" prop="bigTitleNo"/>
      <el-table-column
        label="给分点类型"
        align="center"
        prop="scorePointType"
      />
      <el-table-column label="满分" align="center" prop="fullscore"/>
      <el-table-column label="全部选项" align="center" prop="allOptions"/>
      <el-table-column label="正确选项" align="center" prop="rightOptioin"/>
      <el-table-column
        label="预测难度"
        align="center"
        prop="forecastDifficulty"
      />
      <el-table-column label="是否选做题" align="center" prop="isChoice"/>
      <el-table-column label="选做题组" align="center" prop="choiceGroup"/>
      <el-table-column label="选做题规则" align="center" prop="choiceRule"/>
      <el-table-column label="成绩对应字段" align="center" prop="cjField"/>
      <el-table-column label="扩展字段" align="center" prop="extensions"/>
      <el-table-column label="排序" align="center" prop="orderNum"/>
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
            v-hasPermi="['analysis:alyItemDetail:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['analysis:alyItemDetail:remove']"
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

    <!-- 添加或修改分析细目对话框 -->
    <el-dialog
      :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分析项目" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入分析项目"/>
        </el-form-item>
        <el-form-item label="分析试卷" prop="alyTestpaperId">
          <el-input
            v-model="form.alyTestpaperId"
            placeholder="请输入分析试卷"
          />
        </el-form-item>
        <el-form-item label="科目代码" prop="subjectCode">
          <el-input v-model="form.subjectCode" placeholder="请输入科目代码"/>
        </el-form-item>
        <el-form-item label="给分点" prop="scorePoint">
          <el-input v-model="form.scorePoint" placeholder="请输入给分点"/>
        </el-form-item>
        <el-form-item label="小题号" prop="littleTitleNo">
          <el-input v-model="form.littleTitleNo" placeholder="请输入小题号"/>
        </el-form-item>
        <el-form-item label="大题号" prop="bigTitleNo">
          <el-input v-model="form.bigTitleNo" placeholder="请输入大题号"/>
        </el-form-item>
        <el-form-item label="给分点类型" prop="scorePointType">
          <el-select
            v-model="form.scorePointType"
            placeholder="请选择给分点类型"
          >
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="满分" prop="fullscore">
          <el-input v-model="form.fullscore" placeholder="请输入满分"/>
        </el-form-item>
        <el-form-item label="全部选项" prop="allOptions">
          <el-input v-model="form.allOptions" placeholder="请输入全部选项"/>
        </el-form-item>
        <el-form-item label="正确选项" prop="rightOptioin">
          <el-input v-model="form.rightOptioin" placeholder="请输入正确选项"/>
        </el-form-item>
        <el-form-item label="预测难度" prop="forecastDifficulty">
          <el-input
            v-model="form.forecastDifficulty"
            placeholder="请输入预测难度"
          />
        </el-form-item>
        <el-form-item label="是否选做题" prop="isChoice">
          <el-input v-model="form.isChoice" placeholder="请输入是否选做题"/>
        </el-form-item>
        <el-form-item label="选做题组" prop="choiceGroup">
          <el-input v-model="form.choiceGroup" placeholder="请输入选做题组"/>
        </el-form-item>
        <el-form-item label="选做题规则" prop="choiceRule">
          <el-input v-model="form.choiceRule" placeholder="请输入选做题规则"/>
        </el-form-item>
        <el-form-item label="成绩对应字段" prop="cjField">
          <el-input v-model="form.cjField" placeholder="请输入成绩对应字段"/>
        </el-form-item>
        <el-form-item label="扩展字段" prop="extensions">
          <el-input v-model="form.extensions" placeholder="请输入扩展字段"/>
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
    addAlyItemDetail,
    delAlyItemDetail,
    exportAlyItemDetail,
    getAlyItemDetail,
    listAlyItemDetail,
    updateAlyItemDetail
  } from '@/api/analysis/alyItemDetail'

  export default {
    name: 'AlyItemDetail',
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
        // 分析细目表格数据
        alyItemDetailList: [],
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
          projectId: null,
          alyTestpaperId: null,
          subjectCode: null
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
      /** 查询分析细目列表 */
      getList() {
        this.loading = true
        listAlyItemDetail(this.queryParams).then(response => {
          this.alyItemDetailList = response.rows
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
          alyItemId: null,
          projectId: null,
          alyTestpaperId: null,
          subjectCode: null,
          scorePoint: null,
          littleTitleNo: null,
          bigTitleNo: null,
          scorePointType: null,
          fullscore: null,
          allOptions: null,
          rightOptioin: null,
          forecastDifficulty: null,
          isChoice: null,
          choiceGroup: null,
          choiceRule: null,
          cjField: null,
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
        this.ids = selection.map(item => item.alyItemId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加分析细目'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const alyItemId = row.alyItemId || this.ids
        getAlyItemDetail(alyItemId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改分析细目'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.alyItemId != null) {
              updateAlyItemDetail(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addAlyItemDetail(this.form).then(response => {
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
        const alyItemIds = row.alyItemId || this.ids
        this.$confirm('是否确认删除分析细目编号为"' + alyItemIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delAlyItemDetail(alyItemIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有分析细目数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportAlyItemDetail(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      }
    }
  }
</script>
