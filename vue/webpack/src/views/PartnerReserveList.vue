<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div class="place_list_tableview">
        <select
          id="pageCount"
          v-model="selectedPageCount"
          @change="handlePageCountChange"
        >
          <option v-for="count in pageCounts" :key="count" :value="count">
            {{ count }}
          </option>
        </select>

        검색:
        <input
          v-model="searchQuery"
          @input="fetchData(0)"
          placeholder="예약자 이름"
        />
      </div>

      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">예약번호</th>
            <th scope="col">예약자명</th>
            <th scope="col">전화번호</th>
            <th scope="col">취소여부</th>
            <th scope="col">예약날짜(체크인)</th>
            <th scope="col">예약날짜(체크아웃)</th>
            <th scope="col">요청사항</th>
            <th scope="col">상품</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="item in items"
            :key="item.res_id"
            @click="touchUpInsideTableCell(item.res_id)"
          >
            <td>{{ item.res_id }}</td>
            <td>{{ item.res_name }}</td>
            <td>{{ item.user_tel }}</td>
            <td>{{ item.res_cancel_yn }}</td>
            <td>{{ item.check_in }}</td>
            <td>{{ item.check_out }}</td>
            <td>{{ item.res_message }}</td>
            <td>{{ item.room_name }}</td>
          </tr>
        </tbody>
      </table>
      <div class="pagination">
        <button @click="fetchData(currentPage - 1)" :disabled="currentPage === 0">
          이전
        </button>
        <button
          @click="fetchData(currentPage + 1)"
          :disabled="currentPage >= totalPage - 1"
        >
          다음
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import SideBar from '@/views/SideBar.vue'
import axios from '@/axios'
import { useRouter } from 'vue-router'

export default {
  name: 'PartnerReserveList',
  components: {
    SideBar,
  },
  data() {
    return {
      items: [],
      searchQuery: '',
      currentPage: 0,
      pageSize: 10,
      selectedPageCount: 10,
      pageCounts: [10, 20, 30, 50],
      totalPage: 0,
    }
  },
  methods: {
    handlePageCountChange() {
      this.pageSize = this.selectedPageCount
      this.fetchData(0)
    },
    async fetchData(page) {
      if (page < 0) return
      try {
        const response = await axios.get('/api/partner/items', {
          params: {
            page: page,
            size: this.pageSize,
            search: this.searchQuery,
          },
        })
        this.items = response.data.content
        this.currentPage = page
        this.totalPage = response.data.totalPages
      } catch (err) {
        console.error(err)
      }
    },
  },
  mounted() {
    this.fetchData(0)
  },
  setup() {
    const router = useRouter()
    function touchUpInsideTableCell(res_id) {
      router.push({
        name: 'PartnerReserveDetail',
        params: {
          res_id,
        },
      })
    }

    return {
      touchUpInsideTableCell,
    }
  },
}
</script>

<style scoped>
.place_list_tableview {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.place_list_tableview select,
.place_list_tableview input {
  margin-right: 10px;
  padding: 5px;
  border: 1px solid #ced4da;
  border-radius: 4px;
}

.table {
  width: 100%;
  margin-top: 20px;
  border-collapse: collapse;
  text-align: left;
}

.table th,
.table td {
  padding: 12px 15px;
  border-bottom: 1px solid #ddd;
}

.table th {
  background-color: #f8f9fa;
  color: #343a40;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination button {
  padding: 10px 20px;
  margin: 0 5px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.pagination button:disabled {
  background-color: #6c757d;
  cursor: not-allowed;
}

.pagination button:hover:not(:disabled) {
  background-color: #0056b3;
}
</style>
