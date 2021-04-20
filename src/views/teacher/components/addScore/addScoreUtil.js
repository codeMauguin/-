import {requireGet, requirePost} from "@/Util/Require/request";
import {Message} from "element-ui";
import {getData} from "@/Util/statue/DButil";

let dataList = [];
const title1 = [
    {
        label: "学生姓名",
        prop: "stuName"
    },
    {
        label: "学号",
        prop: "stuId"
    }
];
const className = [];
let scoreArr = []
export {dataList, title1, className, scoreArr}
export const queryStu = async _this_ => {
    const config = Object.assign({}, getData);
    config.url = "tea-role/queryScore";
    config.params.teaId = _this_.$store.getters.getUserName;
    const {data: {score}, isSuc: res, msg: message} = await requireGet(config);
    if (res) {
        scoreArr = new Array(score.length).fill('');
        return {score}
    } else {
        Message.error(message);
        return [];
    }
};
export const AddScore = async (config, index, row) => {
    const {isSuc: res} = await requirePost(config)
    if (res) {
        row["classTable"].splice(
            row["classTable"].indexOf(config.data.classId),
            1
        );
        Message.success("成绩添加成功");
    } else {
        Message.error("失败");
    }
};
