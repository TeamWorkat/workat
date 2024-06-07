<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div v-if="items">
        <p>숙소명: {{ items.place_nm }}</p>
        <p>전화번호: {{ items.place_tel }}</p>
        <p>카테고리: {{ items.category }}</p>
        <p>주소: {{ items.place_addr }}</p>
        <p>소개글: {{ items.place_content }}</p>
        <p>체크인: {{ items.place_in }}</p>
        <p>체크아웃: {{ items.place_out }}</p>
        <p>지역: {{ items.loc_nm }}</p>
        <button @click="updatePlaceTouchUpInside"> 수정 </button>
        <button @click="deletePlaceTouchUpInside"> 삭제 </button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerPlaceDetail',
  components: {
    SideBar,
  },

  data() {
    return {
      items: null,
    }
  },

  computed: {
    placeId() {
      return this.$route.params.placeid
    },
  },
  created() {
    console.log(this.$route.params.placeid)
    this.fetchPlaceInfo(this.placeId)
  },
  methods: {
    fetchPlaceInfo(placeId) {
      axios
        .get('/api/partner/placeDetail', {
          params: {
            placeid: placeId,
          },
        })
        .then((res) => {
          this.items = res.data
          console.log(res.data)
        })
        .catch((err) => {
          console.error(err)
        })
    },
    updatePlaceTouchUpInside(){
      this.$router.push({ name: 'PartnerPlaceUpdate',params:{
                    placeid: this.placeId
                } })
    },
    deletePlaceTouchUpInside(){
      axios
      .get('/api/partner/placeDelete',{
        params:{
          placeid: this.placeId
        }
      })
      .then((res) => {
        alert('삭제 되었습니다.');
        window.location.href = 'http://localhost:8090/partner/placelist';
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
