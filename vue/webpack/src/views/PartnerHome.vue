<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      
    </div>
  </div>
</template>

<script>
import SideBar from '@/views/SideBar.vue'

export default {
  name: 'PartnerHome',
  components: {
    SideBar,
  },
  methods: {
    
  },
  setup() {
    // 숙소 추가 버튼 클릭시

    return
  },
}
</script>

<style scoped>
.flex-grow-1 {
  display: flex;
  flex-direction: column;
}

.addButton{
    width: 10%;
    height: 5%;
}
</style>
