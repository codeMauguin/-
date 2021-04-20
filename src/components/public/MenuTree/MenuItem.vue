<template>
  <el-menu-item :index="data.id + ''" :route="data.route" @click="indexSave(data.id + '', data['menuName'])">
    <template slot="title">
      <i :class="data.icon"></i>
      <span>{{ data["menuName"] }}</span>
    </template>
  </el-menu-item>
</template>
<script>
export default {
  name: "MenuItem",
  props: {
    data: {
      type: Object
    }
  }, methods: {
    indexSave(val1, val2) {
      sessionStorage.setItem("activeIndex", val1);
      this.$store.commit('setNamedItem', val2)
    }
  }
};
</script>

<style scoped></style>
