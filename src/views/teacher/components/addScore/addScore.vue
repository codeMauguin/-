<template>
  <el-table :data="dataList" stripe>
    <TableColumn
        v-for="(item, i) in title1"
        :key="i"
        :label="item.label"
        :prop="item.prop"
    />
    <el-table-column label="课程" prop="class" width="180px">
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
    <el-table-column width="130px">
      <template slot-scope="scope">
        <el-input
            v-model="scoreArr[scope.$index]"
            :disabled="dataList[scope.$index]['classTable'].length === 0"
            placeholder="请输入成绩"
        ></el-input>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
            :disabled="scope.row['classTable'].length === 0"
            size="mini"
            type="primary"
            @click="addScore(scope.$index, scope.row)"
        >添加
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import TableColumn from "@/components/public/table/TableColumn";
import TableColumnSelect from "@/components/public/table/TableColumnSelect";
import Message from "element-ui/packages/message/src/main";
import {
  AddScore,
  className,
  dataList,
  queryStu,
  scoreArr,
  title1
} from "@/views/teacher/components/addScore/addScoreUtil";
import {Validated} from "@/Util/statue/DButil";

export default {
  name: "AddScore", data() {
    return {
      dataList,
      title1,
      className,
      scoreArr
    };
  }, async created() {
    let {score} = await queryStu(this);
    this.dataList = score;
  },
  components: {TableColumn, TableColumnSelect},
  methods: {
    addScore(index, row) {
      this.scoreArr[index] = parseInt(this.scoreArr[index]);
      if (isNaN(this.scoreArr[index])) {
        Message.error("请输入正确的成绩");
      } else if (!Validated(this.className[index], this.scoreArr[index])) {
        const config = {
          url: "tea-role/insertScore",
          data: {
            stuId: row.stuId,
            classId: this.className[index],
            scoreValue: this.scoreArr[index]
          }
        };
        AddScore(config, index, row);
        this.scoreArr[index] = '';
      }
    }
  }
};
</script>

<style scoped></style>
