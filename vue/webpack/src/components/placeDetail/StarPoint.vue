<template>
  <div class="row">
    <div class="col-md-3  offset-md-2 star-rating">
      <div class="stars" v-for="index in 5" :key="index" style="display: inline-block;">
      <img class="star" v-if="index <= starAvg" src='/img/별점.png' alt="Full Star" style="width: 50px; height: 50px;">
      <img v-else src='/img/빈별점.png' alt="Empty Star" style="width: 50px; height: 50px;">
    </div>
      <h3>{{starAvg}}</h3>
    </div>
    <div class="col-md-6 star-point-bar">
      <StarPointBar :starPoints="props.starPoints"/>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import StarPointBar from "@/components/placeDetail/StarPointBar.vue"; 

const props = defineProps({
  rating: {
    type: Number,
    required: true,
    default: null
  },
  starPoints: {
    type: Object,
    required: true,
    default: null
  }
})

const starAvg = ref(props.rating ? props.rating : 0)
</script>  

<style scoped>
.star-rating{
  margin-top: 40px;
}
</style>