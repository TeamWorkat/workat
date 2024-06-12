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

          <select v-model="selectedLocation" @change="updateLocation">
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

        <div>
    <p>사진: </p>
        <div v-for="(item, index) in items.picture_sources" :key="index" class="image-container">
          <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
          <button class="delete-button" @click="removeItem(index)">-</button>
        </div>
    <input type="file" multiple @change="handleFileUpload"/>
        <button @click="submitFiles">Upload</button>
  </div>
        
      
        

        
      <div>
        <button @click="updatePlaceTouchUpInside">수정</button>
      </div>
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
      
      //file
      selectedFiles: [],
      fileFolder: 'place'

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
    handleFileUpload(event) {
      this.selectedFiles = Array.from(event.target.files);
    },
    async submitFiles() {
      if (this.selectedFiles.length === 0 || !this.fileFolder) {
        alert('Please select files and enter a folder name.');
        return;
      }

      const formData = new FormData();
      this.selectedFiles.forEach(file => {
        formData.append('files', file);
      });
      
      formData.append('fileFolder', this.fileFolder);
    
      
      try {
        const response = await axios.post('/api/test/aws', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log('Files uploaded successfully:', response.data);
      } catch (error) {
        console.error('Error uploading files:', error);
        
      }
    },

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
          picture_sources: this.items.picture_sources
          
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
    removeItem(index) {
      this.items.picture_sources.splice(index, 1);
      console.log(this.items.picture_sources)
    }
  },
}
</script>

<style scoped>
.img {
  display: inline-block;
  width: 150px;  /* 또는 적절한 값으로 변경 */
  height: 150px; /* 또는 적절한 값으로 변경 */
  background-size: cover;
  background-position: center;
  border-radius: 20%;
}


.image-container {
  position: relative;
  display: inline-block;
  margin: 10px;
}

.delete-button {
  position: absolute;
  top: 0;
  right: 0;
  background-color: red;
  color: white;
  border: none;
  cursor: pointer;
}
</style>
