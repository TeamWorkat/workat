<template>
  <div
    class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark sidebar"
    style="width: 280px"
  >
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="/mypage/resList" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#home"></use>
          </svg>
          예약 내역
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#speedometer2"></use>
          </svg>
          찜한 숙소
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#table"></use>
          </svg>
          프로필 관리
        </a>
      </li>
      <li>
        <a href="/mypage/reviewList" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#grid"></use>
          </svg>
          여행 후기
        </a>
      </li>
    </ul>
  </div>
</template>

<script>


export default {
  name:'SideBar',
    setup() {
        
    },
}
</script>

<style scoped>
/* sidebar */
.sidebar {
  height: 100vh;
}

.nav-link:hover {
  background: #5682da;
  color: rgb(0, 0, 0);
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}

.text-bg-dark {
  background-color: #777373 !important;
}

</style>
