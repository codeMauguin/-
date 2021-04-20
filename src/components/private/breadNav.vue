<template>
  <el-breadcrumb
      separator-class="el-icon-arrow-right"
      style="padding-left: 10px;padding-bottom: 10px;font-size: 12px"
  >
    <el-breadcrumb-item :to="path">首页</el-breadcrumb-item>
    <template v-for="(item, i) in nav">
      <el-breadcrumb-item v-if="item" :key="i">{{ item }}</el-breadcrumb-item>
    </template>
  </el-breadcrumb>
</template>
<script>
export default {
  name: "BreadNav",
  props: ["path", "nav"]
};
</script>

<style scoped></style>
