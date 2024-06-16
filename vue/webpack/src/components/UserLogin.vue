<template>
  <div class="login-page">
    <img src="/img/work_at_icon.png" class="login-image">
    <div class="login-container">
      <h3>Login</h3><br/>
      <input type="email" class="form-control" id="floatingInput" placeholder="email" required v-model="state.form.user_email">
      <input type="password" class="form-control" placeholder="password" required v-model="state.form.user_pwd">
      <a href="#" class="forgot-password">forgot password?</a>
      <br/>
      <button @click="submit()" :disabled="loading">Login</button><br/>
      <p>or you can sign in <a href="">here</a></p>
      <p v-if="error" class="error">{{ error }}</p>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';
import store from "@/scripts/store";
import router from "@/router";
import { reactive, ref } from "vue";

export default {
  setup() {
    const state = reactive({
      form: {
        user_email: '',
        user_pwd: ''
      }
    });
    const loading = ref(false);
    const error = ref(null);

    const submit = async () => {
      loading.value = true;
      error.value = null;

      try {
        const res = await axios.post("/api/login", state.form);
        const token = res.data.token;
        const user_id = res.data.user_id;
        store.commit('setAccount', user_id);
        store.commit('setToken', token);
        sessionStorage.setItem("user_id", user_id);
        sessionStorage.setItem("token", token);
        router.push({ path: "/" });
        console.log(res.data);
        window.alert("Welcome!");
      } catch (err) {
        console.error("Login error:", err);
        error.value = "Invalid email or password.";
        window.alert("Invalid email or password. Please check again.");
      } finally {
        loading.value = false;
      }
    }

    return { state, loading, error, submit };
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
