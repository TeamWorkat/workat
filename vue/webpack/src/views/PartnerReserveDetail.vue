<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
        <p>예약번호: {{ items.res_id }}</p>
        <p>예약자명: {{ items.res_name }}</p>
        <p>전화번호: {{ items.user_tel }}</p>
        <p>취소여부: {{ items.res_cancel_yn }}</p>
        <p>예약날짜(체크인): {{ items.check_in }}</p>
        <p>예약날짜(체크아웃): {{ items.check_out }}</p>
        <p>요청사항: {{ items.res_message }}</p>
        <p>상품: {{ items.room_name }}</p>
        <p>상품: {{ items.paymentKey }}</p>
        <button @click="cancelReserveTouchUpInside"> 예약 취소 </button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
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
<style></style>
