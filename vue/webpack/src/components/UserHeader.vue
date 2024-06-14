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
        >호텔</a>
        <a
            href="/stay"
            class="nav-link px-2"
            :class="{ active: activeMenu === 'stay' }"
            @click="setActiveMenu('stay')"
        >스테이</a>
        <a
            href="/camping"
            class="nav-link px-2"
            :class="{ active: activeMenu === 'camping' }"
            @click="setActiveMenu('camping')"
        >캠핑</a>
      </ul>

      <div class="col-md-3 text-end">
        <button
            type="button"
            class="btn btn-outline-primary me-2"
            v-if="!token"
            @click="clickSigninButton"
        >
          Sign In
        </button>
        <button
            type="button"
            class="btn btn-primary"
            v-else
            @click="clickMypageButton"
        >
          My Page
        </button>
        <button
            type="button"
            class="btn btn-primary"
            v-if="!token"
            @click="clickSignupButton"
        >
          Sign Up
        </button>
        <button
            type="button"
            class="btn btn-primary"
            v-else
            @click="clickLogoutButton"
        >
          Log Out
        </button>
      </div>
    </header>
  </div>
</template>

<script>
import store from "@/scripts/store";

export default {
  name: 'UserHeader',
  data() {
    return {
      activeMenu: null,
      token: store.state.account.token || null, // Vuex store에서 토큰 가져오기
    }
  },
  methods: {
    setActiveMenu(menu) {
      this.activeMenu = menu;
    },
    clickSigninButton() {
      this.$router.push({ name: 'LoginForm' });
    },
    clickMypageButton() {
      const user_id = store.state.account.user_id?.toString().trim();
      if (user_id) {
        this.$router.push({ name: 'UserDetail', params: { user_id } });
      } else {
        console.error("User ID is not available.");
      }
    },
    clickSignupButton() {
      this.$router.push({ name: 'SignupForm' });
    },
    clickLogoutButton() {
      store.commit('clearAccount');
      sessionStorage.removeItem("user_id");
      sessionStorage.removeItem("token");
      this.token = null;
      this.$router.push({ name: 'UserHome' });
    }
  },
  watch: {
    '$store.state.account.token'(newToken) {
      this.token = newToken;
    }
  },
  mounted() {
    this.token = store.state.account.token; // 컴포넌트가 마운트될 때 토큰 상태 동기화
  }
}
</script>

<style scoped>
button {
  background-color: #FFCC5E;
  border: none;
  color: black;
  cursor: pointer;
  border-radius: 4px;
  margin-top: 10px;
}
.nav-link.active {
  border-bottom: 2px solid #000; /* 검은색 선 */
}
</style>
