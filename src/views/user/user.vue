<template>
  <div class="fat">
    <div class="search">
      <el-input
          v-model="class_name"
          class="el-input"
          placeholder="请输入课程名称"
      >
        <el-select slot="prepend" v-model="class_year" placeholder="请选择学年">
          <el-option label="请选择学年" value=""></el-option>
          <el-option label="2018-2019" value="2018"></el-option>
          <el-option label="2019-2020" value="2019"></el-option>
          <el-option label="2020-2021" value="2020"></el-option>
        </el-select>
        <el-select
            slot="prepend"
            v-model="class_term"
            class="input-with-select"
            placeholder="请选择学期"
        >
          <el-option label="学年" value=""></el-option>
          <el-option label="1" value="1"></el-option>
          <el-option label="2" value="2"></el-option>
        </el-select>
        <el-button
            slot="append"
            icon="el-icon-search"
            type="primary"
            @click="search"
        ></el-button>
      </el-input>
    </div>
    <div class="data">
      <ELTable :d-a-t-a="DATA" :title="title"/>
      <el-pagination
          v-show="DATA.length"
          :current-page="page.currentPage"
          :page-count="10"
          :page-size="page.size"
          :page-sizes="page.pageSizes"
          :total="page.totalSize"
          background
          layout="total, sizes, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"/>
    </div>
  </div>
</template>

<script>
import {Message} from "element-ui";
import {search} from "./util";
import ELTable from "../../components/public/table/ELTable";
import {pageSearch} from "@/views/user/util";

export default {
  name: "User",
  components: {ELTable},
  data() {
    return {
      class_name: "",
      class_year: "",
      class_term: "",
      DATA: [],
      isCommit: false,
      title: [
        {
          label: "学号",
          prop: "stu_id"
        },
        {
          label: "班级名称",
          prop: "class_name"
        },
        {
          label: "学年",
          prop: "class_year"
        },
        {
          label: "学期",
          prop: "class_term"
        },
        {
          label: "分数",
          prop: "score_value"
        },
        {
          label: "授课老师",
          prop: "tea_name"
        }
      ],
      page: {
        currentPage: 1,
        pageSizes: [5, 10, 15],
        totalSize: 0,
        size: 5
      }
    };
  },
  watch: {
    class_name(newVal, oldVal) {
      this.isCommit = newVal === oldVal;
    },
    class_year(newVal, oldVal) {
      this.isCommit = newVal === oldVal;
    },
    class_term(newVal, oldVal) {
      this.isCommit = newVal === oldVal;
    }
  },
  created() {
    if (this.$store.state.token === "") {
      this.$alert("未登录", "先登录", {
        confirmButtonText: "确定",
        callback: () => {
          this.$router.replace("/");
        }
      });
    }
  },
  methods: {
    search() {
      if (this.class_term !== "" && this.class_year === "") {
        Message.error("学期选择，学年必须选择！");
      } else {
        //执行搜索
        search(this);
      }
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      pageSearch(this);
    },
    handleSizeChange(val) {
      if (this.DATA.length > 0) {
        this.page.currentPage = 1;
        this.page.size = val;
        pageSearch(this);
      }
    }
  }
};
</script>

<style scoped>
.fat {
  margin: 0 auto;
  width: 80%;
}

.search {
  width: 500px;
}

.el-select {
  width: 120px;
  margin-left: 5px;
}

.input-with-select {
  width: 80px;
}

.data {
  margin: 0 auto;
}

</style>
