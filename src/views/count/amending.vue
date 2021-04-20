<template>
  <el-popover placement="left" trigger="click">
    <el-card class="box-card">
      <div>我的Id:{{ this.$store.getters.getUserName }}</div>
      <div>我的名称:{{ myInfo.userName }}</div>
      <div>
        上次登录时间:{{
          myInfo.lastLoginTime.year +
          "年" +
          myInfo.lastLoginTime.month +
          "月" +
          myInfo.lastLoginTime.day +
          "天" +
          myInfo.lastLoginTime.hour +
          "时" +
          myInfo.lastLoginTime.minute +
          "分"
        }}
      </div>
    </el-card>
    <el-dropdown-item slot="reference" icon="el-icon-info"
    >我的信息
    </el-dropdown-item
    >
  </el-popover>
</template>

<script>
import {requireGet} from "@/Util/Require/request";

export default {
  name: "Amending",
  components: {},
  data() {
    return {
      myInfo: {
        userName: "",
        lastLoginTime: {
          year: "",
          month: "",
          day: "",
          hour: "",
          minute: ""
        }
      }
    };
  },
  created: async function () {
    let {data: result} = await requireGet({
      url: "countInfo/queryInfo",
      params: {id: this.$store.getters.getUserName}
    }) || {};
    const {userName, userLastTime: time} = result
    this.$store.commit('setUserInfo', result);
    this.myInfo.userName = userName;
    this.myInfo.lastLoginTime.year = time.year;
    let flag = this.myInfo.lastLoginTime;
    [flag.month, flag.day, flag.hour, flag.minute] = [time["monthValue"], time["dayOfMonth"], time["hour"], time["minute"]]
  }
};
</script>

<style scoped>
.box-card {
  margin: 0 auto;
  width: 500px;
}
</style>
