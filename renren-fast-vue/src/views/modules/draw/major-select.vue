<template>
  <div class="wrapper">

    <el-row :gutter="20">
      <el-col :span="10">
        <el-tree
          :data="majors"
          :props="defaultProps"
          @node-click="handleNodeClick"
          :expand-on-click-node="false"
          :highlight-current="true"
          node-key="id"
          :default-expanded-keys="expandedkey"
          ref="tree"
        >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
      </span>
        </el-tree>
      </el-col>
      <el-col :span="14">
        <div class="mod-config">
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-input v-model="dataForm.key" placeholder="请输入专家名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="handleNodeClick({id:expertMajor})">查询</el-button>
              <el-button @click="toggleSelection()">选择</el-button>
            </el-form-item>
          </el-form>
          <el-table
            ref="multipleTable"
            :data="dataList"
            border
            v-loading="dataListLoading"
            style="width: 100%;">
            <el-table-column
              type="selection">
            </el-table-column>
            <el-table-column
              prop="code"
              header-align="center"
              align="center"
              label="专业编码">
            </el-table-column>
            <el-table-column
              prop="majorName"
              header-align="center"
              align="center"
              label="名称">
            </el-table-column>
            <el-table-column
              prop="expertCount"
              header-align="center"
              align="center"
              label="专家数">
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="pageSize"
            :total="totalPage"
            layout="total, sizes, prev, pager, next, jumper">
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        title: '',
        dialogFormType: '',
        majors: [],
        expandedkey: [],
        major: {majorName: '', parentId: '', id: '', code: ''},
        dialogFormVisible: false,
        formLabelWidth: '120px',
        defaultProps: {
          children: 'childMajor',
          label: 'majorName'
        },
        expertMajor: '',
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        dataForm: {
          key: ''
        }
      }
    },
    methods: {
      handleNodeClick(data) {
        // 如果做了叶子节点的切换
        if (data.id != this.expertMajor) {
          this.dataForm.key = ''
        }
        this.expertMajor = data.id
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/draw/major/list/' + this.expertMajor),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
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
      // 获取数据列表
      getDataList() {
        this.$http({
          url: this.$http.adornUrl('/draw/major/list/tree'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          this.majors = data.data
          this.expandedkey = [data.data[0].id]
        }).catch(({response}) => {
          this.$message.error(response.statusText)
          this.majors = []
          this.totalPage = 0
        })
      },
      // 每页数
      sizeChangeHandle(val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle(val) {
        this.pageIndex = val
        this.getDataList()
      },
      toggleSelection() {
        var _selectData = this.$refs.multipleTable.selection;
        if(_selectData.length == 0){
          this.$message.error("请选择专业")
        }
        var tableDates=[];
        _selectData.forEach(item =>{
          var tableDate = {};
          tableDate.majorId = item.id;
          tableDate.majorCode = item.code;
          tableDate.majorName = item.majorName;
          tableDate.needCount = 0;
          tableDate.chosedCount = 0;
          tableDate.expertCount = item.expertCount;
          tableDate.placeName = '湖北省';
          tableDate.isEdit = 1;
          tableDates.push(tableDate)
        });
        this.$emit('fathermethod',tableDates);

      }
    },
    created() {
      this.getDataList()
    }

  }
</script>

<style scoped></style>
