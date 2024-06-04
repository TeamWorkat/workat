<template>
  <div class="reservation-form">
    <div class="form-group">
      <label for="check-in">체크인/체크아웃</label>
      <VueDatePicker id="check-in" v-model="date" 
      model-type="yyyy.MM.dd" 
      :enable-time-picker="false"
      :month-change-on-scroll="true" 
      :min-date="new Date()"
      :range="{ noDisabledRange: true }" multi-calendars locale="ko" 
      :disabled-dates= "{ disabledDates }" 
      />
    </div>
    <div class="form-group">
      <label for="guests">인원:</label>
      <input id="guests" type="number" v-model="guests" />
    </div>
    <div class="form-group">
      <label for="fee-per-night">1박당 요금:</label>
      <input id="fee-per-night" type="number" v-model="feePerNight" />
    </div>
    <div class="form-group">
      <label>합계:</label>
      <p>{{ totalFee }}</p>
    </div>
    <button @click="reserve">예약하기</button>
  </div>
</template>

<script>
// import { ref, onMounted } from 'vue'
// import VueDatePicker from '@vuepic/vue-datepicker'
// import '@vuepic/vue-datepicker/dist/main.css'
//
// const date = ref()
//
// onMounted(() => {
//   const startDate = new Date()
//   const endDate = new Date(new Date().setDate(startDate.getDate() + 7))
//   date.value = [startDate, endDate]
// })
// export default {
//   components: { VueDatePicker },
//   name: 'ReservationCalendar',
//   setup() {
//     return {
//       date,
//       // disabledDates: [new Date()]
//     }
//   },
// }
</script>

<style scoped>
</style>
