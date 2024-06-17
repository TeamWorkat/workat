<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items" class="place-detail">
        <h3>{{ items.room_name }}</h3>
        <div class="detail-section">
          <p><strong>총 객실수:</strong> {{ items.room_qnt }} 개</p>
          <p><strong>1박당 요금:</strong> {{ formatCurrency(items.room_price) }}</p>
          <p><strong>객실 설명:</strong> {{ items.room_content }}</p>
          <p><strong>최대 인원:</strong> {{ items.max_people }} 명</p>
          <p><strong>최소 인원:</strong> {{ items.min_people }} 명</p>
          <p><strong>1인당 추가요금:</strong> {{ formatCurrency(items.add_price) }}</p>
          <p><strong>노출여부:</strong> {{ items.status }}</p>
        </div>

        <div class="image-section">
          <p><strong>사진:</strong></p>
          <div v-for="(item, index) in items.picture_sources" :key="index" class="image-container">
            <div class="img" :style="{ backgroundImage: 'url(' + item + ')' }"></div>
          </div>
        </div>

        <div class="button-section">
          <button class="btn btn-primary" @click="updatePlaceTouchUpInside">수정</button>
          <button class="btn btn-danger" @click="deletePlaceTouchUpInside">삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'
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
    roomId() {
      return this.$route.params.room_id
    },
  },

  created() {
    this.fetchPlaceInfo(this.roomId)
  },

  methods: {
    fetchPlaceInfo(roomId) {
      axios
        .get('/api/partner/roomDetail', {
          params: {
            room_id: roomId,
          },
        })
        .then((res) => {
          this.items = res.data
        })
        .catch((err) => {
          console.error(err)
        })
    },
    updatePlaceTouchUpInside() {
      this.$router.push({
        name: 'PartnerRoomUpdate',
        params: { room_id: this.roomId },
      })
    },
    deletePlaceTouchUpInside() {
      axios
        .get('/api/partner/roomDelete', {
          params: {
            room_id: this.roomId,
          },
        })
        .then(() => {
          alert('삭제 되었습니다.')
          window.location.href = 'http://localhost:8090/partner/roomList'
        })
        .catch((err) => {
          alert('실패하였습니다. ' + err)
          console.error(err)
        })
    },
    formatCurrency(value) {
      return parseFloat(value).toLocaleString('ko-KR', { style: 'currency', currency: 'KRW' }).replace('₩', '') + '원';
    },
  },
}
</script>

<style scoped>
.place-detail {
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.detail-section {
  margin-bottom: 20px;
}

.detail-section p {
  margin: 5px 0;
}

.image-section {
  margin-bottom: 20px;
}

.image-container {
  display: inline-block;
  margin-right: 10px;
}

.img {
  width: 150px;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 10px;
}

.button-section {
  margin-top: 20px;
}

.button-section button {
  margin-right: 10px;
}
</style>
