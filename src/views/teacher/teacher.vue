<template>
  <layout-main :aside-width="isCollapse ? '64.5px' : '200px'">
    <slot slot="header">
      <div class="left_box">
        <img alt="header_img" src="@/assets/logo.png"/>
        <span>{{ this.$store.getters.getRole === "STU_ROLE" ? "成绩查询系统" : "学生管理" }}</span>
      </div>
      <div class="right_box">
        <DropDrown/>
      </div>
    </slot>
    <slot slot="aside">
      <div class="toggle_box" @click="toggle">|||</div>
      <menu-tree :is-collapse="isCollapse" :menu-list="MenuList" :path="$route.path">
        <menu-util v-show="this.$store.getters.getRole === 'ADMIN_ROLE'" slot="admin" :data="AdminMenuTreeList"/>
      </menu-tree>
    </slot>
    <slot slot="main">
      <bread-nav :nav="[this.$store.getters.getMenu, this.$store.getters.getMenuIem]" :path="path"/>
    </slot>
  </layout-main>
</template>
<script>
import {MenuTree, MenuUtil} from "@/components/public/MenuTree";
import {MenuTreeList, stuMenuTreeList, AdminMenuTreeList} from "@/views/teacher/util/DATA";
import DropDrown from "@/components/private/DropDrown";
import breadNav from "@/components/private/breadNav";
import layoutMain from '@/components/public/layoutMain'

export default {
  name: "Teacher",
  components: {
    MenuUtil,
    MenuTree,
    DropDrown, breadNav, layoutMain
  },
  data() {
    return {
      isCollapse: false,
      MenuList: [], path: this.$route.matched[0].path, AdminMenuTreeList
    };
  },
  created() {
    if (this.$store.getters.getRole === "STU_ROLE") {
      this.MenuList = stuMenuTreeList
    } else
      this.MenuList = MenuTreeList;
    this.isCollapse = sessionStorage.getItem("isCollapse") === 'true';
  },
  methods: {
    toggle() {
      this.isCollapse = !this.isCollapse;
      sessionStorage.setItem("isCollapse", this.isCollapse)
    }, saveMenuStatus(val) {
      sessionStorage.setItem("activeIndex", '4.1');
      this.$store.commit('setSubMenu', val)

    }, saveStatus(val) {
      this.$store.commit('setNamedItem', val)
    }
  }, watch: {
    /**
     * 监听页面是否存在 welcome 界面
     * @param to 页面即将取得地方
     */
    '$route': function (to) {
      if (to.name === 'welcome' || to.name === 'userView') {
        this.$store.commit('removeActive')
      }
    }
  }
};
</script>

<style scoped>
:root{
  font-family: "Fira Code",monospace;
}
.el-header,
.left_box {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  color: #fff;
  padding-left: 0;
  align-items: center;
}

.toggle_box {
  background: #2b4b6b;
  text-align: center;
  color: #0e7dec;
}

.left_box img {
  width: 40px;
  height: 40px;
  margin-left: 15px;
}

.left_box span {
  margin-left: 15px;
}


</style>
