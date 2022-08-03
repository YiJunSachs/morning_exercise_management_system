
<template>
 <baidu-map class="map" center="南京" :scroll-wheel-zoom="true" >
   <bm-control>
  <div class="login_input"><el-input placeholder="请输入起点" v-model="st"></el-input></div>
     <div class="login_input"><el-input placeholder="请输入终点" v-model="ed"></el-input></div>

     <el-button type="primary" @click="open">保存路线</el-button>
     <el-button type="primary" @click="back">返回首页</el-button>
     <bm-walking :start="st" :end="ed" :auto-viewport="true" location="南京"></bm-walking>
   </bm-control>

 </baidu-map>
</template>

<script>
export default {
  name: "route-design",
  data () {
    return {
    st:"",
      ed:"",
    }
  },
methods:{
  open() {
    this.$store.commit("setstart",this.st);
    this.$store.commit("setend",this.ed);
    this.$confirm('是否保存路线?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      this.$message({
        type: 'success',
        message: '保存成功!'
      });
    }).catch(() => {
      this.$message({
        type: 'info',
        message: '已取消保存'
      });
    });
  },
  back:function (){
    this.$router.push('/student');
  }

}



}


</script>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
.login_input{
  width: 250px;


}
</style>