<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="届别" prop="graduateCode">
        <el-select v-model="queryParams.graduateCode" placeholder="请选择界别">
          <el-option
            v-for="item in GraduateList"
            :key="item.graduateCode"
            :label="item.graduateName"
            :value="item.graduateCode"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="入口名称" prop="entranceName">
        <el-input
          v-model="queryParams.entranceName"
          placeholder="请输入入口名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出口名称" prop="exitName">
        <el-input
          v-model="queryParams.exitName"
          placeholder="请输入出口名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分析状态" prop="alyState">
        <el-select v-model="queryParams.alyState" placeholder="请选择分析状态">
          <el-option
            v-for="item in analysisData"
            :key="item.dictCode"
            :label="item.dictLabel"
            :value="item.dictValue"
            :disabled="item.status == 1"
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
          type="primary"
          icon="el-icon-plus" plain
          size="mini"
          @click="handleAdd"
          v-hasPermi="['analysis:alyProject:add']"
        >新增
        </el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit" plain
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['analysis:alyProject:edit']"
        >修改
        </el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete" plain
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['analysis:alyProject:remove']"
        >删除
        </el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-info"
          size="mini"
          :disabled="multiple"
          @click="prAnalysis"
          v-hasPermi="['analysis:alyProject:remove']"
        >分析
        </el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['analysis:alyProject:export']"
        >导出
        </el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="alyProjectList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="projectId"
      />
      <el-table-column label="名称" align="center" prop="projectName"/>
      <el-table-column
        label="届别"
        align="center"
        prop="graduateCode"
        :formatter="graduateFormat"
      />
      <el-table-column label="入口名称" align="center" prop="entranceName"/>
      <el-table-column label="出口名称" align="center" prop="exitName"/>
      <el-table-column
        label="分析状态"
        align="center"
        prop="alyState"
        :formatter="analysisFormat"
      />
      <el-table-column label="排序" align="center" prop="orderNum"/>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <div v-if="isProgressShow(scope.row)">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-document-checked"
              @click="prAnalysis(scope.row)"
              v-hasPermi="['analysis:alyProject:edit']"
            >分析
            </el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit" plain
              @click="handleUpdate(scope.row)"
              v-hasPermi="['analysis:alyProject:edit']"
            >修改
            </el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete" plain
              @click="handleDelete(scope.row)"
              v-hasPermi="['analysis:alyProject:remove']"
            >删除
            </el-button
            >
          </div>
          <div v-else>
            <el-progress
              :percentage="controllorProgess(scope.row)"
            ></el-progress>
          </div>
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

    <!-- 添加或修改分析项目对话框 -->
    <el-dialog
      :close-on-click-modal="this.$store.state.pubCon.isDialogClose"
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="projectName">
              <el-input v-model="form.projectName" placeholder="请输入名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="届别" prop="graduateCode">
              <el-select
                @change="changeGraduate"
                v-model="form.graduateCode"
                placeholder="请选择界别"
              >
                <el-option
                  v-for="item in GraduateList"
                  :key="item.graduateCode"
                  :label="item.graduateName"
                  :value="item.graduateCode"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="入口名称" prop="entranceName">
              <el-input
                style="width: 250px"
                v-model="form.entranceName"
                placeholder="请输入入口名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <!-- rkExamJson的格式：json数组,例如
         [{"examId": 50,"weight": "0.5" },{"examId": 2,"weight": "0.5" }]-->
            <el-form-item
              style="margin-bottom: 0px"
              label="入口考试"
              prop="rkExamJson"
            >
              <el-select
                v-model="rkExamId"
                multiple
                collapse-tags
                :clearable="true"
                placeholder="请选择入口考试"
                @change="chooseRkExam"
                style="width: 250px"
              >
                <el-option
                  v-for="item in examDataList"
                  :key="item.examId"
                  :label="item.examName"
                  :value="item.examId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <div class="rukouWeight">
            <div class="weightItem" v-for="(item, index) in lists" :key="index">
              <input
                class="weightName"
                type="text"
                v-model="item.name"
                disabled
              />
              <input
                v-model="item.value"
                class="weightVal"
                type="text"
                placeholder="请输入权重值"
              />
              <span
                class="el-icon-delete delExamItem"
                @click="removeRkExamItem(item.eId)"
              ></span>
            </div>
            <div class="weightTips">
              <span v-if="isTipsShow" class="weightNumTips"
              >请输入大于0小于等于1的小数</span
              >
              <span v-if="isAddTipsShow" class="weightNumTips"
              >权重之和必须为1</span
              >
              <span v-if="isWtNull" class="weightNumTips">权重不能为空</span>
            </div>
          </div>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="出口名称" prop="exitName">
              <el-input
                style="width: 250px"
                v-model="form.exitName"
                placeholder="请输入出口名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <!-- ckExamJson的格式：json数组,例如
        [{"examId": 50,"weight": "0.5" },{"examId": 2,"weight": "0.5" }]-->
            <el-form-item
              style="margin-bottom: 0px"
              label="出口考试"
              prop="ckExamJson"
            >
              <el-select
                v-model="ckExamId"
                multiple
                collapse-tags
                :clearable="true"
                @change="chooseCkExam"
                style="width: 250px"
                placeholder="请选择出口考试"
              >
                <el-option
                  v-for="item in examDataList"
                  :key="item.examId"
                  :label="item.examName"
                  :value="item.examId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <div class="rukouWeight">
            <div
              class="weightItem"
              v-for="(item, index) in cklists"
              :key="index"
            >
              <input
                class="weightName"
                type="text"
                v-model="item.name"
                disabled
              />
              <input
                v-model="item.value"
                class="weightVal"
                type="text"
                placeholder="请输入权重值"
              />
              <span
                class="el-icon-delete delExamItem"
                @click="removeCkExamItem(item.eId)"
              ></span>
            </div>
            <div class="weightTips">
              <span v-if="isCkTipsShow" class="weightNumTips"
              >请输入大于0小于等于1的小数</span
              >
              <span v-if="isCkAddTipsShow" class="weightNumTips"
              >权重之和必须为1</span
              >
              <span v-if="isCkWtNull" class="weightNumTips">权重不能为空</span>
            </div>
          </div>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="历次考试" prop="eachExamIds">
              <el-select
                style="width: 250px"
                multiple
                collapse-tags
                :clearable="true"
                v-model="form.eachExamIds"
                placeholder="请选择历次考试"
                @change="historyUpdate"
              >
                <el-option
                  v-for="item in examDataList"
                  :key="item.examId"
                  :label="item.examName"
                  :value="item.examId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <!-- caliber:初始化radio：1->全部考试都在的人，2->至少出入口都在的人-->
            <el-form-item label="统计口径" prop="caliber">
              <el-radio-group v-model="form.caliber">
                <el-radio :label="1"> 全部考试都在的人</el-radio>
                <el-radio :label="2"> 至少出入口都在的人</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="排序" prop="orderNum">
            <el-input
              style="width: 250px"
              v-model="form.orderNum"
              placeholder="请输入排序"
            />
          </el-form-item>
        </el-row>
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
    addAlyProject,
    delAlyProject,
    exportAlyProject,
    getAlyProject,
    getAnalysisProgress,
    getExamDataById,
    listAlyProject,
    listGraduate,
    projectAnalysis,
    updateAlyProject
  } from '@/api/analysis/alyProject'

  export default {
    name: 'AlyProject',
    components: {},
    data() {
      const validateRkExam = (rule, value, callback) => {
        if (this.rkExamId.length <= 0) {
          return callback(new Error('入口考试不能为空'))
        } else {
          callback()
        }
      }
      const validateckExam = (rule, value, callback) => {
        if (this.ckExamId.length <= 0) {
          return callback(new Error('出口考试不能为空'))
        } else {
          callback()
        }
      }
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
        // 分析状态下拉数据
        analysisData: [],
        // 总条数
        total: 0,
        // 分析项目表格数据
        alyProjectList: [],
        // 根据届别获取考试下拉数据
        examDataList: [],
        // 根据选择的入口考试动态增加权重输入
        lists: [
          // { name: "", value: '' }
        ],
        // 根据选择的出口考试动态增加权重输入
        cklists: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 隐藏主键
        hiddenKey: false,
        // 是否出现进度条
        isProgress: [],
        // 状态字典
        statusOptions: [],
        // 初始化届别下拉数据
        GraduateList: [],
        // 删除标记字典
        delFlagOptions: [],
        // 入口考试需要传的examId
        rkExamId: [],
        // 入口权重校验
        isTipsShow: false,
        // 入口权重之和校验
        isAddTipsShow: false,
        // 入口权重是否为空校验
        isWtNull: false,
        // 出口权重校验
        isCkTipsShow: false,
        // 出口权重之和校验
        isCkAddTipsShow: false,
        // 出口权重是否为空校验
        isCkWtNull: false,
        // 出口考试需要传的examId
        ckExamId: [],
        // 进度条进度
        // nuw: 0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectName: null,
          graduateCode: null,
          entranceName: null,
          exitName: null,
          alyState: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          projectName: [
            {required: true, message: '名称不能为空', trigger: 'blur'}
          ],
          graduateCode: [
            {required: true, message: '届别不能为空', trigger: 'blur'}
          ],
          entranceName: [
            {required: true, message: '入口名称不能为空', trigger: 'blur'}
          ],
          rkExamJson: [
            {required: true, validator: validateRkExam, trigger: 'change'}
          ],
          exitName: [
            {required: true, message: '出口名称不能为空', trigger: 'blur'}
          ],
          ckExamJson: [
            {required: true, validator: validateckExam, trigger: 'change'}
          ],
          orderNum: [
            {required: true, message: '排序不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    watch: {
      lists: {
        handler(val, oldVal) {
          this.isWtNull = false
          let weightAll = 0
          let count = 0
          let reg = /^([1](\.0+)?|0\.[1-9]+)$/
          if (val.length == 0) {
            this.isTipsShow = false
            this.isAddTipsShow = false
          }
          for (let i = 0; i < val.length; i++) {
            let res = reg.test(val[i].value)
            weightAll += (Number(val[i].value) * 10)
            if (val[i].value == '') {
              this.isTipsShow = false
              this.isAddTipsShow = false
            } else if (!res) {
              this.isTipsShow = true
              break
            } else {
              this.isTipsShow = false
            }
            if (val[i].value != '') {
              count++
            }
            if (count == val.length) {
              if (weightAll / 10 != 1) {
                this.isAddTipsShow = true
              } else {
                this.isAddTipsShow = false
              }
            }
          }
        },
        deep: true
      },
      cklists: {
        handler(val, oldVal) {
          this.isCkWtNull = false
          let weightAll = 0
          let count = 0
          let reg = /^([1](\.0+)?|0\.[1-9]+)$/
          if (val.length == 0) {
            this.isCkTipsShow = false
            this.isCkAddTipsShow = false
          }
          for (let i = 0; i < val.length; i++) {
            let res = reg.test(val[i].value)
            weightAll += (Number(val[i].value) * 10)
            if (val[i].value == '') {
              this.isCkTipsShow = false
              this.isCkAddTipsShow = false
            } else if (!res) {
              this.isCkTipsShow = true
              break
            } else {
              this.isCkTipsShow = false
            }
            if (val[i].value != '') {
              count++
            }
            if (count == val.length) {
              if (weightAll / 10 != 1) {
                this.isCkAddTipsShow = true
              } else {
                this.isCkAddTipsShow = false
              }
            }
          }
        },
        deep: true
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
      listGraduate().then(response => {
        this.GraduateList = response.data
      })
      this.getDicts('aly_analysis_status').then(response => {
        this.analysisData = response.data
      })

    },
    methods: {
      /** 查询分析项目列表 */
      getList() {
        this.loading = true
        listAlyProject(this.queryParams).then(response => {
          this.alyProjectList = response.rows
          for (let i = 0; i < this.alyProjectList.length; i++) {
            let obj = {
              hasProgress: true,
              projectId: this.alyProjectList[i].projectId,
              num: 0,
              alyState: this.alyProjectList[i].alyState
            }
            this.isProgress.push(obj)
          }
          this.total = response.total
          this.loading = false
          this.updateLoadProgress()
        })
      },
      // 进度条是否显示
      isProgressShow(row) {
        for (let i = 0; i < this.isProgress.length; i++) {
          if (this.isProgress[i].projectId == row.projectId) {
            return this.isProgress[i].hasProgress
          }
        }
      },
      // 进度条进度值
      controllorProgess(row) {
        for (let i = 0; i < this.isProgress.length; i++) {
          if (this.isProgress[i].projectId == row.projectId) {
            return this.isProgress[i].num
          }
        }
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
          projectId: null,
          projectName: null,
          projectCode: null,
          graduateCode: null,
          entranceName: null,
          exitName: null,
          alyState: null,
          caliber: null,
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
        this.ids = selection.map(item => item.projectId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.form.rkExamJson = []
        this.form.ckExamJson = []
        this.lists = []
        this.cklists = []
        this.rkExamId = []
        this.ckExamId = []
        this.open = true
        this.examDataList = []
        this.form.caliber = 1
        this.title = '添加分析项目'
      },
      // 历史考试下拉刷新
      historyUpdate() {
        this.$forceUpdate()
      },
      // 改变届别请求不同考试数据
      changeGraduate(val) {
        this.form.rkExamJson = []
        this.form.ckExamJson = []
        this.form.eachExamIds = []
        this.lists = []
        this.cklists = []
        this.rkExamId = []
        this.ckExamId = []
        let graduateCode = val
        getExamDataById(graduateCode).then(response => {
          this.examDataList = response.data
        })
      },
      // 多选入口考试
      chooseRkExam(val) {
        this.lists = []
        this.form.rkExamJson = []
        for (let i = 0; i < val.length; i++) {
          let obj = {
            eId: val[i],
            name: this.rkExamFormat(val[i]),
            value: ''
          }
          let rkExamJsonObj = {
            examId: val[i],
            weight: ''
          }
          this.lists.push(obj)
          this.form.rkExamJson.push(rkExamJsonObj)
        }
        // console.log(this.form.rkExamJson);
      },
      // 多选入口权重是否为空判断
      isWeightNull() {
        for (let i = 0; i < this.lists.length; i++) {
          if (this.lists[i].value == '') {
            this.isWtNull = true
            break
          } else {
            this.isWtNull = false
          }
        }
      },
      // 多选出口权重是否为空判断
      isCkWeightNull() {
        for (let i = 0; i < this.cklists.length; i++) {
          if (this.cklists[i].value == '') {
            this.isCkWtNull = true
            break
          } else {
            this.isCkWtNull = false
          }
        }
      },
      // 删除入口单项
      removeRkExamItem(val) {
        for (let i = 0; i < this.lists.length; i++) {
          if (this.lists[i].eId == val) {
            this.lists.splice(i, 1)
          }
        }
        for (let k = 0; k < this.rkExamId.length; k++) {
          if (this.rkExamId[k] == val) {
            this.rkExamId.splice(k, 1)
          }
        }
        for (let j = 0; j < this.form.rkExamJson.length; j++) {
          if (this.form.rkExamJson[j].examId == val) {
            this.form.rkExamJson.splice(j, 1)
          }
        }
      },
      // 多选出口考试
      chooseCkExam(val) {
        this.cklists = []
        this.form.ckExamJson = []
        for (let i = 0; i < val.length; i++) {
          let obj = {
            eId: val[i],
            name: this.rkExamFormat(val[i]),
            value: ''
          }
          let ckExamJsonObj = {
            examId: val[i],
            weight: ''
          }
          this.cklists.push(obj)
          this.form.ckExamJson.push(ckExamJsonObj)
        }
        // console.log(this.form.rkExamJson);
      },
      // 删除出口单项
      removeCkExamItem(val) {
        for (let i = 0; i < this.cklists.length; i++) {
          if (this.cklists[i].eId == val) {
            this.cklists.splice(i, 1)
          }
        }
        for (let k = 0; k < this.ckExamId.length; k++) {
          if (this.ckExamId[k] == val) {
            this.ckExamId.splice(k, 1)
          }
        }
        for (let j = 0; j < this.form.ckExamJson.length; j++) {
          if (this.form.ckExamJson[j].examId == val) {
            this.form.ckExamJson.splice(j, 1)
          }
        }
      },
      // 入口权重值导入form参数里
      rkWeightAddForm() {
        for (let i = 0; i < this.form.rkExamJson.length; i++) {
          for (let k = 0; k < this.lists.length; k++) {
            if (this.form.rkExamJson[i].examId == this.lists[k].eId) {
              this.form.rkExamJson[i].weight = this.lists[k].value
            }
          }
        }
      },
      // 出口权重值导入form参数里
      ckWeightAddForm() {
        for (let i = 0; i < this.form.ckExamJson.length; i++) {
          for (let k = 0; k < this.cklists.length; k++) {
            if (this.form.ckExamJson[i].examId == this.cklists[k].eId) {
              this.form.ckExamJson[i].weight = this.cklists[k].value
            }
          }
        }
      },
      // 分析按钮
      async prAnalysis(row) {
        const projectId = row.projectId || this.ids
        // 调用分析接口
        await projectAnalysis(projectId)
        if (row.projectId) {
          // 控制进度条出现
          for (let i = 0; i < this.isProgress.length; i++) {
            if (this.isProgress[i].projectId == projectId) {
              this.isProgress[i].hasProgress = false
            }
          }
          let res = await listAlyProject(this.queryParams)
          // 修改状态
          for (let j = 0; j < res.rows.length; j++) {
            if (res.rows[j].projectId == projectId) {
              for (let k = 0; k < this.alyProjectList.length; k++) {
                if (this.alyProjectList[k].projectId == projectId) {
                  this.alyProjectList[k].alyState = res.rows[j].alyState
                }
              }
            }
          }
          // 控制进度条
          var timer = setInterval(() => {
            var progressnuw = 0
            getAnalysisProgress(projectId, progressnuw).then(result => {
              progressnuw = result.data.actualVal
              // 控制进度条进度
              for (let i = 0; i < this.isProgress.length; i++) {
                if (this.isProgress[i].projectId == projectId) {
                  this.isProgress[i].num = progressnuw
                }
              }
              if (progressnuw >= 100) {
                clearInterval(timer)
                // 控制进度条隐藏
                for (let i = 0; i < this.isProgress.length; i++) {
                  if (this.isProgress[i].projectId == projectId) {
                    this.isProgress[i].hasProgress = true
                  }
                }
                // 进度条值重新归零
                for (let i = 0; i < this.isProgress.length; i++) {
                  if (this.isProgress[i].projectId == projectId) {
                    this.isProgress[i].num = 0
                  }
                }
                // 页面状态值重新赋值
                listAlyProject(this.queryParams).then(updateRes => {
                  // 修改状态
                  for (let j = 0; j < updateRes.rows.length; j++) {
                    if (updateRes.rows[j].projectId == projectId) {
                      for (let k = 0; k < this.alyProjectList.length; k++) {
                        if (this.alyProjectList[k].projectId == projectId) {
                          this.alyProjectList[k].alyState = updateRes.rows[j].alyState
                        }
                      }
                    }
                  }
                  this.$message({
                    message: '分析完成',
                    type: 'success'
                  })
                })
              }
            })
          }, 2000)
        } else if (this.ids.length != 0) {
          // 控制进度条出现
          for (let i = 0; i < this.isProgress.length; i++) {
            for (let o = 0; o < projectId.length; o++) {
              if (this.isProgress[i].projectId == projectId[o]) {
                this.isProgress[i].hasProgress = false
              }
            }
          }
          let res = await listAlyProject(this.queryParams)
          // 修改状态
          for (let j = 0; j < res.rows.length; j++) {
            for (let l = 0; l < projectId.length; l++) {
              if (res.rows[j].projectId == projectId[l]) {
                for (let k = 0; k < this.alyProjectList.length; k++) {
                  if (this.alyProjectList[k].projectId == projectId[l]) {
                    this.alyProjectList[k].alyState = res.rows[j].alyState
                  }
                }
              }
            }
          }
          // 控制进度条
          var timer = setInterval(() => {
            for (let o = 0; o < projectId.length; o++) {
              var progressnuw = 0
              getAnalysisProgress(projectId[o], progressnuw).then(result => {
                progressnuw = result.data.actualVal
                // 控制进度条进度
                for (let i = 0; i < this.isProgress.length; i++) {
                  if (this.isProgress[i].projectId == projectId[o]) {
                    this.isProgress[i].num = progressnuw
                  }
                }
                if (progressnuw >= 100) {
                  clearInterval(timer)
                  // 控制进度条隐藏
                  for (let i = 0; i < this.isProgress.length; i++) {
                    if (this.isProgress[i].projectId == projectId[o]) {
                      this.isProgress[i].hasProgress = true
                    }
                  }
                  // 进度条值重新归零
                  for (let i = 0; i < this.isProgress.length; i++) {
                    if (this.isProgress[i].projectId == projectId[o]) {
                      this.isProgress[i].num = 0
                    }
                  }
                  // 页面状态值重新赋值
                  listAlyProject(this.queryParams).then(updateRes => {
                    // 修改状态
                    for (let j = 0; j < updateRes.rows.length; j++) {
                      if (updateRes.rows[j].projectId == projectId[o]) {
                        for (let k = 0; k < this.alyProjectList.length; k++) {
                          if (this.alyProjectList[k].projectId == projectId[o]) {
                            this.alyProjectList[k].alyState = updateRes.rows[j].alyState
                          }
                        }
                      }
                    }
                    this.$message({
                      message: '分析完成',
                      type: 'success'
                    })
                  })
                }
              })
            }
          }, 2000)
        }
      },
      // 刷新页面如果正在分析，继续出现进度条
      updateLoadProgress() {
        // 控制进度条
        var timer = setInterval(() => {
          console.log(111)
          for (let o = 0; o < this.isProgress.length; o++) {
            // console.log(this.isProgress[o].alyState);
            if (this.isProgress[o].alyState == 2) {
              // 控制进度条出现
              this.isProgress[o].hasProgress = false
              var progressnuw = 0
              getAnalysisProgress(this.isProgress[o].projectId, progressnuw).then(result => {
                progressnuw = result.data.actualVal
                // 控制进度条进度
                this.isProgress[o].num = progressnuw
                if (progressnuw >= 100) {
                  // 控制进度条隐藏
                  this.isProgress[o].hasProgress = true
                  // 进度条值重新归零
                  this.isProgress[o].num = 0
                  // 页面状态值重新赋值
                  listAlyProject(this.queryParams).then(updateRes => {
                    // 修改状态
                    for (let j = 0; j < updateRes.rows.length; j++) {
                      for (let k = 0; k < this.alyProjectList.length; k++) {
                        if (this.alyProjectList[k].projectId == this.isProgress[o].projectId) {
                          this.alyProjectList[k].alyState = updateRes.rows[j].alyState
                          this.isProgress[o].alyState = updateRes.rows[j].alyState
                        }
                        if (this.isProgress[o].alyState == 1) {
                          clearInterval(timer)
                        }
                      }
                    }
                    this.$message({
                      message: '分析完成',
                      type: 'success'
                    })
                  })
                }
              })
            }
          }
          // for (let i = 0; i < this.isProgress.length; i++) {
          //   if (this.isProgress[i].alyState == 1) {
          //     clearInterval(timer);
          //   }
          // }
        }, 2000)
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset()
        this.examDataList = []
        this.form.rkExamJson = []
        this.form.ckExamJson = []
        this.lists = []
        this.cklists = []
        this.rkExamId = []
        this.ckExamId = []
        const projectId = row.projectId || this.ids
        getAlyProject(projectId).then(response => {
          // console.log(response.data);
          this.form = response.data
          this.form.eachExamIds = []
          this.form.rkExamJson = []
          this.form.ckExamJson = []
          getExamDataById(response.data.graduateCode).then(res => {
            this.examDataList = res.data
            // console.log(res.data);
            let rkExamUpdate = []
            let ckExamUpdate = []
            let histroyExamUpdate = []
            for (let i = 0; i < response.data.alyProjectDetails.length; i++) {
              if (response.data.alyProjectDetails[i].projectDetailType == 1) {
                rkExamUpdate.push(response.data.alyProjectDetails[i])
              }
              if (response.data.alyProjectDetails[i].projectDetailType == 2) {
                ckExamUpdate.push(response.data.alyProjectDetails[i])
              }
              if (response.data.alyProjectDetails[i].projectDetailType == 3) {
                histroyExamUpdate.push(response.data.alyProjectDetails[i])
              }
            }
            // console.log(rkExamUpdate, ckExamUpdate, histroyExamUpdate);
            for (let k = 0; k < rkExamUpdate.length; k++) {
              let obj = {
                eId: rkExamUpdate[k].examId,
                name: this.rkExamFormat(rkExamUpdate[k].examId),
                value: rkExamUpdate[k].weight
              }
              let rkExamJsonObj = {
                examId: rkExamUpdate[k].examId,
                weight: rkExamUpdate[k].weight
              }
              this.form.rkExamJson.push(rkExamJsonObj)
              this.rkExamId.push(Number(rkExamUpdate[k].examId))
              this.lists.push(obj)
            }
            for (let j = 0; j < ckExamUpdate.length; j++) {
              let obj = {
                eId: ckExamUpdate[j].examId,
                name: this.rkExamFormat(ckExamUpdate[j].examId),
                value: ckExamUpdate[j].weight
              }
              let ckExamJsonObj = {
                examId: ckExamUpdate[j].examId,
                weight: ckExamUpdate[j].weight
              }
              this.form.ckExamJson.push(ckExamJsonObj)
              this.ckExamId.push(Number(ckExamUpdate[j].examId))
              this.cklists.push(obj)
            }
            for (let o = 0; o < histroyExamUpdate.length; o++) {
              this.form.eachExamIds.push(Number(histroyExamUpdate[o].examId))
            }
          })
          this.open = true
          this.title = '修改分析项目'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.isWeightNull()
        this.isCkWeightNull()
        this.$refs['form'].validate(valid => {
          if (valid) {
            if (
              this.isTipsShow ||
              this.isAddTipsShow ||
              this.isWtNull ||
              this.isCkTipsShow ||
              this.isCkAddTipsShow ||
              this.isCkWtNull
            ) {
              console.log(111)
            } else if (this.form.projectId != null) {
              this.rkWeightAddForm()
              this.ckWeightAddForm()
              console.log(this.form)
              updateAlyProject(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              })
            } else {
              this.rkWeightAddForm()
              this.ckWeightAddForm()
              console.log(this.form)
              // console.log(this.form.rkExamJson);
              addAlyProject(this.form).then(response => {
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
        const projectIds = row.projectId || this.ids
        this.$confirm('是否确认删除分析项目编号为"' + projectIds + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delAlyProject(projectIds)
        }).then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有分析项目数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return exportAlyProject(queryParams)
        }).then(response => {
          this.download(response.msg)
        })
      },
      //届别翻译
      graduateFormat(row, column) {
        var datas = this.GraduateList
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].graduateCode == row.graduateCode) {
            return datas[i].graduateName
          }
        }
        return '无'
      },
      // 分析状态翻译
      analysisFormat(row, column) {
        var datas = this.analysisData
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].dictValue == row.alyState) {
            return datas[i].dictLabel
          }
        }
        return '无'
      },
      // 入口考试翻译
      rkExamFormat(val) {
        var datas = this.examDataList
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].examId == val) {
            return datas[i].examName
          }
        }
        return '无'
      }
    }
  }
</script>
<style lang="scss">
  .rukouWeight {
    margin-left: 80px;
    margin-bottom: 22px;
    position: relative;

  .weightItem {
    margin-bottom: 5px;

  .weightName {
    text-align: center;
    width: 125px;
  }

  .weightVal {
    outline: none;
    text-align: center;
    width: 125px;
  }

  .delExamItem {
    margin-left: 5px;
    cursor: pointer;
  }

  input {
    border: 1px solid rgb(201, 198, 198);
  }

  input::-webkit-input-placeholder {
    color: rgb(204, 197, 197);
  }

  input::-moz-placeholder {
    /* Mozilla Firefox 19+ */
    color: rgb(204, 197, 197);
  }

  input:-moz-placeholder {
    /* Mozilla Firefox 4 to 18 */
    color: rgb(204, 197, 197);
  }

  input:-ms-input-placeholder {
    /* Internet Explorer 10-11 */
    color: rgb(204, 197, 197);
  }

  }
  .weightTips {
    position: absolute;
    bottom: -20px;

  .weightNumTips {
    color: #ff4949;
    font-size: 12px;
    margin-right: 5px;
  }

  }
  }
</style>
