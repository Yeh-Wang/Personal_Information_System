<template>
  <div class="login-box">
    <el-form :model="loginForm" status-icon label-width="120px" class="loginForm">
      <h1>PIM System</h1>
      <el-form-item label="username" prop="username">
        <el-input v-model="loginForm.username"/>
      </el-form-item>
      <el-form-item label="password" prop="password">
        <el-input v-model="loginForm.password" type="password"/>
      </el-form-item>
      <el-form-item>
        <el-link id="li-1" type="primary" @click="registered">Don't have a account?</el-link>
        <el-button id="l1" type="primary" @click="submitForm()">login in</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";
import {ElMessage} from "element-plus";

export default {
  name: "LoginView",

  data() {
    return {
      loginForm: {}
    }
  },
  methods: {
    submitForm() {
      axios.get("http://localhost:9090/login/judgeLogin/" + this.loginForm.username + "," + this.loginForm.password).then(res => {
        if (res.data === 1) {
          let username = this.loginForm.username;
          router.push({path:'/home/'+username});
          ElMessage({
            message: "Login successful",
            type: 'success'
          })
        }
        if (res.data === "Incorrect password!") {
          ElMessage.error("Incorrect password!"
          )
        }
        if (res.data === "Incorrect username!") {
          ElMessage.error("Incorrect username!")
        }
      })
    },
    registered(){
      ElMessage.error("Oops!There is no way!")
    }
  }
}
</script>

<style lang="scss" scoped>
.login-box {
  height: 100%;
  width: 100%;
  margin: 0;
  background-image: url("../assets/Login.jpg");
  background-repeat: no-repeat;
  background-size: 100%;
  box-sizing: border-box;
  padding-top: 220px;

  .loginForm {
    width: 380px;
    padding: 30px;
    background-color: #fff;
    opacity: 0.9;
    border-radius: 20px;
    margin: 0 auto;
    text-align: center;

    #l1 {
      width: 100%;
    }
    #li-1{
      text-align: center;
    }

    h1 {
      width: 100%;
    }
  }
}
</style>