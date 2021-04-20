<template>
  <el-form
      ref="teacher"
      :model="teacher"
      :rules="rules"
      class="demo-tea"
      label-width="100px"
  >
    <bread-nav :nav="['管理员', '添加老师']" path="/teacher"/>
    <el-form-item label="教师编号" prop="teaId">
      <el-input v-model="teacher.teaId"/>
    </el-form-item>
    <el-form-item label="教师姓名" prop="teaName">
      <el-input v-model="teacher.teaName"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('teacher')">添加</el-button>
      <el-button @click="resetForm('teacher')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import breadNav from "@/components/private/breadNav";
import {addTea} from "@/views/admin/util";

export default {
  name: "AddTea",
  components: {
    breadNav
  },
  data() {
    return {
      teacher: {
        teaId: "",
        teaName: ""
      },
      rules: {
        teaId: [{required: true, message: "输入教师编号", trigger: "blur"}],
        teaName: [{required: true, message: "输入教师姓名", trigger: "blur"}]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          addTea(this);
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
.demo-tea {
  width: 300px;
}
</style>
