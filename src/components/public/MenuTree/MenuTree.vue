<template>
  <el-menu
      :collapse="isCollapse"
      :collapse-transition="false"
      :default-active="this.$store.getters.getActiveIndex"
      :router="true"
      :unique-opened="true"
      active-text-color="#409eff"
      background-color="#373d41"
      class="el-menu-vertical-demo"
      text-color="#fff"
  >
    <menu-util :data="menuList"/>
    <slot name="admin"></slot>
  </el-menu>
</template>
<script>
import MenuUtil from './MenuUtil'

export default {
  name: "MenuTree",
  props: {
    menuList: {
      type: Array,
      default() {
        return [];
      }
    },
    isCollapse: {
      type: Boolean,
      default() {
        return false;
      }
    }
  }, components: {MenuUtil}
};
</script>
<style scoped></style>
