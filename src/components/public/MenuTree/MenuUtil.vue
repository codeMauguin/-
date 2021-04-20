<template>
  <div>
    <div v-for="(item, i) in data" :key="i">
      <MenuSubmenu v-if="item.childList!=null" :data="item">
        <menu-util :data="item.childList"></menu-util>
      </MenuSubmenu>
      <menu-item v-else :data="item"/>
    </div>
  </div>
</template>

<script>
import MenuSubmenu from './MenuSubmenu'
import MenuItem from "./MenuItem";

export default {
  name: "MenuUtil", props: ['data'], components: {
    MenuSubmenu, MenuItem
  }
}
</script>

<style scoped>
</style>
