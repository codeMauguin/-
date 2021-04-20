import {requireGet, requirePost} from "@/Util/Require/request";
import {getData, postData} from "@/Util/statue/DButil";
import Message from "element-ui/packages/message/src/main";

const title = [{
    label: "学生姓名",
    prop: "stuName"
},
    {
        label: "班级名称",
        prop: "stuId"
    }
];
let className = [];

const AddStuFuc1 = async _this => {
    const config = Object.assign({}, getData)
    config.url = "tea-role/queryStuClass";
    config.params.teaId = _this.$store.getters.getUserName;
    const {
        isSuc: res,
        data: {
            list,
            len
        },
        msg: message
    } = await requireGet(config);
    if (res) {
        _this.data = list;
        className = new Array(len).fill("");
    } else {
        Message.warning(message)
    }
    _this.isLoad = false;
    return res;
};
const AddFunc2 = async (data, _this, row, index) => {
    const config = Object.assign({}, postData);
    config.url = "tea-role/insertStuClass";
    config.data = data;
    const {
        isSuc: res,
        msg: message
    } = await requirePost(config);
    if (res) {
        row["classTable"].forEach((item, i) => {
            if (item.classId === config.classId) {
                row["classTable"].splice(i, 1);
            }
        });
        _this.className[index] = "";
        Message.success(message);
    } else {
        Message.error(message);
    }
    _this.isLoad = false;
};
export {
    title, className,
    AddStuFuc1,
    AddFunc2
}
