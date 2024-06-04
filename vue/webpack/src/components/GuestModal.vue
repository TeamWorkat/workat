<template>
<div class="container">
  
  <div
    class="modal modal-sheet position-static d-block"
    tabindex="-1"
    role="dialog"
    id="modalSheet"
  >
    <div class="modal-dialog" role="document">
      <div class="modal-content rounded-4 shadow">
        <div class="modal-header border-bottom-0">
          <h1 class="modal-title fs-3">인원</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="closePop()"
          ></button>
          
        </div>
        <div
          class="modal-body d-flex align-items-center gap-2 justify-content-between"
        >
          <h5 class="mb-0">성인</h5>

          <button
            @click="decrement"
            type="button"
            class="btn btn-lg btn-secondary rounded-circle custom-btn"
            data-bs-dismiss="modal"
          >
            -
          </button>
          {{ guestCount }}
          <button
            @click="increment"
            type="button"
            class="btn btn-lg btn-primary rounded-circle custom-btn"
          >
            +
          </button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { ref } from 'vue'

export default {
  
  name: 'GuestModal.vue',
  data(){
    return{
      popState: false
      
    }
  },

  methods:{
    closePop(){
      this.$emit('close');
    }
  },

  
  setup(props,{emit}) {
    const guestCount = ref(0)

    function increment() {
      guestCount.value++;
      emit('updateCount', guestCount.value);
    }
    function decrement() {
      if (guestCount.value > 0) {
        guestCount.value--
        emit('updateCount', guestCount.value);
      }
    }

    return {
      guestCount,
      increment,
      decrement,
    }
  },
}
</script>

<style scoped>
.rounded-circle {
  border-radius: 50% !important; /* 원형으로 만듭니다 */
  width: 50px; /* 원하는 버튼의 너비 설정 */
  height: 50px; /* 높이를 너비와 같게 하여 원 모양을 만듭니다 */
  padding: 0; /* 필요에 따라 패딩 조정 */
  font-size: 24px; /* 아이콘 또는 텍스트 크기 조정 */
  display: flex;
  justify-content: center;
  align-items: center;
  border-color: #ffdd00;
}

.custom-btn {
  color: gray; /* 텍스트 색상 변경 */
}

.custom-btn.btn-primary {
  background-color: white; /* 기본 배경색 */
}

.custom-btn.btn-primary:active {
  background-color: #ffdd00; /* 눌렀을 때 배경색 */
  border-color: #ffdd00; /* 눌렀을 때 테두리 색상 */
}

.custom-btn.btn-primary:hover {
  background-color: #ffdd00; /* 눌렀을 때 배경색 */
  border-color: #ffdd00; /* 눌렀을 때 테두리 색상 */
}

.custom-btn.btn-secondary {
  background-color: white; /* 기본 배경색 */
}

.custom-btn.btn-secondary:active {
  background-color: #ffdd00; /* 눌렀을 때 배경색 */
  border-color: #ffdd00; /* 눌렀을 때 테두리 색상 */
}

.custom-btn.btn-secondary:hover {
  background-color: #ffdd00; /* 눌렀을 때 배경색 */
  border-color: #ffdd00; /* 눌렀을 때 테두리 색상 */
}
</style>
