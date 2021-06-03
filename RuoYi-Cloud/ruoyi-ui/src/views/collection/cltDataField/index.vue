<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="数据类型" prop="examDataTypeCode">
        <el-select
          v-model="queryParams.dataTypeCode"
          @change="searchList"
          placeholder="请选择数据类型"
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
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增
        </el-button>
        <!-- v-hasPermi="['collection:cltDataField:add']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          >修改
        </el-button>
        <!-- v-hasPermi="['collection:cltDataField:edit']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除
        </el-button>
        <!-- v-hasPermi="['collection:cltDataField:remove']" -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          >导出
        </el-button>
        <!-- v-hasPermi="['collection:cltDataField:export']" -->
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="cltDataFieldList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        v-if="hiddenKey"
        label="主键"
        align="center"
        prop="dataFieldId"
      />
      <!-- <el-table-column label="数据类型" align="center" prop="dataTypeCode" /> -->
      <el-table-column label="对应实体" align="center" prop="fieldName" />
      <el-table-column label="显示名称" align="center" prop="dataFieldName" />
      <el-table-column label="文件对应名称" align="center" prop="defaultName" />
      <el-table-column
        label="是否扩展字段"
        align="center"
        prop="isExtend"
        :formatter="isExtendFormat"
      />
      <el-table-column
        label="是否必须"
        align="center"
        prop="isNeed"
        :formatter="isNeedFormat"
      />
      <el-table-column
        v-if="hiddenKey"
        label="对应码表"
        align="center"
        prop="baseTypeCode"
      />
      <el-table-column label="排序" align="center" prop="orderNum" />
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
          </el-button>
          <!-- v-hasPermi="['collection:cltDataField:edit']" -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >删除
          </el-button>
          <!-- v-hasPermi="['collection:cltDataField:remove']" -->
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

    <!-- 添加或修改数据采集类型对话框 -->
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
              label-width="100px "
              label="数据类型"
              prop="examTypeCode"
            >
              <el-select
                v-model="form.dataTypeCode"
                disabled
                placeholder="请输入数据类型"
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
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px "
              label="对应实体"
              prop="fieldName"
            >
              <el-input v-model="form.fieldName" placeholder="请输入对应实体" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px "
              label="显示名称"
              prop="dataFieldName"
            >
              <el-input
                v-model="form.dataFieldName"
                placeholder="请输入显示名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="100px "
              label="文件对应名称"
              prop="defaultName"
            >
              <el-input
                v-model="form.defaultName"
                placeholder="请输入文件对应名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="是否扩展字段"
              prop="isExtend"
            >
              <el-switch
                v-model="form.isExtend"
                active-value="0"
                active-text="开启"
                inactive-text="关闭"
                inactive-value="1"
                @change="changeVal"
              >
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="100px" label="是否必须" prop="isNeed">
              <el-switch
                v-model="form.isNeed"
                active-value="0"
                active-text="开启"
                inactive-text="关闭"
                inactive-value="1"
                @change="changeIsNeedVal"
              >
              </el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label-width="100px"
              label="对应码表"
              prop="baseTypeCode"
            >
              <el-input
                v-model="form.baseTypeCode"
                placeholder="请输入对应码表"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label-width="100px" label="排序" prop="orderNum">
              <el-input v-model="form.orderNum" placeholder="请输入排序" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label-width="100px" label="备注" prop="remark">
              <el-input
                v-model="form.remark"
                type="textarea"
                placeholder="请输入内容"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="parseInt(dict.dictValue)"
                  >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
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
  addCltDataField,
  delCltDataField,
  exportCltDataField,
  getCltDataField,
  listCltDataField,
  updateCltDataField,
  chuCltDatafieldToExam
} from "@/api/collection/cltDataField";

export default {
  name: "cltDataType",
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
      // 数据采集类型表格数据
      cltDataFieldList: [],
      // 初始化数据类型
      chushiExamType: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否扩展字段字典
      isExtendOptions: [],
      // 是否必须字典
      isNeedOptions: [],
      // 状态字典
      statusOptions: [],
      // 删除标记字典
      delFlagOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dataTypeCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getDicts("report_switch").then(response => {
      this.isExtendOptions = response.data;
      this.isNeedOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_del_flag").then(response => {
      this.delFlagOptions = response.data;
    });
    chuCltDatafieldToExam().then(response => {
      this.chushiExamType = response.data
      this.queryParams.dataTypeCode = this.$route.params && this.$route.params.key;
      this.getList();
    });
  },
  // watch: {
  //   $route: {
  //     handler(newRouder) {
  //       // this.searchKey = null
  //       this.queryParams.dataTypeCode = newRouder.query.data
  //       this.getList()
  //     },
  //     // 正常情况下，watch只有值发生变化的时候才会调用
  //     // 一上来会调用一次
  //     immediate: true,
  //   }
  // },
  methods: {
    /** 查询数据采集类型列表 */
    getList() {
      this.loading = true;
      listCltDataField(this.queryParams).then(response => {
        this.cltDataFieldList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否扩展字段字典翻译
    isExtendFormat(row, column) {
      return this.selectDictLabel(this.isExtendOptions, row.isExtend);
    },
    // 是否必须字典翻译
    isNeedFormat(row, column) {
      return this.selectDictLabel(this.isNeedOptions, row.isNeed);
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
        dataFieldId: null,
        dataTypeCode: null,
        fieldName: null,
        dataFieldName: null,
        defaultName: null,
        isExtend: null,
        isNeed: null,
        baseTypeCode: null,
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
    // 下拉搜索
    searchList() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.dataFieldId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.changeVal()
      this.changeIsNeedVal()
      this.form.dataTypeCode = this.queryParams.dataTypeCode
      // this.form.examId = this.$route.query.key
      this.open = true;
      this.title = "添加数据采集类型";
    },
    // 修改开关状态
    changeVal(val) {
      if (val == undefined) {
        this.form.isExtend = "1"
      } else {
        this.form.isExtend = val
      }
    },
    changeIsNeedVal(val) {
      if (val == undefined) {
        this.form.isNeed = "1"
      } else {
        this.form.isNeed = val
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dataFieldId = row.dataFieldId || this.ids
      getCltDataField(dataFieldId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改数据采集类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dataFieldId != null) {
            updateCltDataField(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCltDataField(this.form).then(response => {
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
      const dataFieldIds = row.dataFieldId || this.ids;
      this.$confirm('是否确认删除数据采集类型编号为"' + dataFieldIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCltDataField(dataFieldIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据采集类型数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportCltDataField(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
