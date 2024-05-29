import { createRouter, createWebHistory } from 'vue-router';
import UserHome from '@/views/UserHome.vue';
import UserList from '@/views/UserList.vue';
import PlaceDetail from '@/views/PlaceDetail.vue';
import UserDetail from "@/views/UserDetail.vue";
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
    {        path: '/user/detail',
        name: 'UserDetail',
        component: UserDetail,
        props: route => ({ user_id: route.query.user_id })
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
