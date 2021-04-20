import {requireGet, requirePatch} from "@/Util/Require/request";

const config = {
    url: String,
    params: {teaId: Number},
};

let postConfig = {
    url: String,
    data: {
        teaId: Number
    }
};
export const QueryClass = async _this => {
    config.url = "tea-role/queryTeaClass";
    config.params.teaId = _this.$store.getters.getUserName;
    const {isSuc: res, data: {list}} = await requireGet(config)
    if (res) {
        _this.list = list;
    }
};
const configPage = {
    url: String,
    params: {
        teaId: Number,
        pageNum: Number, pageSize: Number
    }
};
export const getList = (_name, pageNum, pageSize) => {
    configPage.url = 'tea-role/queryScoreList';
    [configPage.params.teaId, configPage.params.pageNum, configPage.params.pageSize] = [_name, pageNum, pageSize];
    return requireGet(configPage)
}
const postConfig2 = {
    url: String,
    data: {
        stuId: Number,
        teaId: Number,
        classId: Number,
        scoreValue: Number
    }
}
export const changeScore = data => {
    postConfig2.url = 'tea-role/updateScore'
    postConfig2.data = data;
    return requirePatch(postConfig2);
}
