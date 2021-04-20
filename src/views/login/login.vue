<template>
  <div class="login">
    <div class="login_box">
      <div class="img_box">
        <el-image
            :src="URL"
            fit="fill"
            lazy
            style="
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background: #eee;
          "
        ></el-image>
      </div>
      <el-form
          ref="login_form"
          :model="login_form"
          :rules="rules"
          class="login_form_box"
      >
        <el-form-item prop="userName">
          <el-input
              v-model="login_form.userName"
              prefix-icon="el-icon-user-solid"
          ></el-input>
        </el-form-item>
        <el-form-item prop="passWord">
          <el-input
              v-model="login_form.passWord"
              prefix-icon="el-icon-lock"
              type="passWord"
          ></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <el-radio-group v-model="login_form.role">
            <el-radio
                v-model="login_form.role"
                border
                label="STU_ROLE"
                name="role"
            >学生
            </el-radio>
            <el-radio v-model="login_form.role" border label="TEA_ROLE"
            >教师
            </el-radio
            >
          </el-radio-group>
        </el-form-item>
        <el-form-item class="login_submit_box">
          <el-button type="primary" @click="submitForm('login_form')"
          >登录
          </el-button
          >
          <el-button @click="resetForm('login_form')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {loginPost} from "./util";
import Message from "element-ui/packages/message/src/main";
import {rules} from "@/views/login/data";

export default {
  name: "Login",
  data() {
    return {
      URL: require("@/assets/logo.png"),
      login_form: {
        userName: "",
        passWord: "",
        role: "STU_ROLE"
      },
      password: "",
      rules,
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          loginPost(this);
        } else {
          Message.error("输入正确账号密码");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  watch: {
    'login_form.passWord': {
      handler(newValue) {
        this.password = window.btoa(newValue)
      }, deep: true, immediate: true
    }
  }
};
</script>

<style scoped>
.login {
  height: 100%;
  background: #2b4b6b;
}

.login_box {
  background: #fff;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  height: 360px;
  border-radius: 7px;
  width: 450px;
}

.img_box {
  width: 130px;
  height: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -40%);
}

.login_form_box {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 15px;
  box-sizing: border-box;
}

.login_submit_box {
  display: flex;
  justify-content: flex-end;
}
</style>
