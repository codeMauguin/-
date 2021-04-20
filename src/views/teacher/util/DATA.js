export const MenuTreeList = [
    {
        id: "1",
        menuName: "学生管理",
        route: null,
        icon: "el-icon-setting",
        childList: [
            {
                id: "1-1",
                menuName: "添加学生",
                route: "/teacher/addStu",
                icon: "el-icon-menu",
                childList: null
            },
            {
                id: "1-2",
                menuName: "学生添加课程",
                route: "/teacher/addStuClass",
                icon: "el-icon-menu",
                childList: null
            },
            {
                id: "1-3",
                menuName: "添加成绩",
                route: "/teacher/addScore",
                icon: "el-icon-menu",
                childList: null
            }
        ]
    },
    {
        id: "2",
        menuName: "我的班级",
        route: null,
        icon: "el-icon-s-home",
        childList: [
            {
                id: "2-1",
                menuName: "查看课程",
                route: "/teacher/classInfo",
                icon: "el-icon-menu",
                childList: null
            },
            {
                id: "2-2",
                menuName: "查看学生",
                route: "/teacher/stuInfo",
                icon: "el-icon-menu",
                childList: null
            },
            {
                id: "2-3",
                menuName: "添加课表",
                route: "/teacher/addClass",
                icon: "el-icon-menu",
                childList: null
            }
        ]
    }, {

        id: "3",
        menuName: "成绩管理",
        route: null,
        icon: "el-icon-edit",
        childList: [
            {
                id: "3-1",
                menuName: "修改成绩",
                route: "/teacher/ModifyScore",
                icon: "el-icon-menu",
                childList: null
            }
        ]
    }
];

export const stuMenuTreeList = [{
    id: "1",
    menuName: "成绩查询",
    route: null,
    icon: "el-icon-setting",
    childList: [
        {
            id: "1-1",
            menuName: "我的成绩",
            route: "/user/user",
            icon: "el-icon-menu",
            childList: null
        }
    ]
},]
export const AdminMenuTreeList = [{
    id: "4",
    menuName: "管理员",
    route: null,
    icon: "el-icon-user-solid",
    childList: [
        {
            id: "4.1",
            menuName: "添加老师",
            route: "/teacher/addTea",
            icon: "el-icon-menu",
            childList: null
        }
    ]
},]

