<template>
  <div style="height: 120px">
  <el-row style="height: 50px">
    <el-col :span="24">
      <div class="grid-content ep-bg-purple-dark"/>
    </el-col>
  </el-row>
  <el-row>
    <el-col :span="5">
      <el-button size="large" text type="primary" plain @click="goBack">Back</el-button>
      |
      <span class="text-large font-600 mr-3" style="font-family: Consolas,sans-serif"> Personal Information </span>
    </el-col>
    <el-col :span="2">
      <div class="grid-content ep-bg-purple-dark"/>
    </el-col>
    <el-col :span="10"><span style="font-family: Consolas,sans-serif"> Maybe it's true </span> </el-col>
    <el-col :span="6">
      <el-button text type="success" style="font-family: Consolas,sans-serif" class="ml-2" @click="editInfo">Edit</el-button>
    </el-col>
    <el-col :span="2">
      <div class="grid-content ep-bg-purple-dark"/>
    </el-col>
  </el-row>
  </div>
  <el-row>
<!--  <div class="backGroundLeft"></div>-->
  <div style="padding-left: 450px;padding-top: 100px" class="backGroundMiddle">
    <el-card class="box-card" v-model="form">
      <el-descriptions style="font-family: Consolas,sans-serif" title="User Info" size="large" border :column="2">
        <el-descriptions-item label="User Name">{{ form.perName }}</el-descriptions-item>
        <el-descriptions-item  label="Sexy">{{ form.perSexy }}</el-descriptions-item>
        <el-descriptions-item label="Age">{{ form.perAge }}</el-descriptions-item>
        <el-descriptions-item label="Birthday">{{ form.perBirthday }}</el-descriptions-item>
        <el-descriptions-item label="School">
          <el-tag size="small">{{ form.perSchool }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Address">{{ form.perAddress }}</el-descriptions-item>
        <el-descriptions-item label="Mobile Phone">{{ form.perMobile }}</el-descriptions-item>
        <el-descriptions-item label="QQ">{{ form.perQq }}</el-descriptions-item>
        <el-descriptions-item :span="2" label="Profession">{{ form.perProfession }}</el-descriptions-item>
        <el-descriptions-item label="Hobbies">{{ form.perHobbies}}</el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
  </el-row>
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
              value-format="YYYY-MM-DD"
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
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from "vue";
import router from "@/router";
import axios from "axios";
import {ElMessage} from "element-plus";

export default defineComponent({
  name: "PerInfo",

  setup() {
    const goBack = () => {
      router.go(-1)
    }
    const form= reactive({
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
    const updateForm= reactive({
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
    const centerUpdateDialogVisible = ref(false)
    const editInfo = () =>{
      centerUpdateDialogVisible.value=true
      updateForm.perId = form.perId;
      updateForm.perAge = form.perAge;
      updateForm.perBirthday = form.perBirthday;
      updateForm.perQq = form.perQq;
      updateForm.perName = form.perName;
      updateForm.perAddress = form.perAddress;
      updateForm.perHobbies = form.perHobbies;
      updateForm.perMobile = form.perMobile;
      updateForm.perProfession = form.perProfession;
      updateForm.perSchool = form.perSchool;
      updateForm.perSexy = form.perSexy;
    }
    const onSubmitUpdate = () =>{
      console.log(updateForm.perBirthday)
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
    const updateClose = () =>{
      centerUpdateDialogVisible.value=false
    }
    return {
      goBack,
      updateForm,
      updateClose,
      onSubmitUpdate,
      centerUpdateDialogVisible,
      editInfo,
      form
    }
  },
  mounted() {
    axios.get("http://localhost:9090/person-info-entity/getSelfInfo").then(res=>{
      this.form.perId = res.data.perId;
      this.form.perAge = res.data.perAge;
      this.form.perBirthday = res.data.perBirthday;
      this.form.perQq = res.data.perQq;
      this.form.perName = res.data.perName;
      this.form.perAddress = res.data.perAddress;
      this.form.perHobbies = res.data.perHobbies;
      this.form.perMobile = res.data.perMobile;
      this.form.perProfession = res.data.perProfession;
      this.form.perSchool = res.data.perSchool;
      this.form.perSexy = res.data.perSexy;
    })
  }
})
</script>

<style scoped>
.backGroundLeft{
  background-image: url("../assets/left.png");
  background-repeat: no-repeat;
  width: 400px;
  height: 620px;
  padding-left: 0;
}
/*.backGroundMiddle{*/
/*  background-color: red;*/
/*  width: 650px;*/
/*  height: 620px;*/
/*}*/
.backGroundRight{
  background-image: url("../assets/right.png");
  width: 450px;
  height: 620px;
  padding-right: 0;
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  height: 400px;
  width: 650px;
}
.el-descriptions-item{
  height: 65px;
}
</style>