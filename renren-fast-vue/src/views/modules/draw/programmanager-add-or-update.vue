<template>
  <div v-loading="fullscreenLoading">
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="180px"
      size="mini"
      style="margin-bottom: 5px"
    >
      <el-row>
        <el-col :span="8">
          <el-form-item label="项目编码" prop="code">
            <el-input
              v-model="dataForm.code"
              placeholder="项目编码"
              :disabled="true"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目名称" prop="name">
            <el-input
              v-model="dataForm.name"
              placeholder="项目名称"
              :disabled="proNameDisabled"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目状态" prop="proStatus">
            <el-select
              v-model="dataForm.proStatus"
              clearable
              placeholder="请选择"
              :disabled="true"
              style="width: 100%"
            >
              <el-option
                v-for="item in proStatuses"
                :key="item.proStatus"
                :label="item.label"
                :value="item.proStatus"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="采购单位" prop="purchasingId">
            <el-autocomplete
              :disabled="disabled"
              style="width: 100%"
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
            <el-input
              v-model="dataForm.extractionUnit"
              placeholder="抽取单位"
              :disabled="true"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="采购方式" prop="purWay">
            <el-select
              v-model="dataForm.purWay"
              clearable
              placeholder="请选择"
              :disabled="disabled"
              style="width: 100%"
            >
              <el-option
                v-for="item in purWays"
                :key="item.purWay"
                :label="item.label"
                :value="item.purWay"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="评审开始时间" prop="startReview">
            <el-date-picker
              :disabled="disabled"
              style="width: 100%"
              v-model="dataForm.startReview"
              type="datetime"
              placeholder="选择日期时间"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="评审结束时间" prop="endReview">
            <el-date-picker
              :disabled="disabled"
              style="width: 100%"
              v-model="dataForm.endReview"
              type="datetime"
              placeholder="选择日期时间"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="参与采购评审人代表" prop="govProPerson">
            <el-input
              v-model="dataForm.govProPerson"
              placeholder="参与采购评审人代表"
              :disabled="disabled"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="评审内容" prop="reviewContent">
            <el-input
              v-model="dataForm.reviewContent"
              placeholder="评审内容"
              :disabled="disabled"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="预算金额(万元)" prop="budgetAmount">
            <el-input-number
              v-model="dataForm.budgetAmount"
              :precision="2"
              :step="0.1"
              :max="10000"
              style="width: 100%"
              :disabled="disabled"
            ></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目所属监管地" prop="supervisoryPlaceId">
            <el-select
              v-model="dataForm.supervisoryPlaceId"
              clearable
              placeholder="请选择"
              :disabled="disabled"
              style="width: 100%"
            >
              <el-option
                v-for="item in supervisoryPlaces"
                :key="item.supervisoryPlaceId"
                :label="item.label"
                :value="item.supervisoryPlaceId"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>


      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="抽取单位联系人" prop="extractionUnitContact">
            <el-input
              v-model="dataForm.extractionUnitContact"
              placeholder="抽取单位联系人"
              :disabled="disabled"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="抽取单位电话" prop="extractionUnitPhone">
            <el-input
              v-model="dataForm.extractionUnitPhone"
              placeholder="抽取单位电话"
              :disabled="disabled"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="备注" prop="remark">
            <el-input
              :rows="2"
              placeholder="备注"
              v-model="dataForm.remark"
              :disabled="disabled"
            >
            </el-input>
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
              v-model="dataForm.address"
              :disabled="disabled"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="回避专家单位" prop="avoidUnit">
            <el-select style="width:100%"
                       v-model="dataForm.avoidUnit"
                       multiple
                       filterable
                       remote
                       :disabled="disabled"
                       reserve-keyword
                       placeholder="请输入搜索关键词"
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
        </el-col>
      </el-row>
    </el-form>
    <el-button @click="addMajor()" :disabled="addMajorBtnDisabled"
    >新增
    </el-button
    >
    <el-table
      size="mini"
      :data="tableData"
      ref="multipleTable"
      max-height="200"
      border
      style="width: 100%;height: 200px;"
    >
      <el-table-column prop="majorCode" label="专业编码"></el-table-column>
      <el-table-column prop="majorName" label="专业名称"></el-table-column>
      <el-table-column label="需抽取专家数">
        <template slot-scope="scope">
          <el-input-number
            v-if="scope.row.isEdit == 1"
            v-model="scope.row.needCount"
            :min="1"
            :max="scope.row.expertCount"
            label="描述文字"
            size="mini"
          ></el-input-number>

          <span v-else>{{ scope.row.needCount }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="expertCount" label="库存专家数"></el-table-column>
      <el-table-column prop="placeName" label="专业所属地区"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            @click="deleteRow(scope.$index, tableData)"
            type="text"
            size="small"
            :disabled="addMajorBtnDisabled"
          >删除
          </el-button
          >
          <el-button
            type="text"
            @click="mod(scope.$index, scope.row)"
            size="small"
            :disabled="addMajorBtnDisabled"
          >编辑
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-row :gutter="24" style="margin-top: 10px">
      <el-col :span="11" :offset="6">
        <el-button
          type="primary"
          @click="dataFormSubmit()"
          :disabled="saveDisabled"
        >保存
        </el-button
        >
        <el-button @click="resetForm()" :disabled="resetBtnDisabled"
        >重置
        </el-button
        >
        <el-button @click="formUpdate()" :disabled="updateBtnDisabled"
        >修改
        </el-button
        >
        <div v-if="specialLogic" style="display: inline">
          <el-popover placement="top" width="300" v-model="specialLogicVisible">
            <el-input v-model="specialLogicList" style="width: 100%"></el-input>
            <div style="text-align: center; margin-top: 10px">
              <el-button
                type="primary"
                size="mini"
                @click="chouquMethod()">确定
              </el-button>
            </div>
            <el-button slot="reference" :disabled="chouQuDisabled">抽取
            </el-button>
          </el-popover>
        </div>
        <el-button v-if="!specialLogic" :disabled="chouQuDisabled" @click="chouquMethod()">抽取
        </el-button>
        <el-button @click="bcTableVisible = true" :disabled="chouQuDisabled">补抽</el-button>
        <el-button
          @click="singlePrint"
          icon="el-icon-printer"
          :disabled="printDisabled"
        >打印
        </el-button
        >
      </el-col>
    </el-row>
    <!-- 弹窗, 新增 / 修改 -->
    <el-dialog
      :title="'专业选择'"
      :close-on-click-modal="false"
      :visible.sync="majorAddVisible"
    >
      <major-select @fathermethod="fathermethod"></major-select>
    </el-dialog>
    <div class="demo">
      <form id="form1" hidden>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="middle">
            湖北华通工程咨询有限公司评审专家抽取结果表
          </td>
          </tr>
          <tr>
            <td align="center" valign="middle">
              <table  width="80%" border="0" cellspacing="0" cellpadding="0"><tbody><tr>
                <td>项目详情</td></tr></tbody></table>

            </td>
          </tr>
          <tr>
            <td align="center"  valign="middle">
              <table id="table2" width="80%" style="border: 1px solid black; border-collapse: collapse;">
                <tr>
                  <td style="border:solid 1px black">项目名称</td>
                  <td style="border:solid 1px black" colspan="3"></td>
                  <td style="border:solid 1px black">采购方式</td>
                  <td style="border:solid 1px black"></td>
                </tr>
                <tr>
                  <td style="border:solid 1px black">评审开始时间</td>
                  <td style="border:solid 1px black"></td>
                  <td style="border:solid 1px black">评审结束时间</td>
                  <td style="border:solid 1px black"></td>
                  <td style="border:solid 1px black">政府采购计划确认书</td>
                  <td style="border:solid 1px black"></td>
                </tr>
                <tr>
                  <td style="border:solid 1px black">抽取单位</td>
                  <td style="border:solid 1px black"></td>
                  <td style="border:solid 1px black">抽取单位联系人</td>
                  <td style="border:solid 1px black"></td>
                  <td style="border:solid 1px black">抽取单位电话</td>
                  <td style="border:solid 1px black"></td>
                </tr>
                <tr>
                  <td style="border:solid 1px black">采购单位</td>
                  <td style="border:solid 1px black"></td>
                  <td style="border:solid 1px black">采购人代表</td>
                  <td style="border:solid 1px black"></td>
                  <td style="border:solid 1px black">预算金额</td>
                  <td style="border:solid 1px black"></td>
                </tr>
                <tr>
                  <td style="border:solid 1px black">评审地址</td>
                  <td style="border:solid 1px black" colspan="5"></td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td align="center" valign="middle">
              <table  width="80%" border="0" cellspacing="0" cellpadding="0"><tbody><tr>
                <td>专家详情</td></tr></tbody></table>

            </td>
          </tr>
          <tr>
            <td align="center" valign="middle">
              <table
                id="table1"
                width="80%"
                style="border:solid 1px black;border-collapse:collapse"
              >
                <caption id="caption1" align="center">
                  专家抽取
                </caption>
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
    <el-dialog title="补抽列表" :visible.sync="bcTableVisible"  size="min">
      <div style="text-align: center;">
        <el-table size="min"
                  max-height="400"
                  border
                  :data="buCgridData"
                  style="width: 80%;height: 400px;margin-left: 10%">
          <el-table-column property="phone" label="专家手机号">
            <template slot-scope="scope">
              <el-input placeholder="请输入内容" v-model="scope.row.phone"></el-input>

            </template>
          </el-table-column>
          <el-table-column property="cancelReason" label="取消原因">
            <template slot-scope="scope">
              <el-select v-model="scope.row.cancelReason" placeholder="请选择">
                <el-option
                  v-for="item in cancelReasons"
                  :key="item.value"
                  :label="item.value"
                  :value="item.value">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="添加">
            <template slot-scope="scope">
              <el-button type="text" @click="addCancelLine(scope.$index, scope.row)" size="small"
              >添加
              </el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: center; margin-top: 10px">
        <el-button
          type="primary"
          size="mini"
          @click="bcMethod()">保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import MajorSelect from './major-select'
import {getLodop} from '@/tools/lodop.js'

export default {
    components: {
      MajorSelect
    },
    created () {
      /*  console.log(this.$store.state.common.mainTabsActiveName);
              var tab = this.$store.state.common.mainTabs.filter(item => item.name === this.$store.state.common.mainTabsActiveName);

              tab[0].title ="123"; */
      // 获取传入的参数
      var param = this.$route.query
      if (param && param.id && param.id !== '0') {
        console.log(param.id)
        this.init(param.id)
      // this.fetchData(param.id);
      }
    },
    data () {
      return {
        bcTableVisible: false,
        fullscreenLoading: false,
        majorAddVisible: false,
        specialLogicList: '',
        specialLogicVisible: false,
        specialLogic: false,
        disabled: false,
        resetBtnDisabled: false,
        updateBtnDisabled: true,
        isRouterAlive: true,
        saveDisabled: false,
        chouQuDisabled: true,
        printDisabled: true,
        proNameDisabled: false,
        addMajorBtnDisabled: false,
        restaurants: [],
        buCgridData: [{
          phone: '',
          cancelReason: '提前告知'
        }],
        dataForm: {
          id: 0,
          code: '',
          name: '',
          proStatus: '1',
          purchasingId: '',
          extractionUnit: '湖北华通工程咨询有限公司',
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
          supervisoryPlaceId: '',
          avoidUnit: [],
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
          avoidUnit: [],
          remark: []
        },
        options: [],
        value: [],
        list: [],
        loading: false,
        cancelReasons: [{
          value: "提前告知",
          label: "提前告知"
        }],
        purWays: [
          {
            purWay: '1',
            label: '公开招标'
          },
          {
            purWay: '2',
            label: '竞争性谈判'
          },
          {
            purWay: '3',
            label: '竞争性磋商'
          },
          {
            purWay: '4',
            label: '询价采购'
          },
          {
            purWay: '5',
            label: '邀请'
          },
          {
            purWay: '6',
            label: '其他'
          }
        ],
        tableData: [],
        supervisoryPlaces: [
          {
            supervisoryPlaceId: '1',
            label: '省本级'
          },
          {
            supervisoryPlaceId: '2',
            label: '武汉市'
          },
          {
            supervisoryPlaceId: '3',
            label: '黄石市'
          },
          {
            supervisoryPlaceId: '4',
            label: '十堰市'
          },
          {
            supervisoryPlaceId: '5',
            label: '宜昌市'
          },
          {
            supervisoryPlaceId: '6',
            label: '襄樊市'
          },
          {
            supervisoryPlaceId: '7',
            label: '鄂州市'
          },
          {
            supervisoryPlaceId: '8',
            label: '荆门市'
          },
          {
            supervisoryPlaceId: '9',
            label: '孝感市'
          },
          {
            supervisoryPlaceId: '10',
            label: '荆州市'
          },
          {
            supervisoryPlaceId: '11',
            label: '黄冈市'
          },
          {
            supervisoryPlaceId: '12',
            label: '随州市'
          },
          {
            supervisoryPlaceId: '13',
            label: '仙桃市'
          },
          {
            supervisoryPlaceId: '14',
            label: '潜江市'
          },
          {
            supervisoryPlaceId: '15',
            label: '天门市'
          },
          {
            supervisoryPlaceId: '16',
            label: '神农架林区'
          }
        ],
        proStatuses: [
          {
            proStatus: '1',
            label: '底稿'
          },
          {
            proStatus: '2',
            label: '待抽取'
          },
          {
            proStatus: '3',
            label: '抽取中'
          },
          {
            proStatus: '4',
            label: '抽取完成'
          }
        ]
      }
    },
    mounted () {
      var userName = this.$store.state.user.name
      if (userName == 'admin') {
        this.specialLogic = true
      } else {
        this.specialLogic = false
      }
      this.$http({
        url: this.$http.adornUrl(
          `/draw/expert/unitList`
        ),
        method: 'get',
        params: {

        }
      }).then(({data}) => {
        this.list = data.map(item => {
          return {value: `${item}`, label: `${item}`}
        })
      })
    },
    methods: {
      remoteMethod (query) {
        if (query !== '') {
          this.loading = true
          setTimeout(() => {
            this.loading = false
            this.options = this.list.filter(item => {
              return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1
            })
          }, 200)
        } else {
          this.options = []
        }
      },
      fathermethod (tableDates) {
        this.majorAddVisible = false
      // 去重
        for (var i = 0; i < tableDates.length; i++) {
          var flag = true
          for (var j = 0; j < this.tableData.length; j++) {
            if (tableDates[i].majorId == this.tableData[j].majorId) {
              flag = false
              break
            }
          }
          if (flag) {
            this.tableData.push(tableDates[i])
          }
        }
      },
      addCancelLine() {
        var gridDate = {
            phone: '',
            cancelReason: '提前告知'
          }
        this.buCgridData.push(gridDate);
      },
      fetchData(id) {
        // set tagsview title
        this.setTagsViewTitle(id)

      // set page title
        this.setPageTitle(id)
      },
      setTagsViewTitle (id) {
        const title = '项目详情'
        const route = Object.assign({}, this.tempRoute, {
          title: `${title}-${id}`
        })
        this.$store.dispatch('tagsView/updateVisitedView', route)
      },
      setPageTitle (id) {
        const title = '项目详情'
        document.title = `${title} - ${id}`
      },
      bcMethod(){
        this.bcTableVisible = false;
        this.fullscreenLoading = true;
        this.$http({
          url: this.$http.adornUrl(
            `/draw/choseexpert/bcLottery`
          ),
          method: "post",
          data:this.$http.adornData({
            proId: this.dataForm.id  || undefined,
            buCgridData: this.buCgridData
          })
        }).then(({data}) => {
          this.fullscreenLoading = false;
          if (data && data.code === 0) {
            this.$message({
              message: "抽取完成",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.init(this.dataForm.id);
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        })
      },
      chouquMethod () {
        this.specialLogicVisible = false
        this.fullscreenLoading = true
        this.$http({
          url: this.$http.adornUrl(
            `/draw/choseexpert/lottery`
          ),
          method: 'get',
          params: {
            'proId': this.dataForm.id,
            'phones': this.specialLogicList
          }
        }).then(({data}) => {
          this.fullscreenLoading = false
          if (data && data.code === 0) {
            this.$message({
              message: '抽取完成',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.init(this.dataForm.id)
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      init (id) {
        this.dataForm.id = id || 0
        this.disabled = true
        this.$nextTick(() => {
          /*   tab[0].name = Vue.set() */
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(
                `/draw/programmanager/info/${this.dataForm.id}`
              ),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.updateBtnDisabled = false
                this.chouQuDisabled = false
                this.resetBtnDisabled = true
                this.saveDisabled = true
                this.proNameDisabled = true
                this.addMajorBtnDisabled = true
                var programManager = data.programManagerDetail.programManager
                this.dataForm.code = programManager.code
                this.dataForm.name = programManager.name
                this.dataForm.proStatus = programManager.proStatus + ''
                console.log(this.dataForm.proStatus)
                if (this.dataForm.proStatus == 4) {
                  this.printDisabled = false
                }
                this.dataForm.purchasingId = programManager.purchasingId
                this.dataForm.extractionUnit = programManager.extractionUnit
                this.dataForm.purWay = programManager.purWay + ''
                this.dataForm.startReview = programManager.startReview
                this.dataForm.endReview = programManager.endReview
                this.dataForm.govProRecordNumber =
                  programManager.govProRecordNumber
                this.dataForm.govProPerson = programManager.govProPerson
                this.dataForm.reviewContent = programManager.reviewContent
                this.dataForm.budgetAmount = programManager.budgetAmount
                this.dataForm.extractionUnitContact =
                  programManager.extractionUnitContact
                this.dataForm.extractionUnitPhone =
                  programManager.extractionUnitPhone
                this.dataForm.address = programManager.address
                this.dataForm.supervisoryPlaceId =
                  programManager.supervisoryPlaceId + "";
                if (programManager.avoidUnit) {
                  this.dataForm.avoidUnit = programManager.avoidUnit.split(',');
                }
                this.dataForm.remark = programManager.remark
                data.programManagerDetail.choseMajorEntities.forEach(item => {
                  item.isEdit = 0
                })
                this.tableData = data.programManagerDetail.choseMajorEntities
              }
            })
          }
        })
      },
      resetForm () {
        this.$refs['dataForm'].resetFields()
      },
      deleteRow (index, rows) {
        rows.splice(index, 1)
      },
      formUpdate () {
        this.disabled = false
        this.saveDisabled = false
        this.chouQuDisabled = true
        this.printDisabled = true
        this.addMajorBtnDisabled = false
      },
      addMajor () {
        this.majorAddVisible = true
      },
      singlePrint () {
        this.$http({
          url: this.$http.adornUrl(
            `/draw/choseexpert/listByProId/${this.dataForm.id}`
          ),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            if (data.choseExpert.length == 0) {
              this.$message.error('请先抽取专家！')
              return
            }
            var proName = this.dataForm.name
            document.getElementById('table1').innerHTML = ''
            var html =
              ''
            html +=
              '<tr>\n' +
              '                  <td style="border:solid 1px black">序号</td>\n' +
              '                  <td style="border:solid 1px black">专家名称</td>\n' +
              '                  <td style="border:solid 1px black">专家单位</td>\n' +
              '                  <td style="border:solid 1px black">专家专业</td>\n' +
              '                  <td style="border:solid 1px black">联系方式</td>\n' +
              '                  <td style="border:solid 1px black">地址</td>\n' +
              '                  <td style="border:solid 1px black">抽取时间</td>\n' +
              '                </tr>'
            var LODOP = getLodop()
            for (var k = 0; k < data.choseExpert.choseExpertDtoList.length; k++) {
              var a = data.choseExpert.choseExpertDtoList[k]
              var num = k + 1
              html +=
                '<tr>\n' +
                '                  <td style="border:solid 1px black">' +
                num +
                '</td>\n' +
                '                    <td style="border:solid 1px black">' +
                a.expertName +
                '</td>\n' +
                '                  <td style="border:solid 1px black">' +
                a.unit +
                '</td>\n' +
                '                   <td style="border:solid 1px black">' +
                a.majorName +
                '</td>\n' +
                '                  <td style="border:solid 1px black">' +
                a.phone +
                '</td>\n' +
                '                  <td style="border:solid 1px black">' +
                a.address +
                '</td>\n' +
                '                  <td style="border:solid 1px black">' +
                a.addbytime +
                '</td>\n' +
                '                </tr>'
            }
            document.getElementById('table1').innerHTML = html;
            var programEnty = data.choseExpert.programManager;
            document.getElementById('table2').innerHTML = '';
            var html2 = '<tr>\n' +
                '        <td style="border:solid 1px black">项目名称</td>\n' +
                '        <td style="border:solid 1px black" colspan="3">'+programEnty.name +'</td>\n' +
                '        <td style="border:solid 1px black">采购方式</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.purWayText+'</td>\n' +
                '      </tr>\n' +
                '      <tr>\n' +
                '        <td style="border:solid 1px black">评审开始时间</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.startReviewText+'</td>\n' +
                '        <td style="border:solid 1px black">评审结束时间</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.endReviewText+'</td>\n' +
                '        <td style="border:solid 1px black">政府采购计划确认书</td>\n' +
                '        <td style="border:solid 1px black">'+""+'</td>\n' +
                '      </tr>\n' +
                '       <tr>\n' +
                '        <td style="border:solid 1px black">抽取单位</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.extractionUnit+'</td>\n' +
                '        <td style="border:solid 1px black">抽取单位联系人</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.extractionUnitContact+'</td>\n' +
                '        <td style="border:solid 1px black">抽取单位电话</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.extractionUnitPhone+'</td>\n' +
                '      </tr>\n' +
                '      <tr>\n' +
                '        <td style="border:solid 1px black">采购单位</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.purchasingId+'</td>\n' +
                '        <td style="border:solid 1px black">采购人代表</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.govProPerson+'</td>\n' +
                '        <td style="border:solid 1px black">预算金额</td>\n' +
                '        <td style="border:solid 1px black">'+programEnty.budgetAmount+'万元</td>\n' +
                '      </tr>\n' +
                '      <tr>\n' +
                '        <td style="border:solid 1px black">评审地址</td>\n' +
                '        <td style="border:solid 1px black" colspan="5">'+programEnty.address+'</td>\n' +
                '      </tr>';

              document.getElementById('table2').innerHTML = html2;
              console.log(document.getElementById('form1').innerHTML)
            LODOP.ADD_PRINT_HTM(
              30,
              0,
              '210mm',
              '297mm',
              document.getElementById('form1').innerHTML
            )
            LODOP.PREVIEW()
          }
        })
      },
      querySearch (queryString, cb) {
        var that = this
        var url = '/draw/queryByName'
        this.$http({
          url: this.$http.adornUrl(`/draw/unit/queryByName/` + queryString),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            that.items = []
            var ds = data.list
            for (var i = 0; i < ds.length; i++) {
              that.items.push({value: ds[i]})
            }
            cb(that.items)
          }
        })
      },
      handleSelect (item) {
      },
      mod (index, row) {
        row.isEdit = 1
      },
      // 表单提交
      dataFormSubmit () {
        if (this.tableData.length == 0) {
          this.$message.error('请新增专业后保存')
          return
        }
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            this.fullscreenLoading = true
            this.saveDisabled = true
            this.$http({
              url: this.$http.adornUrl(`/draw/programmanager/save`),
              method: 'post',
              data: this.$http.adornData({
                programManager: {
                  id: this.dataForm.id || undefined,
                  code: this.dataForm.code,
                  name: this.dataForm.name,
                  proStatus: this.dataForm.proStatus,
                  purchasingId: this.dataForm.purchasingId,
                  extractionUnit: this.dataForm.extractionUnit,
                  purWay: this.dataForm.purWay,
                  startReview: this.dataForm.startReview,
                  endReview: this.dataForm.endReview,
                  govProRecordNumber: this.dataForm.govProRecordNumber,
                  govProPerson: this.dataForm.govProPerson,
                  reviewContent: this.dataForm.reviewContent,
                  budgetAmount: this.dataForm.budgetAmount,
                  extractionUnitContact: this.dataForm.extractionUnitContact,
                  extractionUnitPhone: this.dataForm.extractionUnitPhone,
                  address: this.dataForm.address,
                  supervisoryPlaceId: this.dataForm.supervisoryPlaceId,
                  avoidUnit: this.dataForm.avoidUnit,
                  remark: this.dataForm.remark
                },
                majorList: this.tableData
              })
            }).then(({data}) => {
              this.fullscreenLoading = false
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.init(data.msg)
                  }
                })
              } else {
                this.saveDisabled = false
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
