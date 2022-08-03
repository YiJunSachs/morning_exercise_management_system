<template>
  <el-container>
   <el-menu
      :default-active="this.$route.path" router
      class="el-menu-vertical-demo"

      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
    <el-menu-item index="/collegetable">
      <i class="el-icon-menu"></i>
      <span slot="title">显示学院统计报表</span>
    </el-menu-item>
    <el-menu-item index="/PE_teacher">
      <i class="el-icon-menu"></i>
      <span slot="title">创建学校跑操活动</span>
    </el-menu-item>
  </el-menu>

    <el-main>
 <el-form><el-form-item><el-input placeholder="请输入学院名查询" v-model="collegename" style="width: 20%"></el-input>
   <el-button type="primary" icon="el-icon-search" @click="searchbycollge">搜索</el-button></el-form-item></el-form>
      <el-table :data="tableData">
        <el-table-column prop="addtime" label="日期" width="400">
        </el-table-column>
        <el-table-column prop="college" label="学院名" width="400">
        </el-table-column>

        <el-table-column prop="collegeaddsum" label="打卡人数" width="300">
        </el-table-column>
        <el-table-column prop="collegesum" label="总人数" width="300">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
import request from "@/util/request";

export default {
  name: "collegetable",
  data(){
    return{
collegename:'',
      tableData: [],
    }
  },
  mounted() {
    request.post("/user/returnsportteacher").then(res=>{
      this.tableData=res;
      
    })
  },
  methods:{
    searchbycollge:function (){
      request.post("/user/returnsportteacherbycollege",this.collegename).then(res=>{
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