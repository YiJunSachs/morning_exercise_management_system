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

<el-main><el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="活动名称" >
      <el-input v-model="form.name"></el-input>
    </el-form-item>
  <el-form-item></el-form-item>
  <el-form-item></el-form-item>
  <el-form-item label="活动区域">
    <el-select v-model="form.region" placeholder="请选择活动区域" style="width: 100%">
      <el-option label="东南大学九龙湖校区" value="nine"></el-option>
      <el-option label="东南大学四牌楼校区" value="four"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item></el-form-item>
  <el-form-item></el-form-item>
  <el-form-item label="活动时间">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择开始日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择结束日期" v-model="form.date2" style="width: 100%;"></el-date-picker>
      </el-col> </el-form-item>
  <el-form-item></el-form-item>
  <el-form-item></el-form-item>
    <el-form-item label="活动备注" prop="desc" >
      <el-input type="textarea" ></el-input>
    </el-form-item>
    <el-form-item>

    <el-button type="primary" @click="open">立即创建</el-button>
    </el-form-item>
  </el-form>
</el-main>
</el-container>
</template>

<script>
export default {
  name: "PE_teacher",
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',

      }
    }
  },
  methods: {
    open() {
      this.$confirm('是否创建此次跑操活动?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '创建成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消创建'
        });
      });
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


.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 800px;
}

</style>