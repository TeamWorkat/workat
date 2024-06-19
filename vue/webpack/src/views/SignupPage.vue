<template>
  <div class="signup-page">
    <div class="signup-content">
      <div class="signup-image">
        <img src="/img/Signup.gif" alt="Signup Image">
      </div>
      <div class="signup-container" :style="{ backgroundColor: selectedAccountType === 'user' ? '#FFF3DA' : '#BFCEEC' }">
        <h4>회원 가입</h4><br/>
        <div class="account-type-selection">
          <button @click="selectedAccountType = 'user'" :style="{ backgroundColor: selectedAccountType === 'user' ? '#FFCC5E' : '' }">개인 회원</button>
          <button @click="selectedAccountType = 'partner'" :style="{ backgroundColor: selectedAccountType === 'partner' ? '#436DBE' : '' }">기업 회원</button>
        </div>
        <component :is="signupComponent" />
      </div>
    </div>
  </div>
</template>

<script>
import UserSignUp from '@/components/UserSignup.vue';
import PartnerSignUp from '@/components/PartnerSignup.vue';

export default {
  name: 'SignupForm',
  components: {
    UserSignUp,
    PartnerSignUp,
  },
  data() {
    return {
      selectedAccountType: 'user',
    };
  },
  computed: {
    signupComponent() {
      return this.selectedAccountType === 'user' ? 'UserSignUp' : 'PartnerSignUp';
    },
  },
};
</script>

<style scoped>
.signup-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 200vh;
  padding-top: 20px;
}

.signup-content {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 100%;
  max-width: 1200px;
}

.signup-image {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.signup-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  max-width: 600px;
  padding: 20px;
  margin-left: 20px;
}

.signup-image img {
  max-width: 80%;
  height: auto;
  object-fit: cover;
}

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
