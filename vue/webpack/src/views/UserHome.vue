<template>
  <div>
    <MainSearch />
    <div v-if="route.meta.type === 'home'">
    <PopularPlace />
    <SwiperBanner /> 
  </div>
    <div class="user_home">
      <div class="album py-3 ">
        <div class="container">
         <div>
          <div v-if="route.meta.type === 'home'">
          <div class="allplace_title">
          <h2>전체 숙소</h2>
        </div>
        </div>
        </div>
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
import PopularPlace from '@/components/PopularPlace.vue';
import { useRoute } from 'vue-router';

import axios from '@/axios';
import { reactive, watch } from 'vue';

export default {
  components: {
    MainSearch,
    UserCard,
    PopularPlace,
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

    return { state, route };
  },
};
</script>

<style scoped>
/* 스타일을 여기에 추가하세요 */
.py-3 {
    padding-top: 3rem !important;
    padding-bottom: 3rem !important;
}
.allplace_title{
  padding-bottom: 30px;
  padding-top: 30px;
}
</style>
