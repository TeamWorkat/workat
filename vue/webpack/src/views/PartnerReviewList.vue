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
  name: 'PartnerReviewList',
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
    
    return{
      state,
      touchUpInsideTableCell,
    
    }
  },
}
</script>

<style scoped>

</style>
