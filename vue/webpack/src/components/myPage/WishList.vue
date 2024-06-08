<template>  

    <div class="user_home">
      <div class="album py-5 bg-body-tertiary">
        <div class="container">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col" v-for="(item, idx) in items" :key="idx">
              <UserCard :item="item" />
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import UserCard from '@/components/UserCard.vue';

const items = ref([]);

onMounted(() => {
   fetchReviews();
  });

const fetchReviews = async () => {
  await axios.get(`/api/wish/list`)
      .then(res => {
        items.value = res.data;
      })
      .catch(error => {
        console.error("There was an error fetching the place details:", error);
      });
};
</script>

<style scoped></style>