<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
        <p>
          숙소명: 
          <input v-model="items.place_nm" placeholder="ex) 홍길동 호텔" />
        </p>
        <p>
          전화번호:
          <input v-model="items.place_tel" placeholder="ex) 028279037" />
        </p>
        <div>
          카테고리:

          <select v-model="selectedCategory" @change="updateCategory">
            <option
              v-for="category in categories"
              :key="category"
              :value="category"
            >
              {{ category }}
            </option>
          </select>
          <p>선택된 카테고리: {{ selectedCategory }}</p>
        </div>
        <p>
          주소:
          <input v-model="items.place_addr" placeholder="ex) 경기도 신곡로36" />
        </p>

        <p>
          소개글:
          <textarea
            v-model="items.place_content"
            placeholder="add multiple lines"
          >
          </textarea>
        </p>

        <div>
          time: {{ time }}

          <VueDatePicker
            v-model="time"
            time-picker
            disable-time-range-validation
            range
            placeholder="Select Time"
          />
        </div>

        <p>체크인: {{ checkin }}</p>
        <p>체크아웃: {{ checkout }}</p>
        <div>
          지역:

          <select v-model="selectedLocation" @change="updateLoaction">
            <option
              v-for="loaction in loactions"
              :key="loaction"
              :value="loaction"
            >
              {{ loaction }}
            </option>
          </select>
          <p>선택된 카테고리: {{ selectedLocation }}</p>
        </div>

        <button @click="updatePlaceTouchUpInside">수정</button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerPlaceUpdate',
  components: {
    SideBar,
  },
  data() {
    return {
      items: null,
      categories: ['Hotel', 'Stay', 'Camping'],
      selectedCategory: '', // 초기값은 빈 문자열 또는 기본값
      checkin: '',
      checkout: '',
      time: [],
      loactions: [
        '서울',
        '경기도',
        '강원도',
        '경상도',
        '전라도',
        '충청도',
        '제주도',
      ],
      selectedLocation: '',
    }
  },
  computed: {
    placeId() {
      return this.$route.params.placeid
    },
  },
  created() {
    console.log(this.$route.params.placeid)
    this.fetchPlaceInfo(this.placeId)
  },
  methods: {
    fetchPlaceInfo(placeId) {
      axios
        .get('/api/partner/placeDetail', {
          params: {
            placeid: placeId,
          },
        })
        .then((res) => {
          this.items = res.data
          // 받아온 데이터의 카테고리를 selectedCategory로 설정
          this.selectedCategory = res.data.category
          this.selectedLocation = res.data.loc_nm
          this.checkin = this.convertTimeStringToObject(res.data.place_in)
          this.checkout = this.convertTimeStringToObject(res.data.place_out)
          this.time = [this.checkin, this.checkout]
          console.log(res.data)
        })
        .catch((err) => {
          console.error(err)
        })
    },
    convertTimeStringToObject(timeString) {
      const [hours, minutes] = timeString.split(':').map(Number)
      return {
        hours: hours,
        minutes: minutes,
        seconds: 0,
      }
    },
    updateCategory() {
      console.log(`선택된 카테고리: ${this.selectedCategory}`)
    },
    updateLocation() {
      console.log(`선택된 지역: ${this.selectedLocation}`)
    },

    updateCheckinoutTime() {
      console.log(`선택된 카테고리: ${this.checkin}`)
      console.log(`선택된 카테고리: ${this.checkout}`)
    },

    updatePlaceTouchUpInside() {
      axios
        .post('/api/partner/placeUpdate', {
          place_id: this.placeId,
          place_nm: this.items.place_nm,
          category: this.selectedCategory,
          place_addr: this.items.place_addr,
          place_tel: this.items.place_tel,
          place_content: this.items.place_content,
          place_in: this.convertTimeObjectToString(this.time[0]),
          place_out: this.convertTimeObjectToString(this.time[1]),
          loc_nm: this.selectedLocation,
          //이부분 수정해야함
          picture_source: 'https://workatbucket.s3.amazonaws.com/place/dining-room-3108037_1920.jpg',
          
        })
        .then((res) => {
          alert('수정되었습니다.');
          window.location.href = 'http://localhost:8090/partner/placelist';
          console.log(res)
        })
        .catch((err) => {
          alert('실패했습니다.' + err);
          console.log(err)
        })
    },
    convertTimeObjectToString(timeObject) {
      // 각 시간 값을 가져옵니다.
      const { hours, minutes } = timeObject

      // 시간과 분이 한 자릿수일 경우 앞에 0을 붙입니다.
      const formattedHours = hours.toString().padStart(2, '0')
      const formattedMinutes = minutes.toString().padStart(2, '0')

      // 시간과 분을 콜론으로 연결합니다.
      return `${formattedHours}:${formattedMinutes}`
    },
  },
}
</script>

<style></style>
