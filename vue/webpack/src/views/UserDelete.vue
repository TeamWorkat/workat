<template>
  <div class="delete-account-container">
    <h4>회원 탈퇴</h4>
    <p>
      계정을 삭제하면 예약 내역, 회원 정보, 리뷰 등 모든 활동 정보가 삭제됩니다.
      또한 계정 삭제 후 7일 이내에는 재가입이 불가합니다.
    </p>
    <input type="password" v-model="rawPassword" placeholder="비밀번호를 입력하세요">
    <button @click="checkPassword">확인</button>

    <div v-if="showPopup">
      <div class="popup-container">
          <div class="popup">
            <h2>경고</h2>
            <p>탈퇴 후에는 철회 및 계정 복구가 불가합니다.</p>
            <p>정말로 탈퇴하시겠습니까?</p>
            <button @click="confirmDelete">확인</button>
            <button @click="closePopup">취소</button>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';
import store from "@/scripts/store";

export default {
  name: 'UserDelete',
  data() {
    return {
      rawPassword: '',
      showPopup: false,
      user_id: null
    };
  },
  
  methods: {
    async checkPassword() {
      try {
        const response = await axios.post(`/api/user/check-password`, {
          user_id: this.user_id,
          user_pwd: this.rawPassword
        });

        if (response.data) {
          this.showPopup = true;
        } else {
          alert('비밀번호가 일치하지 않습니다.');
        }
      } catch (error) {
        console.error('Error checking password:', error);
      }
    },
    async confirmDelete() {
      try {
        await axios.delete(`/api/user/delete`);
        alert('계정이 성공적으로 삭제되었습니다.');
        store.commit('clearAccount');
        sessionStorage.removeItem("user_id");
        sessionStorage.removeItem("token");
        this.token = null;
        this.$router.push('/');
      } catch (error) {
        console.error('Error deleting account:', error);
      }
    },
    closePopup() {
      this.showPopup = false;
    }
  }
};
</script>

<style scoped>
.delete-account-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

input[type="password"] {
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
  margin-right: 10px;
}

button:nth-of-type(2) {
  background-color: #dc3545;
}

.popup-container {
  position: fixed;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.popup {
  position: fixed;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  background-color: white;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  width: 100%;
}

.popup button {
  background-color: #dc3545;
}
</style>