<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
        <p>예약번호: {{ items.res_id }}</p>
        <p>예약자명 : {{ items.res_name }} 개</p> 
        <p>입실날짜:  {{ items.check_in }}</p>
        <p>퇴실날짜: {{ items.check_out }}</p>
        <p>리뷰 내용: {{ items.room_content }}</p>
        <p>숙소명: {{ items.place_nm}}</p>
        <p>객실명: {{ items.room_name }}</p>
        <p>답글작성여부: {{ items.reply_exist }} </p>
        


        <p>사진:</p>
        <div
          v-for="(item, index) in items.picture_sources"
          :key="index"
          class="image-container"
        >
          <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
        </div>

        <div>
          <button @click="deletePeviewTouchUpInside">댓글달기</button>
          <button @click="deletePeviewTouchUpInside">삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerPevieweDetail',
  components: {
    SideBar,
  },

  data() {
    return {
      items: null,
      
    }
  },

  computed: {
    reviewId() {
      return this.$route.params.review_id
    },
  },

  created() {
    this.fetchPlaceInfo(this.reviewId)
  },

  methods: {
    fetchPlaceInfo(reviewId) {
      axios
        .get('/api/partner/reviewDetail', {
          params: {
            review_id: reviewId,
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
    insertReplyTouchUpInside() {
      this.$router.push({
        name: 'PartnerReviewInsert',
        params: { review_id: this.reviewId },
      })
    },
    deletePeviewTouchUpInside() {
      axios
        .get('/api/partner/reviewDelete', {
          params: {
            review_id: this.reviewId,
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
