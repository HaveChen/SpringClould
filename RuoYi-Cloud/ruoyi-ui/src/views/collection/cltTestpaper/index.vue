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
      <el-form-item label="试卷名称" prop="testpaperName">
        <el-input
            v-model="queryParams.testpaperName"
            placeholder="请输入试卷名称"
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
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
        >修改
        </el-button
        >
        <!-- v-hasPermi="['collection:cltTestpaper:edit']" -->
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
        <!-- v-hasPermi="['collection:cltTestpaper:remove']" -->
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
        <!-- v-hasPermi="['collection:cltTestpaper:export']" -->
      </el-col>
      <right-toolbar
          :showSearch.sync="showSearch"
          @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
        v-loading="loading"
        :data="cltTestpaperList"
        @selection-change="handleSelectionChange"
        @expand-change="getTestpaperDetList"
    >
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-table
              :data="scope.row.ruleItemData"
              tooltip-effect="dark"
              style="width: 100%"
          >
            <el-table-column
                v-if="hiddenKey"
                label="主键"
                align="center"
                prop="testpaperDetailId"
            />
            <el-table-column
                v-if="hiddenKey"
                label="试卷"
                align="center"
                prop="testpaperId"
            />
            <el-table-column
                v-if="hiddenKey"
                label="考试"
                align="center"
                prop="examId"
            />
            <el-table-column label="科目" align="center" prop="subjectId"/>
            <el-table-column label="试卷满分" align="center" prop="fullscore"/>
            <el-table-column label="客观题成绩" align="center" prop="kgscore"/>
            <el-table-column label="主观题成绩" align="center" prop="zgscore"/>
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
                    v-hasPermi="['collection:cltTestpaperDetail:edit']"
                >修改
                </el-button
                >
                <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="handleDelete(scope.row)"
                    v-hasPermi="['collection:cltTestpaperDetail:remove']"
                >删除
                </el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55" align="center"/>

      <el-table-column
          v-if="hiddenKey"
          label="主键"
          align="center"
          prop="testpaperId"
      />
      <el-table-column
          v-if="hiddenKey"
          label="考试"
          align="center"
          prop="examId"
      />
      <el-table-column label="试卷名称" align="center" prop="testpaperName"/>
      <el-table-column
          v-if="hiddenKey"
          label="试卷类型"
          align="center"
          prop="testpaperType"
      />
      <el-table-column label="试卷满分" align="center" prop="fullscore"/>
      <el-table-column label="是否有细目表" align="center">
        <template slot-scope="scope">
          <el-tooltip
              class="item"
              effect="light"
              content="详情"
              placement="top-start"
              v-if="hasTablePan(scope.row)"
          >
            <router-link
                :to="`/collection/exam/cltItemDetail/${scope.row.examId}/${scope.row.testpaperId}`"
                class="link-type"
            >
              <span>{{ hasTableFormat(scope.row) }}</span>
            </router-link>
          </el-tooltip>
          <span v-else>{{ hasTableFormat(scope.row) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有成绩" align="center">
        <template slot-scope="scope">
          <el-tooltip
              class="item"
              effect="light"
              content="详情"
              placement="top-start"
              v-if="hasCjPan(scope.row)"
          >
            <router-link
                :to="`/collection/exam/cjDetail/${scope.row.examId}/${scope.row.testpaperId}`"
                class="link-type"
            >
              <span>{{ hasExamFormat(scope.row) }}</span>
            </router-link>
          </el-tooltip>
          <span v-else>{{ hasExamFormat(scope.row) }}</span>
        </template>
      </el-table-column>
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
          <!-- v-hasPermi="['collection:cltTestpaper:edit']" -->
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
          >删除
          </el-button
          >
          <!-- v-hasPermi="['collection:cltTestpaper:remove']" -->
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

    <!-- 添加或修改试卷对话框 -->
    <el-dialog
        :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
        :title="title"
        :visible.sync="open"
        width="500px"
        append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item v-if="hiddenUpdata" label="考试" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入考试"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdata" label="试卷名称" prop="testpaperName">
          <el-input v-model="form.testpaperName" placeholder="请输入试卷名称"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdata" label="试卷类型" prop="testpaperType">
          <el-select v-model="form.testpaperType" placeholder="请选择试卷类型">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="试卷满分" prop="fullscore">
          <el-input v-model="form.fullscore" placeholder="请输入试卷满分"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdata" label="是否有细目表" prop="hasItem">
          <el-input v-model="form.hasItem" placeholder="请输入是否有细目表"/>
        </el-form-item>
        <el-form-item v-if="hiddenUpdata" label="是否有成绩" prop="hasCj">
          <el-input v-model="form.hasCj" placeholder="请输入是否有成绩"/>
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
        <el-form-item v-if="hiddenUpdata" label="状态">
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
        <!-- <el-form-item label="删除标记">
          <el-radio-group v-model="form.delFlag">
            <el-radio
              v-for="dict in delFlagOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
              >{{ dict.dictLabel }}</el-radio
            >
          </el-radio-group>
        </el-form-item> -->
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
    addCltTestpaper,
    delCltTestpaper,
    exportCltTestpaper,
    getCltTestpaper,
    getTestpaperDetailById,
    listCltTestpaper,
    updateCltTestpaper
  } from '@/api/collection/cltTestpaper'

  export default {
    name: 'CltTestpaper',
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
        searchKey: null,
        // 总条数
        total: 0,
        // 隐藏主键
        hiddenKey: false,
        // 试卷表格数据
        cltTestpaperList: [],
        // 获取根据试卷Id获取试卷详情列表
        testPaperList: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 隐藏修改
        hiddenUpdata: true,
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          examId: null,
          testpaperName: null,
          testpaperType: null
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
    //       this.queryParams.examId = newRouder.query.key;
    //       this.getList()
    //     },
    //     // 正常情况下，watch只有值发生变化的时候才会调用
    //     // 一上来会调用一次
    //     immediate: true,
    //   }
    // },
    methods: {
      /** 查询试卷列表 */
      getList() {
        this.loading = true
        listCltTestpaper(this.queryParams).then(response => {
          if (response) {
            //给每行数据强制追加一个数据项
            response.rows.map(item => {
              item.ruleItemData = []
            })
          }
          this.cltTestpaperList = response.rows
          this.total = response.total
          this.loading = false
        })
      },
      getTestpaperDetList(row, expandedRows) {
        // 该处是用于判断是展开还是收起行，只有展开的时候做请求，避免多次请求！
        // 展开的时候expandedRows有值，收起的时候为空.
        if (expandedRows.length > 0) {
          getTestpaperDetailById(row.testpaperId).then(res => {
            // 遍历当前页面表
            this.cltTestpaperList.forEach((temp, index) => {
              // 找到当前点击的行，把动态获取到的数据赋值进去
              if (temp.testpaperId === row.testpaperId) {
                this.cltTestpaperList[index].ruleItemData = res.data
              }
            })
          })
        }
        // let res = await getTestpaperDetailById(testpaperId)
        // this.testPaperList = res.data
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
          testpaperId: null,
          examId: null,
          testpaperName: null,
          testpaperType: null,
          fullscore: null,
          hasItem: null,
          hasCj: null,
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
        this.queryParams.examId = this.$route.query.key
        this.handleQuery()
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.testpaperId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        this.hiddenUpdata = false
        const testpaperId = row.testpaperId || this.ids
        getCltTestpaper(testpaperId).then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改试卷'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (this.form.testpaperId != null) {
              updateCltTestpaper(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              addCltTestpaper(this.form).then(response => {
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
        const testpaperIds = row.testpaperId || this.ids
        this.$confirm('是否确认删除试卷编号为"' + testpaperIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return delCltTestpaper(testpaperIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有试卷数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
          return exportCltTestpaper(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      },
      // 是否有细目表翻译
      hasTableFormat(row, column) {
        if (row.hasItem == 0) {
          this.hasxiTab = true
          return '有'
        }
        if (row.hasItem == 1) {
          this.hasxiTab = false
          return '无'
        }
      },
      // 是否有细目表判断
      hasTablePan(row, column) {
        if (row.hasItem == 0) {
          return true
        }
        if (row.hasItem == 1) {
          return false
        }
      },
      // 是否有成绩判断
      hasCjPan(row, column) {
        if (row.hasCj == 0) {
          return true
        }
        if (row.hasCj == 1) {
          return false
        }
      },
      // 是否有成绩表翻译
      hasExamFormat(row, column) {
        if (row.hasCj == 0) {
          return '有'
        }
        if (row.hasCj == 1) {
          return '无'
        }
      }
    }
  }
</script>
