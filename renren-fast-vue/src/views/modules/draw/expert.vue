<template>
  <div class="mod-config" v-loading="fullscreenLoading">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('draw:expert:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('draw:expert:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
        <el-button v-if="isAuth('draw:expert:delete')" type="primary" @click="uploadFile()">上传</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center">
      </el-table-column>
      <el-table-column
        prop="expertName"
        header-align="center"
        align="center"
        label="专家名称">
      </el-table-column>
      <el-table-column
        prop="majorName"
        header-align="center"
        align="center"
        label="专业名称">
      </el-table-column>
      <el-table-column
        prop="phone"
        header-align="center"
        align="center"
        label="手机号">
      </el-table-column>
      <el-table-column
        prop="idCard"
        header-align="center"
        align="center"
        label="身份证">
      </el-table-column>
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="邮箱">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="在职状态">
        <template slot-scope="scope">
            <el-switch
              v-model="scope.row.jobStatus"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value=1
              :inactive-value=2
              :disabled= true
              >
            </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="title"
        header-align="center"
        align="center"
        label="职称">
      </el-table-column>
      <el-table-column
        prop="birth"
        header-align="center"
        align="center"
        label="出生年月">
      </el-table-column>
      <el-table-column
        prop="address"
        header-align="center"
        align="center"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="unit"
        header-align="center"
        align="center"
        label="工作单位">
      </el-table-column>

      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150px"
        label="操作">
        <template slot-scope="scope">
          <el-button  size="mini" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="danger" size="mini" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
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
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

    <el-dialog
      :title="'专家&专业上传'"
      :close-on-click-modal="false"
      :visible.sync="uploadFileVisible">
      <el-upload
        el-upload
        drag
        :action="actionUrl"
        accept=".xls,.xlsx"
        ref="upload"
        :on-change="onUploadChange"
        :on-success="handleSuccess"
        :auto-upload="false"
        :limit="1"
        list-type="text"
        :file-list="files">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
    </el-dialog>

  </div>
</template>
<script>
  import AddOrUpdate from './expert-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        fullscreenLoading: false,
        actionUrl: `${window.SITE_CONFIG.baseUrl}/draw/file/expertUpload`,
        files: [],
        base64File:'',
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        uploadFileVisible : false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      submitUpload()
      {
        this.fullscreenLoading = true;
        this.uploadFileVisible = false;
        this.$refs.upload.submit();
      },
      handleSuccess (res, file) {
        console.log(res);
        this.fullscreenLoading = false;
        if (data && data.code === 0) {
          this.$message.info('上传成功');
        }else {
          this.$message.error(res.msg);
        }
      },
      onUploadChange(file) {
        var flieArr = file.name.split('.');
        var suffix = flieArr[flieArr.length - 1];
        const isIMAGE = (suffix === 'xls' || suffix === 'xlsx');

        if (!isIMAGE) {
          this.$message.error('上传文件只能是excel格式!');
          return false;
        }
      },
      switchChange (data) {
        console.log(data)
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('/draw/expert/list'),
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
        }).catch(({response}) => {
          this.$message.error(response.statusText)
          this.dataList = []
          this.totalPage = 0
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
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      uploadFile(){
        this.uploadFileVisible = true;
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/draw/expert/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
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
          }).catch(({response}) => {
            this.$message.error(response.statusText)
          })
        })
      }
    }
  }
</script>
<style>
  .el-table th.gutter{
   display: table-cell!important;
}
</style>
