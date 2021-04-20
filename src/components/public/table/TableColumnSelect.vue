<template>
  <el-select
      v-model="arr[index]"
      :disabled="DATA.length === 0"
      :placeholder="selectHold"
      clearable
      filterable
      remote
  >
    <el-option
        v-for="(item, i) in DATA"
        :key="i"
        :label="item[label]"
        :value="item[value]"
    >
    </el-option>
  </el-select>
</template>

<script>
export default {
  name: "TableColumnSelect",
  props: ["DATA", "label", "value", "index", "arr", "selectHold"],
};
</script>

<style scoped></style>
