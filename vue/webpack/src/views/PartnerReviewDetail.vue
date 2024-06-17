<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items" class="d-flex">
        <div class="left-content">
          <p>예약번호: {{ items.res_id }}</p>
          <p>예약자명: {{ items.res_name }}</p>
          <p>입실날짜: {{ items.check_in }}</p>
          <p>퇴실날짜: {{ items.check_out }}</p>
          <p>리뷰 내용: {{ items.content }}</p>
          <p>평점: {{ items.rating }}</p>
          <p>작성일: {{ items.created_date }}</p>
          <p>수정일: {{ items.modified_date }}</p>
          <p>숙소명: {{ items.place_nm }}</p>
          <p>객실명: {{ items.room_name }}</p>
          <p>답글작성여부: {{ items.reply_exist }}</p>

          <div v-if="items.picture_sources && items.picture_sources.length > 0">
            <div
              v-for="(item, index) in items.picture_sources"
              :key="index"
              class="image-container"
            >
              <span
                class="img"
                :style="{ backgroundImage: `url(${item})` }"
              ></span>
            </div>
          </div>

          <div>
            <button @click="deletePeviewTouchUpInside">삭제</button>
          </div>
        </div>
        
        <div class="right-content">
          <div v-if="items.reply_exist === 'N'" class="comment-container">
            <p>리뷰에 댓글을 작성하세요</p>
            <textarea v-model="reply_comment"></textarea>
            <br />
            <button @click="insertReplyTouchUpInside">댓글 작성</button>
          </div>
          <div v-else class="comment-container">
            <p>작성한 댓글:</p>
            <p>{{ items.reply_content }}</p>
            <button class="delete-button" @click="deleteReplyTouchUpInside">댓글 삭제</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerRevieweDetail',
  components: {
    SideBar,
  },

  data() {
    return {
      items: null,
      reply_comment: '',
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
      axios
        .post('/api/partner/replyInsert', {
          review_id: this.reviewId,
          res_id: this.items.res_id,
          reply_content: this.reply_comment,
          status: 'active',
        })
        .then(() => {
          alert('댓글이 작성되었습니다.')
          window.location.href = 'http://localhost:8090/partner/reviewList'
        })
        .catch((err) => {
          alert('댓글 작성에 실패했습니다.')
          console.error(err)
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
          window.location.href = 'http://localhost:8090/partner/reviewList'
        })
        .catch((err) => {
          alert('실패하였습니다. ' + err)
          console.error(err)
        })
    },
    deleteReplyTouchUpInside() {
      axios
        .get('/api/partner/replyDelete', {
          params: {
            review_id: this.reviewId,
          },
        })
        .then(() => {
          alert('삭제 되었습니다.')
          window.location.href = 'http://localhost:8090/partner/reviewList'
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

.comment-container {
  position: relative; /* 상대 위치를 기준으로 절대 위치 요소를 설정할 수 있도록 설정 */
  border: 1px solid #ccc;
  padding: 10px;
  height: 50vh; /* 높이를 화면의 절반으로 설정 */
  width: 33vw; /* 너비를 화면의 3분의 1로 설정 */
  box-sizing: border-box; /* 패딩과 테두리를 포함한 전체 크기로 설정 */
}

textarea {
  width: 100%; /* textarea의 너비를 100%로 설정하여 부모 요소에 맞게 설정 */
  height: calc(100% - 30px); /* 높이를 100%로 설정하되, 버튼 높이 만큼 뺀 크기로 설정 */
  resize: vertical; /* textarea의 크기를 수직으로만 조절할 수 있도록 설정 */
  margin-bottom: 10px; /* 아래 여백 설정 */
}

.delete-button {
  position: absolute; /* 절대 위치를 설정하여 부모 요소를 기준으로 위치를 설정 */
  bottom: 10px; /* 부모 요소의 아래에서 10px 위로 위치 */
  right: 10px; /* 부모 요소의 오른쪽에서 10px 왼쪽으로 위치 */
}

.left-content {
  flex: 1; /* 왼쪽 콘텐츠를 화면의 절반으로 설정 */
  padding-right: 10px; /* 오른쪽 여백 설정 */
}

.right-content {
  flex: 1; /* 오른쪽 콘텐츠를 화면의 절반으로 설정 */
  padding-left: 10px; /* 왼쪽 여백 설정 */
}
</style>