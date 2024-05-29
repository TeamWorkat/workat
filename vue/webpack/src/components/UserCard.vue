<template>
  <div class="card shadow-sm" @click="goDetailPage(item.place_id)">
    <span
      class="img"
      :style="{ backgroundImage: `url(${item.picture_source})` }"
    />
    <div class="card-body">
      <p class="card-text">{{ item.place_nm }}</p>
      <div class="d-flex justify-content-between align-items-center">
        <!-- <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div> -->

        <small class="text-muted"> 설명 </small>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "UserCard",
  props: {
    item: Object
  },
   methods: {
    goDetailPage(placeId) {
      this.$router.push({ name: 'PlaceDetail', params: { placeId } });
      console.log(placeId);
    },
  },

}
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}
</style>
