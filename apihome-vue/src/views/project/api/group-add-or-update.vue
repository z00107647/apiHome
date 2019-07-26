<template>
  <el-dialog
    :title="!dataForm.groupId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="名称" prop="hostName">
        <el-input v-model.trim="dataForm.groupName" auto-complete="off"></el-input>
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
      return {
        visible: false,
        dataForm: {
          projectId: 0,
          groupName: '',
          groupLevel: 1,
          remark: '',
          status: 0
        },
        dataRule: {
          groupName: [
            { required: true, message: '分组名称不能为空', trigger: 'blur' },
            { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
          ],
          remark: [
            { required: false, message: '请输入描述', trigger: 'blur' },
            { max: 1024, message: '不能超过1024个字符', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      // init 是调转过来的首要执行方法。
      init (projectId, groupId) {
        this.dataForm.groupId = groupId || 0;
        this.dataForm.projectId = projectId || 0;
        this.visible = true;
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields();
          if (this.dataForm.groupId) {
            this.$http({
              url: this.$http.adornUrl(`/apiTest/group/info/${this.dataForm.groupId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.groupName = data.group.groupName;
                this.dataForm.remark = data.group.remark;
                this.dataForm.status = data.group.status;
                this.dataForm.groupLevel = data.group.groupLevel;
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
              url: this.$http.adornUrl(`/apiTest/group/${!this.dataForm.hostId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'groupId': this.dataForm.groupId || undefined,
                'projectId': this.dataForm.projectId,
                'groupName': this.dataForm.groupName,
                'groupLevel': this.dataForm.groupLevel,
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
