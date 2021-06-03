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
      <!--      <el-form-item label="学校" prop="deptCode">-->
      <!--        <el-select v-model="queryParams.deptCode" placeholder="请选择学校">-->
      <!--          <el-option-->
      <!--              v-for="item in listSchoolItem"-->
      <!--              :key="item.deptCode"-->
      <!--              :label="item.deptName"-->
      <!--              :value="item.deptCode"-->
      <!--              :disabled="item.status == 1"-->
      <!--          ></el-option>-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
      <el-form-item label="试卷" prop="testpaperId">
        <el-select v-model="queryParams.testpaperId" placeholder="请选择试卷">
          <el-option
              v-for="item in listTestpaperItem"
              :key="item.testpaperId"
              :label="item.testpaperName"
              :value="item.testpaperId"
              :disabled="item.status == 1"
          ></el-option>
        </el-select>
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
      <el-form-item label="成绩标识" prop="cjSign">
        <el-select v-model="queryParams.cjSign" placeholder="请选择成绩标识">
          <el-option
              v-for="item in cjSigns"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue"
          ></el-option>
        </el-select>
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
            v-if="hiddenAdd"
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
        >新增
        </el-button
        >
        <!-- v-hasPermi="['collection:cjDetail:add']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
        >修改
        </el-button
        >
        <!-- v-hasPermi="['collection:cjDetail:edit']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
        >删除
        </el-button
        >
        <!-- v-hasPermi="['collection:cjDetail:remove']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
        >导出
        </el-button
        >
        <!-- v-hasPermi="['collection:cjDetail:export']" -->
      </el-col>
      <right-toolbar
          :showSearch.sync="showSearch"
          @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
        v-loading="loading"
        :data="cjDetailList"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
          v-if="hiddenKey"
          label="主键"
          align="center"
          prop="cjId"
      />
      <el-table-column
          label="试卷"
          align="center"
          prop="testpaperId"
          :formatter="testpaperFormat"
      />
      <el-table-column label="准考证号" align="center" prop="studentZkzh"/>
      <el-table-column label="成绩标识" align="center" prop="cjSign" :formatter="cjSignFormat"/>
      <el-table-column label="全卷总分" align="center" prop="totalScore"/>
      <el-table-column label="客观题总分" align="center" prop="kgScore"/>
      <el-table-column label="主观题总分" align="center" prop="zgScore"/>
      <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
          >修改
          </el-button
          >
          <!-- v-hasPermi="['collection:cjDetail:edit']" -->
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
          >删除
          </el-button
          >
          <!-- v-hasPermi="['collection:cjDetail:remove']" -->
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

    <!-- 添加或修改成绩数据采集对话框 -->
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
        <el-form-item label="试卷" prop="testpaperId">
          <el-input v-model="form.testpaperId" placeholder="请输入试卷"/>
        </el-form-item>
        <el-form-item label="科目" prop="subjectCode">
          <el-input v-model="form.subjectCode" placeholder="请输入科目"/>
        </el-form-item>
        <el-form-item label="准考证号" prop="studentZkzh">
          <el-input
              v-model="form.studentZkzh"
              type="textarea"
              placeholder="请输入内容"
          />
        </el-form-item>
        </el-form-item>
        <el-form-item label="成绩标识" prop="cjSign">
          <el-radio-group v-model="form.cjSign">
            <el-radio
                v-for="dict in cjSigns"
                :key="dict.dictValue"
                :label="parseInt(dict.dictValue)"
            >{{ dict.dictLabel }}
            </el-radio
            >
          </el-radio-group>
        </el-form-item>
        <el-form-item label="全卷总分" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入全卷总分"/>
        </el-form-item>
        <el-form-item label="客观题总分" prop="kgScore">
          <el-input v-model="form.kgScore" placeholder="请输入客观题总分"/>
        </el-form-item>
        <el-form-item label="主观题总分" prop="zgScore">
          <el-input v-model="form.zgScore" placeholder="请输入主观题总分"/>
        </el-form-item>
        <el-form-item label="扩展字段" prop="extensions">
          <el-input v-model="form.extensions" placeholder="请输入扩展字段"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="parseInt(dict.dictValue)"
            >{{ dict.dictLabel }}
            </el-radio
            >
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
    addCjDetail,
    delCjDetail,
    exportCjDetail,
    getCjDetail,
    listCjDetail,
    listSchool,
    listTestpaper,
    updateCjDetail
  } from '@/api/collection/cjDetail'

  export default {
    name: 'CjDetail',
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
        searchKey: null,
        // 隐藏主键
        hiddenKey: false,
        // 隐藏添加
        hiddenAdd: false,
        // 上个页面传过来的key
        key: null,
        // 成绩数据采集表格数据
        cjDetailList: [],
        // 初始化学校下拉数据
        listSchoolItem: [],
        // 初始化试卷下拉数据
        listTestpaperItem: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        //采集标识
        cjSigns: [],
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          examId: null,
          testpaperId: null,
          studentZkzh: null,
          classCode: null,
          deptCode: null,
          studentZkzh: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      }
    },
    created() {
      // this.getList();
      this.searchKey = null
      this.queryParams.examId = this.$route.params && this.$route.params.key
      this.key = this.$route.params && this.$route.params.key
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.getDicts('clt_cj_sign').then(response => {
        this.cjSigns = response.data
      })
      this.getDicts('sys_del_flag').then(response => {
        this.delFlagOptions = response.data
      })
      listSchool(this.key).then(response => {
        this.listSchoolItem = response.data
      })
      listTestpaper(this.key).then(response => {
        this.listTestpaperItem = response.data
        if (response.data.length != 0) {
          this.queryParams.testpaperId = response.data[0].testpaperId
        }
        this.getList()
      })
    },
    methods: {
      /** 查询成绩数据采集列表 */
      getList() {
        this.loading = true
        listCjDetail(this.queryParams).then(response => {
          this.cjDetailList = response.rows
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
      // 成绩标识翻译
      cjSignFormat(row, column) {
        return this.selectDictLabel(this.cjSigns, row.cjSign)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          cjId: null,
          examId: null,
          testpaperId: null,
          subjectCode: null,
          studentZkzh: null,
          classCode: null,
          deptCode: null,
          parentAllCode: null,
          cjSign: null,
          totalScore: null,
          kgScore: null,
          zgScore: null,
          extensions: null,
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
        this.queryParams.examId = this.$route.query.key
        this.handleQuery()
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.cjId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加成绩数据采集'
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        const cjId = row.cjId || this.ids
        getCjDetail(cjId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改成绩数据采集'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.cjId != null) {
              updateCjDetail(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addCjDetail(this.form).then(response => {
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
        const cjIds = row.cjId || this.ids
        this.$confirm('是否确认删除成绩数据采集编号为"' + cjIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return delCjDetail(cjIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有成绩数据采集数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return exportCjDetail(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      },
      //试卷翻译
      testpaperFormat(row, column) {
        var datas = this.listTestpaperItem
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].testpaperId == row.testpaperId) {
            return datas[i].testpaperName
          }
        }
        return '无'
      },
      // 学校翻译
      schoolrFormat(row, column) {
        var datas = this.listSchoolItem
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].deptCode == row.deptCode) {
            return datas[i].deptName
          }
        }
        return '无'
      }
    }
  }
</script>
