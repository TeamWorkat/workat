<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div class="place_list_tableview">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">예약번호</th>
              <th scope="col">예약자명</th>
              <th scope="col">입실날짜</th>
              <th scope="col">퇴실날짜</th>
              <th scope="col">숙소명</th>
              <th scope="col">객실명</th>
              <th scope="col">답글작성여부</th>
              <th scope="col">리뷰숨김여부</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(item, index) in state.items"
              :key="item.review_id"
              @click="touchUpInsideTableCell(item.review_id)"
            >
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ item.res_id }}</td>
              <td>{{ item.res_name }}</td>
              <td>{{ item.check_in }}</td>
              <td>{{ item.check_out }}</td>
              <td>{{ item.place_nm }}</td>
              <td>{{ item.room_name }}</td>
              <td>{{ item.reply_exist }}</td>
              <td>
                <span v-if="item.status === 'active'">안숨김</span>
                <span v-else>숨김</span>
              </td>
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
  name: 'PartnerReviewList',
  components: {
    SideBar,
  },
  methods: {},
  setup() {
    const state = reactive({
      items: [],
    })

    axios.get('/api/partner/reviewlist').then((res) => {
      console.log(res.data)
      state.items = res.data
    })

    const router = useRouter()
    function touchUpInsideTableCell(review_id) {
      router.push({
        name: 'PartnerReviewDetail',
        params: {
          review_id,
        },
      })
    }

    return {
      state,
      touchUpInsideTableCell,
    }
  },
}
</script>

<style scoped>
.place_list_tableview {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.table {
  width: 100%;
  margin-bottom: 20px;
  border-collapse: separate;
  border-spacing: 0 15px;
}

.table thead th {
  background-color: #f8f9fa;
  color: #343a40;
  font-weight: 600;
  text-align: left;
  padding: 10px;
  border: none;
  border-bottom: 2px solid #dee2e6;
}

.table tbody tr {
  background-color: #fff;
  transition: background-color 0.3s;
  cursor: pointer;
}

.table tbody tr:hover {
  background-color: #f1f3f5;
}

.table tbody td {
  padding: 10px;
  border-top: 1px solid #dee2e6;
}

.table tbody th {
  padding: 10px;
  font-weight: 400;
  color: #495057;
  border-top: 1px solid #dee2e6;
}

.table tbody td span {
  padding: 5px 10px;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 600;
}

.table tbody td span:nth-child(1) {
  background-color: #28a745;
  color: #fff;
}

.table tbody td span:nth-child(2) {
  background-color: #dc3545;
  color: #fff;
}
</style>
