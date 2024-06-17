<template>
  <div>
    <component :is="headerComponent" />
    <router-view />
    <UserFooter />
  </div>
</template>

<script>
import UserHeader from './components/UserHeader.vue';
import PartnerHeader from './components/PartnerHeader.vue';
import UserFooter from './components/UserFooter.vue';
import store from "@/scripts/store";

export default {
  name: 'App',
  components: {
    UserHeader,
    PartnerHeader,
    UserFooter
  },
  data() {
    return {
      headerComponent: 'UserHeader'
    };
  },
  created() {
    const user_id = sessionStorage.getItem("user_id");
    if (user_id) {
      store.commit("setAccount", user_id);
    }

    this.$router.beforeEach((to, from, next) => {
      if (to.path.startsWith('/partner')) {
        this.headerComponent = 'PartnerHeader';
      } else {
        this.headerComponent = 'UserHeader';
      }
      next();
    });
  }
}
</script>
