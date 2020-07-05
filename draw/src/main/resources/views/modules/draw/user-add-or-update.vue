<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="手机号" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="名字" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="名字"></el-input>
    </el-form-item>
    <el-form-item label="密码hash" prop="passwordHash">
      <el-input v-model="dataForm.passwordHash" placeholder="密码hash"></el-input>
    </el-form-item>
    <el-form-item label="活跃sessionId" prop="activeSessionId">
      <el-input v-model="dataForm.activeSessionId" placeholder="活跃sessionId"></el-input>
    </el-form-item>
    <el-form-item label="最近登录时间" prop="lastLoginDt">
      <el-input v-model="dataForm.lastLoginDt" placeholder="最近登录时间"></el-input>
    </el-form-item>
    <el-form-item label="最近活跃时间" prop="lastActiveDt">
      <el-input v-model="dataForm.lastActiveDt" placeholder="最近活跃时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="isOnline">
      <el-input v-model="dataForm.isOnline" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="新增人ID" prop="addby">
      <el-input v-model="dataForm.addby" placeholder="新增人ID"></el-input>
    </el-form-item>
    <el-form-item label="新增时间" prop="addbytime">
      <el-input v-model="dataForm.addbytime" placeholder="新增时间"></el-input>
    </el-form-item>
    <el-form-item label=" 最近修改人ID" prop="lastmodifiedby">
      <el-input v-model="dataForm.lastmodifiedby" placeholder=" 最近修改人ID"></el-input>
    </el-form-item>
    <el-form-item label="最近修改时间" prop="lastmodifiedbytime">
      <el-input v-model="dataForm.lastmodifiedbytime" placeholder="最近修改时间"></el-input>
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
          id: 0,
          mobile: '',
          nickname: '',
          passwordHash: '',
          activeSessionId: '',
          lastLoginDt: '',
          lastActiveDt: '',
          isOnline: '',
          addby: '',
          addbytime: '',
          lastmodifiedby: '',
          lastmodifiedbytime: ''
        },
        dataRule: {
          mobile: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '名字不能为空', trigger: 'blur' }
          ],
          passwordHash: [
            { required: true, message: '密码hash不能为空', trigger: 'blur' }
          ],
          activeSessionId: [
            { required: true, message: '活跃sessionId不能为空', trigger: 'blur' }
          ],
          lastLoginDt: [
            { required: true, message: '最近登录时间不能为空', trigger: 'blur' }
          ],
          lastActiveDt: [
            { required: true, message: '最近活跃时间不能为空', trigger: 'blur' }
          ],
          isOnline: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          addby: [
            { required: true, message: '新增人ID不能为空', trigger: 'blur' }
          ],
          addbytime: [
            { required: true, message: '新增时间不能为空', trigger: 'blur' }
          ],
          lastmodifiedby: [
            { required: true, message: ' 最近修改人ID不能为空', trigger: 'blur' }
          ],
          lastmodifiedbytime: [
            { required: true, message: '最近修改时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/draw/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.mobile = data.user.mobile
                this.dataForm.nickname = data.user.nickname
                this.dataForm.passwordHash = data.user.passwordHash
                this.dataForm.activeSessionId = data.user.activeSessionId
                this.dataForm.lastLoginDt = data.user.lastLoginDt
                this.dataForm.lastActiveDt = data.user.lastActiveDt
                this.dataForm.isOnline = data.user.isOnline
                this.dataForm.addby = data.user.addby
                this.dataForm.addbytime = data.user.addbytime
                this.dataForm.lastmodifiedby = data.user.lastmodifiedby
                this.dataForm.lastmodifiedbytime = data.user.lastmodifiedbytime
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
              url: this.$http.adornUrl(`/draw/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'mobile': this.dataForm.mobile,
                'nickname': this.dataForm.nickname,
                'passwordHash': this.dataForm.passwordHash,
                'activeSessionId': this.dataForm.activeSessionId,
                'lastLoginDt': this.dataForm.lastLoginDt,
                'lastActiveDt': this.dataForm.lastActiveDt,
                'isOnline': this.dataForm.isOnline,
                'addby': this.dataForm.addby,
                'addbytime': this.dataForm.addbytime,
                'lastmodifiedby': this.dataForm.lastmodifiedby,
                'lastmodifiedbytime': this.dataForm.lastmodifiedbytime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
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
