<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="考试" prop="examId" v-if="hiddenKey">
        <el-input
          v-model="searchKey"
          placeholder="请输入考试"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="识别类型" prop="importType">
        <el-select
          clearable
          v-model="queryParams.importType"
          @change="searchList"
          placeholder="请选择识别类型"
        >
          <el-option
            v-for="item in chushiExamType"
            :key="item.dataTypeCode"
            :label="item.dataTypeName"
            :value="item.dataTypeCode"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete" plain
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
        <!-- v-hasPermi="['collection:cltProgress:remove']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
        >导出
        </el-button>
        <!-- v-hasPermi="['collection:cltProgress:export']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          size="mini"
          type="success"
          icon="el-icon-upload"
          @click="handleUpload"
        >上传
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-message"
          size="mini"
          @click="handleImport"
        >导入
        </el-button>
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>
    <!-- 导入对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="10"
        :multiple="true"
        accept=".xlsx, .xls,.zip,.rar"
        :headers="upload.headers"
        :action="upload.url + '?importType=bmk&examId=' + upload.examId"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" style="color: red" slot="tip">
          提示：仅允许导入"xls"或"xlsx"格式文件！
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-table
      v-loading="loading"
      :data="cltProgressList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="progressId"
      />
      <el-table-column
        v-if="hiddenKey"
        label="考试"
        align="center"
        prop="examId"
      />
      <el-table-column label="文件名称" align="center" prop="fileName"/>
      <el-table-column
        label="识别类型"
        align="center"
        prop="importType"
        :formatter="dataTypeFormat"
      />
      <el-table-column label="解析科目" align="center" prop="subjectCode"/>
      <el-table-column label="总行数" align="center" prop="sumLine"/>
      <el-table-column label="成功行数" align="center" prop="sucessLine"/>
      <el-table-column
        label="采集状态"
        align="center"
        prop="importStatus"
        :formatter="importStatusFormat"
      />
      <el-table-column label="最后操作时间" align="center" prop="updateTime"/>
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
          >详情
          </el-button>
          <!-- v-hasPermi="['collection:cltProgress:edit']" -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
          <!-- v-hasPermi="['collection:cltProgress:remove']" -->
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

    <!-- 添加或修改采集进度对话框 -->
    <el-dialog
      :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="考试" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入考试"/>
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名称"/>
        </el-form-item>
        <el-form-item label="识别类型" prop="importType">
          <el-input v-model="form.importType" placeholder="请选择识别类型">
          </el-input>
        </el-form-item>
        <el-form-item label="字段匹配" prop="fieldMapping">
          <el-input
            v-model="form.fieldMapping"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="总行数" prop="sumLine">
          <el-input v-model="form.sumLine" placeholder="请输入总行数"/>
        </el-form-item>
        <el-form-item label="成功行数" prop="sucessLine">
          <el-input v-model="form.sucessLine" placeholder="请输入成功行数"/>
        </el-form-item>
        <el-form-item label="失败行数" prop="errorLine">
          <el-input v-model="form.errorLine" placeholder="请输入失败行数"/>
        </el-form-item>
        <el-form-item label="采集状态">
          <el-input v-model="form.importStatus"></el-input>
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
    addCltProgress,
    chuCltDatafieldToExam,
    delCltProgress,
    exportCltProgress,
    getCltProgress,
    listCltProgress
  } from '@/api/collection/cltProgress'
  import {studentImport} from '@/api/collection/cltExamInfo'
  import {getToken} from '@/utils/auth'

  export default {
    name: 'CltProgress',
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
        // 手动搜索输入
        searchKey: null,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 接收传过来的key值
        key: null,
        // 隐藏主键
        hiddenKey: false,
        // 采集进度表格数据
        cltProgressList: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 初始化数据线类型下拉
        chushiExamType: [],
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
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        importStatusList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          examId: null,
          importType: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      }
    },
    created() {
      // this.getList();
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.getDicts('sys_del_flag').then(response => {
        this.delFlagOptions = response.data
      })
      this.getDicts('clt_import_status').then(response => {
        this.importStatusList = response.data
      })
      chuCltDatafieldToExam().then(response => {
        this.searchKey = null
        this.key = this.$route.params && this.$route.params.key
        this.chushiExamType = response.data
        this.queryParams.examId = this.$route.params && this.$route.params.key
        this.getList()
      })
    },
    methods: {
      /** 查询采集进度列表 */
      getList() {
        this.loading = true
        listCltProgress(this.queryParams).then(response => {
          this.cltProgressList = response.rows
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
      // 导入状态字典翻译
      importStatusFormat(row, column) {
        return this.selectDictLabel(this.importStatusList, row.importStatus)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          progressId: null,
          examId: null,
          fileName: null,
          importType: null,
          fieldMapping: null,
          sumLine: null,
          sucessLine: null,
          errorLine: null,
          importStatus: '0',
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
      /** 下拉搜索按钮操作 */
      searchList() {
        if (this.searchKey != null) {
          this.queryParams.examId = this.searchKey
        }
        this.queryParams.pageNum = 1
        this.getList()
      },
      /** 重置按钮操作 */
      // resetQuery() {
      //   this.searchKey = null
      //   this.resetForm("queryForm");
      //   this.getList();
      // },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.progressId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加采集进度'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const progressId = row.progressId || this.ids
        getCltProgress(progressId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '采集进度'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.progressId != null) {
              this.open = false
            } else {
              addCltProgress(this.form).then(response => {
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
        const progressIds = row.progressId || this.ids
        this.$confirm('是否确认删除采集进度编号为"' + progressIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delCltProgress(progressIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有采集进度数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportCltProgress(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      },
      /** 上传按钮操作 */
      handleUpload() {
        this.upload.examId = this.key
        this.upload.title = '报名库文件'
        this.upload.open = true
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false
        this.upload.isUploading = false
        this.$refs.upload.clearFiles()
        this.$alert(response.msg, '导入结果', {dangerouslyUseHTMLString: true})
        this.getList()
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit()
      },
      /** 导入按钮操作 */
      handleImport() {
        this.msgSuccess('开始导入，请稍后。。。。。。')
        studentImport(this.key)
      },
      // 数据类型翻译
      dataTypeFormat(row, column) {
        var datas = this.chushiExamType
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].dataTypeCode == row.importType) {
            return datas[i].dataTypeName
          }
        }
        return '无'
      }
    }
  }
</script>
