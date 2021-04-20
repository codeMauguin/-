<template>
  <div class="query_stu">
    <el-form :inline="true" :model="search" class="demo-form-inline">
      <el-form-item label="课程名称">
        <TableColumnSelect
            :arr="search.classId"
            :d-a-t-a="search.options"
            :index="0"
            label="className"
            select-hold="课程名称"
            value="classId"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchData">查询</el-button>
        <el-button type="primary" @click="searchAll">查询所有</el-button>
      </el-form-item>
    </el-form>
    <ELTable :d-a-t-a="student" :loading="isLoading" :title="title"/>
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
import {getClass, getStudent} from "../../util/require";
import ELTable from "@/components/public/table/ELTable";
import {MessageBox} from "element-ui";
import TableColumnSelect from "@/components/public/table/TableColumnSelect";

export default {
  name: "QueryStu",
  components: {
    ELTable,
    TableColumnSelect
  },
  data() {
    return {
      student: [],
      title: [
        {
          label: "课程名称",
          prop: "class_name"
        },
        {
          label: "学生班级",
          prop: "stu_class"
        },
        {
          label: "学号",
          prop: "stu_id"
        },
        {
          label: "学生姓名",
          prop: "stu_name"
        }
      ],
      search: {
        classId: {type: Array(1)},
        options: []
      },
      isLoading: false,
      page: {
        currentPage: 1,
        size: 5,
        pageSizes: [5, 10, 15],
        totalSize: 0
      }
    };
  },
  mounted() {
    getClass(this.$store.getters.getUserName, this);
  },
  methods: {
    searchData() {
      this.page.currentPage = 1;
      if (
          this.search.classId[0] !== undefined &&
          this.search.classId[0] !== ""
      ) {
        this.isLoading = true;
        getStudent(this.search.classId[0], this);
      } else {
        MessageBox.alert("选择课程！");
      }
    },
    searchAll() {
      this.search.classId[0] = ''
      this.page.currentPage = 1;
      this.isLoading = true;
      getStudent(-1, this);
    },
    handleCurrentChange(val) {
      this.isLoading = true;
      this.page.currentPage = val;
      let classId;
      if (
          this.search.classId[0] === undefined ||
          this.search.classId[0] === ""
      ) {
        classId = -1;
      } else {
        classId = this.search.classId[0];
      }
      getStudent(classId, this);
    },
    handleSizeChange(val) {
      if (this.student.length > 0) {
        this.isLoading = true;
        this.page.size = val;
        let classId;
        if (
            this.search.classId[0] === undefined ||
            this.search.classId[0] === ""
        ) {
          classId = -1;
        } else {
          classId = this.search.classId[0];
        }
        this.page.currentPage = 1;
        getStudent(classId, this);
      }
    }
  }
};
</script>

<style scoped>

</style>
