<template>
  <div class="app-container">


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:user:remove']"
        >删除</el-button>
      </el-col>


      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sortList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类别编号" align="center" key="sortId" prop="sortId" v-if="columns[0].visible" width="120"/>
      <el-table-column label="类别名称" align="center" key="sortName" prop="refuseType" v-if="columns[1].visible" :show-overflow-tooltip="true" />
      <el-table-column label="描述" align="center" key="description" prop="description" v-if="columns[2].visible" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[3].visible" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="160"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope" v-if="scope.row.userId !== 1">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:user:remove']"
          >删除</el-button>

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

  </div>
</template>

<script>
import {list, getUser, delUser} from "@/api/monitor/sort";

export default {
  name: "index",
  data() {
    return {
      ids: [],
      sortList: null,
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      total: 0,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        refuseType: undefined,
      },

      // 列信息
      columns: [
        { key: 0, label: `类别编号`, visible: true },
        { key: 1, label: `类别名称`, visible: true },
        { key: 2, label: `描述`, visible: true },
        { key: 3, label: `创建时间`, visible: true },
      ],
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询垃圾类别列表 */
    getList() {
      this.loading = true;
      list(this.queryParams).then(response => {
        console.log(response.rows)
          this.sortList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 新增按钮操作 */
    handleAdd() {
      getUser().then(response => {
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.title = "添加用户";
        this.form.password = this.initPassword;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const userId = row.userId || this.ids;
      getUser(userId).then(response => {
        this.form = response.data;
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.$set(this.form, "postIds", response.postIds);
        this.$set(this.form, "roleIds", response.roleIds);
        this.open = true;
        this.title = "修改用户";
        this.form.password = "";
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
  }
}
</script>

<style scoped>

</style>
