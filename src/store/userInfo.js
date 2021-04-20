export default {
    state: {
        userId: sessionStorage.getItem("new:userId"),
        LoginIp: sessionStorage.getItem("new:LoginIp"),
        userLastTime: sessionStorage.getItem("new:userLastTime"),
    },
    mutations: {
        setUserInfo(state, info) {
            const {userName, LoginIp, userLastTime} = info;
            [state.userId, state.LoginIp, state.userLastTime] = [userName, LoginIp, userLastTime]
            sessionStorage.setItem("new:userId", userName);
            sessionStorage.setItem("new:LoginIp", LoginIp);
            sessionStorage.setItem("new:userLastTime", JSON.stringify(userLastTime));
        }
    },
    actions: {},
    getters: {
        getUserId(state) {
            return state.userId;
        },
        getUserInfo(state) {
            const {userId, LoginIp, userLastTime} = state
            return {userId, LoginIp, userLastTime}
        },
    }
}
