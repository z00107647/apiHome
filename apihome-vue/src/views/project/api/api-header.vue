<template>
  <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="header" name="fold">
      <el-table :data="form.head" highlight-current-row ref="multipleHeadTable">
        <el-table-column type="selection" min-width="5%" label="头部">
        </el-table-column>
        <el-table-column prop="name" label="标签" min-width="20%" sortable>
          <template slot-scope="scope">
            <el-select placeholder="head标签" filterable v-model="scope.row.name">
              <el-option v-for="(item,index) in header" :key="index+''" :label="item.label" :value="item.value"></el-option>
            </el-select>
            <el-input class="selectInput" v-model.trim="scope.row.name" :value="scope.row.name" placeholder="请输入内容"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="value" label="内容" min-width="40%" sortable>
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.value" :value="scope.row.value" placeholder="请输入内容"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="7%">
          <template slot-scope="scope">
            <i class="el-icon-delete" style="font-size:30px;cursor:pointer;" @click="delHead(scope.$index)"></i>
          </template>
        </el-table-column>
        <el-table-column label="" min-width="10%">
          <template slot-scope="scope">
            <el-button v-if="scope.$index===(form.head.length-1)" size="mini" class="el-icon-plus"
                       @click="addHead"></el-button>
          </template>
        </el-table-column>
        <!--              <el-table-column min-width="18%"></el-table-column>-->
      </el-table>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
  export default {
    data () {
      return {
        // 用于对应的name名称来展开，默认都闭合
        activeNames: [],
        header: [{value: 'Accept', label: 'Accept'},
          {value: 'Accept-Charset', label: 'Accept-Charset'},
          {value: 'Accept-Encoding', label: 'Accept-Encoding'},
          {value: 'Accept-Language', label: 'Accept-Language'},
          {value: 'Accept-Ranges', label: 'Accept-Ranges'},
          {value: 'Authorization', label: 'Authorization'},
          {value: 'Cache-Control', label: 'Cache-Control'},
          {value: 'Connection', label: 'Connection'},
          {value: 'Cookie', label: 'Cookie'},
          {value: 'Content-Length', label: 'Content-Length'},
          {value: 'Content-Type', label: 'Content-Type'},
          {value: 'Content-MD5', label: 'Content-MD5'},
          {value: 'Date', label: 'Date'},
          {value: 'Expect', label: 'Expect'},
          {value: 'From', label: 'From'},
          {value: 'Host', label: 'Host'},
          {value: 'If-Match', label: 'If-Match'},
          {value: 'If-Modified-Since', label: 'If-Modified-Since'},
          {value: 'If-None-Match', label: 'If-None-Match'},
          {value: 'If-Range', label: 'If-Range'},
          {value: 'If-Unmodified-Since', label: 'If-Unmodified-Since'},
          {value: 'Max-Forwards', label: 'Max-Forwards'},
          {value: 'Origin', label: 'Origin'},
          {value: 'Pragma', label: 'Pragma'},
          {value: 'Proxy-Authorization', label: 'Proxy-Authorization'},
          {value: 'Range', label: 'Range'},
          {value: 'Referer', label: 'Referer'},
          {value: 'TE', label: 'TE'},
          {value: 'Upgrade', label: 'Upgrade'},
          {value: 'User-Agent', label: 'User-Agent'},
          {value: 'Via', label: 'Via'},
          {value: 'Warning', label: 'Warning'}],
        form: {
          head: [{name: "", value: ""}],
        }
      }
    },
    methods: {
      toggleHeadSelection(rows) {
        rows.forEach(row => {
          this.$refs.multipleHeadTable.toggleRowSelection(row, true);
        });
      },
      addHead() {
        let headers = {name: "", value: ""};
        this.form.head.push(headers);
        let rows = [this.form.head[this.form.head.length - 1]];
        this.toggleHeadSelection(rows)
      },
      delHead(index) {
        if (this.form.head.length !== 1) {
          this.form.head.splice(index, 1)
        }
      },
      handleChange(val) {
      }
    },
    mounted() {
      this.toggleHeadSelection(this.form.head);
    }
  }
</script>
<style lang="scss" scoped>
  .selectInput {
    /*position:absolute;*/
    /*margin-left:7px;*/
    /*padding-left:10px;*/
    /*width: 63%;*/
    /*height:25px;*/
    /*left:1px;*/
    /*top:1px;*/
    /*border-bottom:0px;*/
    /*border-right:0px;*/
    /*border-left:0px;*/
    /*border-top:0px;*/
    position: absolute;
    /*margin-left: 7px;*/
    padding-left: 9px;
    width: 180px;
    /*border-radius:0px;*/
    /*height: 38px;*/
    left: 1px;
    border-right: 0px;
  }

  .raw {
    border: 1px solid #e6e6e6;
    margin-bottom: 10px;
    padding: 15px;
    text-align: center
  }

  .HOST {
    position: absolute;
    right: 10px;
    top: 0px;
  }
</style>
<style lang="scss">
  .selectInput {
    input {
      border-right: 0px;
      border-radius: 4px 0px 0px 4px;
    }
  }
</style>
