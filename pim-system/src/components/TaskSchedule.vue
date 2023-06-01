<template>
  <el-dialog
      v-model="centerDialogVisible"
      title="add a memo of schedule"
      width="30%"
      align-center
  >
    <el-form :model="form" label-width="120px">
      <el-form-item label="Task name">
        <el-input v-model="form.taskName"/>
      </el-form-item>
      <el-form-item label="Begin time">
        <el-col :span="11">
          <el-date-picker
              v-model="form.taskBegin"
              type="datetime"
              placeholder="Choose a cutoff time"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="End time">
        <el-col :span="11">
          <el-date-picker
              v-model="form.taskEnd"
              type="datetime"
              placeholder="Choose a cutoff time"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="event">
        <el-input v-model="form.taskTxt" type="textarea"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Ok</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-row>
    <el-col :span="8">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="All" name="first"/>
        <el-tab-pane label="Completed" name="second"/>
        <el-tab-pane label="Unfinished" name="third"/>
      </el-tabs>
    </el-col>
    <el-col :span="13">
      <div class="grid-content ep-bg-purple"></div>
    </el-col>
    <el-col :span="3">
      <el-button @click="addTaskInfo" color="#626aef" icon="Plus" >Add</el-button>
    </el-col>
  </el-row>

  <el-row align="middle" type="flex" style="flex-wrap: wrap">
    <el-space wrap>
    <el-col :span="8" v-for="(taskInfo) in taskInfoArr.taskInfo" :key="taskInfo.taskId" offset="0" class="card-col-wb">
      <el-card class="box-card" :body-style="{ padding: '0px' }">
        <template #header>
          <div class="card-header">
            <span>{{ taskInfo.taskName }}</span>
              <div style="font-size: small">
                Begin:<el-date-picker
                  size="small"
                  v-model="taskInfo.taskBegin"
                  type="datetime"
                  placeholder="Select date and time"
                  style="width: 150px;"
                  value-format="YYYY-MM-DD HH:mm:ss"
              />
                <br>
              End:&nbsp&nbsp<el-date-picker
                  size="small"
                  v-model="taskInfo.taskEnd"
                  type="datetime"
                  placeholder="Select date and time"
                  style="width: 150px;"
                  value-format="YYYY-MM-DD HH:mm:ss"
              />
              </div>
            <el-tag v-if="taskInfo.taskStatus===true" type="info">Completed</el-tag>
            <el-button class="button" v-else type="success" plain @click="confirmComplete(taskInfo)">Complete</el-button>
          </div>
        </template>
        <div class="text item">
          <el-input
              v-model="taskInfo.taskName"
              :autosize="{ minRows: 1, maxRows: 1 }"
              type="textarea"
              placeholder="Please input your Task name"
          />
          <el-input
              v-model="taskInfo.taskTxt"
              :autosize="{ minRows: 3, maxRows: 5 }"
              type="textarea"
              placeholder="Please input your Task Information"
          />
        </div>
        <div>
          <el-row>
            <el-col :span="19"><div class="grid-content ep-bg-purple" /></el-col>
            <el-col :span="5"><div class="grid-content ep-bg-purple-light">
              <el-tooltip
                  effect="dark"
                  content="Commit Changes "
                  placement="left"
              >
              <el-button type="success" icon="Check" @click="updateTask(taskInfo)" circle />
              </el-tooltip>
              <el-button type="danger" icon="Delete" @click="deleteTask(taskInfo.taskId)" circle />
            </div></el-col>
          </el-row>
        </div>
      </el-card>
    </el-col>
    </el-space>
  </el-row>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from "vue";
import axios from "axios";
import type {TabsPaneContext} from 'element-plus'
import {ElMessage} from "element-plus";

export default defineComponent({
  name: "TaskSchedule",

  setup(){
    interface taskInfo {
      perId:string;
      taskId:number;
      taskName:string;
      taskTxt:string;
      taskBegin:string;
      taskEnd:string;
      taskStatus:number;
      taskComDate:string
    }
    const form = reactive({
      perId:"1",
      taskId:1,
      taskName:" ",
      taskTxt:" ",
      taskBegin:" ",
      taskEnd:" ",
      taskStatus:0,
      taskComDate:""
    })
    const taskInfoArr = reactive({
      taskInfo: [{
        perId:"1",
        taskId:1,
        taskName:" ",
        taskTxt:" ",
        taskBegin:" ",
        taskEnd:" ",
        taskStatus:0,
        taskComDate:""
      }]
    })
    const centerDialogVisible = ref(false)
    const formData = ref({
      perId:"1",
      taskId:1,
      taskName:" ",
      taskTxt:" ",
      taskBegin:" ",
      taskEnd:" ",
      taskStatus:0,
      taskComDate:""
    })
    const confirmComplete = (taskInfo:taskInfo) =>{
      let year = new Date().getFullYear();
      let month = new Date().getMonth()+1;
      let day = new Date().getDate();
      taskInfo.taskComDate=year + "-" + month + "-" + day
      taskInfo.taskStatus=1
    }
    const activeName = ref('first')
    const handleClick = (tab: TabsPaneContext, event: Event) => {
      if(tab.paneName==="first"){
        axios.get("http://localhost:9090/task-schedule-entity/getAllTaskInfo").then(res =>{
          taskInfoArr.taskInfo=res.data
        })
      }else if(tab.paneName==="second"){
        axios.get("http://localhost:9090/task-schedule-entity/getAllTaskByStatus/1").then(res =>{
          taskInfoArr.taskInfo=res.data
        })
      }else{
        axios.get("http://localhost:9090/task-schedule-entity/getAllTaskByStatus/0").then(res =>{
          taskInfoArr.taskInfo=res.data
        })
      }
    }
    const addTaskInfo = () =>{
      centerDialogVisible.value=true
    }
    const onSubmit = () =>{
      axios.post("http://localhost:9090/task-schedule-entity/addTaskInfo",form).then(res=>{
        if(res.data===1){
          ElMessage({
            message: "Add successful",
            type: "success"
          })
          form.taskEnd=" ";form.taskTxt=" ";form.taskBegin=" ";form.taskName=" ";
          centerDialogVisible.value=false
        }
      })
    }
    const cancel = () =>{
      form.taskEnd=" ";form.taskTxt=" ";form.taskBegin=" ";form.taskName=" ";
      centerDialogVisible.value=false
    }
    const updateTask = (taskInfo: taskInfo) =>{
      axios.post("http://localhost:9090/task-schedule-entity/updateTaskInfo",taskInfo).then(res=>{
        if(res.data===1){
          ElMessage({
            message: "Update successful",
            type: "success"
          })
        }
      })
    }
    const deleteTask = (id:number) =>{
      axios.post("http://localhost:9090/task-schedule-entity/deleteTaskInfo/"+id).then(res=>{
        if(res.data===1){
          ElMessage({
            message: "Delete successful",
            type: "success"
          })
        }
      })
    }
    return{
      taskInfoArr,
      formData,
      confirmComplete,
      activeName,
      handleClick,
      addTaskInfo,
      updateTask,
      deleteTask,
      centerDialogVisible,
      form,
      onSubmit,
      cancel
    }
  },

  mounted() {
    axios.get("http://localhost:9090/task-schedule-entity/getAllTaskInfo").then(res =>{
      this.taskInfoArr.taskInfo=res.data
    })
  }
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
}
</style>