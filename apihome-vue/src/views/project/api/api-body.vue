<template>
  <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="body" name="fold">
      <div style="margin: 5px">
        <el-row :span="24">
          <el-col :span="4">
            <el-radio v-model="body.bodyMode" label="formData">表单(form-data)</el-radio>
          </el-col>
          <el-col :span="4">
            <el-radio v-model="body.bodyMode" label="raw" v-if="rawShow">源数据(raw)</el-radio>
          </el-col>
          <el-col v-show="rawShow" :span="16">
            <el-checkbox v-model="radioType" label="3" v-show="bodyType">表单转源数据</el-checkbox>
          </el-col>
        </el-row>
      </div>
      <el-table ref="multipleParameterTable" :data="body.bodyForm" highlight-current-row
                :class="bodyType? 'body-a': 'body-b'" @selection-change="selsChangeParameter">
        <el-table-column type="selection" min-width="5%" label="头部">
        </el-table-column>
        <el-table-column prop="name" label="参数名" min-width="40%" sortable>
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.name" :value="scope.row.name" placeholder="请输入参数值"
                      @input="changeValue"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="value" label="参数值" min-width="80%" sortable>
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.value" :value="scope.row.value" placeholder="请输入参数值"
                      @input="changeValue"></el-input>
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
            <el-button v-if="scope.$index===(body.bodyForm.length-1)" size="mini" class="el-icon-plus"
                       @click="addParameter"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="" min-width="18%"></el-table-column>
      </el-table>
      <template>
        <el-input :class="bodyType? 'body-b': 'body-a'" type="textarea" :rows="5"
                  placeholder="请输入内容" v-model.trim="body.bodyRaw" @input="changeValue"></el-input>
      </template>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
  export default {
    data() {
      return {
        activeNames: [],
        bodyType: true,
        loadingSend: false,
        radioType: "",
        result: true,
        rawShow: true,
        body: {
          bodyRaw: "",
          bodyForm: [{name: "", value: "", required: "", restrict: "", description: ""}],
          bodyMode: "formData"
        }
      }
    },
    methods: {
      toggleParameterSelection(rows) {
        rows.forEach(row => {
          this.$refs.multipleParameterTable.toggleRowSelection(row, true);
        });
      },
      selsChangeParameter: function (sels) {
        this.parameters = sels
      },
      addParameter() {
        let bodyLine = {name: "", value: "", required: "True", restrict: "", description: ""};
        this.body.bodyForm.push(bodyLine);
        let rows = [this.body.bodyForm[this.body.bodyForm.length - 1]];
        this.toggleParameterSelection(rows)
      },
      delParameter(index) {
        if (this.body.bodyForm.length !== 1) {
          this.body.bodyForm.splice(index, 1)
        }
      },
      changeParameterType() {
        this.bodyType = !this.bodyType;
        this.body.bodyMode = this.radio;
      },
      handleChange(val) {
      },
      changeValue(){
        this.$emit('refreshBodyData', this.body)
      }
    },
    watch: {
      radio() {
        this.changeParameterType()
      }
    },
    mounted() {
      this.toggleParameterSelection(this.body.bodyForm);
    }
  }
</script>
<style lang="scss" scoped>
  .body-a {
    display: block;
  }

  .body-b {
    display: none;
  }

  .raw {
    border: 1px solid #e6e6e6;
    margin-bottom: 10px;
    padding: 15px;
    text-align: center
  }
</style>
