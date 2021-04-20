import {requireGet} from "@/Util/Require/request";

const config = {
    url: String,
    params: {
        teaId: Number,
        classId: -1
    }
};
const querySTuConfig = {
    url: String,
    params: {
        teaId: Number,
        classId: -1,
        pageCurrent: 1,
        pageSize: 5
    }
};
export const getStudent = async (classId, _this) => {
    querySTuConfig.url = "tea-role/queryStudent";
    let {
        $store: {
            getters: {getUserName}
        },
        page: {
            currentPage,
            size
        }
    } = _this;
    const params = querySTuConfig.params;
    [params.teaId, params.classId, params.pageCurrent, params.pageSize] = [getUserName, classId, currentPage, size];
    querySTuConfig.params = params;
    const {
        data: {
            list: {
                list,
                total
            } = {}
        },
        isSuc: res
    } = await requireGet(querySTuConfig) || {};
    if (res) {
        _this.student = list;
        _this.page.totalSize = total;
    }
    _this.isLoading = false;
};
export const getClass = async (data, _this) => {
    config.url = "tea-role/queryClass";
    config.params.teaId = data;
    const {
        data: {
            list
        }
    } = await requireGet(config);
    _this.search.options = list;
};
const config1 = {
    url: String,
    data: {
        stuId: Number,
        stuName: String,
        stuClass: String
    }
};
