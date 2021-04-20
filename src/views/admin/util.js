import {requirePost} from "@/Util/Require/request";
import {Message} from "element-ui";

const config = {
    url: "admin/addTea",
    data: {}
};
export const addTea = async _this => {
    config.data = _this.teacher;
    const {isSuc, msg} = await requirePost(config)
    if (isSuc) {
        Message.success("添加成功");
        _this.$refs["teacher"].resetFields();
    } else {
        _this.$refs["teacher"].resetFields();
        Message.error(msg);
    }
};
