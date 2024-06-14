<template>
    <!-- SideBar -->
  <div
    class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark sidebar"
    style="width: 280px"
  >
    <a
      href="/partner/home"
      class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none"
    >
      <svg class="bi pe-none me-2" width="40" height="32">
        <use xlink:href="#bootstrap"></use>
      </svg>
      <span class="fs-4">김아무개님</span>
    </a>
    <hr />

    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="/partner/placelist" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#home"></use>
          </svg>
          숙소 정보
        </a>
      </li>
      <li>
        <a href="/partner/reservelist" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#speedometer2"></use>
          </svg>
          예약 정보
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#table"></use>
          </svg>
          객실 관리
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="30">
            <use xlink:href="#grid"></use>
          </svg>
          리뷰 관리
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
  background: #bfceec;
  color: white;
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
  background-color: #869ecc !important;
}

</style>
