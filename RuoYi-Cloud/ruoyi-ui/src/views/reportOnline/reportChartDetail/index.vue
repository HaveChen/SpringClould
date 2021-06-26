<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="关联资源" prop="resourceId">
        <el-input
          v-model="queryParams.resourceId"
          placeholder="请输入关联资源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="head">
        <el-input
          v-model="queryParams.head"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="副标题" prop="subHead">
        <el-input
          v-model="queryParams.subHead"
          placeholder="请输入副标题"
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
          v-hasPermi="['reportOnline:reportChartDetail:add']"
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
          v-hasPermi="['reportOnline:reportChartDetail:edit']"
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
          v-hasPermi="['reportOnline:reportChartDetail:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['reportOnline:reportChartDetail:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportChartDetailList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column v-if="hiddenKey" label="主键" align="center" prop="chartDetailId"/>
      <el-table-column label="关联资源" align="center" prop="resourceId"/>
      <el-table-column label="类型" align="center" prop="chartType"/>
      <el-table-column label="标题" align="center" prop="head"/>
      <el-table-column label="副标题" align="center" prop="subHead"/>
      <el-table-column label="X轴格式化" align="center" prop="xaxesFormat"/>
      <el-table-column label="Y轴对应字段" align="center" prop="yaxesField"/>
      <el-table-column label="Y轴名称" align="center" prop="yaxesName"/>
      <el-table-column label="开启图例" align="center" prop="openLend" :formatter="openLendFormat"/>
      <el-table-column label="图例字段" align="center" prop="legendName"/>
      <el-table-column label="图例位置" align="center" prop="legendPosition"/>
      <el-table-column label="开启toolBox" align="center" prop="openToolbox"
                       :formatter="openToolboxFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reportOnline:reportChartDetail:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['reportOnline:reportChartDetail:remove']"
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

    <!-- 添加或修改图明细对话框 -->
    <el-dialog :close-on-click-modal="this.$store.state.pubCon.isDialogClose" :title="title"
               :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联资源" prop="resourceId">
          <el-input v-model="form.resourceId" placeholder="请输入关联资源"/>
        </el-form-item>
        <el-form-item label="类型" prop="chartType">
          <el-input v-model="form.chartType" placeholder="请输入图形类型"/>
        </el-form-item>
        <el-form-item label="标题" prop="head">
          <el-input v-model="form.head" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="副标题" prop="subHead">
          <el-input v-model="form.subHead" placeholder="请输入副标题"/>
        </el-form-item>
        <el-form-item label="X轴对应字段" prop="xaxesField">
          <el-input v-model="form.xaxesField" placeholder="请输入X轴对应字段"/>
        </el-form-item>
        <el-form-item label="X轴名称" prop="xaxesName">
          <el-input v-model="form.xaxesName" placeholder="请输入X轴名称"/>
        </el-form-item>
        <el-form-item label="X轴格式化" prop="xaxesFormat">
          <el-input v-model="form.xaxesFormat" placeholder="请输入X轴格式化"/>
        </el-form-item>
        <el-form-item label="Y轴对应字段" prop="yaxesField">
          <el-input v-model="form.yaxesField" placeholder="请输入Y轴对应字段"/>
        </el-form-item>
        <el-form-item label="Y轴名称" prop="yaxesName">
          <el-input v-model="form.yaxesName" placeholder="请输入Y轴名称"/>
        </el-form-item>
        <el-form-item label="Y轴格式化" prop="yaxesFormat">
          <el-input v-model="form.yaxesFormat" placeholder="请输入Y轴格式化"/>
        </el-form-item>
        <el-form-item label="Y轴起始值" prop="yaxesMin">
          <el-input v-model="form.yaxesMin" placeholder="请输入Y轴起始值"/>
        </el-form-item>
        <el-form-item label="开启图例">
          <el-radio-group v-model="form.openLend">
            <el-radio
              v-for="dict in openLendOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图例字段" prop="legendName">
          <el-input v-model="form.legendName" placeholder="请输入图例字段"/>
        </el-form-item>
        <el-form-item label="图例位置" prop="legendPosition">
          <el-input v-model="form.legendPosition" placeholder="请输入图例位置"/>
        </el-form-item>
        <el-form-item label="开启toolBox">
          <el-radio-group v-model="form.openToolbox">
            <el-radio
              v-for="dict in openToolboxOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图形方向" prop="chartPosition">
          <el-input v-model="form.chartPosition" placeholder="请输入图形方向"/>
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
    addReportChartDetail,
    delReportChartDetail,
    exportReportChartDetail,
    getReportChartDetail,
    listReportChartDetail,
    updateReportChartDetail
  } from "@/api/reportOnline/reportChartDetail";

  export default {
    name: "ReportChartDetail",
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
        // 图明细表格数据
        reportChartDetailList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 开启图例字典
        openLendOptions: [],
        // 开启toolBox字典
        openToolboxOptions: [],
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          resourceId: null,
          head: null,
          subHead: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          resourceId: [
            {required: true, message: "关联资源不能为空", trigger: "blur"}
          ],
          xaxesField: [
            {required: true, message: "X轴对应字段不能为空", trigger: "blur"}
          ],
          yaxesField: [
            {required: true, message: "Y轴对应字段不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("report_switch").then(response => {
        this.openLendOptions = response.data;
      });
      this.getDicts("report_switch").then(response => {
        this.openToolboxOptions = response.data;
      });
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("sys_del_flag").then(response => {
        this.delFlagOptions = response.data;
      });
    },
    methods: {
      /** 查询图明细列表 */
      getList() {
        this.loading = true;
        listReportChartDetail(this.queryParams).then(response => {
          this.reportChartDetailList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 开启图例字典翻译
      openLendFormat(row, column) {
        return this.selectDictLabel(this.openLendOptions, row.openLend);
      },
      // 开启toolBox字典翻译
      openToolboxFormat(row, column) {
        return this.selectDictLabel(this.openToolboxOptions, row.openToolbox);
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
          chartDetailId: null,
          resourceId: null,
          head: null,
          subHead: null,
          xaxesField: null,
          xaxesName: null,
          xaxesFormat: null,
          yaxesField: null,
          yaxesName: null,
          yaxesFormat: null,
          yaxesMin: null,
          openLend: "0",
          legendName: null,
          legendPosition: null,
          openToolbox: "0",
          chartPosition: null,
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
        this.ids = selection.map(item => item.chartDetailId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加图明细";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const chartDetailId = row.chartDetailId || this.ids
        getReportChartDetail(chartDetailId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改图明细";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.chartDetailId != null) {
              updateReportChartDetail(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addReportChartDetail(this.form).then(response => {
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
        const chartDetailIds = row.chartDetailId || this.ids;
        this.$confirm('是否确认删除图明细编号为"' + chartDetailIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delReportChartDetail(chartDetailIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有图明细数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportReportChartDetail(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
