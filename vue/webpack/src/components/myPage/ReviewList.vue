<template>  
  <div class="review_list">
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="row row-cols-1">
          <div class="col" v-for="(item, idx) in items" :key="idx">
            <ReviewCard :item="item" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import ReviewCard from './ReviewCard.vue';

const items = ref([]);

onMounted(() => {
   fetchReviews();
  });

const fetchReviews = async () => {
  await axios.get(`/api/review/list`)
      .then(res => {
        items.value = res.data;
      })
      .catch(error => {
        console.error("There was an error fetching the place details:", error);
      });
};

</script>

<style>
</style>