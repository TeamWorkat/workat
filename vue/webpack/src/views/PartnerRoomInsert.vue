<template>
  <div class="vacation-container">
    <SideBar />
    <div class="main-content">
      
        <p class="input-group">
          객실명:
          <input v-model="room_name" placeholder="ex) 디럭스" class="input-field" />
        </p>
        <p class="input-group">
          객실수:
          <input v-model="room_qnt" placeholder="ex) 1" class="input-field" />
        </p>
        <div class="input-group">
          1박당 요금:
          <input v-model="room_price" placeholder="ex) 90000" class="input-field" />
        </div>

        <p class="input-group">
          객실 설명:
          <textarea
            v-model="room_content"
            placeholder="2~3명 커플이나 가족이 함께 하기 좋습니다."
            class="input-field"
          ></textarea>
        </p>

        <p class="input-group">
          최대 인원:
          <input v-model="max_people" placeholder="ex) 10" class="input-field" />
        </p>

        <p class="input-group">
          최소 인원:
          <input v-model="min_people" placeholder="ex) 1" class="input-field" />
        </p>

        <p class="input-group">
          1인당 추가요금:
          <input v-model="add_price" placeholder="ex) 10000" class="input-field" />
        </p>

        <div class="input-group">
          숙소명:
          <select v-model="selectedPlace" class="input-field">
            <option
              v-for="place_name in placeNameList"
              :key="place_name"
              :value="place_name"
            >
              {{ place_name }}
            </option>
          </select>
        </div>
        <p class="input-group">선택된 카테고리: {{ selectedPlace }}</p>
        <p class="input-group">선택된 숙소 ID: {{ selectedPlaceID }}</p>

        <p class="input-group">사진:</p>
        <div
          v-for="(item, index) in picturefileURL"
          :key="item"
          class="image-container"
        >
          <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
          <button class="delete-button" @click="removeItem(index)">-</button>
        </div>
        <input type="file" multiple @change="handleFileUpload" class="file-input" />
        <button @click="submitFiles" class="submit-btn">추가</button>
      </div>
    </div>
</template>

<script>
import axios from '@/axios'
import SideBar from '@/views/SideBar.vue'
import { reactive} from 'vue'

export default {
  name: 'PartnerRoomInsert',
  components: {
    SideBar,
  },
  data() {
    return {
      items: null,
      placeNameList: [],
      placeIDList:[],
      picturefileURL: reactive([]),
      pictureArray: reactive([]),
      selectedPlace: '',

      //file

      fileFolder: 'room',

      insertFileNum: 0,

      room_name: '',
      room_qnt: '',
      room_price: '',
      room_content: '',
      max_people: '',
      min_people: '',
      add_price: '',
      place_id: ''
    }
  },
  computed: {
    selectedPlaceID() {
      const index = this.placeNameList.indexOf(this.selectedPlace);
      return index !== -1 ? this.placeIDList[index] : null;
    }
  },
  created() {
    this.fetchPartnerGetPlaceList();
  },

  methods: {
    //사장이 가지고 있는 숙소명받아오기
    fetchPartnerGetPlaceList() {
      axios.get(`/api/partner/placelist`)
        .then(res => {
          res.data.forEach((datalist) => {
              this.placeNameList.push(datalist.place_nm);
              this.placeIDList.push(datalist.place_id);
          });

        })
        .catch(error => {
          console.error("숙소 정보를 가져오는 중 에러 발생:", error);
        });
    },

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
        if (this.insertFileNum != 0) {
          this.pictureArray.splice(-this.insertFileNum, this.insertFileNum)
        }

        this.insertFileNum = files.length
      } else {
        alert('사진은 3개까지 업로드 가능합니다.')
      }
    },

    async submitFiles() {
      if (this.pictureArray.length === 0 || !this.fileFolder) {
        alert('사진을 선택하고 폴더 이름을 입력하세요.')
        return
      }

      const formData = new FormData()
      this.pictureArray.forEach((file) => {
        if (file instanceof File) {
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
        response.data.forEach((url) => {
          this.picturefileURL.push(url)
        })
        this.picturefileURL = this.picturefileURL.filter((url) =>
          url.startsWith('https://workatbucket.s3.amazonaws.com/room/')
        )
        this.insertRoomTouchUpInside()
      } catch (error) {
        console.error('파일 업로드 실패:', error)
      }
    },

    insertRoomTouchUpInside() {
      axios
        .post('/api/partner/roomInsert', {
          room_name: this.room_name,
          room_qnt: this.room_qnt,
          room_price: this.room_price,
          room_content: this.room_content,
          sold_num: '0',
          max_people: this.max_people,
          min_people: this.min_people,
          add_price: this.add_price,
          picture_id: '1000',
          status: 'active',
          picture_sources: this.picturefileURL,
          place_id: this.selectedPlaceID
        })
        .then(() => {
          alert('추가되었습니다.')
          window.location.href = 'http://localhost:8090/partner/roomList'
        })
        .catch((err) => {
          alert('추가 실패: ' + err)
          console.error(err)
        })
    },

    removeItem(index) {
      this.picturefileURL.splice(index, 1)
      this.pictureArray.splice(index, 1)
    },

  },
}
</script>

<style scoped>
.vacation-container {
  display: flex;
  background-color: #f8fafc; /* 배경색 */
}

.main-content {
  flex-grow: 1;
  padding: 20px;
  background-color: #ffffff; /* 배경색 */
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 */
}

.input-group {
  margin-bottom: 20px;
}

.input-field {
  width: calc(100% - 24px); /* 24px는 padding 12px씩 */
  padding: 12px;
  border: 1px solid #d9e1e1; /* 테두리 */
  border-radius: 6px;
  font-size: 16px;
}

textarea.input-field {
  height: 120px; /* 텍스트 에어리어 높이 */
  resize: vertical; /* 수직 리사이즈 */
}

.img {
  display: block;
  width: 150px;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 10px; /* 이미지 둥글게 */
}

.image-container {
  position: relative;
  display: inline-block;
  margin-right: 10px;
}

.delete-button {
  position: absolute;
  top: 5px;
  right: 5px;
  width: 25px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  background-color: #ff6666; /* 삭제 버튼 배경색 */
  color: white;
  border: none;
  border-radius: 50%; /* 삭제 버튼 둥글게 */
  cursor: pointer;
}

.file-input {
  margin-top: 10px;
}
.submit-btn {
    background-color: #869ecc; /* 추가 버튼 배경색 */
    color: white;
    border: none;
    padding: 12px 20px;
    border-radius: 6px; /* 버튼 둥글게 */
    cursor: pointer;
    transition: background-color 0.3s ease; /* 배경색 전환 효과 */
  }

  .submit-btn:hover {
    background-color: #547dc9; /* 마우스 오버 배경색 */
  }

  /* Responsiveness */
  @media (max-width: 768px) {
    .vacation-container {
      flex-direction: column; /* 모바일에서 세로로 배열 */
    }

    .main-content {
      margin-top: 20px;
    }
  }
</style>
