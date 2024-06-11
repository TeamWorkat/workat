<template>
    <div class="place-details">
      <div class="place-info">
        <h2>{{ placeInfo.place_nm }}</h2>
        <p>체크인:{{ formatDate(placeInfo.place_in) }} | 체크아웃:{{ formatDate(placeInfo.place_out) }}</p>
        <div v-for="(placePictureSource, idx) in placePictureSources" :key="idx" class="place-image-container">
          <img :src="placePictureSource" class="place-image" />  
        </div>    
        <p><strong>주소:</strong> {{ placeInfo.place_addr }}</p>
        <p><strong>전화번호:</strong> {{ placeInfo.place_tel }}</p>
        <p><strong>소개글:</strong></p>
        <p> {{ placeInfo.place_content }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import { format } from 'date-fns';

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
    background-color: #f5f5f5;
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
  