<template>
  <div class="container">
    <div class="top-section">
      <PlaceInfo :placeInfo="state.items.placeVO" :placePictureSources="state.items.place_picture_source" />
      <NaverMap :roadAddr="state.items.roadAddr" :jibunAddr="state.items.jibunAddr" :lat="state.items.latitude" :lon="state.items.longitude" />
    </div>
    <h3>객실 정보</h3>
    <div class="bottom-section">
      <RoomDetail :roomList="state.items.roomList" />
    </div>
    <h3>이용 후기</h3>
    <div class="review-section">
      <ReviewDetail :reviewList="state.items.reviewList" :rating="state.items.rating" />
    </div>
  </div>
</template>
  
  <script>
  import NaverMap from "@/components/placeDetail/NaverMap.vue";
  import PlaceInfo from "@/components/placeDetail/PlaceInfo.vue"; 
  import ReviewDetail from "@/components/placeDetail/ReviewDetail.vue"; 
  import RoomDetail from "@/components/placeDetail/RoomDetail.vue"; 
  import axios from 'axios';
  import { reactive } from 'vue'

  export default {
    components: {
      NaverMap,
      PlaceInfo,
      ReviewDetail,
      RoomDetail
    },
    setup() {
    const state = reactive({
      items: [],
    })

    axios.get('/api/place/placeDetail?place_id=1').then((res) => {
      state.items = res.data
      console.log(res)
    })

    return { state }
  }
  };
  </script>
  
  <style scoped>
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    max-width: 1200px;
    margin: 20px auto;
    padding: 20px;
  }
  
  .top-section {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    grid-column: span 2;
  }
  
  .bottom-section {
    grid-column: span 2;
  }
  
  .review-section {
    grid-column: span 2;
  }
  
  h1 {
    text-align: center;
    grid-column: span 2;
  }
  </style>