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
        <div class="room-detail-container">
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
        <StarPoint :rating="items.rating" :starPoints="items.star_points" style="margin-top: 70px;" />
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="review-list">
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
.naver-map {
  margin-top: 50px;
}
.star-point {
  margin-top: 50px;
}
.review-list {
  margin-top: 50px;
}

/* PictureSlide 컴포넌트 내의 스타일을 오버라이드 */
::v-deep .carousel__item {
  min-height: 200px;
  height: 100%;
  width: 100%;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

::v-deep .carousel__item img {
  max-width: 100%;
  max-height: 100%;
  border-radius: 8px;
  object-fit: cover; /* 이미지가 컨테이너에 맞게 조정되도록 설정 */
}

::v-deep .carousel__prev {
  background-color: rgba(254, 220, 0, 0.576); /* 원하는 배경색으로 변경 */
  color: #fff; /* 버튼 텍스트 색상 설정 */
  border: none; /* 테두리 제거 */
  padding: 5px 5px; /* 적절한 패딩 설정 */
  border-radius: 10px; /* 둥근 테두리 설정 */
  cursor: pointer; /* 마우스 커서 설정 */
}

::v-deep .carousel__prev:hover {
  background-color: #ffdd00; /* 호버 상태에서 배경색 변경 */
}

::v-deep .carousel__next {
  background-color: rgba(254, 220, 0, 0.576); /* 원하는 배경색으로 변경 */
  color: #fff; /* 버튼 텍스트 색상 설정 */
  border: none; /* 테두리 제거 */
  padding: 5px 5px; /* 적절한 패딩 설정 */
  border-radius: 10px; /* 둥근 테두리 설정 */
  cursor: pointer; /* 마우스 커서 설정 */
}

::v-deep .carousel__next:hover {
  background-color: #ffdd00; /* 호버 상태에서 배경색 변경 */
}
</style>
