<template>
  <section>
    <router-link
      :to="{ name: '新增接口', params: {project_id: this.$route.params.project_id, formData: this.form, _type: this.radio, _typeData: this.radioType}}"
      style='text-decoration: none;color: aliceblue;'>
      <el-button class="return-list">快速新建API</el-button>
    </router-link>
    <el-form :model="form" ref="form" :rules="formRules">
      <el-col :span="3" class="HOST">
        <el-form-item prop="url">
          <el-select v-model="form.url" placeholder="测试环境">
            <el-option v-for="(item,index) in Host" :key="index+''" :label="item.name" :value="item.host"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <div style="border: 1px solid #e6e6e6;margin-bottom: 10px;padding:15px;padding-bottom: 0px">
        <el-row :gutter="10">
          <el-col :span="3">
            <el-form-item>
              <el-select v-model="form.request4" placeholder="请求方式" @change="checkRequest">
                <el-option v-for="(item,index) in request" :key="index+''" :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <el-form-item>
              <el-select v-model="form.Http4" placeholder="HTTP协议">
                <el-option v-for="(item,index) in Http" :key="index+''" :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='16'>
            <el-form-item prop="addr">
              <el-input v-model.trim="form.addr" placeholder="Enter request URL" auto-complete></el-input>
            </el-form-item>
          </el-col>
          <el-col :span='2'>
            <el-button type="primary" @click="fastTest" :loading="loadingSend">发送</el-button>
          </el-col>
        </el-row>
      </div>
      <el-row :span="24">

        <api-header v-if="true" ref="apiHeader" @refreshHeaderData="getHeaderData"></api-header>

        <api-body v-if="true" ref="apiBody" @refreshBodyData="getBodyData"></api-body>

        <api-response v-if="true" ref="apiResponse" @refreshResponseData="getResponseData"></api-response>
      </el-row>
    </el-form>
  </section>
</template>
<script>
  // import $ from 'jquery'
  // import VuePopper from "element-ui/src/utils/vue-popper";
  // import {test} from '../../../api/api'
  import ApiHeader from './api-header';
  import ApiBody from './api-body';
  import ApiResponse from './api-response';

  export default {
    components: {ApiHeader, ApiBody, ApiResponse},
    data() {
      return {
        request: [{value: 'get', label: 'GET'}, {value: 'post', label: 'POST'},
          {value: 'put', label: 'PUT'}, {value: 'delete', label: 'DELETE'}],
        Http: [{value: 'http', label: 'HTTP'}, {value: 'https', label: 'HTTPS'}],
        ParameterType: true,
        radio: "form-data",
        loadingSend: false,
        header4: "",
        radioType: "",
        result: true,
        activeNames: ['1', '2', '3', '4'],
        Host: [],
        id: "",
        request3: true,
        form: {
          url: "",
          request4: 'POST',
          Http4: 'HTTP',
          addr: '',
          head: [{name: "", value: ""},
            {name: "", value: ""}],
          parameterRaw: "",
          parameter: [{name: "", value: "", required: "", restrict: "", description: ""},
            {name: "", value: "", required: "", restrict: "", description: ""}],
          parameterType: "",
          statusCode: "",
          resultData: "",
          resultHead: "",
        },
        formRules: {
          addr: [
            {required: true, message: '请输入地址', trigger: 'blur'},
          ]
        },
        headers: "",
        parameters: "",
        resultShow: true,
        format: false,
      }
    },
    methods: {
      getHeaderData() {
      },
      getBodyData() {
      },
      getResponseData() {
      },
      checkRequest() {
        let request = this.form.request4;
        if (request === "GET" || request === "DELETE") {
          this.request3 = false
        } else {
          this.request3 = true
        }
      },
      getHost() {
        this.$http({
          url: this.$http.adornUrl('/apiTest/host/list'),
          method: 'get',
          params: this.$http.adornParams({
            // 'page': this.pageIndex,
            'limit': 100, //分页查询，取前1000条
            'projectId': this.$route.params.project_id
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            data.page.list.forEach((item) => {
              if (item.status === 0) {
                let hostSingle = {name: item.hostName, host: item.hostIp};
                this.Host.push(hostSingle)
              }
            });
          } else {
            this.$message.error({
              message: data.msg,
              center: true,
            })
          }
        });
      },
      // selsChangeHead: function (sels) {
      //   this.headers = sels
      // },
      fastTest: function () {

        debugger;

        let host = this.form.addr;
        if (host.indexOf("http://") === 0) {
          this.form.addr = host.slice(7)
        }
        if (host.indexOf("https://") === 0) {
          this.form.addr = host.slice(8)
        }
        console.log(this.form.addr);
        this.loadingSend = true;
        let self = this;
        let _parameter = {};
        let headers = {};
        self.form.statusCode = '';
        self.form.resultData = '';
        self.form.resultHead = '';
        for (let i = 0; i < self.form.head.length; i++) {
          var a = self.form.head[i]["name"];
          if (a) {
            headers[a] = self.form.head[i]["value"]
          }
        }
        let url = self.form.Http4 + "://" + this.form.addr;
        let _type = self.radio;
        if (_type === 'form-data') {
          if (self.radioType) {
            for (let i = 0; i < self.parameters.length; i++) {
              var a = self.parameters[i]["name"];
              if (a) {
                _parameter[a] = self.parameters[i]["value"];
              }
            }
            _parameter = JSON.stringify(_parameter)
          } else {
            _parameter = self.form.parameter
          }
        } else {
          // POST(url, self.form.parameterRaw, headers)
          _parameter = self.form.parameterRaw;
        }
        if (self.form.parameterRaw && _type === "raw") {
          if (!self.isJsonString(self.form.parameterRaw)) {
            self.$message({
              message: '源数据格式错误',
              center: true,
              type: 'error'
            })
          } else {
            // $.ajax({
            //   type: self.form.request4,
            //   url: url,
            //   async: true,
            //   data: _parameter,
            //   headers: headers,
            //   timeout: 5000,
            //   dataType: 'jsonp',
            //   success: function (data, status, jqXHR) {
            //     console.log(data)
            //     self.loadingSend = false;
            //     self.form.statusCode = jqXHR.status;
            //     self.form.resultData = data;
            //     self.form.resultHead = jqXHR.getAllResponseHeaders()
            //   },
            //   error: function (jqXHR, error, errorThrown) {
            //     self.loadingSend = false;
            //     self.form.statusCode = jqXHR.status;
            //     self.form.resultData = jqXHR.responseJSON;
            //     self.form.resultHead = jqXHR.getAllResponseHeaders()
            //   }
            // })
          }
        } else {
          // $.ajax({
          //   type: self.form.request4,
          //   url: url,
          //   async: true,
          //   data: _parameter,
          //   headers: headers,
          //   timeout: 5000,
          //   success: function (data, status, jqXHR) {
          //     self.loadingSend = false;
          //     self.form.statusCode = jqXHR.status;
          //     self.form.resultData = data;
          //     self.form.resultHead = jqXHR.getAllResponseHeaders()
          //   },
          //   error: function (jqXHR, error, errorThrown) {
          //     self.loadingSend = false;
          //     self.form.statusCode = jqXHR.status;
          //     self.form.resultData = jqXHR.responseJSON;
          //     self.form.resultHead = jqXHR.getAllResponseHeaders()
          //   }
          // })
        }
      }
    },
    mounted() {
      this.getHost()
    }
  }
</script>

<style lang="scss" scoped>
  .return-list {
    margin-top: 0px;
    margin-bottom: 10px;
    border-radius: 25px;
  }

  .head-class {
    font-size: 17px
  }

  .HOST {
    position: absolute;
    right: 10px;
    top: 0px;
  }
</style>
