<template>
  <div class="main">
    <el-row :span="24">
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <h1>{{type}}</h1>
          <div>项目类型</div>
        </el-card>
      </el-col>
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <h1>{{version}}</h1>
          <div>版本</div>
        </el-card>
      </el-col>
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <h1>{{updateDate}}</h1>
          <div>最近更新时间</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :span="24">
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <router-link :to="{name: '接口列表'}" style='text-decoration: none;color: #000000;'><h1>{{apiCount}}个接口</h1></router-link>
          <div>接口数量</div>
        </el-card>
      </el-col>
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <h1>{{statusCount}}条状态码</h1>
          <div>通用状态码</div>
        </el-card>
      </el-col>
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <router-link :to="{name: '项目动态'}" style='text-decoration: none;color: #000000;'><h1>{{dynamicCount}}条动态</h1></router-link>
          <div>项目三天内动态</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :span="24">
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <router-link :to="{name: '成员管理'}" style='text-decoration: none;color: #000000;'><h1><img src="~@/assets/img/member.png" class="member">{{memberCount}}人</h1></router-link>
          <div>项目组成员</div>
        </el-card>
      </el-col>
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <router-link :to="{name: '用例列表'}" style='text-decoration: none;color: #000000;'><h1>自动化测试</h1></router-link>
          <div>自由测试接口并生成测试报告</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :span="24">
      <el-col :span="6" class='inline'>
        <el-card class="box-card">
          <h1>{{createDate}}</h1>
          <div>创建时间</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        type: '',
        version: '',
        updateDate: '',
        apiCount: 0,
        statusCount: 0,
        dynamicCount: 0,
        memberCount: 0,
        createDate: '',
      }
    },
    methods: {
      getProjectView() {
        var self = this;
        this.$http({
          url: this.$http.adornUrl(`/apiTest/project/view/${this.$route.params.project_id}`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            self.type = data.project.projectType;
            self.version = data.project.projectVersion;
            self.updateDate = data.project.gmtModify;
            // 需要跨库查询得到
            self.apiCount = 0;
            self.dynamicCount = 0;
            self.memberCount = 0;
            self.createDate = data.project.gmtCreate;
          } else {
            this.$message.error({
              message: data.msg,
              center: true,
            });
          }
        });
      }
    },
    mounted() {
      this.getProjectView()
    }
  }
</script>

<style lang="scss" scoped>
  .box-card {
    width: 100%;
    height: 100%;
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .member {
    width: 7%;
  }
  .main {
    margin: 35px;
    margin-top: 10px;
  }
  .inline {
    margin: 10px;
    margin-left: 0px;
    margin-right: 20px;
  }
</style>
