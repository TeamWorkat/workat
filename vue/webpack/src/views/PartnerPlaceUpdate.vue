<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3 place-update-container">
      <div v-if="items">
        <p>
          <label>숙소명:</label>
          <input v-model="items.place_nm" placeholder="ex) 홍길동 호텔" />
        </p>
        <p>
          <label>전화번호:</label>
          <input v-model="items.place_tel" placeholder="ex) 028279037" />
        </p>
        <div>
          <label>카테고리:</label>
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
          <label>주소:</label>
          <input v-model="items.place_addr" placeholder="ex) 경기도 신곡로36" />
        </p>

        <p>
          <label>소개글:</label>
          <textarea
            v-model="items.place_content"
            placeholder="add multiple lines"
          >
          </textarea>
        </p>

        <div>
          <label>시간:</label>
          <VueDatePicker
            v-model="time"
            time-picker
            disable-time-range-validation
            range
            placeholder="Select Time"
          />
          <p>체크인: {{ checkin }}</p>
          <p>체크아웃: {{ checkout }}</p>
        </div>

        <div>
          <label>지역:</label>
          <select v-model="selectedLocation" @change="updateLocation">
            <option
              v-for="loaction in loactions"
              :key="loaction"
              :value="loaction"
            >
              {{ loaction }}
            </option>
          </select>
          <p>선택된 지역: {{ selectedLocation }}</p>
        </div>

        <div>
          <p>사진:</p>
          <div class="image-gallery">
            <div
              v-for="(item, index) in picturefileURL"
              :key="item"
              class="image-container"
            >
              <span
                class="img"
                :style="{ backgroundImage: `url(${item})` }"
              ></span>
              <button class="delete-button" @click="removeItem(index)">-</button>
            </div>
          </div>
          <input type="file" multiple @change="handleFileUpload" />
          <button class="btn-submit" @click="submitFiles">수정</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';
import SideBar from '@/views/SideBar.vue'
import { reactive } from 'vue'

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

      picturefileURL: reactive([]),
      pictureArray: reactive([]),

      //file

      fileFolder: 'place',

      insertFileNum: 0,
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
        console.log(this.picturefileURL, 'URLRLRLRLRLRLRLRLRL')
        if (this.insertFileNum != 0) {
          this.pictureArray.splice(-this.insertFileNum, this.insertFileNum)
        }

        console.log(this.pictureArray, 'array')
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
        if (file instanceof File) {
          formData.append('files', file)
        }
      })
      formData.append('fileFolder', this.fileFolder)

      const files = formData.getAll('files')
      if (files.length === 0) {
        console.log('추가한 업로드 파일이 없음')
        this.updatePlaceTouchUpInside()
      } else {
        try {
          const response = await axios.post('/api/test/aws', formData, {
            headers: {
              'Content-Type': 'multipart/form-data',
            },
          })
          console.log('Files uploaded successfully:', response.data)

          response.data.forEach((url) => {
            this.picturefileURL.push(url)
          })
          console.log(this.picturefileURL)
          this.picturefileURL = this.picturefileURL.filter((url) =>
            url.startsWith('https://workatbucket.s3.amazonaws.com/place/')
          )
          this.updatePlaceTouchUpInside()
          console.log(this.picturefileURL, '결과 URL')
        } catch (error) {
          console.error('Error uploading files:', error)
        }
        console.log('Files added to FormData:', files)
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

          this.picturefileURL = res.data.picture_sources
          this.pictureArray = [...this.picturefileURL]

          console.log(this.pictureArray, '처음array')
          console.log(this.picturefileURL, '처음 url')
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

          picture_sources: this.picturefileURL,
        })
        .then((res) => {
          alert('수정되었습니다.')
          window.location.href = 'http://localhost:8090/partner/placelist'
          console.log(res)
        })
        .catch((err) => {
          alert('실패했습니다.' + err)
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
      this.picturefileURL.splice(index, 1)
      this.pictureArray.splice(index, 1)
      console.log(this.picturefileURL)
    },
  },
}
</script>

<style scoped>
.place-update-container {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

p {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

input,
textarea,
select {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ced4da;
  border-radius: 4px;
}

textarea {
  resize: vertical;
  height: 100px;
}

.button-group {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}

.btn-submit {
  background-color: #869ecc;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
}

.btn-submit:hover {
  background-color: #869ecc;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.img {
  display: inline-block;
  width: 150px;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 20%;
  border: 2px solid #dee2e6;
}

.image-container {
  position: relative;
  display: inline-block;
}

.delete-button {
  position: absolute;
  top: -10px;
  right: -10px;
  background-color: red;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 50%;
  width: 25px;
  height: 25px;
  font-size: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
