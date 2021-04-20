import {postData, Validated} from "@/Util/statue/DButil";
import {requirePost} from "@/Util/Require/request";
import Message from "element-ui/packages/message/src/main";

const classYears = [2018, 2019, 2020, 2021, 2022];
const classTerms = [1, 2];
const rules = {
    classId: [{
        required: true,
        message: "请输入班级编号",
        trigger: "blur"
    },
        {
            min: 1,
            max: 10,
            message: "长度在 1 到10 个字符",
            trigger: "blur"
        }
    ],
    className: [{
        required: true,
        message: "请输入课程名称",
        trigger: "blur"
    },
        {
            min: 1,
            max: 10,
            message: "长度在 1 到10 个字符",
            trigger: "blur"
        }
    ],
    classYear: [{
        required: true,
        message: "请输入学年",
        trigger: "blur"
    }],
    classTerm: [{
        required: true,
        message: "请输入课程学期",
        trigger: "blur"
    }]
}
export {
    classTerms,
    classYears,
    rules
}
export const AddClassUtil = async _this => {
    const config = Object.assign({}, postData);
    config.url = "tea-role/insertClass";
    config.data.teaId = _this.$store.getters.getUserName;
    config.data = Object.assign(config.data, _this.classInfo);
    const {
        isSuc: res,
        msg: message
    } = await requirePost(config);
    if (res) {
        Message.success(message);
    } else {
        Message.error(message);
    }
    _this.$refs["classInfo"].resetFields();
}
