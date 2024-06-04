<template>
<div class="star-rating">
    <h3>별점</h3>   
    <div class="stars" v-for="index in 5" :key="index">
      <span class="star" v-if="index <= starAvg">🍎</span>
      <span v-else>🍏</span>
    </div>
    <h3>{{starAvg}}</h3>
  </div>
<div>
    <div class="star-point-bar-wrapper">
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

.star-rating-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.star-rating {
    display: flex;
    align-items: center;
  margin-right: 400px; /* 별점과 바 그래프 사이의 간격 조절 */
}

.star-point-bar-wrapper {
  margin-top: 100px; /* StarPointBar 위 여백 추가 */
}
</style>