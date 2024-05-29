<script setup>
import { defineProps } from 'vue';
import { NaverMap } from 'vue3-naver-maps'
import { NaverMarker } from 'vue3-naver-maps'
const props = defineProps(['lat', 'lon']);
const mapOptions = {
  latitude: props.lat, // 지도 중앙 위도
  longitude: props.lon, // 지도 중앙 경도
  zoom: 19,
}
</script>

<template>
  <naver-map
    style="width: 100%; height: 400px"
    :map-options="mapOptions"
  >
    <naver-marker
      :latitude="props.lat"
      :longitude="props.lon"
    />
  </naver-map>
</template>

<style lang="scss" scoped></style>