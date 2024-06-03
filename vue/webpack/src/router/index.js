import { createRouter, createWebHistory } from 'vue-router';
import UserHome from '@/views/UserHome.vue';
import UserList from '@/views/UserList.vue';
import PlaceDetail from '@/views/PlaceDetail.vue';
const routes = [
    {
        path: '/',
        name: 'UserHome',
        component: UserHome,
        meta: { type: 'home' }
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
    },
    {
        path: '/hotel',
        name: 'UserHotel',
        component: UserHome,
        meta: { type: 'hotel' }
    },
    {
        path: '/stay',
        name: 'UserStay',
        component: UserHome,
        meta: { type: 'stay' }
    },
    {
        path: '/camping',
        name: 'UserCamping',
        component: UserHome,
        meta: { type: 'camping' }
    },
    {
        path: '/search',
        name: 'UserSearch',
        component: UserHome,
        meta: { type: 'search' }
    },
    
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
