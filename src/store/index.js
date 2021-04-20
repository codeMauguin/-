import Vue from "vue";
import Vuex from "vuex";
import model1 from "@/store/model1";
import userInfo from "@/store/userInfo";

Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        userName: sessionStorage.getItem("new:userName"),
        token: sessionStorage.getItem("new:token"),
        authority: sessionStorage.getItem("new:authority")
    },
    mutations: {
        refreshUserName(state, userName) {
            state.userName = userName;
            state.token = sessionStorage.getItem(state.userName + ":token");
            state.authority = sessionStorage.getItem(state.userName + ":authority");
        },
        refreshData(state, config) {
            [state.userName, state.token, state.authority] = [config.userName, config.token, config.authority];
            sessionStorage.setItem(state.userName + ":token", state.token);
            sessionStorage.setItem("new:token", state.token);
            sessionStorage.setItem(config.userName, config.userName);
            sessionStorage.setItem("new:userName", state.userName);
            sessionStorage.setItem(config.userName + ":authority", state.authority);
            sessionStorage.setItem("new:authority", state.authority);
        },
        exit(state) {
            state.token = "";
            state.userName = "";
            state.authority = "";
        }
    },
    actions: {},
    modules: {
        mode: model1, userInfo
    },
    getters: {
        getUserName(state) {
            return state.userName;
        },
        getRole(state) {
            return state.authority;
        },
        getToken(state) {
            return state.token;
        }
    }
});
