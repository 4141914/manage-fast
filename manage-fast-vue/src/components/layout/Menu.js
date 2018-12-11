/*菜单管理业务逻辑,作者:高振中,日期:2018-04-12 21:20:48*/
import http from '../../util/http';
import {MessageBox, Message} from 'element-ui'

//初始数据
const initForm = {
  id: null, //id
  parent_id: null, //父菜单ID，一级菜单为0
  name: null, //菜单名称
  url: null, //菜单URL
  perms: null, //授权(多个用逗号分隔，如：user:list,user:create)
  type: null, //类型   0：目录   1：菜单   2：按钮
  icon: null, //菜单图标
  order_num: null, //排序
};

//模型
export default {
  state: {
    menuNavList: [],
    //分页列表
    total: 0,
    page: 1,
    dataList: [],
    loading: false,
    searchForm: {
      id: null, //id
      parent_id: null, //父菜单ID，一级菜单为0
      name: null, //菜单名称
      url: null, //菜单URL
      perms: null, //授权(多个用逗号分隔，如：user:list,user:create)
      type: null, //类型   0：目录   1：菜单   2：按钮
      icon: null, //菜单图标
      order_num: null, //排序
    },
    //新增与修改
    form: {...initForm},
    rules: {
      id: [
        {required: true, message: '请输入id', trigger: 'blur'},
        {min: 1, max: 10, message: 'id长度不正确', trigger: 'blur'},
      ],
      parent_id: [
        {required: true, message: '请输入父菜单ID，一级菜单为0', trigger: 'blur'},
        {min: 1, max: 10, message: '父菜单ID，一级菜单为0长度不正确', trigger: 'blur'},
      ],
      name: [
        {required: true, message: '请输入菜单名称', trigger: 'blur'},
        {min: 1, max: 10, message: '菜单名称长度不正确', trigger: 'blur'},
      ],
      url: [
        {required: true, message: '请输入菜单URL', trigger: 'blur'},
        {min: 1, max: 10, message: '菜单URL长度不正确', trigger: 'blur'},
      ],
      perms: [
        {required: true, message: '请输入授权(多个用逗号分隔，如：user:list,user:create)', trigger: 'blur'},
        {min: 1, max: 10, message: '授权(多个用逗号分隔，如：user:list,user:create)长度不正确', trigger: 'blur'},
      ],
      type: [
        {required: true, message: '请输入类型   0：目录   1：菜单   2：按钮', trigger: 'blur'},
        {min: 1, max: 10, message: '类型   0：目录   1：菜单   2：按钮长度不正确', trigger: 'blur'},
      ],
      icon: [
        {required: true, message: '请输入菜单图标', trigger: 'blur'},
        {min: 1, max: 10, message: '菜单图标长度不正确', trigger: 'blur'},
      ],
      order_num: [
        {required: true, message: '请输入排序', trigger: 'blur'},
        {min: 1, max: 10, message: '排序长度不正确', trigger: 'blur'},
      ],
    },
    title: '',
    dialogMode: "save",
    show: false,
  },
  getters: {},
  mutations: {
    ['menu/refresh'](state) {
      state.loading = true;
      const requestData = {...state.searchForm, page: state.page - 1};
      http.post("/api/menu/queryPage", JSON.stringify(requestData)).then(res => {
        state.loading = false;
        state.dataList = res.data.content;
        state.total = res.data.totalElements;
      }).catch(res => {
        Message.error({
          content: "获取菜单管理列表失败：" + res
        });
        state.loading = false;
      });
    },
    ["menu/addDialog"](state) {
      state.title = "新增菜单管理";
      state.dialogMode = "save";
      state.form = {...initForm};
      state.show = true;
    },
    ["menu/editDialog"](state, row) {
      state.title = "修改菜单管理";
      state.dialogMode = "update";
      state.form = {...row};
      state.show = true;
    },
    ["menu/showDialog"](state, show) {
      state.show = show;
    },
    ["menu/setPage"](state, page) {
      state.page = page;
    },
  },
  actions: {
    ["menu/getMenuNavList"]({state, dispatch, commit}, row) {
      http.post("/api/menu/nav").then(res => {
        state.menuNavList = res.data.menuList;
        sessionStorage.setItem('permissions', JSON.stringify(res.data.permissions || '[]'))
      }).catch(res => {
        Message.error({
          content: "获取菜单管理列表失败：" + res
        });
      });
    },
    ["menu/deleteAction"]({state, dispatch, commit}, row) {
      MessageBox.confirm('你确定要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        http.delete("/api/menu/delete", {
          params: {ids: [row.id]}
        }).then(res => {
          Message.success({
            content: "删除成功"
          });
          commit("menu/refresh");
        }).catch(res => {
          Message.error({
            content: "删除失败：" + res
          });
        });
      }).catch(() => {
      });
    },
    ['menu/save']({state, dispatch, commit}) {
      http.post("/api/menu/" + state.dialogMode, JSON.stringify(state.form)).then(res => {
        commit("menu/refresh");
        commit("menu/showDialog", false);
      }).catch(res => {
        Message.error({
          content: '保存菜单管理信息失败' + res
        });
      })
    }
    ,
  }
}
;
