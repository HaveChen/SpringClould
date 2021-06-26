<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="名称" prop="gradeName">
        <el-input
          v-model="queryParams.gradeName"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代码" prop="gradeCode">
        <el-input
          v-model="queryParams.gradeCode"
          placeholder="请输入代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学段" prop="studyLevelCode">
        <el-select v-model="queryParams.studyLevelCode" placeholder="请输入学段">
          <el-option
            v-for="item in studyLevels"
            :key="item.studyLevelCode"
            :label="item.studyLevelName"
            :value="item.studyLevelCode"
            :disabled="item.status == 1"
          ></el-option>
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
          v-hasPermi="['standard:stdGrade:add']"
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
          v-hasPermi="['standard:stdGrade:edit']"
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
          v-hasPermi="['standard:stdGrade:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download" plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['standard:stdGrade:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stdGradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column v-if="hiddenKey" label="主键" align="center" prop="gradeId"/>
      <el-table-column label="名称" align="center" prop="gradeName"/>
      <el-table-column label="代码" align="center" prop="gradeCode"/>
      <el-table-column label="学段" align="center" prop="studyLevelCode"
                       :formatter="studyLevelFormat"/>
      <el-table-column label="排序" align="center" prop="orderNum"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit" plain
            @click="handleUpdate(scope.row)"
            v-hasPermi="['standard:stdGrade:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete" plain
            @click="handleDelete(scope.row)"
            v-hasPermi="['standard:stdGrade:remove']"
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

    <!-- 添加或修改年级对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="gradeName">
          <el-input v-model="form.gradeName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="代码" prop="gradeCode">
          <el-input v-model="form.gradeCode" placeholder="请输入代码"/>
        </el-form-item>
        <el-form-item label="学段" prop="studyLevelCode">
          <el-select v-model="form.studyLevelCode" placeholder="请输入学段">
            <el-option
              v-for="item in studyLevels"
              :key="item.studyLevelCode"
              :label="item.studyLevelName"
              :value="item.studyLevelCode"
              :disabled="item.status == 1"
            ></el-option>
          </el-select>
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
    addStdGrade,
    delStdGrade,
    exportStdGrade,
    getStdGrade,
    listFiltrateData,
    listStdGrade,
    updateStdGrade
  } from "@/api/standard/stdGrade";

  export default {
    name: "StdGrade",
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
        // 年级表格数据
        stdGradeList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 隐藏主键
        hiddenKey: false,
        // 状态字典
        statusOptions: [],
        // 删除标记字典
        delFlagOptions: [],
        studyLevels: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          gradeName: null,
          gradeCode: null,
          studyLevelCode: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          gradeName: [
            {required: true, message: "名称不能为空", trigger: "blur"}
          ],
          gradeCode: [
            {required: true, message: "代码不能为空", trigger: "blur"}
          ],
          studyLevelCode: [
            {required: true, message: "学段不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("sys_del_flag").then(response => {
        this.delFlagOptions = response.data;
      });
      listFiltrateData().then(response => {
        this.studyLevels = response.data;
      });
    },
    methods: {
      /** 查询年级列表 */
      getList() {
        this.loading = true;
        listStdGrade(this.queryParams).then(response => {
          this.stdGradeList = response.rows;
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
      studyLevelFormat(row, column) {
        var datas = this.studyLevels;
        for (let i = 0; i < datas.length; i++) {
          if (datas[i].studyLevelCode == row.studyLevelCode) {
            return datas[i].studyLevelName;
          }
        }
        return "无"
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          gradeId: null,
          gradeName: null,
          gradeCode: null,
          studyLevelCode: null,
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
        this.ids = selection.map(item => item.gradeId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加年级";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const gradeId = row.gradeId || this.ids
        getStdGrade(gradeId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改年级";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.gradeId != null) {
              updateStdGrade(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addStdGrade(this.form).then(response => {
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
        const gradeIds = row.gradeId || this.ids;
        this.$confirm('是否确认删除年级编号为"' + gradeIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delStdGrade(gradeIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有年级数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportStdGrade(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
