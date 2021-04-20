import {requirePatch, requirePost} from "@/Util/Require/request";
import {encryptionPassword, Validated} from "@/Util/statue/DButil";
import Message from "element-ui/packages/message/src/main";

export const changePwd = async (userName, oldPwd, newPwd) => {
    if (Validated(userName,oldPwd,newPwd)){
        Message.warning("不可以有空输入");
        return;
    }
    const con = {
        url: "count/getPrivateKey",
        data: {
            id: userName
        }
    }
    const {data: {PUBLIC_KEY: PUBLIC_KEY}} = await requirePost(con);
    oldPwd = encryptionPassword(oldPwd, PUBLIC_KEY);
    newPwd = encryptionPassword(newPwd, PUBLIC_KEY);
    con.url = "countInfo/userChangePwd";
    con.data = {
        oldPwd, newPwd, "userName": userName
    }
    const {isSuc: result} = await requirePatch(con);
    if (result) {
        Message.success("修改成功");
    } else {
        Message.error("修改失败");
    }
}
