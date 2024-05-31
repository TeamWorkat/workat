<template>
  <div class="signup-container">
    <h4>회원 가입</h4>
    <label>이메일:</label>
    <input v-model="user.user_email" type="email" required="required">

    <label>비밀번호:</label>
    <input v-model="user_pwd" type="password" required="required">

    <label>비밀번호 확인:</label>
    <input v-model="confirmPassword" type="password" required="required">

    <label>이름:</label>
    <input v-model="user.user_nm" type="text" required="required">

    <label>전화번호:</label>
    <input v-model="user.user_tel" type="text" required="required">

    <button @click="signup">회원 가입</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'SignupForm',
  data() {
    return {
      user: {
        user_email: '',
        user_nm: '',
        user_tel: ''
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
        await axios.post('/api/user/signup', {
          ...this.user,
          user_pwd: this.user_pwd
        });
        alert('회원 가입이 완료되었습니다.');
        this.$router.push('/');
      } catch (error) {
        console.error('Error signing up:', error);
      }
    }
  }
};
</script>

<style scoped>
.signup-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.signup-container label {
  display: block;
  margin-top: 10px;
}

.signup-container input {
  display: block;
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  border: none;
  color: white;
  cursor: pointer;
  border-radius: 4px;
  margin-top: 10px;
}
</style>
