<template>
  <el-dropdown class="drop_box" @command="handleCommand">
    <el-button type="primary">
      {{ this.$store.getters.getUserId }}<i class="el-icon-arrow-down el-icon--right"></i>
    </el-button>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="home" icon="el-icon-house">主页</el-dropdown-item>
      <amending/>
      <mod-pwd/>
      <el-dropdown-item icon="el-icon-circle-close" @click.native="exit()"
      >退出
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
import amending from "@/views/count/amending";
import modPwd from "@/views/count/modPwd"
import {requirePost} from "@/Util/Require/request";
import Message from "element-ui/packages/message/src/main";

export default {
  name: "DropDrown", methods: {
    async exit() {
      const {msg, isSuc} = await requirePost({
        url: "logOut"
      });
      if (isSuc) {
        Message.success(msg);
        this.$store.commit("exit");
        sessionStorage.clear();
        await this.$router.replace("/");
      }
    },
    handleCommand() {
      this.$router.push(this.$route.matched[0].path)
    }
  },
  components: {
    amending, modPwd
  },
};
</script>

<style scoped>
.drop_box {
  color: #0e7dec;
}
</style>
