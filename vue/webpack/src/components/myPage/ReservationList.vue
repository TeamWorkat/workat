<template>
  <div class="d-flex">
    <div class="flex-grow-1 p-3">
      <div class="place_list_tableview">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">예약 번호</th>
              <th scope="col">숙소 명</th>
              <th scope="col">체크인</th>
              <th scope="col">체크아웃</th>
              <th scope="col">예약 상태</th>
              <th scope="col">예약 옵션</th>
              <th scope="col">예약 인원</th>
              <th scope="col">예약자명</th>
              <th scope="col">결제 금액</th>
              <th scope="col">예약 일시</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item) in displayItem" :key="item.res_id" @click="touchUpInsideTableCell(item.res_id)">
              <td>{{ item.res_id }}</td>
              <td>{{ item.place_nm }}</td>
              <td>{{ item.startDate }}</td>
              <td>{{ item.endDate }}</td>
              <td>{{ item.res_cancel_yn }}</td>
              <td>{{ item.room_name }}</td>
              <td>{{ item.res_people_num }}</td>
              <td>{{ item.res_name }}</td>
              <td>{{ item.amount }}</td>
              <td>{{ item.createdAt }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="pagination">
          <button class="page-btn" @click="currentPage = Math.max(currentPage - 1, 1)">&lt;</button>
          <button class="page-btn" v-for="page in totalPages" :key="page" @click="goToPage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
          <button class="page-btn" @click="currentPage = Math.min(currentPage + 1, totalPages)">&gt;</button>
        </div>
    </div>
  </div>
</template>

<script setup>
import axios from '@/axios.js';
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const items = ref([]);
const currentPage = ref(1);

const totalPages = computed(() => {
  return Math.ceil(items.value.length / 15);
});

const displayItem = computed(() => {
  const startIndex = (currentPage.value - 1) * 15;
  const endIndex = startIndex + 15;
  return items.value.slice(startIndex, endIndex);
});

onMounted(() => {
  fetchReservations();
});

const fetchReservations = async () => {
  try {
    const res = await axios.get('/api/reserve/list');
    items.value = res.data;
  } catch (error) {
    console.error("예약 정보를 가져오는 중 오류가 발생했습니다:", error);
  }
};

const touchUpInsideTableCell = (resId) => {
  router.push({ name: 'ReservationDetail', params: { reservation_id: resId } });
};

const goToPage = (page) => {
  currentPage.value = page;
};
</script>

<style>
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem;
  gap: 0.5rem;
}

.page-btn {
  background-color: #FFEFD5; /* 연한 노랑 */
  border: 1px solid #FFD700; /* 연한 노랑보다 조금 더 진한 노랑 */
  border-radius: 50%;
  padding: 0.5rem 0.75rem;
  font-size: 1rem;
  color: #FFA500; /* 연한 주황색 */
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
}

.page-btn:hover {
  background-color: #FFD700; /* 연한 노랑보다 조금 더 진한 노랑 */
  transform: translateY(-2px);
}

.page-btn.active {
  background-color: #FFD700; /* 연한 노랑보다 조금 더 진한 노랑 */
  font-weight: bold;
  color: white;
}

.page-btn:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(255, 215, 0, 0.5); /* 연한 주황색 */
}
</style>

