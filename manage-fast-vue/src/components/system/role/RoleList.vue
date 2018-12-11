<!--
角色 @author lijianan @email 4141914@gmail.com @date 2018-04-13 20:59:41
-->
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="角色名称">
        <el-input placeholder="请输入角色名称" size="small" v-model="form.roleName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
      <el-table-column prop="id" label="Id"></el-table-column>
      <el-table-column prop="roleName" label="角色名称"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column prop="createTime" label="创建时间">
        <template slot-scope="props">
          {{props.row.createTime|date_time_format}}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination small layout="prev, pager, next" :current-page="page" :total="total"
                     @current-change="(curr) => {this.page = curr ; this.refresh();}"></el-pagination>
    </div>
    <RoleDialog ref="dialog" :refresh="refresh"></RoleDialog>
  </div>
</template>
<script>
  import RoleDialog from './RoleDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        dataList: [],
        form: {
          id: null,
          roleName: null,
          remark: null,
          createUserId: null,
          createTime: null,

        },
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
        const requestData = {...that.form, page: that.page - 1};
        that.$http.post("/api/role/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalSize;
        }).catch(res => {
          that.$message.error("获取角色列表失败：" + res);
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
          that.$http.delete("/api/role/delete", {
            params: {ids: [row.id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(res => {
          console.log(res);
        });
      }
    },
    components: {
      RoleDialog
    }
  }
</script>
<style>

</style>
