<template>
  <div class="banner-container">
    <div class="banner-slide" v-for="(slide, index) in slides" :key="index" :class="{ active: currentIndex === index }">
      <img :src="slide.image" :alt="'Slide ' + (index + 1)" class="slide-image" />
    </div>
    <div class="pagination">
      <span
        v-for="(slide, index) in slides"
        :key="index"
        class="pagination-item"
        :class="{ active: currentIndex === index }"
        @click="goToSlide(index)"
      ></span>
    </div>
    <button @click="prevSlide" class="prev-btn">&#9664;</button>
    <button @click="nextSlide" class="next-btn">&#9654;</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentIndex: 0,
      slides: [
        {
          image: 'https://workatbucket.s3.amazonaws.com/place/bec32ce3-1c52-478c-b643-9a9fb00f82f4.png',
          
        },
        {
          image: 'https://workatbucket.s3.amazonaws.com/place/e4b20dfc-a189-4f49-b671-aaf7c47f3840.png',
          
        },
        {
          image: 'https://workatbucket.s3.amazonaws.com/place/add4fbc3-981a-4619-882c-0da6931d4032.png',
          
        },
      ],
      intervalId: null, // setInterval의 ID를 저장할 변수
    };
  },
  mounted() {
    // 컴포넌트가 마운트된 후 자동으로 슬라이드 시작
    this.startSlideShow();
  },
  beforeUnmount() {
    // 컴포넌트가 언마운트되기 전 clearInterval로 타이머 제거
    clearInterval(this.intervalId);
  },
  methods: {
    prevSlide() {
      this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    },
    nextSlide() {
      this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    },
    startSlideShow() {
      // 3초마다 다음 슬라이드로 넘어가는 타이머 설정
      this.intervalId = setInterval(() => {
        this.nextSlide();
      }, 5000); // 3초(3000밀리초)마다 실행
    },
    goToSlide(index) {
      this.currentIndex = index;
    },
  },
};
</script>

<style scoped>
.banner-container {
  position: relative;
  width: 68%;
  height: 300px;
  overflow: hidden;
  margin: 0 auto;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.banner-slide {
  position: absolute;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: opacity 0.5s ease-in-out;
}

.banner-slide.active {
  opacity: 1;
}

.slide-image {
  width: 100%;
  height: 100%;
  object-fit:cover; /* 이미지를 배너에 맞게 조정 */
  border-radius: 10px;
}



.pagination {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
}

.pagination-item {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.5);
  margin: 0 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.pagination-item.active {
  background-color: #ffdd00;
}

.prev-btn,
.next-btn {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(254, 220, 0, 0.576); /* 원하는 배경색으로 변경 */
  color: #fff;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s;
}

.prev-btn {
  left: 10px;
}

.next-btn {
  right: 10px;
}

.prev-btn:hover,
.next-btn:hover {
  background-color: #ffdd00; /* 호버 상태에서 배경색 변경 */
}
</style>
