<template>
  <div class="login-page">
    <div class="account-type-selection">
      <button @click="selectedAccountType = 'user'" :style="{ backgroundColor: selectedAccountType === 'user' ? '#FFCC5E' : '' }">개인 회원</button>
      <button @click="selectedAccountType = 'partner'" :style="{ backgroundColor: selectedAccountType === 'partner' ? '#436DBE' : '' }">기업 회원</button>
    </div>

    <template v-if="selectedAccountType === 'user'">
      <UserLogin />
    </template>
    <template v-else-if="selectedAccountType === 'partner'">
      <PartnerLogin />
    </template>
  </div>
</template>

<script>
import UserLogin from '/src/components/UserLogin.vue';
import PartnerLogin from '/src/components/PartnerLogin.vue';

export default {
  components: {
    UserLogin,
    PartnerLogin,
  },
  data() {
    return {
      selectedAccountType: 'user',
    };
  },
};
</script>

<style scoped>
.account-type-selection {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

button {
  margin: 0 10px;
  padding: 10px 20px;
  cursor: pointer;
  border: none;
  color: black;
  border-radius: 4px;
}

button:nth-child(1) {
  background-color: #FFCC5E;
}

button:nth-child(2) {
  background-color: #436DBE;
}
</style>
