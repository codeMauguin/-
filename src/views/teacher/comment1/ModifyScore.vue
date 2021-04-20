<template>
  <div>
    <ELTable :DATA="list" :title="title">
      <slot slot="slot1">
        <el-table-column label="课程名称">
          <template slot-scope="scope">
            <TableColumnSelect
                :arr="classId"
                :change="change(scope.$index,scope.row)"
                :d-a-t-a="scope.row['classTable']"
                :index="scope.$index"
                label="className"
                select-hold="课程名称"
                value="classId"
            />
          </template>
        </el-table-column>
        <el-table-column label="分数">
          <template slot-scope="scope">
            <span>{{ ScoreValue[scope.$index] }}</span>
          </template>
        </el-table-column>
        <el-table-column label="修改">
          <template slot-scope="scope">
            <el-button type="danger" @click="modifyScore(scope.$index,scope.row)">修改</el-button>
          </template>
        </el-table-column>
      </slot>
    </ELTable>
    <el-dialog
        :visible.sync="centerDialogVisible"
        center
        title="修改成绩"
        width="30%">
      <el-form v-model="scoreInfo">
        <el-form-item label="用户Id" label-width="120px">
          <span>{{ stuName }}</span>
        </el-form-item>
        <el-form-item label="课程名称" label-width="120px">
          <span>{{ className }}</span>
        </el-form-item>
        <el-form-item label="课程分数" label-width="120px">
          <el-input v-model="scoreInfo.scoreValue"/>
        </el-form-item>
      </el-form>
      <el-button @click="centerDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="commitScore">确 定</el-button>
    </el-dialog>
    <el-pagination
        :current-page="page.currentPage"
        :page-count="10"
        :page-size="5"
        :page-sizes="page.pageSizes"
        :total="page.totalSize"
        background
        layout="total, sizes, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
    />
  </div>
</template>

<script>
import ELTable from "@/components/public/table/ELTable";
import {changeScore, getList} from "@/views/teacher/util/require2";
import TableColumnSelect from "@/components/public/table/TableColumnSelect";
import Message from "element-ui/packages/message/src/main";

export default {
  name: "ModifyScore", data() {
    return {
      list: [], title: [{
        label: '学生姓名', prop: 'stuName'
      }], page: {
        currentPage: 1,
        size: 5,
        pageSizes: [5, 10, 15],
        totalSize: 0
      }, classId: [], ScoreValue: [], centerDialogVisible: false,
      scoreInfo: {
        stuId: '',
        classId: '',
        scoreValue: '',
        teaId: this.$store.getters.getUserName
      }, className: '', stuName: '', ActiveIndex: ''
    }
  }, components: {
    ELTable, TableColumnSelect
  }, created() {
    this.dataHandler();
  }, methods: {
    async dataHandler() {
      const {data: result} = await getList(
          this.$store.getters.getUserName,
          this.page.currentPage,
          this.page.size);
      this.list = result.list.list;
      this.classId = [];
      this.ScoreValue = [];
      this.classId = new Array(this.list.length).fill('');
      this.ScoreValue = new Array(this.list.length).fill('');
      this.page.totalSize = result.list.total
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.dataHandler();
    },
    handleSizeChange(val) {
      if (this.list.length > 0) {
        this.page.currentPage = 1;
        this.page.size = val;
        this.dataHandler();
      }
    }, modifyScore(index, row) {
      this.ActiveIndex = index;
      if (this.classId[index] !== '') {
        this.scoreInfo.stuId = row.stuId;
        this.stuName = row.stuName;
        row['classTable'].forEach(item => {
          if (item.classId === this.classId[index]) {
            this.className = item.className;
          }
        })
        this.scoreInfo.classId = this.classId[index]
        this.centerDialogVisible = true
      } else {
        Message.warning("请选择修改成绩的课程")
      }
    },
    change(index, row) {
      row['classTable'].forEach(item => {
        if (item.classId === this.classId[index]) {
          this.ScoreValue[index] = item.scoreValue;
        }
      })
    }, async commitScore() {
      if (this.scoreInfo.scoreValue !== '') {
        const {isSuc: res, msg: message} = await changeScore(this.scoreInfo)
        if (res) {
          Message.success(message);
          this.ScoreValue[this.ActiveIndex] = this.scoreInfo.scoreValue;
          setTimeout(() => {
            this.$router.go(0);
          }, 1000)

        } else {
          Message.error(message)
        }
      } else {
        Message.info("你什么都没有操作")
      }
      this.centerDialogVisible = false
    }
  }
}
</script>

<style scoped>
</style>
