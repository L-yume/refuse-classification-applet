<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="视频标题" prop="roleName">
        <el-input
          v-model="queryParams.roleName"
          placeholder="请输入视频标题"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['system:role:add']"
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
          v-hasPermi="['system:role:edit']"
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
          v-hasPermi="['system:role:remove']"
        >删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="videoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="视频编号" prop="videoId" width="120" />
      <el-table-column label="视频标题" prop="name" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="时长" prop="duration" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="视频大小" prop="videoSize" width="100" />
      <el-table-column label="视频" align="center" width="100">
        <template slot-scope="scope">
          <el-button
            type="success"
            size="small"
            icon="el-icon-caret-left"
            round
            @click="playVideo(scope.row.title, scope.row.path)"
          >播放
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="status" width="150">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:role:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:role:remove']"
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

    <el-dialog :title="videoTitle" :visible.sync="openVideo" width="500px" append-to-body>
      <video
        :src="videoPath"
        controls
        autoplay
        ref="dialogVideo"
        width="100%"
      />
    </el-dialog>

    <!-- 添加或修改视频对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="视频标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入视频标题" />
        </el-form-item>

        <el-form-item label="视频上传" prop="path">
          <!-- action必选参数, 上传的地址 -->
          <el-upload
            class="avatar-uploader el-upload--text"
            :action="uploadUrl"
            :headers="{Authorization: getToken()}"
            :show-file-list=false
            :on-success="handleVideoSuccess"
            :before-upload="beforeUploadVideo"
            :on-progress="uploadVideoProcess"
          >
            <video
              v-if="form.path != '' && videoFlag == false"
              :src="form.path"
              class="avatar"
              controls="controls"
              width="300px"
              ref="videoRef"
            >
              您的浏览器不支持视频播放
            </video>
            <i
              v-else-if="form.path == '' && videoFlag == false"
              class="el-icon-plus avatar-uploader-icon"
            ></i>
            <el-progress
              v-if="videoFlag == true"
              type="circle"
              :percentage="videoUploadPercent"
              style="margin-top:30px;"
            ></el-progress>
          </el-upload>
          <el-button @click="handleRemoveSkuResource(editVideoSkuForm.videoUrl,'add')">删除</el-button>
        </el-form-item>

        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
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
import { getToken } from '@/utils/auth'
import { listRole, getRole, delRole, addRole, updateRole, dataScope, changeRoleStatus, deptTreeSelect } from "@/api/system/role";
import {listVideo, addVideo, delVideo, uploadUrl} from "@/api/monitor/video";

export default {
  name: "Video",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      uploadUrl,

      videoFlag: false,
      videoUploadPercent: 0,
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
      // 角色表格数据
      videoList: [],
      videoPath: "",
      // 弹出层标题
      title: "",
      videoTitle: "",
      // 是否显示弹出层
      open: false,
      openVideo: false,

      // 日期范围
      dateRange: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleName: undefined,
        roleKey: undefined,
        status: undefined
      },
      // 表单参数
      form: {
        title: '',
        name: '',
        path: '',
        duration: 0,
        videoSize: '',
        vidoeFormat: '',
        remark: ''
      },

      // 表单校验
      rules: {
        title: [
          { required: true, message: "视频标题不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getToken,
    /** 查询视频列表 */
    getList() {
      this.loading = true;
      listVideo(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          console.log(response.rows)
          this.videoList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.roleId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    // 角色状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.roleName + '"角色吗？').then(function() {
        return changeRoleStatus(row.roleId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
    // 验证视频格式
    beforeUploadVideo(file) {
      if (
        [
          'video/mp4',
          'video/ogg',
          'video/flv',
          'video/avi',
          'video/wmv',
          'video/rmvb'
        ].indexOf(file.type) === -1
      ) {
        this.$message.error('请上传正确的视频格式')
        return false
      }
    },
    // 上传进度显示
    uploadVideoProcess(event, file, fileList) {
      console.log(event.percent, file, fileList)
      this.videoFlag = true
      this.videoUploadPercent = Math.floor(event.percent)
    },

    // 获取上传图片地址
    handleVideoSuccess(res, file) {
      console.log('sssssssssssss'+uploadUrl)
      this.videoFlag = false
      this.videoUploadPercent = 0
      console.log('444444444444444'+JSON.stringify(res))
      const resp = JSON.parse(JSON.stringify(res))
      if (resp.code === 0) {
        this.form.path = resp.result.path
        this.form.duration = resp.result.duration
        this.form.videoSize = resp.result.size
        this.form.vidoeFormat = resp.result.format
        this.form.name = resp.result.name
      } else {
        this.$message.error('视频上传失败，请重新上传！')
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 取消按钮（数据权限）
    cancelDataScope() {
      this.openDataScope = false;
      this.reset();
    },
    // 表单重置
    reset() {
      if (this.$refs.menu != undefined) {
        this.$refs.menu.setCheckedKeys([]);
      }
      this.menuExpand = false,
        this.menuNodeAll = false,
        this.deptExpand = true,
        this.deptNodeAll = false,
        this.form = {
          roleId: undefined,
          roleName: undefined,
          roleKey: undefined,
          roleSort: 0,
          status: "0",
          menuIds: [],
          deptIds: [],
          menuCheckStrictly: true,
          deptCheckStrictly: true,
          remark: undefined
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },


    playVideo(title, path) {
      this.videoTitle = title;
      this.videoPath = path;
      this.openVideo = true;
    },



    /** 新增按钮操作 */
    handleAdd() {
      //this.reset();
      //this.getMenuTreeselect();
      this.open = true;
      this.title = "添加角色";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roleId = row.roleId || this.ids
      //const roleMenu = this.getRoleMenuTreeselect(roleId);
      getRole(roleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.$nextTick(() => {
          roleMenu.then(res => {
            let checkedKeys = res.checkedKeys
            checkedKeys.forEach((v) => {
              this.$nextTick(()=>{
                this.$refs.menu.setChecked(v, true ,false);
              })
            })
          });
        });
        this.title = "修改角色";
      });
    },

    /** 分配数据权限操作 */
    handleDataScope(row) {
      this.reset();
      //const deptTreeSelect = this.getDeptTree(row.roleId);
      getRole(row.roleId).then(response => {
        this.form = response.data;
        this.openDataScope = true;
        this.$nextTick(() => {
          deptTreeSelect.then(res => {
            this.$refs.dept.setCheckedKeys(res.checkedKeys);
          });
        });
        this.title = "分配数据权限";
      });
    },
    /** 分配用户操作 */
    handleAuthUser: function(row) {
      const roleId = row.roleId;
      this.$router.push("/system/role-auth/user/" + roleId);
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.videoList.videoId != undefined) {
            updateRole(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVideo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 提交按钮（数据权限） */
    submitDataScope: function() {
      if (this.form.roleId != undefined) {
        //this.form.deptIds = this.getDeptAllCheckedKeys();
        dataScope(this.form).then(response => {
          this.$modal.msgSuccess("修改成功");
          this.openDataScope = false;
          this.getList();
        });
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const videoIds = row.videoId || this.ids;
      this.$modal.confirm('是否确认删除角色编号为"' + videoIds + '"的数据项？').then(function() {
        return delVideo(videoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

  }
};
</script>

<style>
.avatar-uploader-icon {
  border: 1px dashed #d9d9d9 !important;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9 !important;
  border-radius: 6px !important;
  position: relative !important;
  overflow: hidden !important;
}
.avatar-uploader .el-upload:hover {
  border: 1px dashed #d9d9d9 !important;
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 300px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 300px;
  height: 178px;
  display: block;
}
</style>
