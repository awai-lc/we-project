<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      node-key="catId"
      ref="tree"
      @node-click="nodeclick"
    >
    </el-tree>
  </div>
</template>

<script>
export default {
  data () {
    return {
      title: '',
      menus: [],
      expandedkey: [],
      category: {name: '', parentCid: '', catLevel: '', showStatus: 1, sort: 0},
      dialogFormVisible: false,
      formLabelWidth: '120px',
      defaultProps: {
        children: 'childCategoryEntities',
        label: 'name'
      }
    }
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({ data }) => {
        this.menus = data.data
      })
    },
// 向父组件发送事件
    nodeclick (data, node, component) {
      // console.log(data)
      // console.log(node)
      // console.log(component)
      this.$emit('tree-node-click', data, node, component)
    }
  },
  created () {
    console.log('aaaa')
    this.getDataList()
  }
}
</script>

<style></style>
