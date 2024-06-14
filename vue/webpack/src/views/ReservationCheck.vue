<template>
<div>
  <h4 class="reservation-title">예약 확인 및 결제</h4>
</div>
<hr class="long-divider">
    <div class="reservation-container">
      <!-- 방 이미지 -->
      <div class="room-picture-container">
    <div v-if="roomPictureList.length > 0" class="room-info">
      <PictureSlide :pictureList="roomPictureList" />
      <h2 class="room-name">{{ roomVO.room_name }}</h2>
    </div>
  </div>
  
      <!-- 예약 양식 -->
      <div class="reservation-form">
        <!-- 체크인/체크아웃 -->
        <div class="form-group">
          <label for="check-in">체크인/체크아웃</label>
          <VueDatePicker 
            id="check-in" 
            v-model="date" 
            :enable-time-picker="false"
            :month-change-on-scroll="true" 
            :min-date="new Date()"
            :range="{ noDisabledRange: true }"
            multi-calendars 
            locale="ko" 
            :disabled-dates="disabledDates"
          />
        </div>
  
        <!-- 인원 -->
        <div class="form-group">
          <label for="guests">인원:</label>
          <input id="guests" type="number" v-model="guests" min="0" :max="maxPeople"/>
        </div>

        <div class="form-group">
          <label for="price">요금 세부정보:</label>
          <h6>{{ roomPrice }}x{{ bak }}박</h6>
          <h6>{{ minPeople }}인 이상 추가요금:{{ addPrice }}</h6>
        </div>
  
        <!-- 합계 -->
        <div class="form-group">
          <label>합계:</label>
          <p>{{ totalFee }}원</p>
        </div>

        <div class="form-group request-group">
        <label for="request">요청사항:</label>
        <textarea id="request" v-model="request" rows="4" placeholder="예약과 관련된 요청사항을 입력해주세요."></textarea>
      </div>
  
        <!-- 결제 버튼 -->
        <button @click="payment">결제하기</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, computed } from 'vue';
  import VueDatePicker from '@vuepic/vue-datepicker'
  import '@vuepic/vue-datepicker/dist/main.css'
  import { loadTossPayments } from '@tosspayments/payment-sdk';
  import axios from '@/axios';
  import PictureSlide from '@/components/myPage/PictureSlide.vue';

  const date = ref()
  const request = ref('');
  const reserveInfo = history.state;
  const roomVO = ref(JSON.parse(reserveInfo.roomVO || '{}'));
  const totalFeeDefault = ref(reserveInfo.totalFee || 0);
  const placeId = ref(reserveInfo.placeId || 0);
  const guests = ref(reserveInfo.guests || 0);
  const bakDefault = ref(JSON.parse(reserveInfo.bak || '{}'));
  const startDate = ref(reserveInfo.startDate ? new Date(reserveInfo.startDate) : null);
  const endDate = ref(reserveInfo.endDate ? new Date(reserveInfo.endDate) : null);
  const roomPictureList = ref(JSON.parse(reserveInfo.roomPictureList || '[]'));
  const disabledDates = ref(JSON.parse(reserveInfo.disabledDates || '[]'));
  const roomPrice = ref(roomVO.value.room_price);
  const minPeople = ref(roomVO.value.min_people);
  const addPrice = ref(roomVO.value.add_price);
  const maxPeople = roomVO.value.max_people;
  
  const bak = computed(() => {
    if (date.value && date.value.length === 2) {
      const [startDate, endDate] = date.value;
      if (startDate instanceof Date && endDate instanceof Date) {
        const days = (endDate - startDate) / (1000 * 60 * 60 * 24);
        return days
      }
    }
    return bakDefault;
  })
  
  const totalFee = computed(() => {
    if (date.value && date.value.length === 2) {
      const [startDate, endDate] = date.value;
      if (startDate instanceof Date && endDate instanceof Date) {
        const days = (endDate - startDate) / (1000 * 60 * 60 * 24);
        let fee = roomPrice.value * days;
        const excessGuests = guests.value - minPeople.value;
        if (excessGuests > 0) {
          fee += excessGuests * addPrice.value;
        }
        return fee;
      }
    }
    return totalFeeDefault;
  });
  
  onMounted(() => {
    date.value = [startDate.value, endDate.value];
  })

 const payment = async () => {

  const reserveInfo = {
    check_in: startDate.value.toISOString(),
    check_out: endDate.value.toISOString(),
    res_people_num: guests.value,
    res_price: totalFee.value,
    place_id: placeId.value,
    room_id: roomVO.value.room_id,
    res_message: request.value
  };

  const savedInfo = await axios.post('/api/reserve/saveInfo', reserveInfo);

  const clientKey = 'test_ck_pP2YxJ4K871mNJ6MKR1vVRGZwXLO';
  const paymentData = { 
    amount: savedInfo.data.amount,
    orderId: savedInfo.data.orderId,
    orderName: savedInfo.data.orderName, 
    customerName: savedInfo.data.userName,
    successUrl: savedInfo.data.yourSuccessUrl, 
    failUrl: savedInfo.data.yourFailUrl
  };

  try {
    const tossPayments = await loadTossPayments(clientKey);
    tossPayments.requestPayment('카드', paymentData)
  } catch (error) {
    console.error('Failed to load Toss Payments SDK:', error);
  }
};

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
  