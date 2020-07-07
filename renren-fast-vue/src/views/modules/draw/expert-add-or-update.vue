<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="专家名称" prop="expertName">
      <el-input v-model="dataForm.expertName" placeholder="专家名称"></el-input>
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
    <el-form-item label="在职状态" prop="jobStatus">
      <el-switch
              v-model="dataForm.jobStatus"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value=1
              :inactive-value=2
              >
      </el-switch>
    </el-form-item>
    <el-form-item label="出生年月" prop="birth">
      <!-- <el-input v-model="dataForm.birth" placeholder="出生年月"></el-input> -->
      <el-date-picker
        v-model="dataForm.birth"
        type="date"
        placeholder="选择日期"
        value-format="yyyy-mm-dd">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="职称" prop="title">
      <el-input v-model="dataForm.title" placeholder="职称"></el-input>
    </el-form-item>
     <el-form-item label="专业" prop="majorId">
      <!-- <el-input v-model="dataForm.majorId" placeholder="专业id"></el-input> -->
      <el-cascader
        v-model="dataForm.majorIds"
        :options="majors"
        :props="props"
        filterable>
      </el-cascader>
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
          jobStatus: 1,
          birth: '',
          title: '',
          addby: '',
          lastmodifiedby: '',
          lastmodifiedbytime: '',
          majorIds: []
        },
        props: {
          value: 'id',
          label: 'majorName',
          children: 'childMajor'
        },
        majors: [],
        dataRule: {
          expertName: [
            { required: true, message: '专家名称不能为空', trigger: 'blur' }
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
          ]
        }
      }
    },
    methods: {
      getDataList () {
        this.$http({
          url: this.$http.adornUrl('/draw/major/list/tree'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({ data }) => {
          this.majors = data.data
        })
      },
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
                this.dataForm.lastmodifiedby = data.expert.lastmodifiedby
                this.dataForm.majorIds = data.expert.majorIds
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        if (this.dataForm.id == '') {
          this.$refs['dataForm'].validate((valid) => {
            if (valid) {
              this.$http({
                url: this.$http.adornUrl('/draw/expert/save'),
                method: 'post',
                data: this.$http.adornData({
                  'id': this.dataForm.id || undefined,
                  'expertName': this.dataForm.expertName,
                  'majorId': this.dataForm.majorIds[this.dataForm.majorIds.length - 1],
                  'phone': this.dataForm.phone,
                  'email': this.dataForm.email,
                  'address': this.dataForm.address,
                  'unit': this.dataForm.unit,
                  'jobStatus': this.dataForm.jobStatus,
                  'birth': this.dataForm.birth,
                  'title': this.dataForm.title,
                  'addby': this.$store.state.user.id,
                  'lastmodifiedby': this.$store.state.user.id
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
        } else {
          this.$refs['dataForm'].validate((valid) => {
            if (valid) {
              this.$http({
                url: this.$http.adornUrl('/draw/expert/update'),
                method: 'post',
                data: this.$http.adornData({
                  'id': this.dataForm.id || undefined,
                  'expertName': this.dataForm.expertName,
                  'majorId': this.dataForm.majorIds[this.dataForm.majorIds.length - 1],
                  'phone': this.dataForm.phone,
                  'email': this.dataForm.email,
                  'address': this.dataForm.address,
                  'unit': this.dataForm.unit,
                  'jobStatus': this.dataForm.jobStatus,
                  'birth': this.dataForm.birth,
                  'title': this.dataForm.title,
                  'lastmodifiedby': this.$store.state.user.id
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
    },
    created () {
      this.getDataList()
    }
  }
</script>
<style scoped>
  .el-cascader{
    width: 500px;  
}
</style>
