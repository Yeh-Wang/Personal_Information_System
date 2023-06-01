<template>
  <el-table :data="tableData.personInfo" height="550" stripe style="width: 100%">
    <el-table-column type="expand">
      <template #default="props">
        <div m="5" style="text-align: center">
          <p m="t-0 b-2">ID: &nbsp&nbsp&nbsp&nbsp {{ props.row.perId }}</p>
          <p m="t-0 b-2">School: &nbsp&nbsp&nbsp&nbsp {{ props.row.perSchool }}</p>
          <p m="t-0 b-2">Address: &nbsp&nbsp&nbsp&nbsp {{ props.row.perAddress }}</p>
          <p m="t-0 b-2">Profession: &nbsp&nbsp&nbsp&nbsp{{ props.row.perProfession }}</p>
          <p m="t-0 b-2">Hobbies: &nbsp&nbsp&nbsp&nbsp{{ props.row.perHobbies }}</p>
        </div>
      </template>
    </el-table-column>
    <el-table-column prop="perName" label="Name" width="190"/>
    <el-table-column prop="perSexy" label="Sexy" width="190"/>
    <el-table-column prop="perAge" label="Age" width="190"/>
    <el-table-column prop="perBirthday" label="Birthday" width="190"/>
    <el-table-column prop="perMobile" label="Mobile" width="190"/>
    <el-table-column prop="perQq" label="QQ" width="190"/>
    <el-table-column align="right">
      <template #header>
        <el-row>
          <el-input v-model="search" size="small" placeholder="Type to search" style="width: 100px"/>
          <el-button type="primary" plain style="width: 80px" @click="searchPerson()" icon="Search">Search</el-button>
          <el-button type="success" style="width: 90px" @click="addPerson()" icon="CirclePlusFilled">Add</el-button>
        </el-row>
      </template>
      <template #default="scope">
        <el-button size="default" style="width: 100px" @click="handleEdit(scope.$index,scope.row)"
        >Edit
        </el-button
        >
        <el-button
            size="default"
            type="danger"
            @click="handleDelete(scope.$index,scope.row)" icon="DeleteFilled"
        >Delete
        </el-button
        >
      </template>
    </el-table-column>
  </el-table>

  <!--    确认框-->
  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="30%"
  >
    <span>Are you sure?</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="deleteMakeSure">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--    Update a person Information-->
  <el-dialog
      v-model="centerUpdateDialogVisible"
      title="Update a person Information"
      width="30%"
      align-center
  >
    <el-form :model="updateForm" label-width="120px">
      <el-form-item label="Name">
        <el-input v-model="updateForm.perName"/>
      </el-form-item>
      <el-form-item label="Sexy">
        <el-select v-model="updateForm.perSexy" placeholder="please select your zone">
          <el-option label="man" value="man"/>
          <el-option label="woman" value="woman"/>
          <el-option label="other" value="other"/>
        </el-select>
      </el-form-item>
      <el-form-item label="Birthday">
        <el-col :span="11">
          <el-date-picker
              v-model="updateForm.perBirthday"
              type="date"
              placeholder="Pick a date"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="School">
        <el-input v-model="updateForm.perSchool"/>
      </el-form-item>
      <el-form-item label="Address">
        <el-input v-model="updateForm.perAddress"/>
      </el-form-item>
      <el-form-item label="Mobile Phone">
        <el-input maxlength="11" show-word-limit v-model="updateForm.perMobile"/>
      </el-form-item>
      <el-form-item label="QQ">
        <el-input maxlength="10" show-word-limit v-model="updateForm.perQq"/>
      </el-form-item>
      <el-form-item label="Profession">
        <el-input v-model="updateForm.perProfession"/>
      </el-form-item>
      <el-form-item label="Hobbies">
        <el-input v-model="updateForm.perHobbies"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmitUpdate">Submit</el-button>
        <el-button @click="updateClose">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!--  add a personInfo-->
  <el-dialog
      v-model="centerDialogVisible"
      title="add a person info"
      width="30%"
      align-center
  >
    <el-form :model="form" label-width="120px">
      <el-form-item label="Name">
        <el-input v-model="form.perName"/>
      </el-form-item>
      <el-form-item label="Sexy">
        <el-select v-model="form.perSexy" placeholder="please select your sexy">
          <el-option label="man" value="man"/>
          <el-option label="woman" value="woman"/>
          <el-option label="other" value="other"/>
        </el-select>
      </el-form-item>
      <el-form-item label="Birthday">
        <el-col :span="11">
          <el-date-picker
              v-model="form.perBirthday"
              type="date"
              placeholder="Pick a date"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="School">
        <el-input v-model="form.perSchool"/>
      </el-form-item>
      <el-form-item label="Address">
        <el-input v-model="form.perAddress"/>
      </el-form-item>
      <el-form-item label="Mobile Phone">
        <el-input maxlength="11" show-word-limit v-model="form.perMobile"/>
      </el-form-item>
      <el-form-item label="QQ">
        <el-input maxlength="10" show-word-limit v-model="form.perQq"/>
      </el-form-item>
      <el-form-item label="Profession">
        <el-input v-model="form.perProfession"/>
      </el-form-item>
      <el-form-item label="Hobbies">
        <el-input v-model="form.perHobbies"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Submit</el-button>
        <el-button @click="addClose">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script lang="ts">
import {defineComponent, reactive, ref, computed, provide} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import HomeHeader from "@/components/HomeHeader.vue";

export default defineComponent({
  name: "AddressBookMain",
  components:{
    HomeHeader
  },
  setup() {
    const myPersonId = ref(' ')
    const search = ref('')    //search 变量
    const centerDialogVisible = ref(false)  //添加信息对话框显示控制
    const centerUpdateDialogVisible = ref(false)
    const dialogVisible = ref(false)  //delete dialog flag
    interface personInfo {
      perId: string,
      perName: string,
      perSexy: string,
      perAge: string,
      perBirthday: string,
      perSchool: string,
      perAddress: string,
      perMobile: string,
      perQq: string,
      perProfession: string,
      perHobbies: string
    }
    const tableData = reactive({
      personInfo: [{
        perId: " ",
        perName: " ",
        perSexy: " ",
        perAge: " ",
        perBirthday: " ",
        perSchool: " ",
        perAddress: " ",
        perMobile: " ",
        perQq: " ",
        perProfession: " ",
        perHobbies: " "
      }]
    });   //后端传来数据
    const form: personInfo = reactive({
      perId: "",
      perName: "",
      perSexy: "",
      perAge: "",
      perBirthday: "",
      perSchool: "",
      perAddress: "",
      perMobile: "",
      perQq: "",
      perProfession: "",
      perHobbies: ""
    })
    const updateForm: personInfo = reactive({
      perId: "",
      perName: "",
      perSexy: "",
      perAge: "",
      perBirthday: "",
      perSchool: "",
      perAddress: "",
      perMobile: "",
      perQq: "",
      perProfession: "",
      perHobbies: ""
    })
    const addPerson = () => {              //响应添加用户信息按钮
      centerDialogVisible.value = true
    }
    const addClose = () =>{
      form.perId = '';form.perAge = '';form.perBirthday = '';form.perQq = '';form.perName = '';form.perAddress = '';
      form.perHobbies = '';form.perMobile = '';form.perProfession = '';form.perSchool = '';form.perSexy = '';
      centerDialogVisible.value = false
    }
    const onSubmit = () => {              //向后端添加用户
      let birthday = new Date(form.perBirthday)
      let date = new Date()
      form.perAge = (date.getFullYear() - birthday.getFullYear()).toString()
      axios.post("http://localhost:9090/person-info-entity/addPersonInfo", form).then(res => {
        if (res.data === 1) {
          ElMessage({
            message: "Add successful",
            type: 'success'
          })
          form.perId = '';form.perAge = '';form.perBirthday = '';form.perQq = '';form.perName = '';form.perAddress = '';
          form.perHobbies = '';form.perMobile = '';form.perProfession = '';form.perSchool = '';form.perSexy = '';
          centerDialogVisible.value = false
        }
      })
    }
    const onSubmitUpdate = () => {
      let birthday = new Date(updateForm.perBirthday);
      let date = new Date();
      updateForm.perAge = (date.getFullYear() - birthday.getFullYear()).toString();
      axios.post("http://localhost:9090/person-info-entity/updatePersonInfo", updateForm).then(res => {
        if (res.data === 1) {
          ElMessage({
            message: "The update was successful",
            type: 'success'
          })
          centerUpdateDialogVisible.value = false
        }
      })
    }
    const searchPerson = () => {          //响应搜索按钮
      if (search.value === '') {
        axios.get("http://localhost:9090/person-info-entity/getAllInfo").then(res => {
          tableData.personInfo = res.data
        })
      } else {
        axios.get("http://localhost:9090/person-info-entity/queryPersonByName/" + search.value).then(res => {
          tableData.personInfo = res.data
        });
      }
    }
    const updateClose = () =>{
      // updateForm.perId = ' ';updateForm.perAge = ' ';updateForm.perBirthday = ' ';updateForm.perQq = ' ';updateForm.perName = ' ';updateForm.perAddress = ' ';
      // updateForm.perHobbies = ' ';updateForm.perMobile = ' ';updateForm.perProfession = ' ';updateForm.perSchool = ' ';updateForm.perSexy = ' ';
      centerUpdateDialogVisible.value = false
    }
    const deleteMakeSure = () => {
      axios.post("http://localhost:9090/person-info-entity/deletePersonInfo/" + myPersonId.value).then(res => {
        if (res.data === 1) {
          ElMessage({
            message: "Delete successful",
            type: 'success'
          })
        } else {
          ElMessage.error("There is no person")
        }
      });
      dialogVisible.value = false
    }
    const filterTableData = computed(() => tableData.personInfo.filter(
            (data) =>
                !search.value ||
                data.perName.toLowerCase().includes(search.value.toLowerCase())
        ))
    const handleEdit = (index: number, row: personInfo) => {      //响应Edit按钮
      centerUpdateDialogVisible.value = true;
      updateForm.perId = row.perId;
      updateForm.perAge = row.perAge;
      updateForm.perBirthday = row.perBirthday;
      updateForm.perQq = row.perQq;
      updateForm.perName = row.perName;
      updateForm.perAddress = row.perAddress;
      updateForm.perHobbies = row.perHobbies;
      updateForm.perMobile = row.perMobile;
      updateForm.perProfession = row.perProfession;
      updateForm.perSchool = row.perSchool;
      updateForm.perSexy = row.perSexy;
    }
    const handleDelete = (index: number, row: personInfo) => {    //响应delete 按钮
      dialogVisible.value = true
      myPersonId.value = row.perId
    }
    return {
      tableData,
      search,
      filterTableData,
      handleEdit,
      handleDelete,
      searchPerson,
      addPerson,
      centerDialogVisible,
      form,
      onSubmit,
      centerUpdateDialogVisible,
      updateForm,
      onSubmitUpdate,
      dialogVisible,
      deleteMakeSure,
      addClose,
      updateClose,
    }
  },

  mounted() {
    axios.get("http://localhost:9090/person-info-entity/getAllInfo").then(res => {
      this.tableData.personInfo = res.data
      // this.form.perId=res.data[res.data.list-1].perId
      // console.log(res.data[res.data.length-1])
    })
  }
})
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>