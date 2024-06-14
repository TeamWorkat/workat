<template>
  <div class="container" v-if="items">
  <div class="row">
    <div class="col-md-6">
      <PlaceInfo :placeInfo="items.placeVO" :placePictureSources="items.place_picture_source" />
    </div>
    <div class="col-md-6 naver-map">
      <NaverMap :roadAddr="items.roadAddr" :jibunAddr="items.jibunAddr" :lat="items.latitude" :lon="items.longitude" />
    </div>
</div>
    <div class="row">
      <div class="col-md-12">
        <div class=" room-detail-container">
        <h3>객실 정보</h3> 
        </div>   
        <RoomDetail :roomList="items.roomList" :placeInfo="items.placeVO" />
      </div>
    </div>
    <div class="row">
      <div class="col-md-12 star-point">
        <StarPoint :rating="items.rating" :starPoints="items.star_points"/>
    </div>
    </div>
        <div class="row">
          <div class="col-md-12">
            <div class=" review-list">
        <h3>이용 후기</h3>
        <ReviewDetail :reviewList="items.reviewList" :rating="items.rating" />
      </div>

        </div>
        </div>
        </div>
</template>

  
  <script>
  import NaverMap from "@/components/placeDetail/NaverMap.vue";
  import PlaceInfo from "@/components/placeDetail/PlaceInfo.vue"; 
  import ReviewDetail from "@/components/placeDetail/ReviewDetail.vue"; 
  import RoomDetail from "@/components/placeDetail/RoomDetail.vue"; 
  import StarPoint from "@/components/placeDetail/StarPoint.vue";
  import axios from '@/axios';

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
  .room-detail-container {
  margin-left: 50px;
}
.naver-map{
  margin-top: 100px;
}
.star-point{
  margin-top: 50px;
}
.review-list{
  margin-top: 50px;
}
  </style>