<template>
  <el-dialog
    :title="!dataForm.hostId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="名称" prop="hostName">
        <el-input v-model.trim="dataForm.hostName" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="Host" prop='hostIp'>
        <el-input v-model.trim="dataForm.hostIp" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop='remark'>
        <el-input type="textarea" :rows="5" v-model.trim="dataForm.remark"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      var checkIp = (rule, value, callback) => {
        if (!this.isValidIP(value)) {
          return callback(new Error('IP地址格式错误'));
        } else {
          return callback()
        }
      };
      return {
        visible: false,
        dataForm: {
          projectId: 0,
          hostId: 0,
          hostName: '',
          hostIp: '',
          hostType: 1,
          remark: '',
          status: 0
        },
        dataRule: {
          hostName: [
            { required: true, message: 'Host域名不能为空', trigger: 'blur' },
            { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
          ],
          hostIp: [
            { required: true, message: 'host具体IP地址不能为空', trigger: 'blur' },
            { validator: checkIp, trigger: 'blur' }
          ],
          remark: [
            { required: false, message: '请输入描述', trigger: 'blur' },
            { max: 1024, message: '不能超过1024个字符', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      // IP格式验证
      isValidIP(ip) {
        var regIp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        var regPort = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5]):([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$$/;

        return regPort.test(ip) || regIp.test(ip);
      },
      // init 是调转过来的首要执行方法。
      init (projectId, hostId) {
        this.dataForm.hostId = hostId || 0;
        this.dataForm.projectId = projectId || 0;
        this.visible = true;
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields();
          if (this.dataForm.hostId) {
            this.$http({
              url: this.$http.adornUrl(`/apiTest/host/info/${this.dataForm.hostId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.hostName = data.host.hostName;
                this.dataForm.hostIp = data.host.hostIp;
                this.dataForm.remark = data.host.remark;
                this.dataForm.status = data.host.status;
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/apiTest/host/${!this.dataForm.hostId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'hostId': this.dataForm.hostId || undefined,
                'projectId': this.dataForm.projectId,
                'hostName': this.dataForm.hostName,
                'hostIp': this.dataForm.hostIp,
                'hostType': this.dataForm.hostType,
                'remark': this.dataForm.remark,
                'status': this.dataForm.status
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 500,
                  onClose: () => {
                    this.visible = false;
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
