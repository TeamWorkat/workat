<template>
  <div class="user-list-container">
    <h3 class="user-list-title">회원 리스트</h3>
    <p class="search-results">검색 결과: {{ users.length }}건</p>
    <table class="user-table">
      <thead>
      <tr>
        <th><input type="checkbox" @click="selectAll"></th>
        <th>번호</th>
        <th>이메일</th>
        <th>이름</th>
        <th>가입일</th>
        <th>권한</th>
        <th>잠금여부</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.user_id">
        <td><input type="checkbox" v-model="selectedUsers" :value="user.user_id"></td>
        <td>{{ user.user_id }}</td>
        <td>{{ user.user_email }}</td>
        <td>{{ user.user_nm }}</td>
        <td>{{ user.created_date }}</td>
        <td>{{ user.role }}</td>
        <td>{{ user.account_locked }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserList',
  data() {
    return {
      users: [],
      selectedUsers: []
    }
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('/api/admin/users');
        this.users = response.data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    selectAll(event) {
      if (event.target.checked) {
        this.selectedUsers = this.users.map(user => user.user_id);
      } else {
        this.selectedUsers = [];
      }
    }
  }
}
</script>

<style scoped>
.user-list-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 80%;
  margin: 0 auto;
}

.user-list-title {
  margin-bottom: 5px;
}

.search-results {
  margin-bottom: 10px;
  font-weight: bold;
}

.user-table {
  width: 100%;
  border-collapse: collapse;
}

.user-table th, .user-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

.user-table th {
  background-color: #f2f2f2;
}

.user-table tr:nth-child(even) {
  background-color: #f9f9f9;
}

.user-table tr:hover {
  background-color: #ddd;
}
</style>
