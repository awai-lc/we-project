<template>

  <el-row :gutter="20">
  <el-col :span="6">
    <div class="mod-home">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
        <span>项目数量</span>
        </div>
        <div class="text item">
        <span>100</span>
        </div>
      </el-card>
    </div>
  </el-col>
  <el-col :span="6">
    <div class="mod-home">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>专家数量</span>
        </div>
        <div class="text item">
        <span>100</span>
        </div>
      </el-card>
    </div>
  </el-col>
</el-row>
  
</template>

<script>
  export default {
  
  }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }

</style>

