<template>
  <div class="card-horizontal">
    <div class="card-border">
      <div class="card-body">
        <div class="content-wrapper">
          <div class="content-left">
            <h5 class="card-subtitle mb-2 text-muted">{{ props.item.place_nm }} ({{ props.item.placeCategory }})</h5>
            <h6 class="card-title">{{ props.item.user_nm }}</h6>
            <p class="card-text">별점: {{ props.item.rating }}</p>
            <p class="card-text">리뷰 작성일: {{ formatDate(props.item.reviewCreatedDate) }}</p>
            <p class="card-text">예약 날짜: {{ formatDate(props.item.startDate) }} ~ {{ formatDate(props.item.endDate) }}</p>
          </div>
          <div class="content-right">
            <div v-if="props.item.picture_sources && props.item.picture_sources.length">
              <div v-for="(picture, index) in props.item.picture_sources" :key="index">
                <img :src="picture" alt="Review Picture" class="review-picture" >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { format } from 'date-fns';

const props = defineProps({
  item: {
    type: Object,
    required: true,
    default: null
  }
});

const formatDate = (date) => {
  return format(new Date(date), 'yyyy-MM-dd HH:mm:ss');
};
</script>

<style scoped>
.card-horizontal {
  display: flex;
  width: 100%;
  flex-grow: 1;
}

.card-border {
  border: 1px solid #ccc;
  width: 100%; /* 추가 */
  border-radius: 15px; 
  margin-right: 10px; 
  flex: 1; 
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); 
}

.card-body {
  display: flex;
  padding: 15px;
  white-space: nowrap;
}

.content-wrapper {
  display: flex;
  flex: 1; 
}

.content-left {
  flex: 1;
}

.content-right {
  margin-left: 650px;
  margin-right: 20px; 
  display: flex;
  align-items: center;
}

.review-picture {
  max-width: 200px; 
  max-height: 200px;
  border-radius: 10px; 
  object-fit: cover;
}
</style>

