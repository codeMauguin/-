<template>
  <el-table-column :label="label" :prop="prop" sortable>
  </el-table-column>
</template>

<script>
export default {
  name: "TableColumn",
  props: ["label", "prop"]
};
</script>

<style scoped></style>
