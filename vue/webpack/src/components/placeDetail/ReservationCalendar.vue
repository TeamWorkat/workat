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
        :range="{ noDisabledRange: true }"
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
import {useRouter} from 'vue-router'

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
const placeId = computed(() => {return props.placeInfo ? props.placeInfo.place_id : 0;});
const roomVO = computed(() => {return props.selectedRoom ? props.selectedRoom.roomVO : 0;});
const roomPictureList = computed(() => {return props.selectedRoom ? props.selectedRoom.room_picture_source : []});
const router = useRouter();

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
  const endDate = new Date(new Date().setDate(startDate.getDate() + 1));
  date.value = [startDate, endDate];
})

function reserve() {
  const startDate = date.value[0];
  const endDate = date.value[1];
  console.log(roomPictureList.value);
  console.log(disabledDates)
  router.push({
  name: 'ReservationCheck',
  state: {
    roomVO: JSON.stringify(roomVO.value),
    bak: bak.value,
    placeId: placeId.value,
    guests: guests.value,
    totalFee: totalFee.value,
    startDate: startDate,
    endDate: endDate,
    roomPictureList: JSON.stringify(roomPictureList.value),
    disabledDates: JSON.stringify(disabledDates.value)
  }
})
}

</script>

<style scoped>
.reservation-form {
  max-width: 400px;
  margin: 0 auto;
  background-color: #fff5cc; /* Soft yellow background */
  padding: 20px;
  border: 2px solid #ffd700; /* Gold border */
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

input[type="number"] {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #ffd700; /* Gold background */
  color: #333; /* Dark text */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #ffcc00; /* Darker gold on hover */
}

p {
  margin: 0;
  font-size: 16px;
  color: #555;
}

p.total {
  font-weight: bold;
  font-size: 18px;
}
</style>
