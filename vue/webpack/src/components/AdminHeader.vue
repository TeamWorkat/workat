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
              height="80px"
          />
        </a>
      </div>
        <button
            type="button"
            class="btn btn-primary"
            v-if="!token"
            @click="clickSignupButton"
            style="margin-left: 10px;"
        >
          Sign Up
        </button>
        <button
            type="button"
            class="btn btn-primary"
            v-else
            @click="clickLogoutButton"
            style="margin-left: 10px;"
        >
          Log Out
        </button>
    </header>
  </div>
</template>

<script>
import store from "@/scripts/store";

export default {
  name: 'AdminHeader',
  data() {
    return {
      activeMenu: null,
      token: store.state.account.token || null,
    }
  },
  methods: {
    clickSignupButton() {
      this.$router.push({ name: 'SignupPage' });
    },
    clickLogoutButton() {
      store.commit('clearAccount');
      sessionStorage.removeItem("user_id");
      sessionStorage.removeItem("token");
      this.token = null;
      this.$router.push({ name: 'UserHome' }).then(() => {
      window.location.reload();
    });
    }
  },
  watch: {
    '$store.state.account.token'(newToken) {
      this.token = newToken;
    }
  },
  mounted() {
    this.token = store.state.account.token;
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
  border-bottom: 2px solid #000;
}
</style>
