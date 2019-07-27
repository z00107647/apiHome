<template>
  <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="响应结果" name="unfold">
      <div style="margin-bottom: 10px">
        <el-button @click="showBody">Body</el-button>
        <el-button @click="showHeader">Head</el-button>
        <el-button type="primary" @click="neatenFormat">格式转换</el-button>
      </div>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span v-model="form.statusCode" style="font-size: 25px">{{form.statusCode}}</span>
        </div>
        <div v-model="form.resultData" :class="resultShow? 'parameter-a': 'parameter-b'" v-show="!format">
          <div style="word-break: break-all;overflow:auto;overflow-x:hidden">{{form.resultData}}</div>
        </div>
        <div v-model="form.resultHead" :class="resultShow? 'parameter-b': 'parameter-a'">{{form.resultHead}}</div>
        <div :class="resultShow? 'parameter-a': 'parameter-b'" v-show="format && form.resultData">
          <pre style="border: 1px solid #e6e6e6;word-break: break-all;overflow:auto;overflow-x:hidden">{{form.resultData}}</pre>
        </div>
        <div v-show="!form.resultData&&!form.resultHead" class="raw">暂无数据</div>
      </el-card>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
  export default {
    data () {
      return {
        activeNames: ['unfold'],
        result: true,
        resultShow: true,
        form: {
          statusCode: "",
          resultData: "",
          resultHead: "",
        },
        format: false
      }
    },
    methods: {
      neatenFormat() {
        let demo = document.getElementsByTagName('pre')[0];
        console.log(demo);
        hljs.highlightBlock(demo);
        this.format = !this.format
      },
      addResponse() {
        let headers = {name: "", value: "", required: "True", restrict: "", description: ""};
        this.form.response.push(headers)
      },
      delResponse(index) {
        if (this.form.response.length !== 1) {
          this.form.response.splice(index, 1)
        }
      },
      showBody() {
        this.resultShow = true
      },
      showHeader() {
        this.resultShow = false
      },
      handleChange(val) {
      }
    }
  }
</script>
<style lang="scss" scoped>
</style>
