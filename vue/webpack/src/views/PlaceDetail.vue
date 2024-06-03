<template>
  <div class="container" v-if="items">
    <div class="top-section">
      <PlaceInfo :placeInfo="items.placeVO" :placePictureSources="items.place_picture_source" />
      <NaverMap :roadAddr="items.roadAddr" :jibunAddr="items.jibunAddr" :lat="items.latitude" :lon="items.longitude" />
    </div>
    <h3>객실 정보</h3>
    <div class="bottom-section">
        <RoomDetail :roomList="items.roomList" :placeInfo="items.placeVO"/>
    </div>
    <div class="star-rating">
      <StarPoint :rating="items.rating" :starPoints="items.star_points"/>
    </div>
    <div class="review-section">
      <h3>이용 후기</h3>
      <ReviewDetail :reviewList="items.reviewList" :rating="items.rating" />
    </div>
  </div>
</template>
  
  <script>
  import NaverMap from "@/components/placeDetail/NaverMap.vue";
  import PlaceInfo from "@/components/placeDetail/PlaceInfo.vue"; 
  import ReviewDetail from "@/components/placeDetail/ReviewDetail.vue"; 
  import RoomDetail from "@/components/placeDetail/RoomDetail.vue"; 
  import StarPoint from "@/components/placeDetail/StarPoint.vue"; 
  import axios from 'axios';

  export default {
    components: {
      NaverMap,
      PlaceInfo,
      ReviewDetail,
      RoomDetail,
      StarPoint
    },

    data() {
    return {
      items: null, 
    };
  },

  computed: {
    placeId() {
      return this.$route.params.placeId;
    },
  },
  
  created() {
    console.log(this.$route.params.placeId)
    this.fetchPlaceDetails(this.placeId);
  },

  methods: {
    fetchPlaceDetails(placeId) {
      axios.get(`/api/place/placeDetail?place_id=${placeId}`)
        .then(res => {
          this.items = res.data;
        })
        .catch(error => {
          console.error("There was an error fetching the place details:", error);
        });
    },
  },

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
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    grid-column: span 2;
  }
  
  .review-section {
    grid-column: span 2;
  }
  
  .room-calendar-section {
    display: flex;
    flex-direction: row;
  }
  
  .room-section {
    flex: 1;
  }
  
  .calendar-section {
    flex: 1;
  }

  .star-rating {
  display: flex;
}
  </style>