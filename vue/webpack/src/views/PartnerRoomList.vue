<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="flex-grow-1 m-0">객실 관리</h3>
        <button class="btn btn-success add-button" @click="addRoom()">추가</button>
      </div>
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
              class="table-row"
            >
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ item.place_nm }}</td>
              <td>{{ item.room_name }}</td>
              <td>{{ parseFloat(item.room_price).toLocaleString('ko-KR', { style: 'currency', currency: 'KRW' }).replace('₩', '') }}</td>
              <td>{{ item.max_people + '명' }}</td>
              <td>{{ item.min_people + '명' }}</td>
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
import axios from '@/axios'
import { useRouter } from 'vue-router'

export default {
  name: 'PartnerRoomList',
  components: {
    SideBar,
  },
  setup() {
    const state = reactive({
      items: [],
    })

    const router = useRouter()

    axios.get('/api/partner/roomlist').then((res) => {
      state.items = res.data
    })

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

    return {
      state,
      touchUpInsideTableCell,
      addRoom,
    }
  },
}
</script>

<style scoped>
.place_list_tableview {
  background-color: #f8f9fa;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.table {
  margin-bottom: 20px;
}

.table th {
  background-color: #869ecc;
  color: white;
  text-align: center;
}

.table td {
  text-align: center;
}

.table-hover tbody tr:hover {
  background-color: #e9ecef;
  cursor: pointer;
}

.table-row {
  transition: background-color 0.3s ease;
}

.btn {
  background-color: #869ecc;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.btn:hover {
  background-color: #4472c6;
}

.add-button {
  margin-left: 10px; /* 추가 버튼과 테이블 사이 여백 */
}
</style>
