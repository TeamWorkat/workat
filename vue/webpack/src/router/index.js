import { createRouter, createWebHistory } from 'vue-router';
import UserHome from '@/views/UserHome.vue';
import UserList from '@/views/UserList.vue';
import PlaceDetail from '@/views/PlaceDetail.vue';
import UserDetail from "@/views/UserDetail.vue";
import UserDelete from "@/views/UserDelete.vue";
import UserUpdate from "@/views/UserUpdate.vue";
import SignupForm from "@/views/SignupForm.vue";
import ReservationCheck from "@/views/ReservationCheck.vue";
import LoginForm from "@/views/LoginForm.vue";

const routes = [
    {
        path: '/',
        name: 'UserHome',
        component: UserHome
    },
    {
        path: '/placeDetail/:placeId',
        name: 'PlaceDetail',
        component: PlaceDetail
    },
    {
        path: '/admin/',
        name: 'UserList',
        component: UserList
    },
    {
        path: '/user/detail',
        name: 'UserDetail',
        component: UserDetail,
        props: route => ({ user_id: route.query.user_id })
    },
    {
        path: '/user/delete',
        name: 'UserDelete',
        component: UserDelete
    },
    {
        path: '/user/update',
        name: 'UserUpdate',
        component: UserUpdate
    },
    {
        path: '/user/signup',
        name: 'SignupForm',
        component: SignupForm
    },
    {
        path: '/reservation/check',
        name: 'ReservationCheck',
        component: ReservationCheck,
    },{
        path: '/user/login',
        name: 'LoginForm',
        component: LoginForm
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
