<template>
  <el-popover placement="left" trigger="click">
    <el-card class="box-card">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px" status-icon>
        <el-form-item label="旧密码">
          <el-input v-model="oldPwd" type="password"/>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input v-model="ruleForm.pass" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input v-model="ruleForm.checkPass" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-dropdown-item slot="reference" icon="el-icon-edit">
      修改密码
    </el-dropdown-item>
  </el-popover>
</template>

<script>
import {changePwd} from "@/views/count/util";

export default {
  name: "modPwd", data() {
    const validatePass = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入密码'));
      } else {
        if (!this.ruleForm.checkPass) {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      oldPwd: '',
      ruleForm: {
        pass: '',
        checkPass: '',
      },
      rules: {
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ],
      }
    }
  }, methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const userName = this.$store.getters.getUserName;
          const oldPwd = this.oldPwd;
          const newPwd = this.ruleForm.pass;
          changePwd(userName, oldPwd, newPwd);
          this.$refs[formName].resetFields();
          this.oldPwd = '';
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>
