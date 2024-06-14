<template>
  <UserHeader/>
  <RouterView/>
  <UserFooter/>
</template>

<script>
import UserHeader from './components/UserHeader.vue';
import UserFooter from './components/UserFooter.vue';
import store from "@/scripts/store";

export default {
  name: 'App',
  components: {
    UserHeader,
    UserFooter
  },
  created() {
    const user_id = sessionStorage.getItem("user_id");
    if (user_id) {
      store.commit("setAccount", user_id);
    }
  }
}
</script>
