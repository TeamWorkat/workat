<template>
  <div class="container">
    <header
      class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom"
    >
      <div class="col-md-3 mb-2 mb-md-0">
        <a
          href="/"
          class="d-inline-flex link-body-emphasis text-decoration-none"
        >
          <img
            src="/img/work_at_icon.png"
            alt="Bootstrap"
            width="100px"
            height="50px"
          />
        </a>
      </div>

      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <a
          href="/hotel"
          class="nav-link px-2"
          :class="{ active: activeMenu === 'hotel' }"
          @click="setActiveMenu('hotel')"
          >호텔</a
        >
        <a
          href="/stay"
          class="nav-link px-2"
          :class="{ active: activeMenu === 'stay' }"
          @click="setActiveMenu('stay')"
          >스테이</a
        >
        <a
          href="/camping"
          class="nav-link px-2"
          :class="{ active: activeMenu === 'camping' }"
          @click="setActiveMenu('camping')"
          >캠핑</a
        >
      </ul>

      <div class="col-md-3 text-end">
        <button type="button" class="btn btn-outline-primary me-2">
          Sign In
        </button>
        <button type="button" class="btn btn-primary">Sign Up</button>
      </div>
    </header>
  </div>

</template>

<script>
export default {
  name: 'UserHeader',
  data() {
    return {
      activeMenu: null, // 초기값은 선택된 메뉴가 없음을 의미
    }
  },
  methods: {
    setActiveMenu(menu) {
      this.activeMenu = menu
    },
  },
}
</script>

<style scoped>
.nav-link.active {
  border-bottom: 2px solid #000; /* 검은색 선 */
}
</style>
