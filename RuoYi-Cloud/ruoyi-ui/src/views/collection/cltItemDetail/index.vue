<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item v-if="hiddenExamKey" label="考试主键" prop="examId">
        <el-input
          v-model="queryParams.examId"
          placeholder="请输入考试主键"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试卷名称" prop="testpaperId">
        <el-select
          @change="handleQuery"
          v-model="queryParams.testpaperId"
          placeholder="请选择试卷"
        >
          <el-option
            v-for="item in listTestpaperItem"
            :key="item.testpaperId"
            :label="item.testpaperName"
            :value="item.testpaperId"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['collection:cltItemDetail:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['collection:cltItemDetail:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['collection:cltItemDetail:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['collection:cltItemDetail:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="cltItemDetailList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="itemId"
      />
      <el-table-column
        v-if="hiddenKey"
        label="考试主键"
        align="center"
        prop="examId"
      />
      <el-table-column
        v-if="hiddenKey"
        label="试卷主键"
        align="center"
        prop="testpaperId"
      />
      <el-table-column label="给分点" align="center" prop="scorePoint" />
      <el-table-column label="小题号" align="center" prop="littleTitleNo" />
      <el-table-column label="大题号" align="center" prop="bigTitleNo" />
      <el-table-column
        label="给分点类型"
        align="center"
        prop="scorePointType"
      />
      <el-table-column label="满分" align="center" prop="fullscore" />
      <el-table-column label="全部选项" align="center" prop="allOptions" />
      <el-table-column label="正确选项" align="center" prop="rightOptioin" />
      <el-table-column
        label="预测难度"
        align="center"
        prop="forecastDifficulty"
      />
      <el-table-column label="是否选做题" align="center" prop="isChoice" />
      <el-table-column label="选做题组 " align="center" prop="choiceGroup" />
      <el-table-column label="选做题规则" align="center" prop="choiceRule" />
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
            v-hasPermi="['collection:cltItemDetail:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['collection:cltItemDetail:remove']"
            >删除</el-button
          >
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

    <!-- 添加或修改细目信息采集对话框 -->
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
            <el-form-item
              label-width="100px"
              v-if="hiddenAddKey"
              label="考试主键"
              prop="examId"
            >
              <el-input v-model="form.examId" placeholder="请输入考试主键" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              v-if="hiddenAddKey"
              label="试卷主键"
              prop="testpaperId"
            >
              <el-input
                v-model="form.testpaperId"
                placeholder="请输入试卷主键"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="科目代码"
              prop="subjectCode"
            >
              <el-input
                v-model="form.subjectCode"
                placeholder="请输入科目代码"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="100px" label="给分点" prop="scorePoint">
              <el-input v-model="form.scorePoint" placeholder="请输入给分点" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="小题号"
              prop="littleTitleNo"
            >
              <el-input
                v-model="form.littleTitleNo"
                placeholder="请输入小题号"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="100px" label="大题号" prop="bigTitleNo">
              <el-input v-model="form.bigTitleNo" placeholder="请输入大题号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="给分点类型"
              prop="scorePointType"
            >
              <el-select
                v-model="form.scorePointType"
                placeholder="请选择给分点类型"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="100px" label="满分" prop="fullscore">
              <el-input v-model="form.fullscore" placeholder="请输入满分" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="全部选项"
              prop="allOptions"
            >
              <el-input
                v-model="form.allOptions"
                placeholder="请输入全部选项"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="正确选项"
              prop="rightOptioin"
            >
              <el-input
                v-model="form.rightOptioin"
                placeholder="请输入正确选项"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="预测难度"
              prop="forecastDifficulty"
            >
              <el-input
                v-model="form.forecastDifficulty"
                placeholder="请输入预测难度"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="是否选做题"
              prop="isChoice"
            >
              <el-input
                v-model="form.isChoice"
                placeholder="请输入是否选做题"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="选做题组 "
              prop="choiceGroup"
            >
              <el-input
                v-model="form.choiceGroup"
                placeholder="请输入选做题组 "
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="选做题规则"
              prop="choiceRule"
            >
              <el-input
                v-model="form.choiceRule"
                placeholder="请输入选做题规则"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="成绩对应字段"
              prop="cjField"
            >
              <el-input
                v-model="form.cjField"
                placeholder="请输入成绩对应字段"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="扩展字段"
              prop="extensions"
            >
              <el-input
                v-model="form.extensions"
                placeholder="请输入扩展字段"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label-width="100px" label="排序" prop="orderNum">
              <el-input v-model="form.orderNum" placeholder="请输入排序" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="100px" label="备注" prop="remark">
              <el-input
                v-model="form.remark"
                type="textarea"
                placeholder="请输入内容"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
              >{{ dict.dictLabel }}</el-radio
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
  listCltItemDetail,
  getCltItemDetail,
  delCltItemDetail,
  addCltItemDetail,
  updateCltItemDetail,
  exportCltItemDetail,
  listTestpaper
} from "@/api/collection/cltItemDetail";

export default {
  name: "CltItemDetail",
  components: {
  },
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
      // 隐藏考试主键搜索条件
      hiddenExamKey: false,
      // 隐藏新增主键
      hiddenAddKey: false,
      // 细目信息采集表格数据
      cltItemDetailList: [],
      // 初始化试卷下拉数据
      listTestpaperItem: [],
      // 弹出层标题
      title: "",
      // 传过来的考试主键
      key: null,
      // 传过来的试卷主键
      testId: null,
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
        testpaperId: null,
        subjectCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.queryParams.examId = this.$route.params && this.$route.params.key;
    // this.queryParams.testpaperId = this.$route.params && this.$route.params.testId;
    this.key = this.$route.params && this.$route.params.key;
    this.testId = this.$route.params && this.$route.params.testId;
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_del_flag").then(response => {
      this.delFlagOptions = response.data;
    });
    listTestpaper(this.key).then(response => {
      this.listTestpaperItem = response.data
      for (let i = 0; i < response.data.length; i++) {
        if (this.testId == response.data[i].testpaperId) {
          this.queryParams.testpaperId = response.data[i].testpaperId
        }
      }
      this.getList();
    });
  },
  methods: {
    /** 查询细目信息采集列表 */
    getList() {
      this.loading = true;
      listCltItemDetail(this.queryParams).then(response => {
        this.cltItemDetailList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 删除标记字典翻译
    delFlagFormat(row, column) {
      return this.selectDictLabel(this.delFlagOptions, row.delFlag);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        itemId: null,
        examId: null,
        testpaperId: null,
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
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.itemId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.examId = this.key
      this.form.testpaperId = this.testId
      this.open = true;
      this.title = "添加细目信息采集";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const itemId = row.itemId || this.ids
      getCltItemDetail(itemId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改细目信息采集";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.itemId != null) {
            updateCltItemDetail(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCltItemDetail(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const itemIds = row.itemId || this.ids;
      this.$confirm('是否确认删除细目信息采集编号为"' + itemIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCltItemDetail(itemIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有细目信息采集数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportCltItemDetail(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
