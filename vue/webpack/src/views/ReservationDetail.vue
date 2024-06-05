<template>
  <h1>예약 상세내역</h1>
  <div class="reservation-container">
    <div class="room-picture-container">
      <div v-if="roomPictureList.length > 0" class="room-info">
        <h1>{{ roomVO.room_name }}</h1>
        <div v-for="(imageUrl, index) in roomPictureList" :key="index" class="image-wrapper">
          <img :src="imageUrl" alt="Room Image" class="room-image">
        </div>
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
        <h6>{{ roomVO.min_people }}인 이상 추가요금:{{ roomVO.add_people }}</h6>
      </div>

      <div class="form-group">
        <label>합계:</label>
        <p>{{ reservationVO.res_price }}원</p>
      </div>

      <div class="form-group request-group">
      <label for="request">요청사항:</label>
      <textarea id="request" v-model="reservationVO.res_message" rows="4" readonly></textarea>
    </div>
      <button @click="change">예약변경</button>
      <button @click="cancel">예약취소</button>
    </div>
  </div>
  </template>
    
    <script setup>
    import { ref, computed, watch, onMounted } from 'vue';
    import { useRoute } from 'vue-router';
    import axios from 'axios';
    
    const items = ref(null);
    const route = useRoute();
    const resId = computed(() => route.params.reservation_id);
    const roomPictureList = computed(()=>items.value?.roomPictureList || {} );
    const roomVO = computed(() => items.value?.roomVO || {});
    const reservationVO = computed(() => items.value?.reservationVO || {});
    
    const fetchPlaceDetails = (resId) => {
      axios.get(`/api/reserve/resDetail?res_id=${resId}`)
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
    
    onMounted(() => {
      fetchPlaceDetails(resId.value);
    });
    
    watch(resId, (newResId) => {
      if (newResId) {
        fetchPlaceDetails(newResId);
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
}

.room-info {
  margin-bottom: 20px;
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
</style>
