<template>
  <el-row :gutter="30">
    <el-col :span="8" ><el-card shadow="never"> Hi,Dear
      <el-button class="personInfo" text type="danger" @click="personalInfo(user)">{{user}}</el-button>
    </el-card></el-col>
    <el-col :span="8"><el-card shadow="never">PIM SYSTEM</el-card></el-col>
    <el-col :span="8"><el-card shadow="never">
      <el-button type="danger" text @click="reportsView(user)">View Personal Reports</el-button></el-card></el-col>
  </el-row>
</template>

<script lang="ts">
import { defineComponent, inject} from "vue";
import router from "@/router";

export default defineComponent({
  name: 'HomeHeader',
  props:['user'],
  setup(){
    const reportsView = (user:string) =>{
      router.push({path:'/'+user+'/personalReports'})
    }
    const personalInfo = (user:string) =>{
      router.push({path:'/'+user+'/personalInfo'})
    }
    return {
      reportsView,
      personalInfo
    }
  }
})
</script>
<style>
.personInfo:hover{
  text-decoration: underline;
  text-decoration-color: red;
  font-family: Consolas,serif;
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}
.el-card{
  font-family: Consolas,emoji;
  border: none;
}
.el-link {
  font-family: Consolas,emoji;
}
.grid-content {
  border-radius: 4px;
  min-height: 50px;
}
</style>