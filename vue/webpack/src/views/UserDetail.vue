<template>
  <div class="user-detail-container">
    <h4>프로필</h4><br/>
    <div v-if="user">
      <p><strong>이메일:</strong> {{ user.user_email }}</p>
      <p><strong>이름:</strong> {{ user.user_nm }}</p>
      <p><strong>전화번호:</strong> {{ user.user_tel }}</p>
      <p><strong>가입일:</strong> {{ user.created_date }}</p>
    </div>
    <div v-else>
      <p>사용자 정보를 불러오는 중입니다...</p>
    </div>
  </div>
  <div class="button-container">
    <button type="button" class="btn btn-primary" @click="clickEditButton()">수정</button>
    <button type="button" class="btn btn-danger" @click="clickDeleteButton()">탈퇴</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserDetail',
  data() {
    return {
      user: null,
      user_id: null
    };
  },
  mounted() {
    this.fetchUserDetail();
  },
  methods: {
    async fetchUserDetail() {
      try {
        this.user_id = this.$route.query.user_id;
        console.log("Fetching detail for user_id:", this.user_id);
        const response = await axios.get(`/api/user/detail?user_id=${this.user_id}`);
        this.user = response.data;
      } catch (error) {
        console.error('Error fetching user detail:', error);
      }
    },
    clickEditButton() {
      this.$router.push({name: 'UserUpdate', query: {user_id: this.user_id}});
    },
    clickDeleteButton() {
      this.$router.push({name: 'UserDelete', query: {user_id: this.user_id}});
    }
  }
};
</script>

<style scoped>
.user-detail-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  max-width: 600px;
  padding: 20px;
  margin: 0 auto;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px;
  width: 100%;
}

.button-container .btn {
  margin-left: 10px;
}
</style>
