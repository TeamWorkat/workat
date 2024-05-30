<template>
  <div class="user-update-container">
    <h4>프로필 수정</h4>
    <div v-if="user">
      <label>이메일:</label>
      <input v-model="user.user_email" type="email">

      <label>비밀번호:</label>
      <input v-model="user_pwd" type="password" required>

      <label>비밀번호 확인:</label>
      <input v-model="confirmPassword" type="password" required>

      <label>이름:</label>
      <input v-model="user.user_nm" type="text">

      <label>전화번호:</label>
      <input v-model="user.user_tel" type="text">

      <button @click="updateUser">저장</button>
    </div>
    <div v-else>
      <p>사용자 정보를 불러오는 중입니다...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserUpdate',
  data() {
    return {
      user: null,
      user_pwd: '',
      confirmPassword: ''
    };
  },
  mounted() {
    this.fetchUserDetail();
  },
  methods: {
    async fetchUserDetail() {
      try {
        const user_id = this.$route.query.user_id;
        const response = await axios.get(`/api/user/detail?user_id=${user_id}`);
        this.user = response.data;
      } catch (error) {
        console.error('Error fetching user detail:', error);
      }
    },
    async updateUser() {
      if (this.user_pwd !== this.confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      try {
        const userToUpdate = {
          ...this.user,
          user_pwd: this.user_pwd
        };
        await axios.post('/api/user/update', userToUpdate);
        alert('회원 정보 수정이 완료되었습니다.');
        this.$router.push({ name: 'UserDetail', query: { user_id: this.user.user_id } });
      } catch (error) {
        console.error('Error updating user:', error);
      }
    }
  }
};
</script>

<style scoped>
.user-update-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.user-update-container label {
  display: block;
  margin-top: 10px;
}

.user-update-container input {
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