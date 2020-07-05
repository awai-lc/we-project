<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="专业id" prop="majorId">
      <el-input v-model="dataForm.majorId" placeholder="专业id"></el-input>
    </el-form-item>
    <el-form-item label="项目id" prop="proId">
      <el-input v-model="dataForm.proId" placeholder="项目id"></el-input>
    </el-form-item>
    <el-form-item label="是否删除（0否， 1是）" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否删除（0否， 1是）"></el-input>
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
          majorId: '',
          proId: '',
          isDelete: '',
          addby: '',
          addbytime: '',
          lastmodifiedby: '',
          lastmodifiedbytime: ''
        },
        dataRule: {
          majorId: [
            { required: true, message: '专业id不能为空', trigger: 'blur' }
          ],
          proId: [
            { required: true, message: '项目id不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除（0否， 1是）不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/draw/choseexpert/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.majorId = data.choseExpert.majorId
                this.dataForm.proId = data.choseExpert.proId
                this.dataForm.isDelete = data.choseExpert.isDelete
                this.dataForm.addby = data.choseExpert.addby
                this.dataForm.addbytime = data.choseExpert.addbytime
                this.dataForm.lastmodifiedby = data.choseExpert.lastmodifiedby
                this.dataForm.lastmodifiedbytime = data.choseExpert.lastmodifiedbytime
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
              url: this.$http.adornUrl(`/draw/choseexpert/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'majorId': this.dataForm.majorId,
                'proId': this.dataForm.proId,
                'isDelete': this.dataForm.isDelete,
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
