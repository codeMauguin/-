<template>
  <el-submenu :index="data.id + ''" @click.native="submenu(data['menuName'])">
    <template slot="title">
      <i :class="data.icon"></i>
      <span>{{ data["menuName"] }}</span>
    </template>
    <slot></slot>
  </el-submenu>
</template>
<script>
export default {
  name: "MenuSubmenu",
  props: {data: {type: Object}}, methods: {
    submenu(val) {
      this.$store.commit('setSubMenu', val)
    }
  }
};
</script>

<style scoped>
.el-menu--collapse span,
.el-menu-collapse i,
.el-menu--collapse, .el-submenu_icon-arrow {
  height: 0;
  width: 0;
  overflow: hidden;
  visibility: hidden;
  display: inline-block;
}
</style>
