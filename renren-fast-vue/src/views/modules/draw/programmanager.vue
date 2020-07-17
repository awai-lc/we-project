<template>
  <div class="mod-config">
    <el-form :model="dataForm" @keyup.enter.native="getDataList()" size="mini">
      <el-row>
        <el-col :span="5">
          <el-form-item label="项目名称" label-width="70px">
            <el-input v-model="dataForm.name" placeholder="项目名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="评审时间" label-width="90px">
            <el-date-picker v-model="dataForm.reviewTime" class="block"
                            type="datetimerange" range-separator="至"
                            start-placeholder="开始日期" end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="项目状态" label-width="110px">
            <el-select v-model="dataForm.proStatus" clearable placeholder="请选择">
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
        <el-col :span="5">
          <el-form-item label="抽取单位" label-width="70px">
            <el-input v-model="dataForm.extractionUnit" placeholder="抽取单位"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="7">
          <el-form-item label="所属监管地" label-width="90px">
            <el-select v-model="dataForm.supervisoryPlaceId" clearable placeholder="请选择">
              <el-option
                v-for="item in supervisoryPlaces"
                :key="item.supervisoryPlaceId"
                :label="item.label"
                :value="item.supervisoryPlaceId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="1">
          <el-form-item>
            <el-button @click="getDataList()" size="mini">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table :data="dataList" border v-loading="dataListLoading" size="mini"
              max-height="400" style="width: 100%;height: 400px">
      <el-table-column prop="code" header-align="center" align="center" label="项目编码">
      </el-table-column>
      <el-table-column prop="name" header-align="center" align="center" label="项目名称">
      </el-table-column>
      <el-table-column prop="extractionUnit" header-align="center" align="center" label="抽取单位">
      </el-table-column>
      <el-table-column prop="proStatusText" header-align="center" align="center" label="项目状态">
      </el-table-column>
      <el-table-column prop="purWayText" header-align="center" align="center" label="采购方式">
      </el-table-column>
      <el-table-column prop="startReviewText" header-align="center" align="center" label="评审开始时间" width="150">
      </el-table-column>
      <el-table-column prop="endReviewText" header-align="center" align="center" label="评审结束时间" width="150">
      </el-table-column>
      <el-table-column prop="budgetAmount" header-align="center" align="center" label="预算金额(万元)">
      </el-table-column>
      <el-table-column prop="supervisoryPlaceText" header-align="center" align="center" label="项目所属监管地">
      </el-table-column>
      <el-table-column header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
         <router-link :to="{name:'programmanager-update',params:{id:scope.row.id}}">
            <el-button type="primary" size="small" icon="el-icon-edit">
              查看
            </el-button>
          </router-link>
        </template>

      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './programmanager-add-or-update'

  export default {
    data () {
      return {
        dataForm: {
          name: '',
          reviewTime: '',
          extractionUnit: '',
          proStatus: '',
          supervisoryPlaceId: ''
        },
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
        }
        ],
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
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    watch: {
      $route: 'routeHandle'
    },
    activated () {
      this.getDataList()
    },
    computed: {
      sidebarLayoutSkin: {
        get () { return this.$store.state.common.sidebarLayoutSkin }
      },
      sidebarFold: {
        get () { return this.$store.state.common.sidebarFold }
      },
      menuList: {
        get () { return this.$store.state.common.menuList },
        set (val) { this.$store.commit('common/updateMenuList', val) }
      },
      menuActiveName: {
        get () { return this.$store.state.common.menuActiveName },
        set (val) { this.$store.commit('common/updateMenuActiveName', val) }
      },
      mainTabs: {
        get () { return this.$store.state.common.mainTabs },
        set (val) { this.$store.commit('common/updateMainTabs', val) }
      },
      mainTabsActiveName: {
        get () { return this.$store.state.common.mainTabsActiveName },
        set (val) { this.$store.commit('common/updateMainTabsActiveName', val) }
      }
    },
    methods: {
      writeObj (obj) {
        var description = ''
        for (var i in obj) {
          var property = obj[i]
          description += i + ' = ' + property + '\n'
        }
        console.log(description)
      },
      routeHandle (route) {
        this.writeObj(route)
        this.writeObj(route.query)
        console.log('params')
        this.writeObj(route.params)
        if (route.meta.isTab) {
          console.log('isTab')
          console.log('query' + route.params)
          console.log('name' + route.name)
          // tab选中, 不存在先添加
          var tab = this.mainTabs.filter(item => item.name == route.name && item.params.id == route.params.id)[0]
          if (!tab) {
            console.log('Tab')
            if (route.meta.isDynamic) {
              route = this.dynamicMenuRoutes.filter(item => item.name === route.name)[0]
              if (!route) {
                return console.error('未能找到可用标签页!')
              }
            }
            tab = {
              name: route.name + '/' + route.params.id,
              title: route.meta.title + '-' + route.params.id,
              params: route.params,
              query: route.query
            }
            this.mainTabs = this.mainTabs.concat(tab)
          }
          this.mainTabsActiveName = tab.name
        }
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/draw/programmanager/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
            name: this.dataForm.name,
            startReviewTime: this.dataForm.reviewTime[0],
            endReviewTime: this.dataForm.reviewTime[1],
            extractionUnit: this.dataForm.extractionUnit,
            proStatus: this.dataForm.proStatus,
            supervisoryPlaceId: this.dataForm.supervisoryPlaceId
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      }
    }
  }
</script>
