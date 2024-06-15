<template>
  <div class="border d-flex">
    <MyPageSideBar class="border" v-if="showSidebar" />
    <router-view class="border flex-fill" />
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import MyPageSideBar from '@/components/myPage/MyPageSideBar.vue';

const route = useRoute();
const routesWithSidebar = ['/mypage','/mypage/resList','/mypage/reviewList', '/mypage/wishList','/mypage/user/detail','/mypage/user/update','/mypage/user/delete']; // 사이드바를 표시할 경로 목록

const showSidebar = computed(() => routesWithSidebar.includes(route.path));


</script>
<style scoped>
.image-links {
  display: flex; /* Flexbox 사용 */
  justify-content: space-between; /* 각 요소 사이에 공간을 동일하게 배분하여 정렬 */
}

/* 이미지 크기 조절 */
.image-links img {
  width: auto; /* 이미지의 너비를 조절합니다. */
  height: auto; /* 높이를 자동으로 조절하여 가로세로 비율을 유지합니다. */
}
</style>