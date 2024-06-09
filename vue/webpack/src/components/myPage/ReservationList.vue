<template>
    <div class="d-flex">
      <div class="flex-grow-1 p-3">
        <div class="place_list_tableview">
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
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
              <tr v-for="(item, index) in items" :key="item.res_id" @click="touchUpInsideTableCell(item.res_id)">
                <th scope="row">{{ index + 1 }}</th>
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
      </div>
    </div>
  </template>
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const items = ref([]);

onMounted(() => {
    fetchReservations();
  });

const fetchReservations = async () => {
  await axios.get(`/api/reserve/list`)
      .then(res => {
        items.value = res.data;
      })
      .catch(error => {
        console.error("There was an error fetching the place details:", error);
      });
};

</script>
<style>
</style>