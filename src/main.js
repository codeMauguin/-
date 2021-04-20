import Vue from "vue";
import App from "./App.vue";
import "element-ui/lib/theme-chalk/index.css";
import router from "./router";
import store from "./store";
import "@/assets/css/global.css";
import '@/plugins/element'

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount("#app");
