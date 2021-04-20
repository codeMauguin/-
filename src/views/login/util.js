import {requirePost} from "@/Util/Require/request";
import {DataUtil, encryptionPassword, Validated} from "@/Util/statue/DButil";
import Message from "element-ui/packages/message/src/main";

let DATA1 = {
    url: "login",
    data: {
        countId: Number,
        countPwd: String
    }
};
export const loginPost = async _this => {
    let {
        userName,
        role
    } = _this.login_form;
    let passWord = _this.password
    const con = {
        url: "count/getPrivateKey",
        data: {
            id: userName
        }
    }
    if (Validated(userName,passWord)){
        Message.warning("用户名或者密码为空")
        return;
    }
    const {data: {PUBLIC_KEY: PUBLIC_KEY}} = await requirePost(con);
    passWord = encryptionPassword(window.atob(passWord), PUBLIC_KEY);
    _this.$store.commit("refreshUserName", userName);
    DataUtil(DATA1.data, ['countId', 'countPwd'], [userName, passWord]);
    const {
        isSuc: res,
        msg: message,
        data: {
            token: token,
            role: {
                authority: authority
            } = {}
        } = {}
    } = await requirePost(DATA1) || {};
    _this.$refs['login_form'].resetFields();
    if (res) {
        _this.$store.commit("refreshData", {
            token: token,
            userName: userName,
            authority: authority
        });
        if (authority === role) {
            Message.success("登录成功");
            switch (authority) {
                case "STU_ROLE":
                    _this.$router.push({
                        path: "/user"
                    }).then();
                    break;
                case "TEA_ROLE":
                    _this.$router.push({
                        path: "/teacher"
                    }).then();
                    break;
                default:
                    Message.info("没有权限");
                    break;
            }
        } else if (authority === "ADMIN_ROLE") {
            Message.success(
                "管理员登陆" + _this.$store.getters.getUserName + "你好"
            );
            _this.$router.push({
                path: "/teacher"
            }).then();
        } else {
            Message.info("没有权限")
        }
    } else {
        Message.error(message);
    }
}
