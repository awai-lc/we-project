<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="项目编码" prop="code">
      <el-input v-model="dataForm.code" placeholder="项目编码"></el-input>
    </el-form-item>
    <el-form-item label="项目名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="项目名称"></el-input>
    </el-form-item>
    <el-form-item label="项目状态（1底稿，2可抽取，3抽取中，4需补抽，5完成）" prop="proStatus">
      <el-input v-model="dataForm.proStatus" placeholder="项目状态（1底稿，2可抽取，3抽取中，4需补抽，5完成）"></el-input>
    </el-form-item>
    <el-form-item label="采购单位ID" prop="purchasingId">
      <el-input v-model="dataForm.purchasingId" placeholder="采购单位ID"></el-input>
    </el-form-item>
    <el-form-item label="抽取单位ID" prop="extractionUnit">
      <el-input v-model="dataForm.extractionUnit" placeholder="抽取单位ID"></el-input>
    </el-form-item>
    <el-form-item label="采购方式ID" prop="purWay">
      <el-input v-model="dataForm.purWay" placeholder="采购方式ID"></el-input>
    </el-form-item>
    <el-form-item label="评审开始时间" prop="startReview">
      <el-input v-model="dataForm.startReview" placeholder="评审开始时间"></el-input>
    </el-form-item>
    <el-form-item label="评审结束时间" prop="endReview">
      <el-input v-model="dataForm.endReview" placeholder="评审结束时间"></el-input>
    </el-form-item>
    <el-form-item label="采购备案号" prop="govProRecordNumber">
      <el-input v-model="dataForm.govProRecordNumber" placeholder="采购备案号"></el-input>
    </el-form-item>
    <el-form-item label="参与采购评审人代表" prop="govProPerson">
      <el-input v-model="dataForm.govProPerson" placeholder="参与采购评审人代表"></el-input>
    </el-form-item>
    <el-form-item label="评审内容" prop="reviewContent">
      <el-input v-model="dataForm.reviewContent" placeholder="评审内容"></el-input>
    </el-form-item>
    <el-form-item label="预算金额" prop="budgetAmount">
      <el-input v-model="dataForm.budgetAmount" placeholder="预算金额"></el-input>
    </el-form-item>
    <el-form-item label="抽取单位联系人" prop="extractionUnitContact">
      <el-input v-model="dataForm.extractionUnitContact" placeholder="抽取单位联系人"></el-input>
    </el-form-item>
    <el-form-item label="抽取单位电话" prop="extractionUnitPhone">
      <el-input v-model="dataForm.extractionUnitPhone" placeholder="抽取单位电话"></el-input>
    </el-form-item>
    <el-form-item label="评审地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="评审地址"></el-input>
    </el-form-item>
    <el-form-item label="项目所属监管地ID" prop="supervisoryPlaceId">
      <el-input v-model="dataForm.supervisoryPlaceId" placeholder="项目所属监管地ID"></el-input>
    </el-form-item>
    <el-form-item label="回避专家单位" prop="avoidUnit">
      <el-input v-model="dataForm.avoidUnit" placeholder="回避专家单位"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
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
          code: '',
          name: '',
          proStatus: '',
          purchasingId: '',
          extractionUnit: '',
          purWay: '',
          startReview: '',
          endReview: '',
          govProRecordNumber: '',
          govProPerson: '',
          reviewContent: '',
          budgetAmount: '',
          extractionUnitContact: '',
          extractionUnitPhone: '',
          address: '',
          supervisoryPlaceId: '',
          avoidUnit: '',
          remark: '',
          addby: '',
          addbytime: '',
          lastmodifiedby: '',
          lastmodifiedbytime: ''
        },
        dataRule: {
          code: [
            { required: true, message: '项目编码不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '项目名称不能为空', trigger: 'blur' }
          ],
          proStatus: [
            { required: true, message: '项目状态（1底稿，2可抽取，3抽取中，4需补抽，5完成）不能为空', trigger: 'blur' }
          ],
          purchasingId: [
            { required: true, message: '采购单位ID不能为空', trigger: 'blur' }
          ],
          extractionUnit: [
            { required: true, message: '抽取单位ID不能为空', trigger: 'blur' }
          ],
          purWay: [
            { required: true, message: '采购方式ID不能为空', trigger: 'blur' }
          ],
          startReview: [
            { required: true, message: '评审开始时间不能为空', trigger: 'blur' }
          ],
          endReview: [
            { required: true, message: '评审结束时间不能为空', trigger: 'blur' }
          ],
          govProRecordNumber: [
            { required: true, message: '采购备案号不能为空', trigger: 'blur' }
          ],
          govProPerson: [
            { required: true, message: '参与采购评审人代表不能为空', trigger: 'blur' }
          ],
          reviewContent: [
            { required: true, message: '评审内容不能为空', trigger: 'blur' }
          ],
          budgetAmount: [
            { required: true, message: '预算金额不能为空', trigger: 'blur' }
          ],
          extractionUnitContact: [
            { required: true, message: '抽取单位联系人不能为空', trigger: 'blur' }
          ],
          extractionUnitPhone: [
            { required: true, message: '抽取单位电话不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '评审地址不能为空', trigger: 'blur' }
          ],
          supervisoryPlaceId: [
            { required: true, message: '项目所属监管地ID不能为空', trigger: 'blur' }
          ],
          avoidUnit: [
            { required: true, message: '回避专家单位不能为空', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/draw/programmanager/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.code = data.programManager.code
                this.dataForm.name = data.programManager.name
                this.dataForm.proStatus = data.programManager.proStatus
                this.dataForm.purchasingId = data.programManager.purchasingId
                this.dataForm.extractionUnit = data.programManager.extractionUnit
                this.dataForm.purWay = data.programManager.purWay
                this.dataForm.startReview = data.programManager.startReview
                this.dataForm.endReview = data.programManager.endReview
                this.dataForm.govProRecordNumber = data.programManager.govProRecordNumber
                this.dataForm.govProPerson = data.programManager.govProPerson
                this.dataForm.reviewContent = data.programManager.reviewContent
                this.dataForm.budgetAmount = data.programManager.budgetAmount
                this.dataForm.extractionUnitContact = data.programManager.extractionUnitContact
                this.dataForm.extractionUnitPhone = data.programManager.extractionUnitPhone
                this.dataForm.address = data.programManager.address
                this.dataForm.supervisoryPlaceId = data.programManager.supervisoryPlaceId
                this.dataForm.avoidUnit = data.programManager.avoidUnit
                this.dataForm.remark = data.programManager.remark
                this.dataForm.addby = data.programManager.addby
                this.dataForm.addbytime = data.programManager.addbytime
                this.dataForm.lastmodifiedby = data.programManager.lastmodifiedby
                this.dataForm.lastmodifiedbytime = data.programManager.lastmodifiedbytime
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
              url: this.$http.adornUrl(`/draw/programmanager/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'code': this.dataForm.code,
                'name': this.dataForm.name,
                'proStatus': this.dataForm.proStatus,
                'purchasingId': this.dataForm.purchasingId,
                'extractionUnit': this.dataForm.extractionUnit,
                'purWay': this.dataForm.purWay,
                'startReview': this.dataForm.startReview,
                'endReview': this.dataForm.endReview,
                'govProRecordNumber': this.dataForm.govProRecordNumber,
                'govProPerson': this.dataForm.govProPerson,
                'reviewContent': this.dataForm.reviewContent,
                'budgetAmount': this.dataForm.budgetAmount,
                'extractionUnitContact': this.dataForm.extractionUnitContact,
                'extractionUnitPhone': this.dataForm.extractionUnitPhone,
                'address': this.dataForm.address,
                'supervisoryPlaceId': this.dataForm.supervisoryPlaceId,
                'avoidUnit': this.dataForm.avoidUnit,
                'remark': this.dataForm.remark,
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
