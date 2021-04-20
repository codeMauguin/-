import {requirePost} from "@/Util/Require/request";
import {postData} from "@/Util/statue/DButil";
import Message from "element-ui/packages/message/src/main";

const student = {
    name: "",
    Id: "",
    stuClass: ""
}
const rules = {
    name: [
        {required: true, message: "请输入学生姓名", trigger: "blur"},
        {min: 1, max: 10, message: "长度在 1 到10 个字符", trigger: "blur"}
    ],
    stuClass: [
        {required: true, message: "请选择班级", trigger: "blur   "}
    ],
    Id: [
        {required: true, message: "输入学号", trigger: "blur"},
        {min: 4, max: 13, message: "长度在 5到13个字符", trigger: "blur"}
    ]
}
const search = {
    options: []
}
export {student, search, rules} ;
export const addStu = async _this => {
    const config = Object.assign({}, postData);
    config.url = "tea-role/insertStu";
    config.data.stuId = _this.student.Id;
    config.data.stuName = _this.student.name;
    config.data.stuClass = _this.student.stuClass;
    const {isSuc: res, msg: message} = await requirePost(config);
    if (res) {
        Message.success(message);
    } else {
        Message.error(message);
    }
    _this.$refs["student"].resetFields();
};
