<template>
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="180px" size="mini">
    <el-form-item label="项目编码" prop="code">
      <el-input v-model="dataForm.code" placeholder="项目编码" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="项目名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="项目名称"></el-input>
    </el-form-item>
    <el-form-item label="项目状态" prop="proStatus">
      <el-input v-model="dataForm.proStatus" placeholder="项目状态" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="采购单位" prop="purchasingId">
     <el-select
        v-model="dataForm.purchasingId"
        multiple
        filterable
        remote
        reserve-keyword
        :remote-method="remoteMethod"
        :loading="loading">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="抽取单位" prop="extractionUnit">
      <el-input v-model="dataForm.extractionUnit" placeholder="抽取单位"></el-input>
    </el-form-item>
    <el-form-item label="采购方式" prop="purWay">
      <el-select v-model="dataForm.purWay" clearable placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="评审开始时间" prop="startReview">

               <el-date-picker
                 v-model="dataForm.startReview"
                 type="datetime"
                 placeholder="选择日期时间">
               </el-date-picker>

    </el-form-item>
    <el-form-item label="评审结束时间" prop="endReview">
          <el-date-picker
            v-model="dataForm.endReview"
            type="datetime"
            placeholder="选择日期时间">
          </el-date-picker>
    </el-form-item>
    <el-form-item label="政府采购计划备案号" prop="govProRecordNumber">
      <el-input v-model="dataForm.govProRecordNumber" placeholder="采购备案号" ></el-input>
    </el-form-item>
    <el-form-item label="参与采购评审人代表" prop="govProPerson">
      <el-input v-model="dataForm.govProPerson" placeholder="参与采购评审人代表" ></el-input>
    </el-form-item>
    <el-form-item label="评审内容" prop="reviewContent">
      <el-input v-model="dataForm.reviewContent" placeholder="评审内容"></el-input>
    </el-form-item>
    <el-form-item label="预算金额(万元)" prop="budgetAmount">
    <el-input-number v-model="dataForm.budgetAmount" :precision="2" :step="0.1" :max="10"></el-input-number>

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
    <el-form-item label="项目所属监管地" prop="supervisoryPlaceId">
       <el-select v-model="dataForm.supervisoryPlaceId" clearable placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>

    </el-form-item>
    <el-form-item label="回避专家单位" prop="avoidUnit">
      <el-input v-model="dataForm.avoidUnit" placeholder="回避专家单位"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
    </el-form-item>
     <el-form-item>
        <el-button type="primary" @click="dataFormSubmit()">保存</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button @click="resetForm('ruleForm')">修改</el-button>
        <el-button @click="resetForm('ruleForm')">打印</el-button>
      </el-form-item>
    </el-form>
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
          remark: ''
        },
        dataRule: {
          code: [

          ],
          name: [
            { required: true, message: '项目名称不能为空', trigger: 'blur' }
          ],
          proStatus: [

          ],
          purchasingId: [
            { required: true, message: '采购单位不能为空', trigger: 'blur' }
          ],
          extractionUnit: [
            { required: true, message: '抽取单位不能为空', trigger: 'blur' }
          ],
          purWay: [
            { required: true, message: '采购方式不能为空', trigger: 'blur' }
          ],
          startReview: [
            { required: true, message: '评审开始时间不能为空', trigger: 'blur' }
          ],
          endReview: [
            { required: true, message: '评审结束时间不能为空', trigger: 'blur' }
          ],
          govProRecordNumber: [

          ],
          govProPerson: [

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
            { required: true, message: '项目所属监管地不能为空', trigger: 'blur' }
          ],
          avoidUnit: [

          ],
          remark: [

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
              url: this.$http.adornUrl(`/draw/programmanager/save`),
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
