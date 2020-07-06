<template>
  <div class="wrapper">
  
    <el-row :gutter="20">
  <el-col :span="10">
    <!-- <div>
<el-button type="warning" style="float:rigth" @click="getCheckedKeys">删除</el-button>
</div> -->
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()" >
      <el-form-item>
        <el-button @click="getCheckedKeys" type="warning">删除</el-button>
      </el-form-item>
    </el-form>
    <el-tree
      :data="majors"
      :props="defaultProps"
      @node-click="handleNodeClick"
      :expand-on-click-node="false"
      show-checkbox
      :highlight-current="true"
      node-key="id"
      :default-expanded-keys="expandedkey"
      ref="tree"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            type="text"
            size="mini"
            @click="() => append(data)"
            >新增</el-button
          >
          <el-button
            type="text"
            size="mini"
            @click="() => edit(data)"
            >修改</el-button
          >
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => open(node, data)"
            >删除</el-button
          >
        </span>
      </span>
    </el-tree></el-col>
  <el-col :span="14">
    <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleNodeClick({id:expertMajor})">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">
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
    
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form el-form :model="major">
        <el-form-item label="专业编码" :label-width="formLabelWidth">
          <el-input v-model="major.code" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <el-form :model="major">
        <el-form-item label="专业名称" :label-width="formLabelWidth">
          <el-input v-model="major.majorName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMajor"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
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
    handleNodeClick (data) {
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
    getDataList () {
      this.$http({
        url: this.$http.adornUrl('/draw/major/list/tree'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'key': this.dataForm.key
        })
      }).then(({ data }) => {
        this.majors = data.data
        console.log(data.data)
        this.expandedkey = [data.data[0].id]
      })
    },
    append (data) {
      console.log(data)
      this.major.parentId = data.id
      console.log('--' + this.major.parentId)
      this.dialogFormVisible = true
      this.dialogFormType = 'append'
      this.title = '添加专业'
    },
    edit (data) {
      this.major.majorName = data.majorName
      this.major.parentId = data.parentId
      this.major.id = data.id
      this.major.code = data.code
      this.dialogFormVisible = true
      this.dialogFormType = 'edit'
      this.title = '修改专业'
    },
    getCheckedKeys () {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          console.log(this.$refs.tree.getCheckedKeys())
          var Ids = this.$refs.tree.getCheckedKeys()
          this.$http({
            url: this.$http.adornUrl('/draw/major/delete'),
            method: 'post',
            data: this.$http.adornData(Ids, false)
          })
        .then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
        .catch(() => {})
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    remove (node, data) {
      console.log('remove', node, 'data', data)
      var Ids = [data.id]
      console.log('Ids', Ids)
      this.expandedkey = node.parent
      this.$http({
        url: this.$http.adornUrl('/draw/major/delete'),
        method: 'post',
        data: this.$http.adornData(Ids, false)
      })
        .then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                console.log(node.parent)
                console.log(node.parent.data.id)
                this.expandedkey = [node.parent.data.id]
                this.getDataList()
                console.log(this.expandedkey)
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
        .catch(() => {})
    },
    open (node, data) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.remove(node, data)
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    addMajor () {
      if (this.dialogFormType === 'append') {
        this.$http({
          url: this.$http.adornUrl('/draw/major/save'),
          method: 'post',
          data: this.$http.adornData(this.major, false)
        })
        .then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.expandedkey = [this.major.parentId]
                this.getDataList()
                console.log(this.expandedkey)
                this.dialogFormVisible = false
                this.major = {majorName: '', parentId: '', id: '', code: ''}
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
        .catch(() => {})
      } else {
        this.$http({
          url: this.$http.adornUrl('/draw/major/update'),
          method: 'post',
          data: this.$http.adornData(this.major, false)
        })
        .then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.expandedkey = [this.major.parentId]
                this.getDataList()
                console.log(this.expandedkey)
                this.dialogFormVisible = false
                this.major = {majorName: '', parentId: '', id: '', code: ''}
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
        .catch(() => {})
      }
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
  },
  created () {
    this.getDataList()
    console.log(this.$store.state.user)
  }
}
</script>

<style scoped></style>
