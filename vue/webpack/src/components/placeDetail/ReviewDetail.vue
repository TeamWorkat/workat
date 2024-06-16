<template>
      <div v-for="review in reviewList" :key="review.reviewVO.review_id" class="review-item">
        <div class="card-horizontal">
    <div class="card-border">
      <div class="card-body">
        <div class="content-wrapper">
          <div class="content-left">
            <p class="card-text"><strong>{{ review.reviewVO.user_id }}</strong></p>
            <p class="card-text">내용: {{review.reviewVO.content }}</p>
            <p class="card-text">별점: {{  review.reviewVO.rating }}</p>
            <p class="card-text">작성일: {{ review.reviewVO.created_date }}</p>
          </div>
          <div class="content-right">
            <div v-if="review.review_picture_source.length > 0" class="review-images">
            <PictureSlide :pictureList="review.review_picture_source" class="review-picture"/>
        </div>
          </div>
        </div>
      </div>
    </div>
  </div>
        <!-- 답글이 있다면 표시 -->
        <div v-if="review.replyVO" class="reply-item">
          <p class="reply-content"><strong>댓글:</strong> {{ review.replyVO.reply_content }}</p>
          <p class="reply-date"><strong>작성일:</strong> {{ review.replyVO.created_date }}</p>
        </div>
      </div>
  </template>
  
  <script>
  import PictureSlide from '../myPage/PictureSlide.vue';
  export default {
    props: {
      reviewList: {
        type: Array,
        required: true
      }
    },
    components:{
      PictureSlide
    }
  };
  </script>
  
  <style scoped>

  .reply-item {
    margin-top: 15px;
    padding: 10px;
    border-left: 4px solid #007bff; /* 왼쪽에 파란색 경계선 추가 */
    background-color: #f1f1f1; /* 연한 회색 배경 */
    border-radius: 5px; /* 모서리 둥글게 */
  }
  
  .reply-item p {
    margin: 5px 0;
  }
  
  .reply-content {
    font-style: italic; /* 이탤릭체로 변경 */
  }
  
  .reply-date,
  .reply-status {
    font-size: 0.9em; /* 작은 폰트 사이즈 */
    color: #555; /* 어두운 회색 텍스트 */
  }

  .card-horizontal {
  display: flex;
  width: 100%;
  flex-grow: 1;
}

.card-border {
  border: 1px solid #ccc;
  width: 100%; /* 추가 */
  border-radius: 15px; 
  margin-right: 10px; 
  flex: 1; 
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); 
}

.card-body {
  display: flex;
  padding: 15px;
  white-space: nowrap;
}

.content-wrapper {
  display: flex;
  flex: 1; 
}

.content-left {
  flex: 1;
  margin-top: 20px;
  margin-left: 50px;
}

.content-right {
  max-width: 20%; 
  max-height: 100%;

  display: flex;
  align-items: center;
}

.review-picture {
  max-width: 100%; 
  max-height: 100%;
  border-radius: 10px; 
  object-fit: cover;
}
  </style>
  