<template>
  <el-table
      v-if="DATA.length"
      :border="true"
      :data="DATA"
      :highlight-current-row="true"
      :v-loading="loading"
      stripe
  >
    <TableColumn
        v-for="(item, i) in title"
        :key="i"
        :label="item.label"
        :prop="item.prop"
    />
    <slot name="slot1"></slot>
  </el-table>
</template>

<script>
import TableColumn from "./TableColumn";

export default {
  name: "ELTable",
  components: {
    TableColumn
  },
  props: {
    DATA: {type: Array},
    title: {type: Array},
    loading: {
      type: Boolean,
      default() {
        return false;
      }
    }
  }
};
</script>

<style scoped></style>
