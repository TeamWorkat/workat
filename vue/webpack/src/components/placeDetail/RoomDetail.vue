<template>
    <div class="room-list">
      <div v-for="room in roomList" :key="room.roomVO.room_id" class="room-item">
        <h3>{{ room.roomVO.room_name }}</h3>
        <p><strong>가격:</strong> {{ room.roomVO.room_price }}원</p>
        <p><strong>수용인원:</strong> {{ room.roomVO.max_people }}명</p>
        <p><strong>설명:</strong> {{ room.roomVO.room_content }}</p>
        <div v-if="room.room_picture_source.length > 0">
          <p><strong>방 사진:</strong></p>
          <div class="room-images">
            <img v-for="(source, index) in room.room_picture_source" :key="index" :src="source" alt="Room Image">
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      roomList: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style>
  .room-list {
    margin-top: 20px;
  }
  
  .room-item {
    margin-bottom: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .room-item h3 {
    margin-top: 0;
  }
  
  .room-item p {
    margin: 5px 0;
  }
  
  .room-images img {
    max-width: 100px;
    margin-right: 10px;
    margin-bottom: 10px;
  }
  </style>
  