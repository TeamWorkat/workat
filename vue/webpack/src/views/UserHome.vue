<template>
  <div>
    <MainSearch />
    <SwiperBanner /> <!-- 배너 추가 -->
    <div class="user_home">
      <div class="album py-5 bg-body-tertiary">
        <div class="container">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col" v-for="(item, idx) in state.items" :key="idx">
              <UserCard :item="item" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainSearch from '@/components/MainSearch.vue';
import UserCard from '@/components/UserCard.vue';
import SwiperBanner from '@/components/SwiperBanner.vue'; // SwiperBanner 컴포넌트 추가
import { useRoute } from 'vue-router';

import axios from '@/axios';
import { reactive, watch } from 'vue';

export default {
  components: {
    MainSearch,
    UserCard,
    SwiperBanner, // SwiperBanner 컴포넌트 추가
  },

  setup() {
    const state = reactive({
      items: [],
    });
    const route = useRoute();

    function getCategoryPlace(category) {
      axios
        .get('/api/place/category', {
          params: {
            category: category,
          },
        })
        .then((res) => {
          state.items = res.data;
          console.log(res.data);
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function updateItemsBasedOnRoute() {
      if (route.meta.type === 'home') {
        axios.get('/api/place/items').then((res) => {
          state.items = res.data;
          console.log(res);
        });
      } else if (route.meta.type === 'hotel') {
        getCategoryPlace('hotel');
      } else if (route.meta.type === 'stay') {
        getCategoryPlace('stay');
      } else if (route.meta.type === 'camping') {
        getCategoryPlace('camping');
      } else if (route.meta.type === 'search') {
        try {
          state.items = JSON.parse(decodeURIComponent(route.query.data));
        } catch (e) {
          console.error('Failed to parse query data:', e);
        }
      }
    }

    // 초기 로드 시 아이템 업데이트
    updateItemsBasedOnRoute();

    // route가 변경될 때마다 업데이트
    watch(route, () => {
      updateItemsBasedOnRoute();
    });

    return { state };
  },
};
</script>

<style scoped>
/* 스타일을 여기에 추가하세요 */
</style>
