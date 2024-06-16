<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
       
        <p>
          객실명:
          <input v-model="items.room_name" placeholder="ex) 디럭스" />
        </p>
        <p>
          객실수:
          <input v-model="items.room_qnt" placeholder="ex) 1" /> 개
        </p>
        <div>
          1박당 요금:
          <input v-model="items.room_price" placeholder="ex) 90000" /> 원
        </div>

        <p>
          객실 설명:
          <textarea
            v-model="items.room_content"
            placeholder="2~3명 커플이나 가족이 함께 하기 좋습니다."
          >
          </textarea>
        </p>

        <p>
          최대 인원:
          <input v-model="items.max_people" placeholder="ex) 10" /> 명
        </p>

        <p>
          최소 인원:
          <input v-model="items.min_people" placeholder="ex) 1" /> 명
        </p>

        <p>
          1인당 추가요금:
          <input v-model="items.add_price" placeholder="ex) 10000" />
        </p>

        <div>
          노출여부:
          <select v-model="selectedCategory">
            <option
              v-for="category in categories"
              :key="category"
              :value="category"
            >
              {{ category }}
            </option>
          </select>
        </div>

        <p>사진:</p>
        <div
          v-for="(item, index) in picturefileURL"
          :key="item"
          class="image-container"
        >
          <span class="img" :style="{ backgroundImage: `url(${item})` }"></span>
          <button class="delete-button" @click="removeItem(index)">-</button>
        </div>
        <input type="file" multiple @change="handleFileUpload" />
        <button @click="submitFiles">수정</button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
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

      //file

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
            url.startsWith('https://workatbucket.s3.amazonaws.com/room/')
          )
          this.updatePlaceTouchUpInside()
          console.log(this.picturefileURL, '결과 URL')
        } catch (error) {
          console.error('Error uploading files:', error)
        }
        console.log('Files added to FormData:', files)
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
          this.pictureArray = [...this.picturefileURL]
          this.selectedCategory = res.data.status

          console.log(this.pictureArray, '처음array')
          console.log(this.picturefileURL, '처음 url')
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
        .then((res) => {
          alert('수정되었습니다.')
          window.location.href = 'http://localhost:8090/partner/roomList'
          console.log(res)
        })
        .catch((err) => {
          alert('실패했습니다.' + err)
          console.log(err)
        })
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
