<template>
<div>
  <h4 class="reservation-title">예약 상세내역</h4>
</div>
<hr class="long-divider">
<div class="reservation-container">
  <div class="room-picture-container">
    <div v-if="roomPictureList.length > 0" class="room-info">
      <PictureSlide :pictureList="roomPictureList" />
      <h2 class="room-name">{{ roomVO.room_name }}</h2>
    </div>
  </div>

  <div class="reservation-form">
    <div class="form-group">
      <label for="check-in">체크인/체크아웃</label>
      <h6>{{ reservationVO.check_in }}~{{ reservationVO.check_out }}</h6>
    </div>

    <div class="form-group">
      <label for="guests">인원:</label>
      <h6>{{ reservationVO.res_people_num }} 인</h6>
    </div>

    <div class="form-group">
      <label for="price">요금 세부정보:</label>
      <h6>{{ reservationVO.res_price }}x{{ bak }}박</h6>
      <h6>{{ roomVO.min_people }}인 이상 추가요금:{{ roomVO.add_price }}</h6>
    </div>

    <div class="form-group">
      <label>합계:</label>
      <p>{{ reservationVO.res_price }}원</p>
    </div>

    <div class="form-group request-group">
    <label for="request">요청사항:</label>
    <textarea id="request" v-model="reservationVO.res_message" rows="4" readonly></textarea>
  </div>
    <button @click="showModal = true">예약취소</button>
    <button v-if="canWriteReview" @click="writeReview">후기작성</button>
  </div>
</div>

<div v-if="showModal" class="modal-overlay" @click="showModal = false">
  <div class="modal-content" @click.stop>
    <template v-if="!showReasonInput">
      <h2>예약을 취소하시겠습니까?</h2>
      <div class="modal-buttons">
        <button class="confirm-button" @click="showReasonInput = true">확인</button>
        <button class="cancel-button" @click="showModal = false">취소</button>
      </div>
    </template>
    <template v-else>
      <h2>취소 사유를 입력하세요:</h2>
      <textarea v-model="cancellationReason" rows="4" placeholder="취소 사유를 입력하세요"></textarea>
      <div class="modal-buttons">
        <button class="confirm-button" @click="cancelReservation">제출</button>
        <button class="cancel-button" @click="showReasonInput = false">뒤로</button>
      </div>
    </template>
  </div>
</div>
</template>
  
  <script setup>
  import { ref, computed, watch, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import axios from 'axios';
  import router from "@/router/index.js"
  import PictureSlide from '@/components/myPage/PictureSlide.vue';
  
  const items = ref(null);
  const route = useRoute();
  const resId = computed(() => route.params.reservation_id);
  const roomPictureList = computed(()=>items.value?.roomPictureList || {} );
  const roomVO = computed(() => items.value?.roomVO || {});
  const reservationVO = computed(() => items.value?.reservationVO || {});
  const showModal = ref(false);
  const showReasonInput = ref(false);
  const cancellationReason = ref('');
  const paymentKey = computed(() => items.value?.paymentKey || {});
  const canWriteReview = computed(() => {
  const checkOutTime = new Date(reservationVO.value.check_out);
    const currentTime = new Date();
    return currentTime > checkOutTime;
} )

  const fetchPlaceDetails = async (resId) => {
    await axios.get(`/api/reserve/resDetail?res_id=${resId}`)
      .then(res => {
        items.value = res.data;
      })
      .catch(error => {
        console.error("There was an error fetching the place details:", error);
      });
  };
  
  const bak = computed(() => {
    if (reservationVO.value.check_in && reservationVO.value.check_out) {
      const { check_in, check_out } = reservationVO.value;
      const startDate = new Date(check_in);
      const endDate = new Date(check_out);
      if (startDate instanceof Date && endDate instanceof Date) {
        const days = (endDate - startDate) / (1000 * 60 * 60 * 24);
        return days;
      }
    } 
    return 0;
  });

  const writeReview = (resId) => {
  router.push({ name: 'ReviewInsert', params: { reservation_id: resId.value } });
};

  const cancelReservation = async () => {
  console.log(paymentKey.value);
  showModal.value = false;

  if (paymentKey.value !== null) {
    try {
      await axios.post('/api/payment/toss/cancel', null, {params:{
        paymentKey: paymentKey.value,
        cancelReason: cancellationReason.value}
      });
    const confirmed = confirm('예약이 취소되었습니다. 확인을 누르면 페이지를 이동합니다.');
      
    if (confirmed) {
      router.push({name: "UserHome"});
    }
    } catch (error) {
      console.error("There was an error cancelling the reservation:", error);
    }
  } else {
    console.error("Payment ID is null. Cannot proceed with cancellation.");
  }
};
  
  onMounted(() => {
    fetchPlaceDetails(resId.value);
  });
  
  watch(resId, (newResId) => {
    if (newResId) {
      fetchPlaceDetails(newResId);
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
