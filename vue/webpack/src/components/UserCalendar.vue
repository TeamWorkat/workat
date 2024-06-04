<template>
  <VueDatePicker
    v-model="date"
    range
    multi-calendars
    locale="ko"
    :enable-time-picker="false"
    :min-date="new Date()"
    :max-date="maxDate"
    @update:modelValue="updateDate"
  >
  
    <template #dp-input>
      <input type="text" :value="formattedDate"  class="custom-input"/>
    </template>
  </VueDatePicker>

</template>

<script>
import { ref, onMounted, computed } from 'vue'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'


export default {
  components: { VueDatePicker },
  name: 'UserCalendar',
  
  data() {
    return {
      selectedDate: null,
      isCalendarOpen: false
    };
  },
  methods: {
    toggleCalendar() {
      this.isCalendarOpen = !this.isCalendarOpen;
    },
    handleDateUpdate(date) {
      this.selectedDate = date;
      this.$emit('update-date', date);
      this.isCalendarOpen = false; // 날짜 선택 후 달력을 닫음
    }
  },
  setup(props, { emit }) {
    const date = ref([])
    onMounted(() => {
      const startDate = new Date()
      const endDate = new Date(new Date().setDate(startDate.getDate() + 1))
      date.value = [startDate, endDate]
    })

    const updateDate = (newDate) => {
      date.value = newDate
      // emit('update-date', newDate)
      
      const startYear = newDate[0].getFullYear()
      const startMonth = (newDate[0].getMonth() + 1).toString().padStart(2,'0');
      const startDay = newDate[0].getDate().toString().padStart(2,'0');

      const startdate = `${startYear}-${startMonth}-${startDay}`

      const endYear = newDate[1].getFullYear()
      const endMonth = (newDate[1].getMonth() + 1).toString().padStart(2,'0');
      const endDay = newDate[1].getDate().toString().padStart(2,'0');

      const enddate = `${endYear}-${endMonth}-${endDay}`
      emit('update-date',[startdate,enddate])
      
    }

    const formattedDate = computed(() => {
      if (date.value && date.value.length === 2) {
        return `${date.value[0].toLocaleDateString()} ~ ${date.value[1].toLocaleDateString()}`
      }
      return ''
    })

    const maxDate = computed(() => {
      const today = new Date();
      const nextYear = new Date(today.setFullYear(today.getFullYear() + 1));
      return nextYear;
    });

    
    return {
      maxDate,
      date,
      updateDate,
      formattedDate
    }
  },
}
</script>


<style>
.dp__main.dp__theme_light {
  width: 100%; /* 원하는 너비로 변경 */
  height: 100%; /* 원하는 높이로 변경 */
  /* 추가적으로 필요한 스타일 지정 */
}
.dp__input_wrap{
  width: 100%; /* 원하는 너비로 변경 */
  height: 100%; /* 원하는 높이로 변경 */
}
</style>
