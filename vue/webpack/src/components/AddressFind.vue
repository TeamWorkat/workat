<template>
    <div class="address-find">
      <span>{{ place_addr }}</span>
      <button @click="toggleModal" class="btn">주소 찾기</button>
      
      <div v-if="isModalOpen" class="modal-overlay" @click="toggleModal">
        <div class="modal-content" @click.stop>
          <button class="close-btn" @click="toggleModal">&times;</button>
          <div ref="embed" class="modal-body"></div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'AddressFind',
    data() {
      return {
        zip: '',
        place_addr: '',
        addr2: '',
        isModalOpen: false
      }
    },
    methods: {
      toggleModal() {
        this.isModalOpen = !this.isModalOpen;
        if (this.isModalOpen) {
          this.$nextTick(() => {
            this.showApi();
          });
        }
      },
      showApi() {
        new window.daum.Postcode({
          oncomplete: (data) => {
            let fullRoadAddr = data.roadAddress; 
            let extraRoadAddr = ''; 
  
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            if (data.buildingName !== '' && data.apartment === 'Y') {
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            if (fullRoadAddr !== '') {
                fullRoadAddr += extraRoadAddr;
            }
  
            this.zip = data.zonecode; 
            this.place_addr = fullRoadAddr;
            this.$emit('update-place_addr', this.place_addr);
            this.toggleModal(); // Close the modal after selecting the address
          }
        }).embed(this.$refs.embed);
      }
    }
  }
  </script>
  
  <style scoped>
  .address-find {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .btn {
    background-color: #869ecc;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
    transition-duration: 0.4s;
  }
  
  .btn:hover {
    background-color: white;
    color: black;
    border: 2px solid #007bff;
  }
  
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  
  .modal-content {
    background: white;
    
    border-radius: 5px;
    width: 500px;
    height: 500px;
    position: relative;
  }
  
  .modal-body {
    height: 100%;
  }
  
  .close-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    background: none;
    border: none;
    font-size: 1.5rem;
    cursor: pointer;
  }
  </style>
  