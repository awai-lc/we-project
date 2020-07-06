<template>
    <el-form :model="dataForm" :inline="true" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
     label-width="180px" size="mini">
    <el-form-item label="项目编码" prop="code">
      <el-input v-model="dataForm.code" placeholder="项目编码" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="项目名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="项目名称" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="项目状态" prop="proStatus">
      <el-input v-model="dataForm.proStatus" placeholder="项目状态" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="采购单位" prop="purchasingId">
     <el-autocomplete :disabled="disabled"
          class="inline-input"
        v-model="dataForm.purchasingId"
          :fetch-suggestions="querySearch"
          placeholder="请输入内容"
          :trigger-on-focus="false"
          @select="handleSelect"
        ></el-autocomplete>
    </el-form-item>
    <el-form-item label="抽取单位" prop="extractionUnit">
      <el-input v-model="dataForm.extractionUnit" placeholder="抽取单位" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="采购方式" prop="purWay">
      <el-select v-model="dataForm.purWay" clearable placeholder="请选择" :disabled="disabled">
        <el-option
          v-for="item in purWays"
          :key="item.purWay"
          :label="item.label"
          :value="item.purWay">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="评审开始时间" prop="startReview" >

               <el-date-picker :disabled="disabled"
                 v-model="dataForm.startReview"
                 type="datetime"
                 placeholder="选择日期时间">
               </el-date-picker>

    </el-form-item>
    <el-form-item label="评审结束时间" prop="endReview" >
          <el-date-picker :disabled="disabled"
            v-model="dataForm.endReview"
            type="datetime"
            placeholder="选择日期时间">
          </el-date-picker>
    </el-form-item>
    <el-form-item label="政府采购计划备案号" prop="govProRecordNumber" >
      <el-input v-model="dataForm.govProRecordNumber" placeholder="采购备案号" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="参与采购评审人代表" prop="govProPerson">
      <el-input v-model="dataForm.govProPerson" placeholder="参与采购评审人代表" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="评审内容" prop="reviewContent">
      <el-input v-model="dataForm.reviewContent" placeholder="评审内容" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="预算金额(万元)" prop="budgetAmount" >
    <el-input-number v-model="dataForm.budgetAmount" :precision="2" :step="0.1" :max="10" :disabled="disabled"></el-input-number>

    </el-form-item>
    <el-form-item label="抽取单位联系人" prop="extractionUnitContact" >
      <el-input v-model="dataForm.extractionUnitContact" placeholder="抽取单位联系人" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="抽取单位电话" prop="extractionUnitPhone">
      <el-input v-model="dataForm.extractionUnitPhone" placeholder="抽取单位电话"  :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="评审地址" prop="address" >
      <el-input v-model="dataForm.address" placeholder="评审地址" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="项目所属监管地" prop="supervisoryPlaceId" >
       <el-select v-model="dataForm.supervisoryPlaceId" clearable placeholder="请选择" :disabled="disabled">
            <el-option
              v-for="item in supervisoryPlaces"
              :key="item.supervisoryPlaceId"
              :label="item.label"
              :value="item.supervisoryPlaceId">
            </el-option>
          </el-select>

    </el-form-item>
    <el-form-item label="回避专家单位" prop="avoidUnit" >
      <el-input v-model="dataForm.avoidUnit" placeholder="回避专家单位" :disabled="disabled"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="备注" :disabled="disabled"></el-input>
    </el-form-item>
     <el-form-item >
       <el-button @click="resetForm('ruleForm')">新增</el-button>
       <el-table    size="mini"
                   :data="tableData"
                   border
                   ref="multipleTable"
                   style="width: 100%">
                   <el-table-column
                     fixed
                     prop="majorCode"
                     label="专业编码"
                     width="150">
                   </el-table-column>
                   <el-table-column
                     prop="majorName"
                     label="专业名称"
                     width="120">
                   </el-table-column>
                   <el-table-column
                     prop="needCount"
                     label="需抽取专家数"
                     width="120">
                   </el-table-column>
                   <el-table-column
                     prop="chosedCount"
                     label="已抽取专家数"
                     width="120">
                   </el-table-column>
                   <el-table-column
                     prop="expertCount"
                     label="库存专家数"
                     width="300">
                   </el-table-column>
                   <el-table-column
                     prop="placeName"
                     label="专业所属地区"
                     width="120">
                   </el-table-column>
                   <el-table-column
                     fixed="right"
                     label="操作"
                     width="100">
                     <template slot-scope="scope">
                       <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
                       <el-button type="text" size="small">编辑</el-button>
                     </template>
                   </el-table-column>
                 </el-table>
        </el-form-item>
     <el-form-item>
        <el-button type="primary" @click="dataFormSubmit()">保存</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button @click="formUpdate()">修改</el-button>
        <el-button @click="cancelUpdate()">取消修改</el-button>
        <el-button @click="resetForm('ruleForm')">打印</el-button>
      </el-form-item>
    </el-form>

</template>


<script>
  export default {
    data () {
      return {
        disabled:true,
        visible: false,
        isRouterAlive:true,
        dataForm: {
          id: 0,
          code: '1231',
          name: '12312',
          proStatus: '3123',
          purchasingId: '31231',
          extractionUnit: '1231',
          purWay: '2',
          startReview: '',
          endReview: '',
          govProRecordNumber: '123123',
          govProPerson: '12312',
          reviewContent: '12321',
          budgetAmount: '1231',
          extractionUnitContact: '123123',
          extractionUnitPhone: '12312',
          address: '12321',
          supervisoryPlaceId: '1',
          avoidUnit: '123',
          remark: '132'
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

        },
       purWays: [{
                purWay: '1',
                label: '公开招标'
              },{
                  purWay: '2',
                  label: '竞争性谈判'
               },{
                purWay: '3',
                label: '竞争性磋商'
               },{
                purWay: '4',
                label: '询价采购'
               },{
                 purWay: '5',
                 label: '其他'
               }],
               tableData: [{
                        majorId: '1',
                        majorCode: 'C123',
                        majorName: '王小虎',
                        needCount: '1',
                        chosedCount:0,
                        expertCount: 100,
                        placeName: '湖北省'
                      }],
                       supervisoryPlaces: [{
                                supervisoryPlaceId: '1',
                                label: '省本级'
                              }],
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
      dialogFormAdd() {
            for (var i = 0; i < this.multipleTable.length; i++) {
              return this.multipleTable[i];
            }
          },
      formUpdate(){
        this.disabled = false;
      },
      cancelUpdate(){
            this.disabled = true
            },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/draw/programmanager/save`),
              method: 'post',
              data: this.$http.adornData({
                'programManager':{
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
                  'remark': this.dataForm.remark
                },
                'majorList':this.tableData
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
