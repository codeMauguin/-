<template>
  <el-container class="main_con">
    <el-header>
      <slot name="header"></slot>
    </el-header>
    <el-container>
      <!--侧边栏-->
      <el-aside :width="asideWidth">
        <!--展开/收起-->
        <slot name="aside"></slot>
      </el-aside>
      <el-container>
        <el-main>
          <slot name="main"></slot>
          <keep-alive>
            <el-card class="box-card">
              <router-view/>
            </el-card>
          </keep-alive>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "layoutMain", props: ['asideWidth']
}
</script>

<style scoped>
.main_con {
  height: 100%;
}

.el-aside {
  background-color: #373d41;
  transition: 0.7s width;
}

.el-dropdown-link {
  text-align: center;
  cursor: pointer;
  color: #409eff;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-menu {
  border-right: none;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  color: #fff;
  padding-left: 0;
  align-items: center;
}

.toggle_box {
  background: #4a5064;
  font-weight: bolder;
  font-size: 15px;
  line-height: 25px;
  color: #fff;
  text-align: center;
  cursor: pointer;
}

.el-main {
  background: rgb(233, 237, 240);
}

.box-card {
  height: 95%;
  position: relative;
}
</style>
