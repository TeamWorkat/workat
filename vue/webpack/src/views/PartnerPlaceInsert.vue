<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <p>
        숙소명:
        <input v-model="place_nm" placeholder="ex) 홍길동 호텔" required />

      </p>
      <p>
        전화번호:
        <input v-model="place_tel" placeholder="ex) 028279037" required />
      </p>
      <div>
        카테고리:
        <select v-model="selectedCategory" @change="updateCategory" >
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
        <input v-model="place_addr" placeholder="ex) 경기도 신곡로36" />
      </p>
      <p>
        소개글:
        <textarea v-model="place_content" placeholder="add multiple lines">
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

        <!-- <select v-model="selectedLocation" @change="updateLoaction"> -->
        <select v-model="selectedLocation">
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
        <p>사진 (최소 1장이상, 최대 3장이하)</p>
        <div
          v-for="(item, index) in picturefileURL"
          :key="item"
          class="image-container"
        >
          <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
          <button class="delete-button" @click="removeItem(index)">-</button>
        </div>
        <input type="file" multiple @change="handleFileUpload" />
        <button @click="submitFiles">추가</button>
      </div>
      <!-- <div>
        <button @click="insertPlaceTouchUpInside">추가</button>
      </div> -->
    </div>
  </div>
</template>
<script>
import axios from '@/axios';
import SideBar from '@/views/SideBar.vue'
import { reactive } from 'vue'

export default {
  name: 'PartnerPlaceInsert',
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
      parsingLocation: '',


      picturefileURL: reactive([]),
      pictureArray: reactive([]),

      //file
      
      fileFolder: 'place',

      insertFileNum: 0

    }
  },
  computed: {},
  created() {},
  methods: {
    convertTimeStringToObject(timeString) {
      const [hours, minutes] = timeString.split(':').map(Number)
      return {
        hours: hours,
        minutes: minutes,
        seconds: 0,
      }
    },
    updateCategory() {},
    updateLocation() {},

    updateCheckinoutTime() {},

    handleFileUpload(event) {
      
      const files = Array.from(event.target.files)
      
      if (this.picturefileURL.length + files.length <= 3) {
        files.forEach((file) => {
          const reader = new FileReader()
          reader.onload = (e) => {
            this.picturefileURL.push(e.target.result)
          }
          reader.readAsDataURL(file)
          this.pictureArray.push(file)
        })
        console.log(this.picturefileURL, "URLRLRLRLRLRLRLRLRL")
        if (this.insertFileNum != 0) {
            this.pictureArray.splice(-this.insertFileNum, this.insertFileNum);
        }

        console.log(this.pictureArray,'array')
        this.insertFileNum = files.length
        console.log(this.insertFileNum, '개수')
      } else {
        alert('사진은 3개까지 업로드 가능합니다.')
        console.log(this.picturefileURL)
      }

     
    },

    async submitFiles() {
      
      if (this.pictureArray.length === 0 || !this.fileFolder) {
        alert('Please select files and enter a folder name.')
        return
      }
      
      const formData = new FormData()
      this.pictureArray.forEach((file) => {
        // if (typeof file === 'string' && file.startsWith('data:image/')) {
          if (file instanceof File){
          formData.append('files', file)
        }
      })
      formData.append('fileFolder', this.fileFolder)
      try {
        const response = await axios.post('/api/test/aws', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        console.log('Files uploaded successfully:', response.data)
        
        response.data.forEach((url) =>{
          this.picturefileURL.push(url)
        })
        console.log(this.picturefileURL)
        this.picturefileURL = this.picturefileURL.filter(url => url.startsWith('https://workatbucket.s3.amazonaws.com/place/'));
        this.insertPlaceTouchUpInside()
        console.log(this.picturefileURL, "결과 URL")
      } catch (error) {
        console.error('Error uploading files:', error)
      }
    },

    insertPlaceTouchUpInside() {
      axios
        .post('/api/partner/placeInsert', {
          place_nm: this.place_nm,
          category: this.selectedCategory,
          place_addr: this.place_addr,
          place_tel: this.place_tel,
          place_content: this.place_content,
          place_in: this.convertTimeObjectToString(this.time[0]),
          place_out: this.convertTimeObjectToString(this.time[1]),
          status: 'active',
          loc_nm: this.parsinglocal(this.selectedLocation),
          picture_sources: this.picturefileURL,
        })
        .then((res) => {
          alert('추가되었습니다.')
          window.location.href = 'http://localhost:8090/partner/placelist'
          console.log(res)
        })
        .catch((err) => {
          alert('실패했습니다.' + err)
          console.log(err)
        })
    },
    parsinglocal(loc_nm) {
      switch (loc_nm) {
        case '서울':
          return (this.parsingLocation = 1)

        case '경기도':
          return (this.parsingLocation = 2)

        case '강원도':
          return (this.parsingLocation = 3)

        case '경상도':
          return (this.parsingLocation = 4)

        case '전라도':
          return (this.parsingLocation = 5)

        case '충청도':
          return (this.parsingLocation = 6)

        case '제주도':
          return (this.parsingLocation = 7)

        default:
          return (this.parsingLocation = 8)
      }
    },
    removeItem(index) {
      this.picturefileURL.splice(index, 1)
      this.pictureArray.splice(index, 1)
      console.log(this.picturefileURL)
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

<style scoped>
.img {
  display: inline-block;
  width: 150px; /* 또는 적절한 값으로 변경 */
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

