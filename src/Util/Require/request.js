import Axios from "axios";
import store from "@/store";
import router from "@/router";
import QS from 'qs'
import Message from "element-ui/packages/message/src/main";

const UserERR = ex => {
    Message.warning(ex);
    router.push("/").then();
    store.commit("exit");
    sessionStorage.clear();
};
Axios.defaults.baseURL = BASEURL;
Axios.defaults.timeout = 3000;
Axios.interceptors.request.use(config => {
        if (config.data !== null) {
            config.data = QS.stringify(config.data);
        }
        config.headers.authorization = store.getters.getToken === null ? "ERROR" : store.getters.getToken
        return config;
    },
    () => {
        Message.error("服务器长时间没有响应！");
    })
Axios.interceptors.response.use(result => {
    if(result.status&&result.status===200){
        const {data: {isSuc = {}, code = {}, msg = {}}} = result || {};
        if (isSuc) {
            return result.data
        } else {
            if (code === 101) {
                UserERR(result.data.msg);
            }
            return {isSuc, msg, code}
        }
    }else {
        Message.error("请求出错！");
    }
    },
    () => {
        Message.error("服务器错误！");
        router.replace("/").then();
    })

export const requirePost = config => {
    return Axios.post(config.url, config.data);
}
export const requireGet = config => {
    return Axios.get(config.url, {
        params: config.params
    });
}
export const requirePatch = config => {
    return Axios.patch(config.url, config.data)
}
