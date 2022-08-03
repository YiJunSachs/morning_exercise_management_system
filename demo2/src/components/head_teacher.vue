<template>
  <el-container>
    <el-menu
        :default-active="this.$route.path" router
        class="el-menu-vertical-demo"

        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item >
        <i class="el-icon-menu"></i>
        <span slot="title">显示班级统计报表</span>
      </el-menu-item>

    </el-menu>

    <el-main>
      <el-form><el-form-item><el-input placeholder="请输入学生姓名查询" v-model="searchname" style="width: 20%"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="searchbyname">搜索</el-button>
      </el-form-item></el-form>
      <el-table :data="tableData">
        <el-table-column prop="addtime" label="打卡日期" width="400">
        </el-table-column>
        <el-table-column prop="cname" label="学生姓名" width="300">
        </el-table-column>
        <el-table-column prop="grade" label="班级" width="300">
        </el-table-column>
        <el-table-column prop="college" label="学院" width="300">
        </el-table-column>

      </el-table>
    </el-main>
  </el-container>
</template>

<script>
import request from "@/util/request";

export default {
  name: "head_teacher",
  data(){
    return {
      searchname:'',
    tableData: [],


    }
  },
  mounted() {
    request.post("/user/returnteacher",this.$store.state.username).then(res=>{
      console.log(res);
      this.tableData=res;
    })
  },
  methods:{
    searchbyname:function (){
      request.post("/user/returnteacherbystudent",this.searchname).then(res=>{
        this.tableData=res;
      })

    }
  }
}
</script>

<style scoped>
.el-container{
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>