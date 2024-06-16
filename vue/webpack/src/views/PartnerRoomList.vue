<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <button class="add-button" @click="addRoom()">추가</button>
      <div class="place_list_tableview">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">숙소명</th>
              <th scope="col">객실명</th>
              <th scope="col">1박당 요금</th>
              <th scope="col">최대 인원</th>
              <th scope="col">최소 인원</th>
              <th scope="col">노출여부</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(item, index) in state.items"
              :key="item.room_id"
              @click="touchUpInsideTableCell(item.room_id)"
            >
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ item.place_nm }}</td>
              <td>{{ item.room_name }}</td>
              <td>{{parseFloat(item.room_price).toLocaleString('ko-KR', { style: 'currency', currency: 'KRW' }).replace('₩', '') + '원'}}</td>
              <td>{{ item.max_people + '명' }}</td>
              <td>{{ item.min_people + '명'}}</td>
              <td>{{ item.status }}</td>
            </tr> 
          </tbody>
        </table>

      </div>
    </div>
  </div>
</template>

<script>
import SideBar from '@/views/SideBar.vue'
import { reactive } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

export default {
  name: 'PartnerRoomList',
  components: {
    SideBar,
  },
  methods: {
    
  },
  setup() {
    // 숙소 추가 버튼 클릭
    const state = reactive({
      items: [],
    })

    axios.get('/api/partner/roomlist').then((res) => {
      console.log(res.data)
      state.items = res.data
    })

    const router = useRouter()
    function touchUpInsideTableCell(room_id) {
      router.push({
        name: 'PartnerRoomDetail',
        params: {
          room_id,
        },
      })
    }
    function addRoom() {
      router.push({ name: 'PartnerRoomInsert' })
    }
    return{
      state,
      touchUpInsideTableCell,
      addRoom,
    }
  },
}
</script>

<style scoped>

</style>
