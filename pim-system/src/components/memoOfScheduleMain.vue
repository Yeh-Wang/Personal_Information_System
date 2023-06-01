<template>
  <el-dialog
      v-model="centerDialogVisible"
      title="add a memo of schedule"
      width="30%"
      align-center
  >
    <el-form :model="form" label-width="120px">
      <el-form-item label="Activity name">
        <el-input v-model="form.memoName"/>
      </el-form-item>
      <el-form-item label="cutoff time">
        <el-col :span="11">
          <el-date-picker
              v-model="form.memoTime"
              type="datetime"
              placeholder="Choose a cutoff time"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="event">
        <el-input v-model="form.memoTxt" type="textarea"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Ok</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <div class="new-nav">
    <el-row :gutter="20">
      <el-col :span="3">
        <div class="grid-content ep-bg-purple">
          <el-button type="primary" icon="Plus" @click="addMemoInfo">Add</el-button>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content ep-bg-purple"></div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content ep-bg-purple" />
      </el-col>
      <el-col :span="9">
        <div class="grid-content ep-bg-purple">
          <el-date-picker
              v-model="dateTime"
              type="datetime"
              placeholder="Select a cutoff time"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
          <el-button type="primary" plain style="width: 80px" @click="searchMemo()" icon="Search">Search</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
  <div class="demo-collapse">
    <el-collapse accordion v-for="memoInfo in memoInfoArr.memoInfo">
      <el-collapse-item :name="memoInfo.memoId">
        <template #title>
          {{ memoInfo.memoName }}
          <el-date-picker
              v-model="memoInfo.memoTime"
              type="datetime"
              placeholder="Select date and time"
              style="position: absolute;left: 45%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </template>
        <div>
          <el-input
              v-model="memoInfo.memoName"
              :autosize="{ minRows: 1, maxRows: 1 }"
              type="textarea"
              placeholder="Please input your memo name"
          />
          <el-input
              v-model="memoInfo.memoTxt"
              :autosize="{ minRows: 3, maxRows: 5 }"
              type="textarea"
              placeholder="Please input your memo of schedule"
          />
          <el-button test bg style="width: 50%;margin: 0;padding: 0" type="primary" @click="updateMemo(memoInfo)">Save
          </el-button>
          <el-button test bg style="width: 50%;margin: 0;padding: 0" type="danger" @click="deleteMemo(memoInfo.memoId)">
            Delete
          </el-button>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script lang="ts">
import axios from "axios";
import {defineComponent, reactive, ref} from "vue";
import {ElMessage} from "element-plus";

export default defineComponent({
  name: "memoOfScheduleMain",

  setup() {
    interface memoInfo {
      memoId: number;
      memoName: string;
      memoTxt: string;
      memoTime: string;
      perId: string;
    }

    const memoInfoArr = reactive({
      memoInfo: [{
        memoId: 1,
        memoName: '2',
        memoTxt: '3',
        memoTime: '4',
        perId: '5',
      }]
    })
    const centerDialogVisible = ref(false)
    const form = reactive({
      memoId: 1,
      memoName: ' ',
      memoTxt: ' ',
      memoTime: ' ',
      perId: '1',
    })
    const dateTime = ref('')
    const searchMemo = () =>{
      console.log(dateTime.value)
      if(dateTime.value===''){
        axios.get("http://localhost:9090/memo-of-schedule-entity/getAllMemo").then(res => {
          memoInfoArr.memoInfo.length=0
          memoInfoArr.memoInfo = res.data
        })
      }else{
        axios.get("http://localhost:9090/memo-of-schedule-entity/getAllMemoByDate/"+ dateTime.value).then(res =>{
          memoInfoArr.memoInfo.length=0
          memoInfoArr.memoInfo=res.data
          console.log(res.data)
        })
      }
    }
    const cancel = () => {
      form.memoTxt = ' ';
      form.memoTime = ' ';
      form.memoName = ' ';
      centerDialogVisible.value = false
    }
    const addMemoInfo = () => {
      centerDialogVisible.value = true;
    }
    const onSubmit = () => {
      form.memoId = memoInfoArr.memoInfo.length + 1;
      axios.post("http://localhost:9090/memo-of-schedule-entity/addMemoInfo", form).then(res => {
        if (res.data === 1) {
          ElMessage({
            message: "Add successful",
            type: "success"
          })
          form.memoTxt = ' ';
          form.memoTime = ' ';
          form.memoName = ' ';
          centerDialogVisible.value = false
        }
      })
      console.log(form)
    }
    const deleteMemo = (memoId: number) => {
      // console.log(memoId)
      axios.post("http://localhost:9090/memo-of-schedule-entity/deleteMemoInfo/" + memoId).then(res => {
        if (res.data === 1) {
          ElMessage({
            message: "delete successful",
            type: "success"
          })
        }
      })
    }
    const updateMemo = (memoInfo: memoInfo) => {
       console.log(memoInfo.memoTime)
      axios.post("http://localhost:9090/memo-of-schedule-entity/updateMemoInfo", memoInfo).then(res => {
        if (res.data === 1) {
          ElMessage({
            message: "update successful",
            type: "success"
          })
        }
      })
    }
    return {
      memoInfoArr,
      updateMemo,
      deleteMemo,
      centerDialogVisible,
      addMemoInfo,
      onSubmit,
      form,
      cancel,
      dateTime,
      searchMemo
    }
  },

  mounted() {
    axios.get("http://localhost:9090/memo-of-schedule-entity/getAllMemo").then(res => {
      this.memoInfoArr.memoInfo = res.data
      // console.log(res.data)
    })
  }
})
</script>

<style lang="scss" scoped>
.demo-collapse {

}
</style>
