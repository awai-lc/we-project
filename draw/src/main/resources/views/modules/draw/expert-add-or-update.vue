<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="专家名称" prop="expertName">
      <el-input v-model="dataForm.expertName" placeholder="专家名称"></el-input>
    </el-form-item>
    <el-form-item label="专业id" prop="majorId">
      <el-input v-model="dataForm.majorId" placeholder="专业id"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="工作单位" prop="unit">
      <el-input v-model="dataForm.unit" placeholder="工作单位"></el-input>
    </el-form-item>
    <el-form-item label="在职状态（1退休，2在职）" prop="jobStatus">
      <el-input v-model="dataForm.jobStatus" placeholder="在职状态（1退休，2在职）"></el-input>
    </el-form-item>
    <el-form-item label="出生年月" prop="birth">
      <el-input v-model="dataForm.birth" placeholder="出生年月"></el-input>
    </el-form-item>
    <el-form-item label="职称" prop="title">
      <el-input v-model="dataForm.title" placeholder="职称"></el-input>
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
          expertName: '',
          majorId: '',
          phone: '',
          email: '',
          address: '',
          unit: '',
          jobStatus: '',
          birth: '',
          title: '',
          addby: '',
          addbytime: '',
          lastmodifiedby: '',
          lastmodifiedbytime: ''
        },
        dataRule: {
          expertName: [
            { required: true, message: '专家名称不能为空', trigger: 'blur' }
          ],
          majorId: [
            { required: true, message: '专业id不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          unit: [
            { required: true, message: '工作单位不能为空', trigger: 'blur' }
          ],
          jobStatus: [
            { required: true, message: '在职状态（1退休，2在职）不能为空', trigger: 'blur' }
          ],
          birth: [
            { required: true, message: '出生年月不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '职称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/draw/expert/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.expertName = data.expert.expertName
                this.dataForm.majorId = data.expert.majorId
                this.dataForm.phone = data.expert.phone
                this.dataForm.email = data.expert.email
                this.dataForm.address = data.expert.address
                this.dataForm.unit = data.expert.unit
                this.dataForm.jobStatus = data.expert.jobStatus
                this.dataForm.birth = data.expert.birth
                this.dataForm.title = data.expert.title
                this.dataForm.addby = data.expert.addby
                this.dataForm.addbytime = data.expert.addbytime
                this.dataForm.lastmodifiedby = data.expert.lastmodifiedby
                this.dataForm.lastmodifiedbytime = data.expert.lastmodifiedbytime
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
              url: this.$http.adornUrl(`/draw/expert/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'expertName': this.dataForm.expertName,
                'majorId': this.dataForm.majorId,
                'phone': this.dataForm.phone,
                'email': this.dataForm.email,
                'address': this.dataForm.address,
                'unit': this.dataForm.unit,
                'jobStatus': this.dataForm.jobStatus,
                'birth': this.dataForm.birth,
                'title': this.dataForm.title,
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
