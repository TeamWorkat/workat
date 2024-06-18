<template>
  <Carousel>
    <Slide v-for="(imageUrl, index) in props.pictureList" :key="index">
      <div class="carousel__item">
        <img :src="imageUrl" alt="Room Image" class="room-image" />
      </div>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
  </Carousel>
</template>

<script setup>
import { Carousel, Navigation, Pagination, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import { defineProps } from 'vue'
const props = defineProps({
  pictureList: Array,
})
</script>

<style>
.carousel__item {
  height: 250px; /* 높이를 고정 */
  width: 100%;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden; /* 이미지가 컨테이너를 벗어나지 않도록 설정 */
}

.carousel__item img {
  width: 100%;
  height: 100%;
  border-radius: 8px;
  object-fit: cover; /* 이미지가 컨테이너에 맞게 조정되도록 설정 */
}

.carousel__prev {
  background-color: rgba(254, 220, 0, 0.576); /* 원하는 배경색으로 변경 */
  color: #fff; /* 버튼 텍스트 색상 설정 */
  border: none; /* 테두리 제거 */
  padding: 5px 5px; /* 적절한 패딩 설정 */
  border-radius: 10px; /* 둥근 테두리 설정 */
  cursor: pointer; /* 마우스 커서 설정 */
}

.carousel__prev:hover {
  background-color: #ffdd00; /* 호버 상태에서 배경색 변경 */
}

.carousel__next {
  background-color: rgba(254, 220, 0, 0.576); /* 원하는 배경색으로 변경 */
  color: #fff; /* 버튼 텍스트 색상 설정 */
  border: none; /* 테두리 제거 */
  padding: 5px 5px; /* 적절한 패딩 설정 */
  border-radius: 10px; /* 둥근 테두리 설정 */
  cursor: pointer; /* 마우스 커서 설정 */
}

.carousel__next:hover {
  background-color: #ffdd00; /* 호버 상태에서 배경색 변경 */
}
</style>
