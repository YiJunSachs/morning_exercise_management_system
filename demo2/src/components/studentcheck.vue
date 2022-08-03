<template>
  <el-container>

    <el-container>
      <el-menu

          background-color="#333744"
          text-color="#fff"
          active-text-color="#ffd04b"
          class="el-menu-vertical-demo"
          :default-active="this.$route.path" router><el-menu-item index="/route-design">
        <i class="el-icon-menu"></i>
        <span slot="title">定制个性化路线</span>
      </el-menu-item>
        <el-menu-item index="/showroute">
          <i class="el-icon-menu"></i>
          <span slot="title">显示个性化路线</span>
        </el-menu-item>
        <el-menu-item index="/studentcheck">
          <i class="el-icon-menu"></i>
          <span slot="title">显示打卡信息</span>
        </el-menu-item>
        <el-button type="danger" @click="open" >打卡</el-button>
      </el-menu>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="addtime" label="日期" width="200">

          </el-table-column>
          <el-table-column prop="cname" label="学生姓名" width="100">
          </el-table-column>

          <el-table-column prop="start" label="路线起点" width="300">
          </el-table-column>
          <el-table-column prop="end" label="路线终点" width="300">
          </el-table-column>
          <el-table-column prop="username" label="用户名" width="200">

          </el-table-column>
          <el-table-column prop="college" label="学院" width="300">

          </el-table-column>
          <el-table-column prop="grade" label="班级" width="300">

          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import request from "@/util/request";

export default {
  name: "studentcheck",
  data(){ return{
tableData: [],
student:{
  addtime:'',
  cname:'',
  start:this.$store.state.start,
  end:this.$store.state.end,
  username:this.$store.state.username,
college: '',
  grade:''

}
  }
  },
  mounted:function (){

    request.post("/user/returnstudents",this.student.username).then(res=>{
      console.log(res);
      this.tableData=res;
    })
  },
  methods:{
    design:function () {
      this.$router.push('/route-design')
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },

    open() {

      let year = new Date().getFullYear();//年
      let month = new Date().getMonth() +1;//注意！月份是从0月开始获取的，所以要+1;
      let day = new Date().getDate();//日
      this.student.addtime =
          year +
          '-' +
          (month >=10 ? month:'0'+ month) +
          '-' +
          (day >=10 ? day:'0' + day);
      console.log(this.student.end)
      request.post("/user/addstudent",this.student).then(res=>{
        if(res==true){this.$notify({
          title: '成功',
          message: '打卡成功',
          type: 'success'
        })}
        else{
          this.$notify.error({
            title: '错误',
            message: '打卡失败，没有路线或重复打卡'
          })
        }

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