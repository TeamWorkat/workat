<template>
  <div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-between justify-content-md-between py-0 border-bottom header-border">
      <div class="col-md-3 mb-2 mb-md-0 d-flex align-items-center">
        <router-link to="/partner/home" class="d-inline-flex link-body-emphasis text-decoration-none">
          <img src="/img/workat_biz_icon.png" alt="Bootstrap" class="header-logo" />
        </router-link>
      </div>

      <div class="col-md-3 text-end d-flex align-items-center justify-content-end">
        <template v-if="!token">
          <button type="button" class="btn btn-outline-primary me-2" @click="clickSigninButton">Sign In</button>
          <button type="button" class="btn btn-primary" @click="clickSignupButton">Sign Up</button>
        </template>
        <template v-else>
          <button type="button" class="btn btn-primary" @click="clickLogoutButton">Log Out</button>
        </template>
      </div>
    </header>
  </div>
</template>

<script>
import store from "@/scripts/store";

export default {
  name: 'PartnerHeader',
  data() {
    return {
      token: store.state.account.token || null,
    }
  },
  methods: {
    clickSigninButton() {
      this.$router.push({ name: 'LoginPage' });
    },
    clickSignupButton() {
      this.$router.push({ name: 'SignupPage' });
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
    this.token = store.state.account.token;
  }
}
</script>

<style scoped>
button {
  background-color: #436DBE;
  border: none;
  color: black;
  cursor: pointer;
  border-radius: 4px;
}

.header-logo {
  width: 45%;
  height: auto;
  margin: 0;
}

.header-border {
  width: 100%;
  border-bottom: 1px solid #000;
}
</style>
