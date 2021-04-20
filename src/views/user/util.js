import {requireGet} from "@/Util/Require/request";
import Message from "element-ui/packages/message/src/main";

let params = {
    stuId: -1,
    classYear: String,
    classTerm: String,
    className: String,
    pageNum: 1,
    pageSize: 5
};

const util = _this => {
    params.stuId = _this["$store"].getters.getUserName;
    params.className = _this["class_name"];
    params.classTerm = _this["class_term"];
    params.classYear = _this["class_year"];
};
/**
 * 搜索成绩
 * @param _this
 */
export const search = _this => {
    if (!_this["isCommit"]) {
        util(_this);
        searchUtil(_this).then();
        _this["isCommit"] = true;
    }
};
const utilPage = _this => {
    params.pageNum = _this.page.currentPage;
    params.pageSize = _this.page.size;
};
const searchUtil = async _this => {
    const {data: {list: list, list: {list: dataList}} = {}} = await requireGet({
        url: "stu-role/selectScore",
        params
    }) || {};
    if (dataList.length !== 0) {
        _this.page.totalSize = list.total;
        _this["DATA"] = dataList;
    } else {
        Message.info("没有查到成绩信息");
        _this["DATA"] = [];
    }
};
export const pageSearch = _this => {
    util(_this);
    utilPage(_this);
    searchUtil(_this).then();
};
