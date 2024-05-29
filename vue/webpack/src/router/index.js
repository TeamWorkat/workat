import { createRouter, createWebHistory } from 'vue-router';
import UserHome from '@/views/UserHome.vue';
import UserList from '@/views/UserList.vue';
import PlaceDetail from '@/views/PlaceDetail.vue';
const routes = [
    {
        path: '/',
        name: 'UserHome',
        component: UserHome
    },
    {
        path: '/admin/list',
        name: 'UserList',
        component: UserList
    },
    {
        path: '/placeDetail',
        name: 'PlaceDetail',
        component: PlaceDetail
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
