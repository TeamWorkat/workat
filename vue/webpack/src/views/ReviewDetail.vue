<template>
  <div>
    <h4 class="reservation-title">여행 후기</h4>
  </div>
  <hr class="long-divider">
  <div class="reservation-container">
    <div class="room-picture-container">
      <div v-if="reviewPictureList.length > 0" class="room-info">
        <PictureSlide :pictureList="reviewPictureList" />
      </div>
    </div>

    <div id="app" class="col-md-5 review" style="background-color: #f2f2f2; padding: 20px; border-radius: 5px;">
  <div class="form-group review-group">
    <div class="form-group">
      <h4>{{ placeName }}</h4>
    </div>
    <div class="form-group">
      <label for="check-in">방문일:</label>
      <h6>{{ formatDate(check_in) }} ~ {{ formatDate(check_out) }}</h6>
    </div>
    <label for="rating">별점:</label>
    <div class="rating">
      <span v-for="index in 5" :key="index">
        <img v-if="index <= reviewVO.rating" src="/img/별점.png" alt="YellowStar" style="width: 50px; height: 50px;">
        <img v-else src="/img/빈별점.png" alt="GrayStar" style="width: 50px; height: 50px;">
      </span>
    </div>
  </div>  
  <div class="form-group review-group" style="margin-top: 20px;">
    <label for="review">여행후기:</label>
    <textarea id="review" :value="reviewVO.content" rows="4" readonly style="width: 100%; border: 2; border-radius: 5px; padding: 10px;" placeholder="고객님의 소중한 여행리뷰를 입력해주세요~"></textarea>
  </div>
  <div class="reservation-form">  
      <button @click="updateReview">수정</button>
      <button>삭제</button>
      <button>목록</button>
    </div>
  
</div>
  </div>
  <div v-if="Object.keys(replyVO).length !== 0">
    <div><strong>Review ID:</strong> {{ replyVO.review_id }}</div>
    <div><strong>Reservation ID:</strong> {{ replyVO.res_id }}</div>
    <div><strong>Reply Content:</strong> {{ replyVO.reply_content }}</div>
    <div><strong>Created Date:</strong> {{ formatDate(replyVO.created_date) }}</div>
    <div><strong>Modified Date:</strong> {{ formatDate(replyVO.modified_date) }}</div>
    <div><strong>Status:</strong> {{ replyVO.status }}</div>
  </div>
  <div v-else>
    <p>No reply data available.</p>
  </div>
  
  </template>
    
    <script setup>
    import { ref, computed, watch, onMounted } from 'vue';
    import { useRoute } from 'vue-router';
    import axios from '@/axios';
    import router from "@/router/index.js"
    import PictureSlide from '@/components/myPage/PictureSlide.vue';
    
    const items = ref(null);
    const route = useRoute();
    const reviewId = computed(() => route.params.review_id);
    const reviewPictureList = computed(()=>items.value?.review_picture_source || {} );
    const reviewVO = computed(() => items.value?.reviewVO || {});
    const replyVO = computed(() => items.value?.replyVO || {});
    const placeName = computed(()=>items.value?.place_nm || null);
    const check_in = computed(()=>items.value?.check_in || null);
    const check_out = computed(()=>items.value?.check_out || null);



    const showReasonInput = ref(false);
    const cancellationReason = ref('');
  
    const fetchReviewDetails = async (reviewId) => {
      await axios.get(`/api/review/detail?review_id=${reviewId}`)
        .then(res => {
          items.value = res.data;
        })
        .catch(error => {
          console.error("There was an error fetching the review details:", error);
        });
    };

  const formatDate = (dateString) => {
  const date = new Date(dateString);
  
  // 원하는 포맷 형식으로 변환
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const formattedDate = `${year}-${month}-${day}`;
  
  return formattedDate;
};
    
  
    const updateReview = () => {
    const reviewId = computed(() => reviewVO.value?.review_id || null);
    router.push({ name: 'ReviewUpdate', params: { review_id: reviewId.value } });
  };
    
    onMounted(() => {
      fetchReviewDetails(reviewId.value);
    });
    
    watch(reviewId, (newReviewId) => {
      if (newReviewId) {
        fetchReviewDetails(newReviewId);
      }
    });
  
    watch(showReasonInput, (newVal) => {
    if (!newVal) {
      cancellationReason.value = '';
    }
  });
    </script>
    
  
  <style scoped>
  .reservation-container {
  display: flex;
  justify-content: space-between;
  }
  
  .room-picture-container {
    width: 40%;
    display: flex;
    flex-direction: column;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    margin-left: 30px; /* 왼쪽 여백 설정 */
  }
  
  .room-info {
    display: flex;
    flex-direction: column;
    align-items: center; /* 수평 가운데 정렬 */
  }
  
  .room-name {
    margin-top: 10px; /* 위쪽 여백 추가 */
  }
  
  .image-wrapper {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  }
  
  .room-image {
  width: 50%;
  height: 50%;
  }
  
  .reservation-form {
  width: 55%;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  margin-right: 30px;
  }
  
  .form-group {
  margin-bottom: 10px;
  }
  
  label {
  font-weight: bold;
  }
  
  input[type="number"], input[type="text"], select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
  }
  
  input[type="number"], input[type="text"], select, textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
  }
  
  .request-group {
  background-color: #f7f7f7;
  padding: 10px;
  }
  
  p {
  margin: 0;
  }
  
  .cancel-button {
  background-color: #c0c0bb;
  color: black;
  }
  
  .confirm-button {
  background-color: #fae41d;
  color: black;
  }
  
  .modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  }
  
  .modal-content {
  background: white;
  width: 50%; /* Adjust width to be half of the viewport width */
  max-width: 500px; /* Ensure it doesn't get too wide on larger screens */
  padding: 20px;
  border-radius: 10px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  animation: fadeIn 0.3s ease;
  }
  .modal-buttons {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  }
  
  @keyframes fadeIn {
  from {
    opacity: 0;
    transform: scale(0.9);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
  }
  
  .long-divider {
    margin-left: 20px;
    border: 0; /* 기본 테두리 제거 */
    height: 2px; /* 구분선의 두께 */
    background-color: #615e5e; /* 구분선 색상 */
    margin: 20px 0; /* 위아래 여백 */
  }
  
  .reservation-title {
    margin-left: 50px; /* 오른쪽 여백 설정 */
  }
  </style>
  