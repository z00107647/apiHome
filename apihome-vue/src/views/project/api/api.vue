<template>
  <section>
    <el-row :span="24" class="row-title">
      <el-col :span="4">
        <el-button class="addGroup" @click="addOrUpdateHandle(0)">新增分组</el-button>
        <router-link :to="{ name: '快速测试', params: {project_id: this.$route.params.project_id}}"
                     style='text-decoration: none;color: aliceblue;'>
          <el-button class="addGroup">快速测试</el-button>
        </router-link>
        <div class="api-title"><strong>接口分组</strong></div>
        <div class="api-title" style="cursor:pointer;">
          <router-link :to="{ name: '接口列表', params: {project_id: this.$route.params.project_id}}"
                       style='text-decoration: none;color: aliceblue;'>
            所有接口
          </router-link>
        </div>
        <aside>
          <!--导航菜单-->
          <el-menu default-active="2" class="el-menu-vertical-demo" active-text-color="rgb(32, 160, 255)"
                   :unique-opened="true">
            <template v-for="(item,index) in dataList">
              <router-link :to="{ name: '分组接口列表', params: {project_id: item.projectId, firstGroup: item.groupId}}"
                           style="text-decoration:none;">
                <el-menu-item :index="index+''" :key="item.groupId" class="group">
                  <template slot="title">{{item.groupName}}
                    <el-dropdown trigger="hover" class="editGroup" style="margin-right:10%">
                      <i class="el-icon-more"></i>
                      <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="deleteHandle(item.groupId)">删除</el-dropdown-item>
                        <el-dropdown-item @click.native="addOrUpdateHandle(item.groupId)">修改</el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </template>
                </el-menu-item>
              </router-link>
            </template>
          </el-menu>
        </aside>
      </el-col>
      <!-- 弹窗, 新增 / 修改 -->
      <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

      <!-- 右侧的信息区域-->
      <el-col :span="20">
        <div style="margin-left: 10px;margin-right: 20px">
          <router-view></router-view>
        </div>
      </el-col>
    </el-row>
  </section>
</template>

<script>
  import AddOrUpdate from './group-add-or-update';

  export default {

    data() {
      return {
        projectId: '',
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
      this.getDataList();
      this.projectId = this.$route.params.project_id
    },
    methods: {
      // 获取api分组
      getDataList() {
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('/apiTest/group/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'projectId': this.$route.params.project_id
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
      sizeChangeHandle(val) {
        this.pageSize = val;
        this.pageIndex = 1;
        this.getDataList()
      },
      // 当前页
      currentChangeHandle(val) {
        this.pageIndex = val;
        this.getDataList()
      },
      // 多选
      selectionChangeHandle(val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle(groupId) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(this.$route.params.project_id, groupId)
        })
      },
      // 删除
      deleteHandle(groupId) {
        var groupIds = groupId ? [groupId] : this.dataListSelections.map(item => {
          return item.groupId
        });
        this.$http({
          url: this.$http.adornUrl('/apiTest/group/delete'),
          method: 'post',
          data: this.$http.adornData(groupIds, false)
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
      }
    }
  }

</script>

<style lang="scss" scoped>
  .api-title {
    padding: 15px;
    margin: 0px;
    text-align: center;
    border-radius: 5px;
    font-size: 15px;
    color: aliceblue;
    background-color: rgb(32, 160, 255);
    font-family: PingFang SC;
  }

  .group .editGroup {
    float: right;
  }

  .row-title {
    margin: 35px;
  }

  .addGroup {
    margin-top: 0px;
    margin-bottom: 10px;
    border-radius: 25px;
  }
</style>
