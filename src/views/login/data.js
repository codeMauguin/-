const rules = {
    userName: [
        {required: true, message: "请输入账号", trigger: "blur"},
        {min: 3, max: 15, message: "长度在 3 到 16个字符", trigger: "blur"},
    ],
    passWord: [
        {required: true, message: "请输入账户密码", trigger: "blur"},
        {min: 3, message: "请输入密码", trigger: "blur"},
    ],
}
export {rules}
