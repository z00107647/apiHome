<template>
  <div style="margin:35px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="dataForm" @submit.native.prevent>
        <el-form-item>
          <el-input v-model.trim="dataForm.hostName" placeholder="名称" @keyup.enter.native="getDataList"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="getDataList()">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addOrUpdateHandle(0)">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table :data="dataList" highlight-current-row v-loading="dataListLoading" @selection-change="selectionChangeHandle" style="width: 100%;">
      <el-table-column type="selection" min-width="5%">
      </el-table-column>
      <el-table-column prop="hostName" label="名称" min-width="15%" sortable show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="hostIp" label="HOST" min-width="28%" sortable show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="remark" label="描述" min-width="27%" sortable show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="status" label="状态" min-width="10%" sortable>
        <template slot-scope="scope">
          <img v-show="scope.row.status === 1" src="~@/assets/img/icon-yes.svg"/>
          <img v-show="scope.row.status === 0" src="~@/assets/img/icon-no.svg"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="15%">
        <template slot-scope="scope">
          <el-button size="small" @click="addOrUpdateHandle(scope.row.hostId)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteHandle(scope.row.hostId)">删除</el-button>
          <el-button type="info" size="small" @click="handleChangeStatus(scope.row)">{{scope.row.status===1?'禁用':'启用'}}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-button type="danger" @click="deleteHandle()" :disabled="this.dataListSelections.length===0">批量删除</el-button>
      <el-pagination
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle"
        :current-page="pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :total="totalPage"
        layout="total, sizes, prev, pager, next, jumper"
        style="float:right;">
      </el-pagination>
    </el-col>
  </div>
</template>

<script>
  import AddOrUpdate from './host-add-or-update';
  export default {
    data() {
      return {
        dataForm: {
          hostName: ''
        },
        projectId:'',
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    mounted() {
      this.dataForm.projectId = this.$route.params.project_id;
      this.getDataList();
    },
    methods: {
      // 获取HOST列表
      getDataList() {
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('/apiTest/host/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'hostName': this.dataForm.hostName
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list;
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = [];
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val;
        this.pageIndex = 1;
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val;
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (hostId) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(this.$route.params.project_id, hostId)
        })
      },
      // 删除
      deleteHandle (hostId) {
        var hostIds = hostId ? [hostId] : this.dataListSelections.map(item => {
          return item.hostId
        });
        this.$confirm(`确定对[id=${hostIds.join(',')}]进行[${hostIds ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/apiTest/host/delete'),
            method: 'post',
            data: this.$http.adornData(hostIds, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {})
      },
      handleChangeStatus (row) {

        debugger;

        this.$http({
          url: this.$http.adornUrl(`/apiTest/host/changeStatus`),
          method: 'post',
          data: this.$http.adornData({
            'hostId': row.hostId || undefined,
            'status': row.status === 1 ? 0 : 1
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 500,
              onClose: () => {
                // this.visible = false;
                this.getDataList();
                // this.$emit('refreshDataList')
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })

      },
    }
  }
</script>
<style>
</style>
