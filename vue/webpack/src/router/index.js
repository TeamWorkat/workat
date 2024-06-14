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
import PartnerHome from "@/views/PartnerHome.vue"
import PartnerPlaceList from "@/views/PartnerPlaceList.vue"
import PartnerPlaceInsert from "@/views/PartnerPlaceInsert.vue"
import PartnerPlaceDetail from "@/views/PartnerPlaceDetail.vue"
import PartnerPlaceUpdate from "@/views/PartnerPlaceUpdate.vue"
import ReservationDetail from "@/views/ReservationDetail.vue";
import MyPage from '@/views/MyPage.vue';
import ReservationList from '@/components/myPage/ReservationList.vue';
import ReviewList from '@/components/myPage/ReviewList.vue';
import WishList from '@/components/myPage/WishList.vue'
import PartnerReserveList from "@/views/PartnerReserveList.vue"
import PartnerReserveDetail from "@/views/PartnerReserveDetail.vue"
import ReviewInsert from '@/views/ReviewInsert.vue'
import ReviewDetail from '@/views/ReviewDetail'
import ReviewUpdate from '@/views/ReviewUpdate.vue';


const routes = [
    {
        path: '/',
        name: 'UserHome',
        component: UserHome,
        meta: { type: 'home' }
    },
    {
        path: '/placeDetail/ :placeId',
        name: 'PlaceDetail',
        component: PlaceDetail
    },
    {
        path: '/admin/list',
        name: 'UserList',
        component: UserList
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
    {
        path: '/user/detail/:user_id',
        name: 'UserDetail',
        component: UserDetail
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
        path: '/signup',
        name: 'SignupForm',
        component: SignupForm
    },
    {
        path: '/login',
        name: 'LoginForm',
        component: LoginForm
    },
    {
        path: '/reservation/check',
        name: 'ReservationCheck',
        component: ReservationCheck
    },
    {
        path: '/partner/home',
        name: 'PartnerHome',
        component: PartnerHome
    },
    {
        path: '/partner/placelist',
        name: 'PartnerPlaceList',
        component: PartnerPlaceList
    },
    {
        path: '/partner/placeinsert',
        name: 'PartnerPlaceInsert',
        component: PartnerPlaceInsert
    },
    {
        path: '/partner/placedetail/:placeid',
        name: 'PartnerPlaceDetail',
        component: PartnerPlaceDetail
    },
    {
        path: '/reservation/detail/:reservation_id',
        name: 'ReservationDetail',
        component: ReservationDetail
    },
    {
        path: '/mypage',
        component: MyPage,
        children: [
          {
            path: 'resList',
            component: ReservationList
          },
          {
            path: 'reviewList',
            component: ReviewList
          },
          {
            path: 'wishList',
            component: WishList
          },
          {
            path: 'user/detail',
            component: UserDetail
        }
        ],
      },
    {
        path: '/partner/placeupdate/:placeid',
        name: 'PartnerPlaceUpdate',
        component: PartnerPlaceUpdate
        
    },
    {
        path: '/partner/reservelist',
        name: 'PartnerReserveList',
        component: PartnerReserveList
    },
    {
        path: '/partner/reserveDetail/:res_id',
        name: 'PartnerReserveDetail',
        component: PartnerReserveDetail  
    },
    {
        path: '/review/insertForm/:reservation_id',
        name: 'ReviewInsert',
        component: ReviewInsert,
        props: true
    },
    {
        path: '/review/reviewDetail/:review_id',
        name: 'ReviewDetail',
        component: ReviewDetail
    },
    {
        path: '/review/update/:review_id',
        name: 'ReviewUpdate',
        component: ReviewUpdate
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
