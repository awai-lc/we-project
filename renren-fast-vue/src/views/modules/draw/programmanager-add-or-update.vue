<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm"
             @keyup.enter.native="dataFormSubmit()"
             label-width="180px" size="mini" style="margin-bottom: 5px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="项目编码" prop="code">
            <el-input v-model="dataForm.code" placeholder="项目编码" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目名称" prop="name">
            <el-input v-model="dataForm.name" placeholder="项目名称" :disabled="proNameDisabled"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目状态" prop="proStatus">
            <el-select v-model="dataForm.proStatus" clearable placeholder="请选择" :disabled="true" style="width: 100%">
              <el-option
                v-for="item in proStatuses"
                :key="item.proStatus"
                :label="item.label"
                :value="item.proStatus">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="采购单位" prop="purchasingId">
            <el-autocomplete :disabled="disabled" style="width: 100%"
                             class="inline-input"
                             v-model="dataForm.purchasingId"
                             :fetch-suggestions="querySearch"
                             placeholder="请输入内容"
                             :trigger-on-focus="false"
                             @select="handleSelect"
            ></el-autocomplete>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="抽取单位" prop="extractionUnit">
            <el-input v-model="dataForm.extractionUnit" placeholder="抽取单位" :disabled="disabled"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="采购方式" prop="purWay">
            <el-select v-model="dataForm.purWay" clearable placeholder="请选择" :disabled="disabled" style="width: 100%">
              <el-option
                v-for="item in purWays"
                :key="item.purWay"
                :label="item.label"
                :value="item.purWay">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="评审开始时间" prop="startReview">
            <el-date-picker :disabled="disabled" style="width: 100%"
                            v-model="dataForm.startReview"
                            type="datetime"
                            placeholder="选择日期时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="评审结束时间" prop="endReview">
            <el-date-picker :disabled="disabled" style="width: 100%"
                            v-model="dataForm.endReview"
                            type="datetime"
                            placeholder="选择日期时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="政府采购计划备案号" prop="govProRecordNumber">
            <el-input v-model="dataForm.govProRecordNumber" placeholder="采购备案号" :disabled="disabled"
                      style="width: 100%"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="参与采购评审人代表" prop="govProPerson">
            <el-input v-model="dataForm.govProPerson" placeholder="参与采购评审人代表" :disabled="disabled"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="评审内容" prop="reviewContent">
            <el-input v-model="dataForm.reviewContent" placeholder="评审内容" :disabled="disabled"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="预算金额(万元)" prop="budgetAmount">
            <el-input-number v-model="dataForm.budgetAmount" :precision="2" :step="0.1" :max="10000" style="width: 100%"
                             :disabled="disabled"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="抽取单位联系人" prop="extractionUnitContact">
            <el-input v-model="dataForm.extractionUnitContact" placeholder="抽取单位联系人" :disabled="disabled"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="抽取单位电话" prop="extractionUnitPhone">
            <el-input v-model="dataForm.extractionUnitPhone" placeholder="抽取单位电话" :disabled="disabled"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目所属监管地" prop="supervisoryPlaceId">
            <el-select v-model="dataForm.supervisoryPlaceId" clearable placeholder="请选择" :disabled="disabled"
                       style="width: 100%">
              <el-option
                v-for="item in supervisoryPlaces"
                :key="item.supervisoryPlaceId"
                :label="item.label"
                :value="item.supervisoryPlaceId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="评审地址" prop="address">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="评审地址"
              v-model="dataForm.address" :disabled="disabled">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="回避专家单位" prop="avoidUnit">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="回避专家单位"
              v-model="dataForm.avoidUnit" :disabled="disabled">
            </el-input>

          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="备注" prop="remark">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="备注"
              v-model="dataForm.remark" :disabled="disabled">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-button @click="addMajor()">新增</el-button>
    <el-table size="mini"
              :data="tableData"
              ref="multipleTable"
              max-height="200"
              border
              style="width: 100%;height: 200px;">
      <el-table-column prop="majorCode" label="专业编码">
      </el-table-column>
      <el-table-column prop="majorName" label="专业名称">
      </el-table-column>
      <el-table-column label="需抽取专家数">
        <template slot-scope="scope">

          <el-input-number v-if="scope.row.isEdit == 1" v-model="scope.row.needCount" :min="1"
                           :max="scope.row.expertCount" label="描述文字" size="mini"></el-input-number>

          <span v-else>{{scope.row.needCount}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="chosedCount" label="已抽取专家数">
      </el-table-column>
      <el-table-column prop="expertCount" label="库存专家数">

      </el-table-column>
      <el-table-column prop="placeName" label="专业所属地区">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="deleteRow(scope.$index, tableData)" type="text" size="small">删除</el-button>
          <el-button type="text" @click="mod(scope.$index, scope.row)" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row :gutter="24" style="margin-top: 10px">
      <el-col :span="9" :offset="7">
        <el-button type="primary" @click="dataFormSubmit()" :disabled="saveDisabled">保存</el-button>
        <el-button @click="resetForm()" :disabled="resetBtnDisabled">重置</el-button>
        <el-button @click="formUpdate()" :disabled="updateBtnDisabled">修改</el-button>
        <div v-if="specialLogic" style="display: inline">
          <el-popover
            v-model="specialLogicVisible"
            placement="top"
            width="300">
            <el-input v-model="specialLogicList" style="width: 100%"></el-input>
            <div style="text-align: center; margin-top: 10px">
              <el-button type="primary" size="mini" @click="specialLogicVisible = false">确定</el-button>
            </div>
            <el-button slot="reference" :disabled="chouQuDisabled">抽取</el-button>
          </el-popover>
        </div>
        <el-button v-if="!specialLogic" :disabled="chouQuDisabled">抽取</el-button>
        <!--<el-button @click="singlePrint" icon="el-icon-printer" :disabled="printDisabled">打印</el-button>-->
        <el-button @click="singlePrint" icon="el-icon-printer" :disabled="printDisabled">打印</el-button>
      </el-col>
    </el-row>
    <!-- 弹窗, 新增 / 修改 -->
    <el-dialog
      :close-on-click-modal="false"
      :visible.sync="majorAddVisible">
      <add-or-update ref="addOrUpdate" @refreshDataList=""></add-or-update>
    </el-dialog>
    <div class="demo">
      <form id="form1" hidden>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="middle">
              <table id="table1" width="80%" style="border:solid 1px black;border-collapse:collapse">
                <caption id="caption1" align="center">专家抽取</caption>
                <tr>
                  <td style="border:solid 1px black">序号</td>
                  <td style="border:solid 1px black">专家名称</td>
                  <td style="border:solid 1px black">专家单位</td>
                  <td style="border:solid 1px black">专家专业</td>
                  <td style="border:solid 1px black">电话</td>
                  <td style="border:solid 1px black">抽取时间</td>
                </tr>
                <tr>
                  <td style="border:solid 1px black">序号</td>
                  <td style="border:solid 1px black">专家名称</td>
                  <td style="border:solid 1px black">专家单位</td>
                  <td style="border:solid 1px black">专家专业</td>
                  <td style="border:solid 1px black">电话</td>
                  <td style="border:solid 1px black">抽取时间</td>
                </tr>
                <tr>
                  <td style="border:solid 1px black">序号</td>
                  <td style="border:solid 1px black">专家名称</td>
                  <td style="border:solid 1px black">专家单位</td>
                  <td style="border:solid 1px black">专家专业</td>
                  <td style="border:solid 1px black">电话</td>
                  <td style="border:solid 1px black">抽取时间</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</template>


<script>
  import AddOrUpdate from './major'
  import {PrintAccount} from '@/tools/doPrint.js'
  import {getLodop} from '@/tools/lodop.js'

  export default {
    components: {
      AddOrUpdate
    },
    created() {
      /*  console.log(this.$store.state.common.mainTabsActiveName);
        var tab = this.$store.state.common.mainTabs.filter(item => item.name === this.$store.state.common.mainTabsActiveName);

        tab[0].title ="123";*/
      //获取传入的参数
      console.log(this.$route)
      var param = this.$route.query;
      if (param && param.id != '0') {
        this.init(param.id)
      }
    },
    mounted() {
    },
    data() {
      return {
        majorAddVisible: false,
        specialLogicList: '',
        specialLogicVisible: false,
        specialLogic: true,
        disabled: false,
        resetBtnDisabled: false,
        updateBtnDisabled: true,
        isRouterAlive: true,
        saveDisabled: false,
        chouQuDisabled: false,
        printDisabled: true,
        proNameDisabled: false,
        restaurants: [],
        dataForm: {
          id: 0,
          code: '',
          name: '',
          proStatus: '1',
          purchasingId: '',
          extractionUnit: '',
          purWay: '1',
          startReview: '',
          endReview: '',
          govProRecordNumber: '',
          govProPerson: '',
          reviewContent: '',
          budgetAmount: '',
          extractionUnitContact: '',
          extractionUnitPhone: '',
          address: '',
          supervisoryPlaceId: '1',
          avoidUnit: '',
          remark: ''
        },
        dataRule: {
          code: [],
          name: [
            {required: true, message: '项目名称不能为空', trigger: 'blur'}
          ],
          proStatus: [],
          purchasingId: [
            {required: true, message: '采购单位不能为空', trigger: 'blur'}
          ],
          extractionUnit: [
            {required: true, message: '抽取单位不能为空', trigger: 'blur'}
          ],
          purWay: [
            {required: true, message: '采购方式不能为空', trigger: 'blur'}
          ],
          startReview: [
            {required: true, message: '评审开始时间不能为空', trigger: 'blur'}
          ],
          endReview: [
            {required: true, message: '评审结束时间不能为空', trigger: 'blur'}
          ],
          govProRecordNumber: [],
          govProPerson: [],
          reviewContent: [
            {required: true, message: '评审内容不能为空', trigger: 'blur'}
          ],
          budgetAmount: [
            {required: true, message: '预算金额不能为空', trigger: 'blur'}
          ],
          extractionUnitContact: [
            {required: true, message: '抽取单位联系人不能为空', trigger: 'blur'}
          ],
          extractionUnitPhone: [
            {required: true, message: '抽取单位电话不能为空', trigger: 'blur'}
          ],
          address: [
            {required: true, message: '评审地址不能为空', trigger: 'blur'}
          ],
          supervisoryPlaceId: [
            {required: true, message: '项目所属监管地不能为空', trigger: 'blur'}
          ],
          avoidUnit: [],
          remark: []

        },
        purWays: [{
          purWay: '1',
          label: '公开招标'
        }, {
          purWay: '2',
          label: '竞争性谈判'
        }, {
          purWay: '3',
          label: '竞争性磋商'
        }, {
          purWay: '4',
          label: '询价采购'
        }, {
          purWay: '5',
          label: '其他'
        }],
        tableData: [{
          majorId: '1',
          majorCode: 'C123',
          majorName: '王小虎',
          needCount: '1',
          chosedCount: 0,
          expertCount: 100,
          placeName: '湖北省',
          isEdit: 0
        }],
        supervisoryPlaces: [{
          supervisoryPlaceId: '1',
          label: '省本级'
        }, {
          supervisoryPlaceId: '2',
          label: '武汉市'
        }, {
          supervisoryPlaceId: '3',
          label: '黄石市'
        }, {
          supervisoryPlaceId: '4',
          label: '十堰市'
        }, {
          supervisoryPlaceId: '5',
          label: '宜昌市'
        }, {
          supervisoryPlaceId: '6',
          label: '襄樊市'
        }, {
          supervisoryPlaceId: '7',
          label: '鄂州市'
        }, {
          supervisoryPlaceId: '8',
          label: '荆门市'
        }, {
          supervisoryPlaceId: '9',
          label: '孝感市'
        }, {
          supervisoryPlaceId: '10',
          label: '荆州市'
        }, {
          supervisoryPlaceId: '11',
          label: '黄冈市'
        }, {
          supervisoryPlaceId: '12',
          label: '随州市'
        }, {
          supervisoryPlaceId: '13',
          label: '仙桃市'
        }, {
          supervisoryPlaceId: '14',
          label: '潜江市'
        }, {
          supervisoryPlaceId: '15',
          label: '天门市'
        }, {
          supervisoryPlaceId: '16',
          label: '神农架林区'
        }],
        proStatuses: [{
          proStatus: '1',
          label: '底稿'
        }, {
          proStatus: '2',
          label: '待抽取'
        }, {
          proStatus: '3',
          label: '抽取中'
        }, {
          proStatus: '4',
          label: '抽取完成'
        }]
      }
    },
    methods: {
      init(id) {
        this.dataForm.id = id || 0;
        this.disabled = true;
        this.$nextTick(() => {
          /*   tab[0].name = Vue.set()*/
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/draw/programmanager/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.updateBtnDisabled = false;
                this.chouQuDisabled = false;
                this.resetBtnDisabled = true;
                this.saveDisabled = true;
                this.proNameDisabled = true;
                if(this.dataForm.status == 4){
                  this.printDisabled = false;
                }
                this.dataForm.code = data.programManager.code;
                this.dataForm.name = data.programManager.name;
                this.dataForm.proStatus = data.programManager.proStatus + '';
                this.dataForm.purchasingId = data.programManager.purchasingId;
                this.dataForm.extractionUnit = data.programManager.extractionUnit;
                this.dataForm.purWay = data.programManager.purWay + '';
                this.dataForm.startReview = data.programManager.startReview;
                this.dataForm.endReview = data.programManager.endReview;
                this.dataForm.govProRecordNumber = data.programManager.govProRecordNumber;
                this.dataForm.govProPerson = data.programManager.govProPerson;
                this.dataForm.reviewContent = data.programManager.reviewContent;
                this.dataForm.budgetAmount = data.programManager.budgetAmount;
                this.dataForm.extractionUnitContact = data.programManager.extractionUnitContact;
                this.dataForm.extractionUnitPhone = data.programManager.extractionUnitPhone;
                this.dataForm.address = data.programManager.address;
                this.dataForm.supervisoryPlaceId = data.programManager.supervisoryPlaceId + '';
                this.dataForm.avoidUnit = data.programManager.avoidUnit;
                this.dataForm.remark = data.programManager.remark;
              }
            })
          }
        })
      },
      resetForm() {
        this.$refs['dataForm'].resetFields()
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      },
      formUpdate() {
        this.disabled = false;
        this.saveDisabled = false;
        this.chouQuDisabled = true;
        this.printDisabled = true;
      },
      addMajor() {
        this.majorAddVisible = true;
      },
      singlePrint() {
        this.$http({
          url: this.$http.adornUrl(`/draw/choseexpert/listByProId/${this.dataForm.id}`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            if (data.choseExpert.length == 0) {
              this.$message.error("请先抽取专家！")
              return;
            }
            var proName = this.dataForm.name;
            document.getElementById("table1").innerHTML = '';
            var html = '<caption  align="center">' + proName + '项目抽取结果</caption>';
            html += '<tr>\n' +
              '                  <td style="border:solid 1px black">序号</td>\n' +
              '                  <td style="border:solid 1px black">专家名称</td>\n' +
              '                  <td style="border:solid 1px black">专家单位</td>\n' +
              '                  <td style="border:solid 1px black">专家专业</td>\n' +
              '                  <td style="border:solid 1px black">电话</td>\n' +
              '                  <td style="border:solid 1px black">地址</td>\n' +
              '                  <td style="border:solid 1px black">抽取时间</td>\n' +
              '                </tr>';
            var LODOP = getLodop();
            for (var k = 0; k < data.choseExpert.length; k++) {
              var a = data.choseExpert[k];
              var num = k+1;
              html += '<tr>\n' +
                '                  <td style="border:solid 1px black">' + num + '</td>\n' +
                '                    <td style="border:solid 1px black">' + a.expertName + '</td>\n' +
                '                  <td style="border:solid 1px black">' + a.unit + '</td>\n' +
                '                   <td style="border:solid 1px black">' + a.majorName + '</td>\n' +
                '                  <td style="border:solid 1px black">' + a.phone + '</td>\n' +
                '                  <td style="border:solid 1px black">' + a.address + '</td>\n' +
                '                  <td style="border:solid 1px black">' + a.addbytime + '</td>\n' +
                '                </tr>';
            }
            document.getElementById("table1").innerHTML = html;
            LODOP.ADD_PRINT_HTM(30, 0, '210mm', '297mm', document.getElementById("form1").innerHTML);
            LODOP.PREVIEW()
          }
        });
      },
      querySearch(queryString, cb) {
        var that = this;
        var url = '/draw/queryByName';
        this.$http({
          url: this.$http.adornUrl(`/draw/unit/queryByName/` + queryString),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            that.items = [];
            var ds = data.list;
            for (var i = 0; i < ds.length; i++) {
              that.items.push({"value": ds[i]});
            }
            cb(that.items);
          }
        });
      }
      ,
      handleSelect(item) {
      }
      ,
      mod(index, row) {
        row.isEdit = 1
      }
      ,
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.saveDisabled = true;
            this.$http({
              url: this.$http.adornUrl(`/draw/programmanager/save`),
              method: 'post',
              data: this.$http.adornData({
                'programManager': {
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
                'majorList': this.tableData
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    console.log("dataFormSubmit:" + data.message);
                    this.init(data.msg);
                  }
                })
              } else {
                this.saveDisabled = false;
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
