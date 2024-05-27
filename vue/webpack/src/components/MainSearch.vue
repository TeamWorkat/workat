<template>
  <div class="wrap">
    <div class="bar">
      <!-- <button @click="openModal">Where are you going?</button> -->
      <!-- <LocationModal v-moel:showModal="showModal"></LocationModal> -->
      <button @click="modalOpen">Modal</button>

      <div class="modal-wrap" v-show="modalCheck">
        <div class="modal-container">
          ...
          <!--  모달창 content  -->

          <div class="modal-btn">
            <button @click="modalOpen">닫기</button>
            <button @click="modalOpen">확인</button>
          </div>
        </div>
      </div>

      <div class="check-in">
        <p>체크인</p>
        <input type="text" placeholder="날짜 추가" />
      </div>
      <div class="check-out">
        <p>체크아웃</p>
        <input type="text" placeholder="날짜 추가" />
      </div>
      <div class="guests">
        <p>게스트</p>
        <input type="text" placeholder="게스트 추가" />
        <span><i class="lni lni-search-alt"></i></span>
      </div>
    </div>
  </div>
</template>

<script>
// import LocationModal from './LocationModal.vue'
export default {
  data() {
  return {
    modalCheck: false,
  }
},
methods: {
  modalOpen() {
    this.modalCheck = !this.modalCheck
  }
},
  // components: {
  //   LocationModal,
  // },
  // data() {
  //   return {
  //     showModal: false,
  //   }
  // },
  // methods: {
  //   openModal() {
  //     this.showModal = true
  //   },
  // },
  
  name: 'MainSearch',
}
</script>

<style scoped>
/* dimmed */
.modal-wrap {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
}
/* modal or popup */
.modal-container {
  position: absolute;
  top: 220px;
  justify-content: center;
  display: flex;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  display: none;

}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

html,
body {
  height: 100%;
}

body {
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: sans-serif;
  background-repeat: no-repeat;
}

.wrap {
  display: flex;
  justify-content: center;
}

.bar {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 650px;
  background: white;
  box-shadow: 0 0 5px hsl(0, 0%, 78%);
  height: 55px;
  border-radius: 100vw;
  display: flex;
  justify-content: center;
  font-size: 0.6rem;
}

.bar div {
  border-radius: inherit;
  padding: 0.8rem 1.5rem;
  transition: background 250ms ease;
}

.bar div:hover {
  background: hsl(0, 0%, 94%);
}

.locationBtn {
  width: 34%;
}
.check-in,
.check-out,
.guests {
  width: 22%;
}

input[type='text'] {
  background: none;
  border: none;
  padding: 0.2rem 0 0 0;
}

input[type='text']:focus {
  outline: none;
}

::placeholder {
  font-size: 0.75rem;
}

.guests {
  position: relative;
}
.guests span {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  background: #ff385c;
  color: white;
  font-size: 0.8rem;
  padding: 0.7rem;
  border-radius: 50%;
}

.bar > div {
  position: relative;
}

.bar > div::before {
  position: absolute;
  content: '';
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 1px;
  height: 55%;
  background: hsl(0, 0%, 90%);
}

.bar > div:nth-of-type(1)::before {
  background: transparent;
}

.bar > div:hover::before {
  background: transparent;
}
</style>
