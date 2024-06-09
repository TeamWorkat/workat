<template>
  <Carousel>
    <Slide v-for="(imageUrl, index) in props.pictureList" :key="index">
      <div class="carousel__item">
        <img :src="imageUrl" alt="Room Image" class="room-image">
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
import { defineProps } from "vue";
const props = defineProps({
  pictureList: Array
});

</script>

<style>
.carousel__item {
  min-height: 200px;
  width: 100%;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__item img {
  max-width: 100%;
  max-height: 100%;
  border-radius: 8px;
}
</style>
