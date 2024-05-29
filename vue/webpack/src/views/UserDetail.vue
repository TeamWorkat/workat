<template>
  <div class="user-detail-container">
    <h4>프로필</h4>
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
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserDetail',
  data() {
    return {
      user: null
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
    }
  }
};
</script>

<style scoped>
.user-detail-container {
  display: flex;
  flex-direction: column;
  align-items: center; /* 중앙 정렬 */
  padding: 20px;
}

.user-detail-container h3 {
  margin-bottom: 20px;
}

.user-detail-container p {
  margin: 5px 0;
}
</style>
