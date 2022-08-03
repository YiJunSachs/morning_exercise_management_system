<template>
<div class="login_container">



<div class="login_box">
<div style="margin: 20px 0;text-align:center;font-size: 24px">早操管理系统</div>
  <el-form label-width="10px" :model="loginform">
    <el-form-item class="login_select"> <el-select v-model="value" placeholder="请选择您的身份" @change="getvalue">
      <el-option

          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
      </el-option>
    </el-select></el-form-item>
    <el-form-item class="login_input"> <el-input v-model="loginform.username" placeholder="请输入用户名" prefix-icon="el-icon-user-solid"></el-input></el-form-item>
    <el-form-item class="login_input"> <el-input v-model="loginform.pwd" show-password placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input></el-form-item>
    <el-form-item>  <el-button type="primary" round @click="loginaction">登录</el-button></el-form-item>
    <el-form-item class="login_btn">  <el-button type="primary" round @click="registeraction">注册</el-button></el-form-item>

  </el-form>



</div>
  <el-dialog
      title="注册"
      :visible.sync="dialogVisible"
      width="30%"
      >
    <el-form label-width="10px" :model="registerform">
      <el-form-item class="login_select"> <el-select v-model="value" placeholder="请选择您的身份" @change="getvalue">
        <el-option

            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select></el-form-item>
  <el-form-item><el-input v-model="registerform.username" placeholder="请输入用户名"></el-input></el-form-item>
      <el-form-item><el-input v-model="registerform.pwd" placeholder="请输入密码"></el-input></el-form-item>
      <el-form-item><el-input v-model="registerform.college" placeholder="请输入学院"></el-input></el-form-item>
      <el-form-item><el-input v-model="registerform.grade" placeholder="请输入班级"></el-input></el-form-item>
      <el-form-item><el-input v-model="registerform.cname" placeholder="请输入姓名"></el-input></el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="registerconfirm">确 定</el-button>
  </span>
  </el-dialog>
    </div>

</template>

<script >
import request from "@/util/request";
import axios from "axios";

export default {
  name: "Login",
  data(){
return{
registerform:{
  usercharacter:'',//注册时下拉框身份
username:'',//用户名
pwd:'',//密码
  college:'',//学院
  grade:'',//班级
  cname:'',//姓名

},
  temp:'',//登录时下拉框身份
  loginform:{
  usercharacter: '',
    username:'',
    pwd:'',
  },
  dialogVisible: false,
  options: [{
    value: '选项1',
    label: 'student'
  }, {
    value: '选项2',
    label: 'headteacher'
  }, {
    value: '选项3',
    label: 'sportteacher'
  }],
  value: ''

};

  },

  methods:{
    getvalue:function (){
    this.temp=this.value;
    },
    loginaction:function (){
      if (this.temp == '选项1') {
        this.loginform.usercharacter='student'
      } else if (this.temp == '选项2') {
      this.loginform.usercharacter='headteacher'
      } else if (this.temp == '选项3') {
      this.loginform.usercharacter='sportteacher'
      }
      if(this.loginform.username.length==0||this.loginform.pwd==0){
        this.$notify.error({
          title: '错误',
          message: '用户名或密码为空'
        });
      }
   else {
        request.post("/user/login", this.loginform).then(res => {
          if(res==true){
            this.$notify({
              title: '成功',
              message: '登录成功',
              type: 'success'
            })
            if (this.loginform.usercharacter == 'student') {
              this.$router.push('/student');
            } else if (this.loginform.usercharacter== 'headteacher') {
              this.$router.push('/headteacher');
            } else if (this.loginform.usercharacter == 'sportteacher') {
              this.$router.push('/PE_teacher');
            }
            this.$store.commit('setusername', this.loginform.username);
          }
          else{
            this.$notify.error({
              title: '错误',
              message: '身份、用户名或密码错误'
            });
          }

        })

      }},
    registeraction(){
this.dialogVisible=true;

    },
    registerconfirm(){
      this.dialogVisible=false;
      if(this.temp=='选项1'){this.registerform.usercharacter='student';}
      else if(this.temp=='选项2'){this.registerform.usercharacter='headteacher'}
      else if(this.temp=='选项3'){this.registerform.usercharacter='sportteacher'}

   if(this.registerform.usercharacter.length==0||this.registerform.username.length==0||this.registerform.pwd.length==0
         ||this.registerform.college.length==0||this.registerform.grade.length==0||this.registerform.cname.length==0)
     {
       this.$notify.error({
        title: '错误',
        message: '注册失败，有字段为空'
      });}
    else{   request.post("/user/register",this.registerform).then((res)=>{

      if(res==true){

      this.$notify({
     title: '成功',
     message: '注册成功',
     type: 'success'
   })}
    else{this.$notify.error({
     title: '错误',
     message: '注册失败'
   });}
    });}



    }

  }

}
</script>

<style scoped>
.login_container{

  height: 100%;
  background-image: linear-gradient(to bottom right,#FC466B,#3F5EFB);

  background-position: center center;
}
.login_box{
  width: 450px;
  height: 400px;

  border-color: white;
  position: absolute;
  z-index: 1;
  left: 50%;
  top:50%;
  transform:translate(-50%,-50%);
  border-radius: 10px;
background-color: white;

}
.login_input{
  width: 300px;
  padding-left: 70px;

}
.login_select{
  padding-top: 10px;
}
.login_btn{
  box-sizing: border-box;
  padding-bottom: 50px;
}

</style>