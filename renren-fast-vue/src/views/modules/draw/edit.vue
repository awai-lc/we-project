<template>
  <el-button  size="mini" @click="addOrUpdateHandle()">修改</el-button>
</template>

<script>
  import AddOrUpdate from './programmanager-add-or-update'
  export default {
    name: 'EditForm',
    components: { AddOrUpdate },
    methods:{
      // 新增 / 修改
      addOrUpdateHandle () {
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(1)
        })
      },
    }
  }
</script>
