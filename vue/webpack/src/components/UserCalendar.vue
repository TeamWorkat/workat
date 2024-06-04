<template>
  <VueDatePicker
    v-model="date"
    range
    multi-calendars
    locale="ko"
    :enable-time-picker="false"
    @update:modelValue="updateDate"
  >
    <template #dp-input>
      <input type="text" :value="formattedDate" class="custom-input" />
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
  setup(props, { emit }) {
    const date = ref([])
    onMounted(() => {
      const startDate = new Date()
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7))
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
        return `${date.value[0].toLocaleDateString()} + ${date.value[1].toLocaleDateString()}`
      }
      return ''
    })

    return {
      date,
      updateDate,
      formattedDate
    }
  },
}
</script>

<style scoped>
.custom-input {
  /* border: none; 
  outline: none; */
  width: 100%;
  height: 100%;
}
</style>
