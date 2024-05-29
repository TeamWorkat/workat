<template>
    <div class="place-details">
      <div class="place-info">
        <h2>{{ placeInfo.place_nm }}</h2>
        <div v-for="(placePictureSource, idx) in placePictureSources" :key="idx" class="place-image-container">
          <img :src="placePictureSource" class="place-image" />  
        </div>    
        <p><strong>카테고리:</strong> {{ placeInfo.category }}</p>
        <p><strong>주소:</strong> {{ placeInfo.place_addr }}</p>
        <p><strong>전화번호:</strong> {{ placeInfo.place_tel }}</p>
        <p><strong>소개:</strong> {{ placeInfo.place_content }}</p>
      </div>
    </div>
  </template>
  
  <script>
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
    text-align: center;
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
  