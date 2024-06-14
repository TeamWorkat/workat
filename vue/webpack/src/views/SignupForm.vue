<template>
  <div class="signup-page">
    <div class="signup-content">
      <div class="signup-image">
        <img src="/img/work_at_icon.png" alt="Signup Image">
      </div>
      <div class="signup-container">
        <h4>회원 가입</h4><br/>
        <form @submit.prevent="signup">
          <input v-model="user.user_email" type="email" required placeholder="이메일">
          <input v-model="user_pwd" type="password" required placeholder="비밀번호">
          <input v-model="confirmPassword" type="password" required placeholder="비밀번호 확인">
          <input v-model="user.user_nm" type="text" required placeholder="이름">
          <input v-model="user.user_tel" type="text" required placeholder="전화번호">
          <button type="submit">회원 가입</button>
        </form>
      </div>
    </div>
  </div>
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
        await axios.post('/api/signup', {
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
.signup-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
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

.signup-image img {
  max-width: 80%;
  height: auto;
  object-fit: cover;
}

.signup-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  max-width: 540px;
  padding: 20px;
  background-color: #FFF3DA;
  margin-left: 20px;
}

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
  background-color: #FFCC5E;
  border: none;
  color: black;
  cursor: pointer;
  border-radius: 4px;
  margin-top: 10px;
}
</style>
