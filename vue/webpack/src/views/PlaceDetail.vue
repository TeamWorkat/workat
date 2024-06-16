<template>
  <div class="container" v-if="items">
  <div class="row">
    <div class="col-md-12">
      <PlaceInfo :placeInfo="items.placeVO" :placePictureSources="items.place_picture_source" />
    </div>
    <div class="col-md-12 naver-map">
      <h3>객실 정보</h3>
      <br>
      <RoomDetail :roomList="items.roomList" :placeInfo="items.placeVO" />
    </div>
</div>
    <div class="row" style="margin-top: 50px;">
      <div class="col-md-12">
        <div class=" room-detail-container">
        <h3>숙소 위치</h3> 
        </div>   
        <div>
        <NaverMap :roadAddr="items.roadAddr" :jibunAddr="items.jibunAddr" :lat="items.latitude" :lon="items.longitude" style="margin-top: 50px; margin-bottom: 20px; border:1px solid gainsboro; border-radius: 15px;" />
        </div>
      </div>
      <p>{{ items.jibunAddr }}</p>
    </div>
    <div class="row">
      <div class="col-md-12 star-point">
        <h3>이용 후기</h3>
        <StarPoint :rating="items.rating" :starPoints="items.star_points" style="margin-top: 70px;"/>
    </div>
    </div>
        <div class="row">
          <div class="col-md-12">
            <div class=" review-list">
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
.naver-map{
  margin-top: 50px;
}
.star-point{
  margin-top: 50px;
}
.review-list{
  margin-top: 50px;
}
  </style>