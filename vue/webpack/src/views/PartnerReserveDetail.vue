<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items" class="reservation-details">
        <p><strong>예약번호:</strong> {{ items.res_id }}</p>
        <p><strong>예약자명:</strong> {{ items.res_name }}</p>
        <p><strong>전화번호:</strong> {{ items.user_tel }}</p>
        <p><strong>취소여부:</strong> {{ items.res_cancel_yn }}</p>
        <p><strong>예약날짜(체크인):</strong> {{ items.check_in }}</p>
        <p><strong>예약날짜(체크아웃):</strong> {{ items.check_out }}</p>
        <p><strong>요청사항:</strong> {{ items.res_message }}</p>
        <p><strong>상품:</strong> {{ items.room_name }}</p>
        <p><strong>결제 키:</strong> {{ items.paymentKey }}</p>
        <button class="cancel-button" @click="cancelReserveTouchUpInside">예약 취소</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerReserveDetail',
  components: {
    SideBar,
  },

  data() {
    return {
      items: null,
      paymentKey: ''
    }
  },

  computed: {
    res_id() {
      return this.$route.params.res_id
    },
  },
  created() {
    console.log(this.$route.params.res_id)
    this.fetchPlaceInfo(this.res_id)
  },
  methods: {
    fetchPlaceInfo(res_id) {
      axios
        .get('/api/partner/reserveDetail', {
          params: {
            res_id: res_id,
          },
        })
        .then((res) => {
          this.items = res.data
          this.paymentKey = this.items.paymentKey
          console.log(res.data)
        })
        .catch((err) => {
          console.error(err)
        })
    },
    cancelReserveTouchUpInside(){
      axios
      .post('/api/payment/toss/cancel',null,{
        params:{
          paymentKey: this.paymentKey,
          cancelReason: '사장'
        } 
      })
      .then((res) => {
        alert('취소 되었습니다.');
        window.location.href = 'http://localhost:8090/partner/reservelist';
        console.log(res)
      })
      .catch((err) =>{
        alert('실패하였습니다.' + err);
        console.log(err)
      })
    }
  },
}
</script>

<style scoped>
.reservation-details {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.reservation-details p {
  margin: 10px 0;
  font-size: 16px;
  line-height: 1.5;
}

.reservation-details strong {
  font-weight: 600;
  color: #343a40;
}

.cancel-button {
  display: inline-block;
  padding: 10px 20px;
  margin-top: 20px;
  border: none;
  border-radius: 4px;
  background-color: #dc3545;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.cancel-button:hover {
  background-color: #c82333;
}
</style>
