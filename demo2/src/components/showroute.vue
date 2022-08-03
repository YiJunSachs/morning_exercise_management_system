<template>
  <baidu-map class="map" center="南京" :scroll-wheel-zoom="true" >
<bm-control>
    <bm-walking :start="this.student[0]" :end="this.student[1]" :auto-viewport="true" location="南京"></bm-walking>
  <el-button @click="showmap">显示</el-button>
</bm-control>
  </baidu-map>
</template>

<script>
import request from "@/util/request";

export default {
  name: "showroute",
  data () {
    return {
     student:[

       {
         start: '',
         end: '',
       }

     ],
      username: this.$store.state.username
    }
  },
  methods:{
    showmap(){
   request.post("/user/showmap",this.username).then(res=>{
     var that=this;
     console.log(res);
     that.student=res;
console.log(that.student[0]);
   })
    }
  }
}
</script>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
</style>