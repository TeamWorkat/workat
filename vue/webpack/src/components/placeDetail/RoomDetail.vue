<template>
  <div class="top-section">
    <div>
    <div 
      v-for="room in roomList" 
      :key="room.roomVO.room_id" 
      class="room-item"
      :class="{ 'selected': selectedRoomId === room.roomVO.room_id }"
    >
      <div class="room-content">
        <div v-if="room.room_picture_source.length > 0" class="room-images">
          <PictureSlide :pictureList="room.room_picture_source" />
        </div>
        <div class="room-details">
          <h3>{{ room.roomVO.room_name }}</h3>
          <p><strong>가격:</strong> {{ room.roomVO.room_price }}원</p>
          <p><strong>수용인원:</strong> {{ room.roomVO.max_people }}명</p>
          <p><strong>설명:</strong> {{ room.roomVO.room_content }}</p>
          <button @click="selectRoom(room.roomVO.room_id)">선택하기</button>
        </div>
      </div>
    </div>
  </div>
    <div class="reservation-calendar">
      <ReservationCalendar :selectedRoom="selectedRoom" :placeInfo="placeInfo" />
    </div>
  </div>
</template>

<script>
import ReservationCalendar from './ReservationCalendar.vue';
import PictureSlide from '../myPage/PictureSlide.vue';

export default {
  components: {
    ReservationCalendar,
    PictureSlide
  },
  props: {
    roomList: {
      type: Array,
      required: true,
    },
    placeInfo:{
      type: Object,
      required: true
    }
  },
  data() {
    return {
      selectedRoomId: null,
    };
  },
  computed: {
  selectedRoom() {
    const room = this.roomList.find(room => room.roomVO.room_id === this.selectedRoomId);

    if (room) {
      const reservedDates = room.reserved_date.map(dateStr => new Date(dateStr));
      return {
        roomVO: room.roomVO,
        reserved_date: reservedDates,
        room_picture_source: room.room_picture_source || []
      };
    }
    return null;
  }
},
  methods: {
    selectRoom(roomId) {
      this.selectedRoomId = roomId;
    },
  },
};
</script>

<style scoped>
.room-list {
  display: flex;
  flex-direction: row;
  overflow-x: auto;
}

.top-section {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    grid-column: span 2;
  }
  .room-content {
  display: flex;
  align-items: center; 
}

.room-item {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 10px;
  margin: 5px 10px; 
  flex: 1;
}
.room-details {
  white-space: nowrap;
  margin-right: 50px;
  margin-left: 50px; 
}

.room-item.selected {
  border: 2px solid #007bff;
}

.room-images img {
  width: 100%;
  height: auto;
  margin-bottom: 10px;
}

.reservation-calendar {
  margin-top: 20px;
}
</style>
