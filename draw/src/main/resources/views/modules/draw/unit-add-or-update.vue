<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="单位名称" prop="unitName">
      <el-input v-model="dataForm.unitName" placeholder="单位名称"></el-input>
    </el-form-item>
    <el-form-item label="新增人ID" prop="addby">
      <el-input v-model="dataForm.addby" placeholder="新增人ID"></el-input>
    </el-form-item>
    <el-form-item label="新增时间" prop="addbytime">
      <el-input v-model="dataForm.addbytime" placeholder="新增时间"></el-input>
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
          unitName: '',
          addby: '',
          addbytime: ''
        },
        dataRule: {
          unitName: [
            { required: true, message: '单位名称不能为空', trigger: 'blur' }
          ],
          addby: [
            { required: true, message: '新增人ID不能为空', trigger: 'blur' }
          ],
          addbytime: [
            { required: true, message: '新增时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/draw/unit/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.unitName = data.unit.unitName
                this.dataForm.addby = data.unit.addby
                this.dataForm.addbytime = data.unit.addbytime
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
              url: this.$http.adornUrl(`/draw/unit/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'unitName': this.dataForm.unitName,
                'addby': this.dataForm.addby,
                'addbytime': this.dataForm.addbytime
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
