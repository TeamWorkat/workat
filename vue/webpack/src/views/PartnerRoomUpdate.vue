<template>
  <div class="vacation-container">
    <SideBar />
    <div class="main-content">
      <div v-if="items" class="room-details">
        <p>객실명: <input v-model="items.room_name" class="input-field" placeholder="ex) 디럭스" /></p>
        <p>객실수: <input v-model="items.room_qnt" class="input-field" placeholder="ex) 1" /> </p>
        <p>1박당 요금: <input v-model="items.room_price" class="input-field" placeholder="ex) 90000" /> </p>
        <p>객실 설명: <textarea v-model="items.room_content" class="input-field" placeholder="2~3명 커플이나 가족이 함께 하기 좋습니다."></textarea></p>
        <p>최대 인원: <input v-model="items.max_people" class="input-field" placeholder="ex) 10" /> </p>
        <p>최소 인원: <input v-model="items.min_people" class="input-field" placeholder="ex) 1" /> </p>
        <p>1인당 추가요금: <input v-model="items.add_price" class="input-field" placeholder="ex) 10000" /></p>

        <div class="category">
          노출여부:
          <select v-model="selectedCategory" class="input-field">
            <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
          </select>
        </div>

        <p>사진:</p>
        <div class="image-gallery">
          <div v-for="(item, index) in picturefileURL" :key="item" class="image-container">
            <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
            <button class="delete-button" @click="removeItem(index)">-</button>
          </div>
        </div>
        <input type="file" multiple @change="handleFileUpload" class="file-input" />
        <button class="submit-btn" @click="submitFiles">수정</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'
import SideBar from '@/views/SideBar.vue'
import { reactive } from 'vue'

export default {
  name: 'PartnerRoomUpdate',
  components: {
    SideBar,
  },
  data() {
    return {
      items: null,
      selectedCategory: '',
      categories: ['active', 'inactive'],
      picturefileURL: reactive([]),
      pictureArray: reactive([]),
      fileFolder: 'room',
      insertFileNum: 0,
    }
  },
  computed: {
    roomId() {
      return this.$route.params.room_id
    },
  },
  created() {
    this.fetchPlaceInfo(this.roomId)
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

        if (this.insertFileNum !== 0) {
          this.pictureArray.splice(-this.insertFileNum, this.insertFileNum)
        }

        this.insertFileNum = files.length
      } else {
        alert('사진은 3개까지 업로드 가능합니다.')
      }
    },

    async submitFiles() {
      if (this.pictureArray.length === 0 || !this.fileFolder) {
        alert('파일을 선택하고 폴더명을 입력해주세요.')
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

        this.picturefileURL = response.data.filter((url) =>
          url.startsWith('https://workatbucket.s3.amazonaws.com/room/')
        )
        this.updatePlaceTouchUpInside()
      } catch (error) {
        console.error('파일 업로드 실패:', error)
      }
    },

    fetchPlaceInfo(roomId) {
      axios
        .get('/api/partner/roomDetail', {
          params: {
            room_id: roomId,
          },
        })
        .then((res) => {
          this.items = res.data
          this.picturefileURL = res.data.picture_sources
          this.selectedCategory = res.data.status
        })
        .catch((err) => {
          console.error(err)
        })
    },

    updatePlaceTouchUpInside() {
      axios
        .post('/api/partner/roomUpdate', {
          room_id: this.roomId,
          room_name: this.items.room_name,
          room_qnt: this.items.room_qnt,
          room_price: this.items.room_price,
          room_content: this.items.room_content,
          max_people: this.items.max_people,
          min_people: this.items.min_people,
          add_price: this.items.add_price,
          status: this.selectedCategory,
          picture_sources: this.picturefileURL,
        })
        .then(() => {
          alert('수정되었습니다.')
          window.location.href = 'http://localhost:8090/partner/roomList'
        })
        .catch((err) => {
          alert('수정 실패: ' + err)
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
  background-color: #f4f9f9;
}

.main-content {
  flex-grow: 1;
  padding: 20px;
}

.room-details {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.input-field {
  width: 100%;
  padding: 12px;
  margin-top: 10px;
  margin-bottom: 20px;
  border: 1px solid #d9e1e1;
  border-radius: 6px;
  font-size: 16px;
}

textarea {
  width: 100%;
  height: 120px;
  padding: 12px;
  margin-top: 10px;
  margin-bottom: 20px;
  border: 1px solid #d9e1e1;
  border-radius: 6px;
  font-size: 16px;
  resize: vertical;
}

.category {
  margin-bottom: 20px;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.image-container {
  position: relative;
  display: inline-block;
  margin: 0 10px 10px 0;
}

.img {
  display: block;
  width: 150px;
  height: 150px;
  border-radius: 10px;
  background-size: cover;
  background-position: center;
}

.delete-button {
  position: absolute;
  top: 5px;
  right: 5px;
  width: 25px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  background-color: #ff6666;
  color: white;
  border: none;
  border-radius: 50%;
  cursor: pointer;
}

.file-input {
  margin-top: 10px;
}

.submit-btn {
  background-color: #869ecc;
  color: white;
  border: none;
  padding: 12px 20px;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
}

.submit-btn:hover {
  background-color: #547dc9;
}
</style>
