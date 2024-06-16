<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      
        <p>
          객실명:
          <input v-model="room_name" placeholder="ex) 디럭스" />
        </p>
        <p>
          객실수:
          <input v-model="room_qnt" placeholder="ex) 1" /> 개
        </p>
        <div>
          1박당 요금:
          <input v-model="room_price" placeholder="ex) 90000" /> 원
        </div>

        <p>
          객실 설명:
          <textarea
            v-model="room_content"
            placeholder="2~3명 커플이나 가족이 함께 하기 좋습니다."
          >
          </textarea>
        </p>

        <p>
          최대 인원:
          <input v-model="max_people" placeholder="ex) 10" /> 명
        </p>

        <p>
          최소 인원:
          <input v-model="min_people" placeholder="ex) 1" /> 명
        </p>

        <p>
          1인당 추가요금:
          <input v-model="add_price" placeholder="ex) 10000" />
        </p>

        <div>
        숙소명:

        <!-- <select v-model="selectedLocation" @change="updateLoaction"> -->
        <select v-model="selectedPlace">
          <option
            v-for="place_name in placeNameList"
            :key="place_name"
            :value="place_name"
          >
            {{ place_name }}
          </option>
        </select>
        <p>선택된 카테고리: {{ selectedPlace }}</p>
        <p>선택된 숙소 ID: {{ selectedPlaceID }}</p>
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
        <button @click="submitFiles">추가</button>
      </div>
    </div>
</template>
<script>
import axios from 'axios'
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
          console.error("There was an error fetching the place details:", error);
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
        this.insertRoomTouchUpInside()
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
          this.insertRoomTouchUpInside()
          console.log(this.picturefileURL, '결과 URL')
        } catch (error) {
          console.error('Error uploading files:', error)
        }
        console.log('Files added to FormData:', files)
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
        .then((res) => {
          alert('추가되었습니다.')
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
