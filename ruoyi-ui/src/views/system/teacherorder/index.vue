<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单号
" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单号
"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目号
" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目
"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />

      </el-form-item>

      <el-form-item label="货物名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入货物名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="订单状态
" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态
" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:teacherorder:add']"
        >新增
        </el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:teacherorder:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherorderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" prop="id"/>
      <el-table-column label="订单号
" align="center" prop="orderId"/>
      <el-table-column label="项目号
" align="center" prop="projectId"/>
      <el-table-column label="是否进口
" align="center" prop="projectImport" :formatter="projectImportFormat"/>
      <el-table-column label="品目编码
" align="center" prop="itemCode"/>
      <el-table-column label="货物名称" align="center" prop="productName"/>
      <el-table-column label="技术参数" align="center" prop="technicalParams"/>
      <el-table-column label="商品单价
" align="center" prop="productPrice"/>

      <el-table-column label="商品数量
" align="center" prop="projectNum"/>
      <el-table-column label="商品总价
" align="center" prop="totalPrices"/>
      <el-table-column label="归口单位
" align="center" prop="relevantDepartment"/>
      <el-table-column label="订单状态
" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="申请人姓名
" align="center" prop="username"/>
      <el-table-column label="申请人ID
" align="center" prop="userId"/>
      <el-table-column label="邮箱
" align="center" prop="email"/>
      <el-table-column label="归口单位审核人" align="center" prop="relevantAuditor"/>
      <el-table-column label="归口单位审核状态" align="center" prop="relevantStatu" :formatter="relevantStatuFormat"/>
      <el-table-column label="归口单位审核留言" align="center" prop="relevantMessage"/>
      <el-table-column label="财务处审核人" align="center" prop="financeAuditor"/>
      <el-table-column label="财务处审核状态" align="center" prop="financeStatu" :formatter="financeStatuFormat"/>
      <el-table-column label="财务处审核留言" align="center" prop="financeMessage"/>
      <el-table-column label="资产管理处审核人" align="center" prop="procurementAuditor"/>
      <el-table-column label="资产管理处审核状态" align="center" prop="procurementStatu" :formatter="procurementStatuFormat"/>
      <el-table-column label="资产管理处审核留言" align="center" prop="procurementMessage"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!--          操作栏       -->
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

    <!-- 添加或修改教师订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目号
" prop="projectId">
          <el-select v-model="form.projectId" placeholder="请选择项目号
">
            <el-option v-for="item in projectList"
                       :key="item.id"
                       :value="item.id"
                       :label="item.projectName"/>
          </el-select>
        </el-form-item>

        <el-form-item label="是否进口
" prop="projectImport">
          <el-select v-model="form.projectImport" placeholder="请选择是否进口
">
            <el-option
              v-for="dict in projectImportOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="品目编码
" prop="itemCode">
          <el-input v-model="form.itemCode" placeholder="请输入品目编码
"/>
        </el-form-item>
        <el-form-item label="货物名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入货物名称"/>
        </el-form-item>
        <el-form-item label="技术参数" prop="technicalParams">
          <el-input v-model="form.technicalParams" placeholder="请输入技术参数"/>
        </el-form-item>
        <el-form-item label="商品单价
" prop="productPrice">
          <el-input v-model="form.productPrice" placeholder="请输入商品单价
"/>
        </el-form-item>
        <el-form-item label="商品数量
" prop="projectNum">
          <el-input v-model="form.projectNum" placeholder="请输入商品数量
"/>
        </el-form-item>
        <el-form-item label="商品总价
" prop="totalPrices">
          <el-input v-model="form.totalPrices" readonly="true" placeholder="请输入商品总价
"/>
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
    listTeacherorder,
    getTeacherorder,
    delTeacherorder,
    addTeacherorder,
    updateTeacherorder,
    exportTeacherorder
  } from "@/api/system/teacherorder";
  import {listProject, getProject, delProject, addProject, updateProject, exportProject} from "@/api/system/project";

  export default {
    name: "Teacherorder",
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
        // 项目表格数据
        projectList: [],
        // 教师订单管理表格数据
        teacherorderList: [],
        // 教师下拉框选项
        projectList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 是否进口字典
        projectImportOptions: [],
        // 订单状态字典
        statusOptions: [],
        // 归口单位审核状态字典
        relevantStatuOptions: [],
        // 财务处审核状态字典
        financeStatuOptions: [],
        // 资产管理处审核状态字典
        procurementStatuOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          id: null,
          orderId: null,
          projectId: null,
          projectImport: null,
          itemCode: null,
          productName: null,
          technicalParams: null,
          productPrice: null,
          projectNum: null,
          totalPrices: null,
          relevantDepartment: null,
          status: null,
          username: null,
          userId: null,
          email: null,
          relevantAuditor: null,
          relevantStatu: null,
          relevantMessage: null,
          financeAuditor: null,
          financeStatu: null,
          financeMessage: null,
          procurementAuditor: null,
          procurementStatu: null,
          procurementMessage: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          id: [
            {required: true, message: "编号不能为空", trigger: "blur"}
          ],
          orderId: [
            {required: true, message: "订单号不能为空", trigger: "blur"}
          ],
          projectId: [
            {required: true, message: "项目号不能为空", trigger: "change"}
          ],
          projectImport: [
            {required: true, message: "是否进口不能为空", trigger: "change"}
          ],
          itemCode: [
            {required: true, message: "品目编码不能为空", trigger: "blur"}
          ],
          productName: [
            {required: true, message: "货物名称不能为空", trigger: "blur"}
          ],
          technicalParams: [
            {required: true, message: "技术参数不能为空", trigger: "blur"}
          ],
          productPrice: [
            {required: true, message: "商品单价不能为空", trigger: "blur"}
          ],
          projectNum: [
            {required: true, message: "商品数量不能为空", trigger: "blur"}
          ],
          relevantDepartment: [
            {required: true, message: "归口单位不能为空", trigger: "blur"}
          ],
          status: [
            {required: true, message: "订单状态不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getProjectList();
      this.getDicts("sys_import_notimport").then(response => {
        this.projectImportOptions = response.data;
      });
      this.getDicts("sys_order_stuts").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("sys_pass_notpassorder").then(response => {
        this.relevantStatuOptions = response.data;
      });
      this.getDicts("sys_pass_notpassorder").then(response => {
        this.financeStatuOptions = response.data;
      });
      this.getDicts("sys_pass_notpassorder").then(response => {
        this.procurementStatuOptions = response.data;
      });
    },
    methods: {
      /** 查询教师订单管理列表 */
      getList() {
        this.loading = true;
        listTeacherorder(this.queryParams).then(response => {
          this.teacherorderList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      //项目列表
      getProjectList() {
        this.loading = true;

        listProject(this.queryParams).then(response => {
          this.projectList = response.rows;
          this.loading = false;
        });

      },
      // 是否进口字典翻译
      projectImportFormat(row, column) {
        return this.selectDictLabel(this.projectImportOptions, row.projectImport);
      },
      // 订单状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 归口单位审核状态字典翻译
      relevantStatuFormat(row, column) {
        return this.selectDictLabel(this.relevantStatuOptions, row.relevantStatu);
      },
      // 财务处审核状态字典翻译
      financeStatuFormat(row, column) {
        return this.selectDictLabel(this.financeStatuOptions, row.financeStatu);
      },
      // 资产管理处审核状态字典翻译
      procurementStatuFormat(row, column) {
        return this.selectDictLabel(this.procurementStatuOptions, row.procurementStatu);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          orderId: null,
          projectId: null,
          projectImport: null,
          itemCode: null,
          productName: null,
          technicalParams: null,
          productPrice: null,
          projectNum: null,
          totalPrices: null,
          relevantDepartment: null,
          status: "0",
          username: null,
          userId: null,
          email: null,
          relevantAuditor: null,
          relevantStatu: null,
          relevantMessage: null,
          financeAuditor: null,
          financeStatu: null,
          financeMessage: null,
          procurementAuditor: null,
          procurementStatu: null,
          procurementMessage: null,
          createTime: null,
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
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加教师订单管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getTeacherorder(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改教师订单管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateTeacherorder(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addTeacherorder(this.form).then(response => {
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
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除教师订单管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delTeacherorder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有教师订单管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportTeacherorder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
