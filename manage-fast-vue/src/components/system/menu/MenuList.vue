<!--
菜单管理 @author lijianan @email 4141914@gmail.com @date 2018-04-12 22:47:08
-->
<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      style="width: 100%;">
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        width="80"
        label="ID">
      </el-table-column>
      <TableTreeColumn
        prop="name"
        header-align="center"
        treeKey="id"
        width="150"
        label="名称">
      </TableTreeColumn>
      <el-table-column
        prop="parentName"
        header-align="center"
        align="center"
        width="120"
        label="上级菜单">
      </el-table-column>
      <el-table-column
        prop="icon"
        header-align="center"
        align="center"
        label="图标">
        <template slot-scope="scope">
          <i :class="['fa-lg', scope.row.icon]"></i>
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        label="类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 0" size="small">目录</el-tag>
          <el-tag v-else-if="scope.row.type === 1" size="small" type="success">菜单</el-tag>
          <el-tag v-else-if="scope.row.type === 2" size="small" type="info">按钮</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="orderNum"
        header-align="center"
        align="center"
        label="排序号">
      </el-table-column>
      <el-table-column
        prop="url"
        header-align="center"
        align="center"
        width="150"
        :show-overflow-tooltip="true"
        label="菜单URL">
      </el-table-column>
      <el-table-column
        prop="perms"
        header-align="center"
        align="center"
        width="150"
        :show-overflow-tooltip="true"
        label="授权标识">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="doEdit(scope.row)">修改</el-button>
          <el-button type="text" size="small" @click="doDelete(scope.row)"> 删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <MenuDialog ref="dialog" :refresh="refresh"></MenuDialog>
  </div>
</template>
<script>
  import TableTreeColumn from './TableTreeColumn'
  import {treeDataTranslate} from '../../../util/index.js'
  import MenuDialog from './MenuDialog.vue';

  export default {
    data: function () {
      return {
        dataList: [],
        loading: false
      }
    },
    computed: {},
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.loading = true;
        that.$http.post("/api/menu/queryList", {}).then(res => {
          that.loading = false;
          this.dataList = treeDataTranslate(res.data)
        }).catch(res => {
          that.$message.error("获取菜单管理列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd() {
        this.$refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.$refs["dialog"].editDialog(row);
      },
      doDelete(row) {
        const that = this;
        this.$confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          that.$http.delete("/api/menu/delete", {
            params: {ids: [row.id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(res => {
          console.error(res);
        });
      }
    },
    components: {
      MenuDialog, TableTreeColumn
    }
  }
</script>
<style>

</style>
