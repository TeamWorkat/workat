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
            <button class="delete-button" @click="deletePeviewTouchUpInside">삭제</button>
          </div>
        </div>
        
        <div class="right-content">
          <div v-if="items.reply_exist === 'N'" class="comment-container">
            <p>리뷰에 댓글을 작성하세요</p>
            <textarea v-model="reply_comment"></textarea>
            <div class="button-container">
              <button class="submit-button" @click="insertReplyTouchUpInside">댓글 작성</button>
            </div>
          </div>
          <div v-else class="comment-container">
            <p>작성한 댓글:</p>
            <p>{{ items.reply_content }}</p>
            <div class="button-container">
              <button class="delete-button" @click="deleteReplyTouchUpInside">댓글 삭제</button>
            </div>
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
  width: 150px; 
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 10px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

.image-container {
  position: relative;
  display: inline-block;
  margin: 10px;
}

.comment-container {
  position: relative;
  border: 1px solid #ccc;
  padding: 15px;
  height: 50vh;
  width: 33vw;
  box-sizing: border-box;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
}

textarea {
  flex-grow: 1;
  resize: vertical;
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
}

.button-container {
  display: flex;
  justify-content: flex-end;
}

.delete-button, .submit-button {
  background-color: #ff4d4d;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.delete-button:hover, .submit-button:hover {
  background-color: #e43d3d;
}

.left-content {
  flex: 1;
  padding-right: 10px;
  font-family: Arial, sans-serif;
  color: #333;
}

.right-content {
  flex: 1;
  padding-left: 10px;
  font-family: Arial, sans-serif;
  color: #333;
}

p {
  margin: 5px 0;
}
</style>
