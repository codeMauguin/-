import {JSEncrypt} from 'jsencrypt'
import fa from "element-ui/src/locale/lang/fa";

/**
 * 对象类型工具 快速将另一个对象的值赋给另一个对象的值
 * @param dest
 * @param src
 */

const ObjUtil = (dest, src) => {
    if ((typeof dest) != "object" || (typeof src) != "object") {
        throw Error("传入应该为对象类型！");
    }
    const dest_key = Object.keys(dest);
    const src_key = Object.keys(src);
    const temp = [];
    dest_key.forEach(item => {
        if ((typeof dest[item]) != "object" && src_key.indexOf(item) !== -1) {
            Reflect.set(dest, item, src[item]);
        } else if ((typeof dest[item]) == "object" && src_key.indexOf(item) !== -1 && (typeof src[item]) == "object") {
            temp.push(item);
        }
    })
    temp.forEach(item => {
        ObjUtil(dest[item], src[item]);
    })
    return dest;
}
/**
 * 给对象 赋值
 * @param dest 目标对象
 * @param arr 目标对象 属性
 * @param srcArr 数据源
 * @constructor
 */
const DataUtil = (dest, arr, srcArr) => {
    if (arr instanceof Array && srcArr instanceof Array) {
        arr.forEach((item, index) => {
            Reflect.set(dest, item, srcArr[index])
        })
    } else {
        throw Error("传入数组类型")
    }
}
const getData = {
    url: String,
    params: {}
}
const Validated = (...args) => {
    if (args.length === 0)
        return true
    for (let arg of args) {
        if (!arg)
            return true;
    }
    return false;
}
const postData = {
    url: String,
    data: {}
}
export const encryptionPassword = (pwd, public_key) => {
    let encrypt = new JSEncrypt();
    encrypt.setPublicKey(public_key);
    return encrypt.encrypt(pwd);
}
export {
    ObjUtil,
    DataUtil,
    getData,
    postData,
    Validated
}
