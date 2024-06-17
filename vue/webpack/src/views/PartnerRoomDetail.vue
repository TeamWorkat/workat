<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
        <p>객실명: {{ items.room_name }}</p>
        <p>총 객실수 : {{ items.room_qnt }} 개</p> 
        <p>1박당 요금: {{ parseFloat(items.room_price).toLocaleString('ko-KR', { style: 'currency', currency: 'KRW' }).replace('₩', '') + '원'}}</p>
        <p>객실 설명: {{ items.room_content }}</p>
        <p>최대 인원: {{ items.max_people + '명' }}</p>
        <p>최소 인원: {{ items.min_people + '명'}}</p>
        <p>1인당 추가요금: {{ parseFloat(items.add_price).toLocaleString('ko-KR', { style: 'currency', currency: 'KRW' }).replace('₩', '') + '원'}}</p>
        <p>노출여부: {{ items.status }}</p>


        <p>사진:</p>
        <div
          v-for="(item, index) in items.picture_sources"
          :key="index"
          class="image-container"
        >
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
      // pictureSources: []
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
          console.log(res.data)
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
  },
}
</script>

<style scoped>
.img {
  display: inline-block;
  width: 150px; /* 또는 적절한 값으로 변경 */
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
