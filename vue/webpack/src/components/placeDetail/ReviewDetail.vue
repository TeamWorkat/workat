<template>
    <div class="review-list">
      <div v-for="review in reviewList" :key="review.reviewVO.review_id" class="review-item">
        <p class="review-content"><strong>리뷰 내용:</strong> {{ review.reviewVO.content }}</p>
        <p class="review-rating"><strong>평점:</strong> {{ review.reviewVO.rating }}</p>
        <p class="review-date"><strong>작성일:</strong> {{ review.reviewVO.created_date }}</p>
        <p class="review-user"><strong>작성자:</strong> User ID {{ review.reviewVO.user_id }}</p>
        <!-- 리뷰 사진이 있다면 표시 -->
        <div v-if="review.review_picture_source.length > 0" class="review-images-container">
          <p><strong>리뷰 사진:</strong></p>
          <div class="review-images">
            <img v-for="(source, index) in review.review_picture_source" :key="index" :src="source" alt="Review Image">
          </div>
        </div>
        <!-- 답글이 있다면 표시 -->
        <div v-if="review.replyVO" class="reply-item">
          <p class="reply-content"><strong>댓글:</strong> {{ review.replyVO.reply_content }}</p>
          <p class="reply-date"><strong>작성일:</strong> {{ review.replyVO.created_date }}</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      reviewList: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style>
  .review-list {
    margin-top: 20px;
    background-color: #f5f5f5; /* 회색 배경 */
    padding: 20px; /* 간격 추가 */
    border-radius: 10px; /* 모서리 둥글게 */
  }
  
  .review-item {
    margin-bottom: 20px;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: white; /* 백그라운드 흰색으로 변경 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  }
  
  .review-item p {
    margin: 5px 0;
  }
  
  .review-images-container {
    margin-top: 10px;
  }
  
  .review-images img {
    max-width: 100px;
    margin-right: 10px;
    margin-bottom: 10px;
  }
  
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
  </style>
  