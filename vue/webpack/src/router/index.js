import { createRouter, createWebHistory } from 'vue-router';
import UserHome from '@/views/UserHome.vue';
import UserList from '@/views/UserList.vue';
import PlaceDetail from '@/views/PlaceDetail.vue';
import UserDetail from "@/views/UserDetail.vue";
import UserDelete from "@/views/UserDelete.vue";
import UserUpdate from "@/views/UserUpdate.vue";
import SignupPage from "@/views/SignupPage.vue";
import ReservationCheck from "@/views/ReservationCheck.vue";
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
import PartnerRoomList from "@/views/PartnerRoomList.vue"
import PartnerReserveDetail from "@/views/PartnerReserveDetail.vue"
import PartnerRoomDetail from '@/views/PartnerRoomDetail.vue'
import PartnerRoomUpdate from '@/views/PartnerRoomUpdate.vue'
import PartnerRoomInsert from '@/views/PartnerRoomInsert.vue'
import PartnerReviewList from "@/views/PartnerReviewList.vue"
import PartnerReviewDetail from "@/views/PartnerReviewDetail.vue"
import ReviewInsert from '@/views/ReviewInsert.vue'
import ReviewDetail from '@/views/ReviewDetail'
import ReviewUpdate from '@/views/ReviewUpdate.vue';
import LoginPage from "@/views/LoginPage.vue";
import UserLogin from "@/components/UserLogin.vue";
import PartnerLogin from "@/components/PartnerLogin.vue";


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
        path: '/user/detail',
        name: 'UserDetail',
        component: UserDetail
    },
    {
        path: '/user/delete/:user_id',
        name: 'UserDelete',
        component: UserDelete
    },
    {
        path: '/user/update/:user_id',
        name: 'UserUpdate',
        component: UserUpdate
    },
    {
        path: '/signup',
        name: 'SignupPage',
        component: SignupPage
    },
    {
        path: '/login',
        name: 'LoginPage',
        component: LoginPage
    },
    {
        path: '/login/user',
        name: 'UserLogin',
        component: UserLogin
    },
    {
        path: '/login/partner',
        name: 'PartnerLogin',
        component: PartnerLogin
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
        },
        {
            path: 'user/update',
            component: UserUpdate
        },
        {
            path: 'user/delete',
            component: UserDelete
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
        path: '/partner/roomList',
        name: 'PartnerRoomList',
        component: PartnerRoomList
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
    },
    {
        path: '/partner/roomDetail/:room_id',
        name: 'PartnerRoomDetail',
        component: PartnerRoomDetail
    },
    {
        path: '/partner/roomUpdate/:room_id',
        name: 'PartnerRoomUpdate',
        component: PartnerRoomUpdate
    },
    {
        path: '/partner/roomInsert',
        name: 'PartnerRoomInsert',
        component: PartnerRoomInsert
    },

    {
        path: '/partner/reviewList',
        name: 'PartnerReviewList',
        component: PartnerReviewList
    },
    {
        path: '/partner/reviewDetail/:review_id',
        name: 'PartnerReviewDetail',
        component: PartnerReviewDetail
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
