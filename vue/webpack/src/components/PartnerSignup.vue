<template>

        <form @submit.prevent="signup">
          <input v-model="user.user_email" type="email" required placeholder="이메일">
          <input v-model="user_pwd" type="password" required placeholder="비밀번호">
          <input v-model="confirmPassword" type="password" required placeholder="비밀번호 확인">
          <input v-model="user.user_nm" type="text" required placeholder="이름">
          <input v-model="user.user_tel" type="text" required placeholder="전화번호">
          <button type="submit">회원 가입</button>
        </form>

</template>

<script>
import axios from '@/axios';

export default {
  name: 'SignupForm',
  data() {
    return {
      user: {
        user_email: '',
        user_nm: '',
        user_tel: '',
        role: 'PARTNER'
      },
      user_pwd: '',
      confirmPassword: ''
    };
  },
  methods: {
    async signup() {
      if (this.user_pwd !== this.confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      try {
        await axios.post('/api/signup', {
          ...this.user,
          user_pwd: this.user_pwd
        });
        alert('회원 가입이 완료되었습니다.');
        this.$router.push('/partner/home');
      } catch (error) {
        console.error('Error signing up:', error);
      }
    }
  }
};
</script>

<style scoped>
.signup-container input {
  display: block;
  width: 300px;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button[type="submit"] {
  padding: 10px 20px;
  background-color: #436DBE;
  border: none;
  color: black;
  cursor: pointer;
  border-radius: 4px;
  margin-top: 10px;
}
</style>
