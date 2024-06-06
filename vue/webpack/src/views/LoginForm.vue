<template>
  <div class="login-page">
    <img src="/img/work_at_icon.png" class="login-image">
    <div class="login-container">
      <h3>Login</h3><br/>

      <input id="email" v-model="user_email" type="email" placeholder="email" required>
      <input id="password" v-model="user_pwd" type="password" placeholder="password" required>
      <a href="#" class="forgot-password">forgot password?</a>
      <br/>
      <button @click="login" :disabled="loading">Login</button><br/>
      <p>or you can sign in <a href="">here</a></p>
      <p v-if="error" class="error">{{ error }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LoginForm',
  data() {
    return {
      user_email: '',
      user_pwd: '',
      loading: false,
      error: null
    };
  },
  methods: {
    async login() {
      this.loading = true;
      this.error = null;
      console.log('Login attempt:', this.user_email, this.user_pwd);
      try {
        const response = await axios.post('/api/login', {
          user_email: this.user_email,
          user_pwd: this.user_pwd
        });

        if (response.status === 200 && response.data) {
          this.$router.push('/');
        } else {
          this.error = 'Login failed. Please check your email and password.';
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          this.error = 'Login failed. Please check your email and password.';
        } else {
          this.error = 'An error occurred. Please try again later.';
        }
        console.error('Error during login:', error);
      } finally {
        this.loading = false;
      }
    }
  }

};
</script>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  height: 100vh;
  padding-top: 20px;
}

.login-image {
  max-width: 25%;
  height: auto;
  object-fit: cover;
}

.login-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 540px;
  height: 480px;
  margin: 0 20px;
  padding: 20px;
  background-color: #FFF3DA;
  position: relative;
}

.login-container input {
  display: block;
  width: 60%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.forgot-password {
  position: relative;
  bottom: 10px;
  right: 0;
  color: #888;
  font-style: italic;
}

button {
  padding: 10px 20px;
  background-color: #FFCC5E;
  border: none;
  color: black;
  cursor: pointer;
  border-radius: 4px;
  margin-top: 10px;
  width: 300px;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>
