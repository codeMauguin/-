<template>
  <el-form
    ref="classInfo"
    :model="classInfo"
    :rules="rules"
    class="form"
    label-width="90px"
    lass="demo-student"
  >
    <el-form-item label="课程编号:" prop="classId">
      <el-input v-model="classInfo.classId" />
    </el-form-item>
    <el-form-item label="课程名称:" prop="className">
      <el-input v-model="classInfo.className"></el-input>
    </el-form-item>
    <el-form-item label="学年:" prop="classYear">
      <el-select v-model="classInfo.classYear">
        <el-option
          v-for="item in classYears"
          :key="item"
          :label="item + '-' + (item + 1)"
          :value="item"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="学期" prop="classTerm">
      <el-select v-model="classInfo.classTerm" style="margin-left: 10px">
        <el-option
          v-for="item in classTerms"
          :key="item"
          :label="item"
          :value="item"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('classInfo')"
        >添加
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { Message } from "element-ui";
import { AddClassUtil, classTerms, classYears, rules } from "./addClassUtil";

export default {
  name: "AddClass",
  data() {
    return {
      classInfo: {
        classId: "",
        className: "",
        classYear: "",
        classTerm: "",
      },
      classYears,
      classTerms,
      rules,
    };
  },
  methods: {
    submitForm: function (valida) {
      this.$refs[valida].validate((valid) => {
        if (!valid) {
          console.log("error submit!!");
          return false;
        } else {
          this.classInfo.classId = parseInt(this.classInfo.classId);
          if (isNaN(this.classInfo.classId)) {
            Message.warning("课程编号类型不匹配");
            this.classInfo.classId = "";
          } else {
            AddClassUtil(this);
          }
        }
      });
    },
  },
};
</script>

<style scoped>
.form {
  width: 300px;
}
</style>
