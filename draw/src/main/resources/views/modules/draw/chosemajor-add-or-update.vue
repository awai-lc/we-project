<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="专业id" prop="majorId">
      <el-input v-model="dataForm.majorId" placeholder="专业id"></el-input>
    </el-form-item>
    <el-form-item label="专业编码" prop="majorCode">
      <el-input v-model="dataForm.majorCode" placeholder="专业编码"></el-input>
    </el-form-item>
    <el-form-item label="项目id" prop="proId">
      <el-input v-model="dataForm.proId" placeholder="项目id"></el-input>
    </el-form-item>
    <el-form-item label="专业名称" prop="majorName">
      <el-input v-model="dataForm.majorName" placeholder="专业名称"></el-input>
    </el-form-item>
    <el-form-item label="已抽取专家数" prop="chosedCount">
      <el-input v-model="dataForm.chosedCount" placeholder="已抽取专家数"></el-input>
    </el-form-item>
    <el-form-item label="需抽取专家数" prop="needCount">
      <el-input v-model="dataForm.needCount" placeholder="需抽取专家数"></el-input>
    </el-form-item>
    <el-form-item label="库存专家数" prop="expertCount">
      <el-input v-model="dataForm.expertCount" placeholder="库存专家数"></el-input>
    </el-form-item>
    <el-form-item label="所属区域名称" prop="placeName">
      <el-input v-model="dataForm.placeName" placeholder="所属区域名称"></el-input>
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
          majorId: '',
          majorCode: '',
          proId: '',
          majorName: '',
          chosedCount: '',
          needCount: '',
          expertCount: '',
          placeName: ''
        },
        dataRule: {
          majorId: [
            { required: true, message: '专业id不能为空', trigger: 'blur' }
          ],
          majorCode: [
            { required: true, message: '专业编码不能为空', trigger: 'blur' }
          ],
          proId: [
            { required: true, message: '项目id不能为空', trigger: 'blur' }
          ],
          majorName: [
            { required: true, message: '专业名称不能为空', trigger: 'blur' }
          ],
          chosedCount: [
            { required: true, message: '已抽取专家数不能为空', trigger: 'blur' }
          ],
          needCount: [
            { required: true, message: '需抽取专家数不能为空', trigger: 'blur' }
          ],
          expertCount: [
            { required: true, message: '库存专家数不能为空', trigger: 'blur' }
          ],
          placeName: [
            { required: true, message: '所属区域名称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/draw/chosemajor/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.majorId = data.choseMajor.majorId
                this.dataForm.majorCode = data.choseMajor.majorCode
                this.dataForm.proId = data.choseMajor.proId
                this.dataForm.majorName = data.choseMajor.majorName
                this.dataForm.chosedCount = data.choseMajor.chosedCount
                this.dataForm.needCount = data.choseMajor.needCount
                this.dataForm.expertCount = data.choseMajor.expertCount
                this.dataForm.placeName = data.choseMajor.placeName
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
              url: this.$http.adornUrl(`/draw/chosemajor/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'majorId': this.dataForm.majorId,
                'majorCode': this.dataForm.majorCode,
                'proId': this.dataForm.proId,
                'majorName': this.dataForm.majorName,
                'chosedCount': this.dataForm.chosedCount,
                'needCount': this.dataForm.needCount,
                'expertCount': this.dataForm.expertCount,
                'placeName': this.dataForm.placeName
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
