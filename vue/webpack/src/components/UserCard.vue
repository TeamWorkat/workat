<template>
  <div class="card shadow-sm" @click="goDetailPage(item.place_id)">
    <span class="img">
      <PictureSlide :pictureList="item.picture_sources" @click.stop />
    </span>
    <div class="card-body">
      <p class="card-text">{{ item.place_nm }}</p>
      <div class="d-flex justify-content-between align-items-center">
        <small class="text-muted">설명</small>
        <button @click.stop="toggleFavorite" class="btn btn-sm btn-outline-secondary">
          <span class="heart" :class="{ 'is-favorite': isFavorite }"></span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import PictureSlide from './myPage/PictureSlide.vue';

export default {
  name: "UserCard",
  props: {
    item: Object
  },
  components: {
    PictureSlide
  },
  data() {
    return {
      isFavorite: this.item.liked || false
    };
  },
  methods: {
    goDetailPage(placeId) {
      this.$router.push({ name: 'PlaceDetail', params: { placeId } });
    },
    async toggleFavorite() {
      this.isFavorite = !this.isFavorite;
      const newFavoriteStatus = this.isFavorite;
      const statusToSend = newFavoriteStatus ? 'Y' : 'N';
      console.log(this.item.place_id);
      console.log(statusToSend);
      try {
        const response = await axios.post('/api/wish/update', {
          place_id: this.item.place_id,
          liked: statusToSend
        });
        console.log('좋아요 상태 업데이트:', response.data);
        this.isFavorite = newFavoriteStatus;
      } catch (error) {
        console.error('좋아요 상태 업데이트 실패:', error);
      }
    }
  }
};
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px; /* 원하는 높이로 설정 */
  overflow: hidden; /* 이미지가 컨테이너를 벗어나지 않도록 설정 */
}

.card .card-body .price {
  text-decoration: line-through;
}

/* 하트 모양 */
.heart {
  display: inline-block;
  width: 20px;
  height: 20px;
  background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="%23ccc"><path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/></svg>');
  background-size: contain;
  background-repeat: no-repeat;
  cursor: pointer;
}

/* 찜된 상태의 하트 색상 변경 */
.heart.is-favorite {
  filter: invert(54%) sepia(88%) saturate(3677%) hue-rotate(338deg) brightness(94%) contrast(88%);
}
</style>
