<template>
  <el-form
      ref="student"
      :model="student"
      :rules="rules"
      class="demo-student"
      label-width="100px"
  >
    <el-form-item label="学生姓名" prop="name">
      <el-input v-model="student.name"></el-input>
    </el-form-item>
    <el-form-item label="学号" prop="Id">
      <el-input v-model="student.Id"></el-input>
    </el-form-item>
    <el-form-item label="学生班级" prop="stuClass">
      <el-input v-model="student.stuClass"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('student')">添加</el-button>
      <el-button @click="resetForm('student')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import {Message, MessageBox} from "element-ui";
import {search, student, rules} from "./addStuState";
import {addStu} from "@/views/teacher/components/addStu/addStuState";


export default {
  name: "AddStu",
  data: function () {
    return {
      student,
      rules,
      search
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.student.Id = parseInt(this.student.Id);
          MessageBox.confirm("检查输入无误", "添加学生", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            addStu(this);
          })
              .catch(() => {
                Message.info("取消添加");
              });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>
.demo-student {
  width: 300px;
}
</style>
