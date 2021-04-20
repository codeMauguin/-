<template>
  <ELTable :DATA="list" :title="title"/>
</template>

<script>
import ELTable from "@/components/public/table/ELTable";
import {QueryClass} from "@/views/teacher/util/require2";

export default {
  name: "ClassInfo",
  components: {
    ELTable
  },
  data() {
    return {
      title: [
        {
          label: "课程名称",
          prop: "className"
        },
        {
          label: "学年",
          prop: "classYear"
        },
        {
          label: "学期",
          prop: "classTerm"
        },
        {
          label: "已加入课程学生",
          prop: "stuNum"
        }
      ],
      list: []
    };
  },
  created() {
    QueryClass(this);
  }
};
</script>

<style scoped></style>
