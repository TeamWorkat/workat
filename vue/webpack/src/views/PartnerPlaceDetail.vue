<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
        <p>숙소명: {{ items.place_nm }}</p>
        <p>전화번호: {{ items.place_tel }}</p>
        <p>카테고리: {{ items.category }}</p>
        <p>주소: {{ items.place_addr }}</p>
        <p>소개글: {{ items.place_content }}</p>
        <p>체크인: {{ items.place_in }}</p>
        <p>체크아웃: {{ items.place_out }}</p>
        <p>지역: {{ items.loc_nm }}</p>
        
        <p>사진: </p>
        <div v-for="(item, index) in items.picture_sources" :key="index" class="image-container">
          <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
        </div>

        <div>
          <button @click="updatePlaceTouchUpInside">수정</button>
          <button @click="deletePlaceTouchUpInside">삭제</button>
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
      // pictureSources: []
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
.img {
  display: inline-block;
  width: 150px;  /* 또는 적절한 값으로 변경 */
  height: 150px; /* 또는 적절한 값으로 변경 */
  background-size: cover;
  background-position: center;
  border-radius: 20%;
}


.image-container {
  position: relative;
  display: inline-block;
  margin: 10px;
}

</style>
