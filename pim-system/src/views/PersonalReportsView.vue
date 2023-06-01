<template>
  <el-row><el-col :span="24"><div class="grid-content ep-bg-purple-dark"/></el-col></el-row>
  <el-row>
    <el-col :span="4">
      <el-button size="large" text type="primary" plain @click="goBack">Back</el-button>|
      <span class="text-large font-600 mr-3"> Personal Reports </span>
    </el-col>
    <el-col :span="3"><div class="grid-content ep-bg-purple-dark"/></el-col>
    <el-col :span="10">
      <el-input v-model="newReport.repUsername" style="width: 220px" placeholder="Please enter the report name"/>
      &nbsp
      <el-date-picker
          v-model="newReport.repBegin"
          type="datetime"
          placeholder="Select date and time"
          style="width: 180px;"
          value-format="YYYY-MM-DD HH:mm:ss"
      />
      <span> To </span>
      <el-date-picker
          v-model="newReport.repEnd"
          type="datetime"
          placeholder="Select date and time"
          style="width: 180px;"
          value-format="YYYY-MM-DD HH:mm:ss"
      />
    </el-col>
    <el-col :span="6">
      <el-button text type="success" class="ml-2" @click="createReports">Generating a Report</el-button>
      <el-button text @click="getAllReports">Viewing All Reports</el-button>
    </el-col>
    <el-col :span="2"><div class="grid-content ep-bg-purple-dark"/></el-col>
  </el-row>
  <div class="report" style="padding-left: 160px">
    <el-row align="middle" type="flex" style="flex-wrap: wrap">
      <el-space wrap>
        <el-col :span="24" v-for="(reportInfo) in reportArr.reportInfo" :key="reportInfo.repId" offset="0" class="card-col-wb">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <el-row>
                <el-col :span="5"><span>NO:{{ reportInfo.repId }}</span></el-col>
                <el-col :span="14"><span>{{ reportInfo.repUsername }}</span></el-col>
                <el-col :span="3"><el-button type="danger" class="button" @click="deleteReport(reportInfo.repId)" text>Delete</el-button></el-col>
                </el-row>
              </div>
            </template>
            <div>
              <el-descriptions size="large" border>
                <el-descriptions-item label="Initial time">{{ reportInfo.repBegin }}</el-descriptions-item>
                <el-descriptions-item label="End time">{{ reportInfo.repEnd }}</el-descriptions-item>
                <el-descriptions-item label="Total Income">{{ reportInfo.repIncome }}</el-descriptions-item>
                <el-descriptions-item label="Total Outlay">{{ reportInfo.repOutlay }}</el-descriptions-item>
                <el-descriptions-item label="Number of completed tasks">{{ reportInfo.repTaskFinished }}</el-descriptions-item>
                <el-descriptions-item label="Number of unfinished tasks">{{ reportInfo.repTaskUnfinished }}</el-descriptions-item>
              </el-descriptions>
            </div>
          </el-card>
        </el-col>
      </el-space>
    </el-row>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from "vue";
import router from "@/router";
import axios from "axios";
import {ElMessage} from "element-plus";

export default defineComponent({
  name: "personalReports",

  setup() {
    const goBack = () => {
      router.go(-1)
    }
    interface reportInfo{
      perId:string,
      repId:number,
      repUsername:string,
      repTaskFinished:number,
      repTaskUnfinished:number,
      repIncome:number,
      repOutlay:number,
      repBegin:string,
      repEnd:string,
    }
    const reportArr = reactive({
      reportInfo:[{
        perId:" ",
        repId:1,
        repUsername:" ",
        repTaskFinished:0,
        repTaskUnfinished:0,
        repIncome:0.00,
        repOutlay:0.00,
        repBegin:" ",
        repEnd:" ",
      }]
    })
    const newReport = ref({
      perId:" ",
      repId:0,
      repUsername:" ",
      repTaskFinished:0,
      repTaskUnfinished:0,
      repIncome:0.00,
      repOutlay:0.00,
      repBegin:" ",
      repEnd:" ",
    })
    const createReports = () =>{
      if(newReport.value.repBegin===" "||newReport.value.repEnd===" "){
        ElMessage.error({
          message: "Please pick the date",
        })
      }else{
        axios.post("http://localhost:9090/personal-reports-entity/createReport",newReport.value).then(res=>{
          if(res.data===1){
            ElMessage({
              message: "Create successful",
              type: 'success'
            })
          }
        })
      }
    }
    const deleteReport = (id:number) =>{
      axios.post("http://localhost:9090/personal-reports-entity/deleteReport/"+id).then(res=>{
        if(res.data===1){
          ElMessage({
            message: "Delete successful",
            type: 'success'
          })
        }
      })
    }
    const getAllReports = () =>{
      axios.get("http://localhost:9090/personal-reports-entity/getAllReports").then(res=>{
        reportArr.reportInfo=res.data
        console.log(res.data)
      })
    }
    return {
      goBack,
      createReports,
      getAllReports,
      reportArr,
      newReport,
      deleteReport
    }
  },

  mounted() {
    axios.get("http://localhost:9090/personal-reports-entity/getAllReports").then(res=>{
      this.reportArr.reportInfo=res.data
    })
  }
})
</script>

<style lang="scss">
.block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
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

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.box-card{
  height: 350px;
  width: 600px;
}
</style>
