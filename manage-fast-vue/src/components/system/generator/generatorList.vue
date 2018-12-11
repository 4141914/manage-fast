<!--
课种表 @author huochai @email 4141914@gmail.com @date 2018-01-16 21:14:23
-->
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="表名">
        <el-input placeholder="请输入表名" size="small" v-model="form.tableName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table border :data="filterDate()" v-loading="loading" element-loading-text="正在加载......"
              style="width: 100%">
      <el-table-column prop="tableName" label="表名"></el-table-column>
      <el-table-column prop="tableComment" label="描述"></el-table-column>
      <el-table-column prop="engine" label="存储引擎"></el-table-column>
      <!--<el-table-column prop="createTime" label="创建时间"></el-table-column>-->
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="gen(props.row)">生成</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination small layout="prev, pager, next" :current-page="page" :total="total"
                     @current-change="(curr) => {this.page = curr ; this.refresh();}"></el-pagination>
    </div>
  </div>
</template>
<script>
  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        dataList: [],
        form: {
          tableName: ""
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
        that.$http.post("/api/gen/list").then(res => {
          that.loading = false;
          that.dataList = res.data;
        }).catch(res => {
          that.$message.error("获取生成列表失败：" + res);
          that.loading = false;
          console.error(res);
        });
      },
      filterDate() {
        const that = this;
        if (that.dataList == null || that.dataList.length == 0) {
          return [];
        }
        let list = [];
        for (let i = 0; i < that.dataList.length; i++) {
          if (that.dataList[i].tableName.indexOf(that.form.tableName) >= 0) {
            list.push(that.dataList[i]);
          }
        }
        return list;
      },
      gen(row) {
        const that = this;
        that.$http.get("/api/gen/code", {
          params: {
            tables: row.tableName
          }
        }).then(res => {
          that.$message.success(row.tableName + "生成完成!");
        });
      }

    },
    components: {}
  }
</script>
<style>

</style>
