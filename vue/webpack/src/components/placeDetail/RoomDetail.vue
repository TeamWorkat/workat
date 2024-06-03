<template>
  <div class="top-section">
    <div class="room-list">
      <div 
        v-for="room in roomList" 
        :key="room.roomVO.room_id" 
        :class="['room-item', { 'selected': selectedRoomId === room.roomVO.room_id }]"
        @click="selectRoom(room.roomVO.room_id)"
      >
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
    <div class="reservation-calendar">
      <ReservationCalendar :selectedRoom="selectedRoom" :placeInfo="placeInfo" />
    </div>
  </div>
</template>

<script>
import ReservationCalendar from './ReservationCalendar.vue';

export default {
  components: {
    ReservationCalendar,
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
        reserved_date: reservedDates  || []
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
  flex-wrap: wrap;
  gap: 20px;
}

.top-section {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    grid-column: span 2;
  }

.room-item {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  width: calc(33.333% - 20px);
  cursor: pointer;
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
