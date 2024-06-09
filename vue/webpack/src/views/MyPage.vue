<template>
  <div class="d-flex">
    <MyPageSideBar v-if="showSidebar" />
    <router-view />
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import MyPageSideBar from '@/components/myPage/MyPageSideBar.vue';

const route = useRoute();
const routesWithSidebar = ['/mypage/resList','/mypage/reviewList','/mypage/wishList']; // 사이드바를 표시할 경로 목록

const showSidebar = computed(() => routesWithSidebar.includes(route.path));
</script>