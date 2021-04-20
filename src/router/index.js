import Vue from "vue";
import VueRouter from "vue-router";
import login from "@/views/login/login";
import { Message } from "element-ui";
import store from "@/store";

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push ( location )
{
    return originalPush.call( this, location ).catch( err => err );
};
const queryStu = () =>
{
    return import( "@/views/teacher/components/queryStu/queryStu" );
};
const AddStu = () =>
{
    return import( "@/views/teacher/components/addStu/AddStu" );
};
const addStuClass = () =>
{
    return import( "@/views/teacher/components/addStuClass/addStuClass" );
};
const addScore = () =>
{
    return import( "@/views/teacher/components/addScore/addScore" );
};
const addClass = () =>
{
    return import( "@/views/teacher/components/addClass/addClass" );
};
const addTea = () =>
{
    return import( "@/views/admin/addTea" );
};
const classInfo = () =>
{
    return import( "@/views/teacher/components/classInfo/classInfo" );
};
const ModifyScore = () =>
{
    return import( '@/views/teacher/comment1/ModifyScore' );
};
const welcome = () =>
{
    return import( '@/views/teacher/comment1/welcome' );
};
const user = () =>
{
    return import( '@/views/user/user' );
};
Vue.use( VueRouter );

const routes = [
    {
        path: "/",
        name: "login",
        component: login
    },
    {
        path: "/user",
        name: "userView",
        component: () =>
            import(/* webpackChunkName: "about" */ "@/views/user/userView" ),
        meta: {
            role: [ "STU_ROLE" ]
        }, children: [ {
            path: "user",
            name: "user",
            component: user
        } ]
    },
    {
        path: "/teacher",
        redirect: '/teacher/welcome',
        name: "teacher",
        component: () => import( "../views/teacher/teacher" ),
        meta: {
            role: [ "ADMIN_ROLE", "TEA_ROLE" ]
        },
        children: [
            {
                path: 'welcome',
                name: 'welcome',
                component: welcome
            },
            {
                path: "stuInfo",
                name: "queryStu",
                component: queryStu
            },
            {
                path: "addStu",
                name: "AddStu",
                component: AddStu
            },
            {
                path: "addStuClass",
                name: "addStuClass",
                component: addStuClass
            },
            {
                path: "addScore",
                name: "addScore",
                component: addScore
            },
            {
                path: "addClass",
                name: "addClass",
                component: addClass
            },
            {
                path: "addTea",
                name: "addTea",
                component: addTea
            },
            {
                path: "classInfo",
                name: "classInfo",
                component: classInfo
            }, {
                path: 'ModifyScore',
                name: 'ModifyScore',
                component: ModifyScore
            }
        ]
    },
    {
        path: "/amending",
        name: "amending",
        component: () => import( "@/views/count/amending" )
    }
];

const router = new VueRouter( {
    mode: "hash",
    base: process.env.BASE_URL,
    routes
} );
router.beforeEach( ( to, from, next ) =>
{
    if ( to.matched.length === 0 || to.name === null )
    {
        Message.warning( "路经出错" );
        next( "/" );
    } else if ( Reflect.ownKeys( to.matched[ 0 ].meta ).length !== 0 )
    {
        let isHasRole = false;
        for ( const item of to.matched[ 0 ].meta.role )
        {
            if ( item === store.getters.getRole )
            {
                isHasRole = true;
                next();
            }
        }
        if ( isHasRole === false )
        {
            Message.info( "没有权限进入" );
            next( "/" );
        }
    } else
    {
        next();
    }
    if ( to.path === '/' )
    {
        store.commit( 'removeActive' );
    }
} );
export default router;
