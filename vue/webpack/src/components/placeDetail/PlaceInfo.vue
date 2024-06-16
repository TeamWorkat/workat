<template>
    <div class="place-details">
      <div class="place-info">
        <h2><strong>{{ placeInfo.place_nm }}</strong></h2>
        <p>체크인:{{ formatDate(placeInfo.place_in) }} | 체크아웃:{{ formatDate(placeInfo.place_out) }}</p>
        <div v-if="placePictureSources.length > 0" class="room-info" style="margin-top: 50px;">
        <PictureSlide :pictureList="placePictureSources" />
      <br>
      </div>  
        <p><strong>연락처:</strong> {{ placeInfo.place_tel }}</p>
        <p><strong>소개:</strong></p>
        <p> {{ placeInfo.place_content }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import { format } from 'date-fns';
  import PictureSlide from '../myPage/PictureSlide.vue';
  export default {
  props: {
    placePictureSources: {
      type: Array,
      required: true
    },
    placeInfo: {
      type: Object,
      required: true
    }
  },
  components: {PictureSlide},
  methods: {
    formatDate(date) {
      return format(new Date(date), 'HH:mm');
    }
  }
};

  </script>
  
  <style>
  .place-details {
    padding: 20px;
    border-radius: 10px;
  }
  
  .place-info {
    text-align: left;
  }
  
  .place-image-container {
    margin: 10px 0;
  }
  
  .place-image {
    max-width: 100%;
    height: auto;
    border-radius: 5px;
    display: block;
    margin: 0 auto;
  }
  
  .place-info h2 {
    margin-top: 20px;
  }
  
  .place-info p {
    margin: 5px 0;
  }
  </style>
  