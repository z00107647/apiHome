<template>
  <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="body" name="fold">
      <div style="margin: 5px">
        <el-row :span="24">
          <el-col :span="4">
            <el-radio v-model="radio" label="form-data">表单(form-data)</el-radio>
          </el-col>
          <el-col :span="4">
            <el-radio v-model="radio" label="raw" v-if="request3">源数据(raw)</el-radio>
          </el-col>
          <el-col v-show="request3" :span="16">
            <el-checkbox v-model="radioType" label="3" v-show="ParameterType">表单转源数据</el-checkbox>
          </el-col>
        </el-row>
      </div>
      <el-table ref="multipleParameterTable" :data="form.parameter" highlight-current-row
                :class="ParameterType? 'parameter-a': 'parameter-b'" @selection-change="selsChangeParameter">
        <el-table-column type="selection" min-width="5%" label="头部">
        </el-table-column>
        <el-table-column prop="name" label="参数名" min-width="40%" sortable>
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.name" :value="scope.row.name" placeholder="请输入参数值"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="value" label="参数值" min-width="80%" sortable>
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.value" :value="scope.row.value" placeholder="请输入参数值"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="15%">
          <template slot-scope="scope">
            <i class="el-icon-delete" style="font-size:30px;cursor:pointer;"
               @click="delParameter(scope.$index)"></i>
          </template>
        </el-table-column>
        <el-table-column label="" min-width="10%">
          <template slot-scope="scope">
            <el-button v-if="scope.$index===(form.parameter.length-1)" size="mini" class="el-icon-plus"
                       @click="addParameter"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="" min-width="18%"></el-table-column>
      </el-table>
      <template>
        <el-input :class="ParameterType? 'parameter-b': 'parameter-a'" type="textarea" :rows="5"
                  placeholder="请输入内容" v-model.trim="form.parameterRaw"></el-input>
      </template>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
  export default {
    data () {
      return {
        activeNames: [],
        ParameterType: true,
        radio: "form-data",
        loadingSend: false,
        header4: "",
        radioType: "",
        result: true,
        request3: true,
        form: {
          parameterRaw: "",
          parameter: [{name: "", value: "", required: "", restrict: "", description: ""}],
          parameterType: "",
        },
      }
    },
    methods: {
      isJsonString(str) {
        try {
          if (typeof JSON.parse(str) === "object") {
            return true;
          }
        } catch (e) {
        }
        return false;
      },
      toggleParameterSelection(rows) {
        rows.forEach(row => {
          this.$refs.multipleParameterTable.toggleRowSelection(row, true);
        });
      },
      selsChangeParameter: function (sels) {
        this.parameters = sels
      },
      addParameter() {
        let headers = {name: "", value: "", required: "True", restrict: "", description: ""};
        this.form.parameter.push(headers);
        let rows = [this.form.parameter[this.form.parameter.length - 1]];
        this.toggleParameterSelection(rows)
      },
      delParameter(index) {
        if (this.form.parameter.length !== 1) {
          this.form.parameter.splice(index, 1)
        }
      },
      changeParameterType() {
        if (this.radio === 'form-data') {
          this.ParameterType = !this.ParameterType
        } else {
          this.ParameterType = !this.ParameterType
        }
      },
      handleChange(val) {
      }
    },
    watch: {
      radio() {
        this.changeParameterType()
      }
    },
    mounted() {
      this.toggleParameterSelection(this.form.parameter);
    }
  }
</script>
<style lang="scss" scoped>
  .parameter-a {
    display: block;
  }

  .parameter-b {
    display: none;
  }
  .raw {
    border: 1px solid #e6e6e6;
    margin-bottom: 10px;
    padding: 15px;
    text-align: center
  }
</style>
