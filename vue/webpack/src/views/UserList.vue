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
    <div class="button-container">
      <button type="button" class="btn btn-resetpw" @click="clickResetPasswordButton()">비밀번호 초기화</button>
      <button type="button" class="btn btn-delete" @click="clickDeleteButton()">삭제</button>
      <button type="button" class="btn btn-unlock" @click="clickUnlockButton()">잠금 해제</button>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';

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
        const response = await axios.get('/api/admin/list');
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
    },
    clickResetPasswordButton() {
      // 비밀번호 초기화 이벤트 처리
    },
    async clickDeleteButton() {
      if (this.selectedUsers.length === 0) {
        alert('삭제할 회원을 선택해주세요.');
        return;
      }

      if (confirm('선택된 회원 정보를 삭제하시겠습니까?')) {
        try {
          for (const user_id of this.selectedUsers) {
            const url = `/api/user/${user_id}`;
            console.log('DELETE 요청 URL:', url);
            await axios.delete(url);
          }

          this.fetchUsers();
          this.selectedUsers = [];
        } catch (error) {
          console.error('Error deleting users:', error);
          alert('회원 정보 삭제 중 오류가 발생했습니다.');
        }
      }
    },
    clickUnlockButton(){
      // 계정 잠금 해제 버튼
    }
  }
}
</script>

<style scoped>
.user-list-container {
  display: flex;
  flex-direction: column;
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

.button-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
  width: 100%;
}

.button-container .btn {
  margin-left: 10px;
}

.btn-resetpw {
  background-color: #FFBF5E;
  border: 1px solid #FFBF5E;
  color: black;
}

.btn-delete {
  background-color: #FF8D8D;
  border: 1px solid #FF8D8D;
  color: black;
}

.btn-unlock {
  background-color: #8DC1FF;
  border: 1px solid #8DC1FF;
  color: black;
}
</style>