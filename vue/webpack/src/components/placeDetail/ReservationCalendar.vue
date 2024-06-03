<template>
  <div class="reservation-form">
    <div class="form-group">
      <label for="check-in">체크인/체크아웃</label>
      <VueDatePicker 
        id="check-in" 
        v-model="date" 
        :enable-time-picker="false"
        :month-change-on-scroll="true" 
        :min-date="new Date()"
        :range="true" 
        multi-calendars 
        locale="ko" 
        :disabled-dates="disabledDates"
      />
    </div>
    <div class="form-group">
      <label for="guests">인원:</label>
      <input id="guests" type="number" v-model="guests" min="0" :max="maxPeople"/>
    </div>
    <div class="form-group">
      <label for="fee-per-night">숙박 기간:</label>
      <p>{{ bak }} 박</p>
    </div>
    <div class="form-group">
      <label for="fee-per-night">1박당 요금:</label>
      <p>{{ roomPrice }}</p>
    </div>
    <div class="form-group">
      <label>합계:</label>
      <p>{{ totalFee }}</p>
    </div>
    <button @click="reserve">예약하기</button>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted, defineProps } from 'vue'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const props = defineProps({
  selectedRoom: {
    type: Object,
    required: true,
    default: () => ({})
  },
  placeInfo: {
    type: Object
  }
})

const date = ref()
const guests = ref(1)
const roomPrice = computed(() => {return props.selectedRoom ? props.selectedRoom.roomVO.room_price : 0;});
const disabledDates = computed(() => {return props.selectedRoom ? props.selectedRoom.reserved_date : [];});
const maxPeople = computed(() => {return props.selectedRoom ? props.selectedRoom.roomVO.max_people : 0;});
const minPeople = computed(() => {return props.selectedRoom ? props.selectedRoom.roomVO.min_people : 0;});
const addPrice = computed(() => {return props.selectedRoom ? props.selectedRoom.roomVO.add_price : 0;});

const bak = computed(() => {
  if (date.value && date.value.length === 2) {
    const [startDate, endDate] = date.value;
    if (startDate instanceof Date && endDate instanceof Date) {
      const days = (endDate - startDate) / (1000 * 60 * 60 * 24);
      return days
    }
  }
  return 0;
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
  return 0;
});

watch(() => props.selectedRoom, (newSelectedRoom) => {
  if (newSelectedRoom) {
    roomPrice.value = newSelectedRoom.room_price
    if (guests.value > maxPeople.value) {
    guests.value = 1;
  }
  }
})

onMounted(() => {
  const startDate = new Date();
  const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
  date.value = [startDate, endDate];
})

function reserve() {
  alert(`예약 완료! 총 요금: ${totalFee.value}원`)
}

</script>

<style scoped>
.reservation-form {
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="number"] {
  width: 100%;
  padding: 8px;
  font-size: 16px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

p {
  margin: 0;
}
</style>