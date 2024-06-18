<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3 place-details-container">
      <div v-if="items">
        <div class="details-header">
          <h2>{{ items.place_nm }}</h2>
          <div class="button-group">
            <button @click="updatePlaceTouchUpInside" class="btn btn-primary">수정</button>
            <button @click="deletePlaceTouchUpInside" class="btn btn-danger">삭제</button>
          </div>
        </div>
        
        <p><strong>전화번호:</strong> {{ items.place_tel }}</p>
        <p><strong>카테고리:</strong> {{ items.category }}</p>
        <p><strong>주소:</strong> {{ items.place_addr }}</p>
        <p><strong>소개글:</strong> {{ items.place_content }}</p>
        <p><strong>체크인:</strong> {{ items.place_in }}</p>
        <p><strong>체크아웃:</strong> {{ items.place_out }}</p>
        <p><strong>지역:</strong> {{ items.loc_nm }}</p>
        
        <p><strong>사진:</strong></p>
        <div class="image-gallery">
          <div v-for="(item, index) in items.picture_sources" :key="index" class="image-container">
            <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerPlaceDetail',
  components: {
    SideBar,
  },

  data() {
    return {
      items: null,
    }
  },

  computed: {
    placeId() {
      return this.$route.params.placeid
    },
  },

  created() {
    this.fetchPlaceInfo(this.placeId)
  },

  methods: {
    fetchPlaceInfo(placeId) {
      axios
        .get('/api/partner/placeDetail', {
          params: {
            placeid: placeId,
          },
        })
        .then((res) => {
          this.items = res.data
          console.log(res.data)
        })
        .catch((err) => {
          console.error(err)
        })
    },
    updatePlaceTouchUpInside(){
      this.$router.push({ name: 'PartnerPlaceUpdate', params: { placeid: this.placeId } })
    },
    deletePlaceTouchUpInside(){
      axios
        .get('/api/partner/placeDelete', {
          params: {
            placeid: this.placeId,
          },
        })
        .then(() => {
          alert('삭제 되었습니다.');
          window.location.href = 'http://localhost:8090/partner/placelist';
        })
        .catch((err) => {
          alert('실패하였습니다. ' + err);
          console.error(err);
        });
    },
  },
}
</script>

<style scoped>
.place-details-container {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.details-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.details-header h2 {
  margin: 0;
  font-size: 1.5rem;
}

.button-group {
  display: flex;
  gap: 10px;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.img {
  display: inline-block;
  width: 150px;  /* 또는 적절한 값으로 변경 */
  height: 150px; /* 또는 적절한 값으로 변경 */
  background-size: cover;
  background-position: center;
  border-radius: 10%;
}

.image-container {
  position: relative;
  display: inline-block;
  margin: 10px;
  border: 2px solid #dee2e6;
  border-radius: 10%;
  overflow: hidden;
  transition: transform 0.3s ease;
}

.image-container:hover {
  transform: scale(1.05);
}
</style>
