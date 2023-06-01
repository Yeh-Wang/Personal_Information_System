<template>
  <div class="common-layout">
    <el-container>
      <el-affix :offset="0">
      <el-header>
        <HomeHeader :user="user"></HomeHeader>
      </el-header>
      </el-affix>
      <el-main>
      <router-view /></el-main>
      <el-footer>
        <el-affix position="bottom" :offset="0">
        <el-menu
            class="el-menu-demo"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            style="width: 100%"
            @select="handleSelect"
        >
          <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1">
            <el-menu-item index="1" >Memo Of Schedule</el-menu-item>
          </el-col>
          <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="8">
            <el-menu-item index="2">Contacts</el-menu-item>
          </el-col>
          <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="8">
            <el-menu-item index="3">Task Schedule</el-menu-item>
          </el-col>
          <el-col :xs="6" :sm="4" :md="2" :lg="1" :xl="1">
            <el-menu-item index="4" style="width: 185px">Financial Manage</el-menu-item>
          </el-col>
        </el-menu>
        </el-affix>
      </el-footer>
    </el-container>
  </div>


</template>

<script lang="ts">
import {defineComponent, inject, provide, ref} from 'vue';
import HomeHeader from "@/components/HomeHeader.vue";
import AddressBookMain from "@/components/AddressBookMain.vue"
import router from "@/router";
import TaskSchedule from "@/components/TaskSchedule.vue";
import FinancialManage from "@/components/FinancialManage.vue";
import memoOfScheduleMain from "@/components/memoOfScheduleMain.vue";

export default defineComponent({
  name: 'HomeView',
  components: {
    HomeHeader,
    AddressBookMain,
    TaskSchedule,
    FinancialManage,
    memoOfScheduleMain,
  },
  data() {
    return this.user
  },
  setup() {
    const personFlag = ref(false)
    const user = ref(" ");
    const disShow = ref(true)
    const handleSelect = (key: string, keyPath: string[]) => {
      if(key==="1"){
        router.push({
          name: 'memoOfSchedule'
        })
      }else if(key==="2"){
        router.push({name : 'addressBook'})
      }else if(key==="3"){
        router.push({name : 'taskSchedule'})
      }else if(key==="4"){
        router.push({name : 'financialManage'})
      }
    }
    const RefChild =ref(null)
    return {
      user,
      handleSelect,
      personFlag,
      disShow,
      RefChild
    }
  },
  mounted() {
    this.user = <string>this.$route.params.username;
  }
});
</script>

<style lang="scss" scoped>
.common-layout {
  height: 100%;
  width: 100%;
  padding: 0;
  margin: 0;

  .el-container {
    min-height: 100vh;

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
  }
}
</style>
