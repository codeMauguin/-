<template>
  <ELTable :d-a-t-a="data" :loading="isLoad" :title="title">
    <slot slot="slot1">
      <el-table-column label="可添加课程" prop="class" width="180">
        <template slot-scope="scope">
          <TableColumnSelect
              :arr="className"
              :d-a-t-a="scope.row['classTable']"
              :index="scope.$index"
              label="className"
              select-hold="课程名称"
              value="classId"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              :disabled="scope.row['classTable'].length === 0"
              size="mini"
              type="primary"
              @click="addClass(scope.$index, scope.row)"
          >添加
          </el-button>
        </template>
      </el-table-column>
    </slot>
  </ELTable>
</template>

<script>
import Message from "element-ui/packages/message/src/main";
import ELTable from "@/components/public/table/ELTable";
import TableColumnSelect from "@/components/public/table/TableColumnSelect";
import {
  title, className,
  AddStuFuc1,
  AddFunc2,
} from "./addStuClassState";

export default {
  name: "AddStuClass",
  components: {
    ELTable,
    TableColumnSelect,
  },
  data() {
    let data = [];
    return {
      data,
      title,
      className,
      isLoad: true,
    };
  },
  created() {
    AddStuFuc1(this);
  },
  methods: {
    addClass(index, row) {
      if (this.className[index] === "" || this.className[index] === undefined) {
        Message.info("请补全信息!");
      } else {
        const data = {
          stuId: row.stuId,
          classId: this.className[index],
          teaId: this.$store.getters.getUserName,
        };
        this.isLoad = true;
        AddFunc2(data, this, row, index);
      }
    },
  },
};
</script>

<style scoped></style>
