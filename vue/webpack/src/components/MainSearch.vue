<template>
  <div class="wrap" @click="closeDropdown">
    <div class="bar" ref="bar" @click.stop>
      <div ref="dropdownButton" @click="toggleDropdown" class="localBtn">
        <p>여행지</p>
        <p>{{ localName }}</p>

        <ul
          v-if="isDropdownOpen"
          :style="dropdownStyle"
          class="dropdown-menu position-absolute d-grid gap-1 p-2 rounded-3 mx-0 shadow w-220px"
          data-bs-theme="light"
        >
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('서울')">서울</a>
          </li>
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('경기도')">경기도</a>
          </li>
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('강원도')">강원도</a>
          </li>
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('경상도')">경상도</a>
          </li>
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('전라도')">전라도</a>
          </li>
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('충청도')">충청도</a>
          </li>
          <li>
            <a class="dropdown-item rounded-2" @click="selectLocation('제주도')">제주도</a>
          </li>
        </ul>
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
        
      </div>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
        <button type="submit" class="searchBtn btn-primary">
        <i class="bi bi-search"></i>
        </button>
    
    </div>
  </div>
</template>

<script>
import { ref, onMounted, nextTick } from 'vue'

export default {
  name: 'MainSearch',
  setup() {
    const localName = ref('지역')
    const isDropdownOpen = ref(false)
    const dropdownStyle = ref({
      top: '0px',
      left: '0px',
    })
    const bar = ref(null)

    const toggleDropdown = async () => {
      isDropdownOpen.value = !isDropdownOpen.value
      if (isDropdownOpen.value) {
        await nextTick()
        setPosition()
      }
    }

    const setPosition = () => {
      const rectbar = bar.value.getBoundingClientRect()
      dropdownStyle.value.top = `${rectbar.bottom}px` // wrap 하단에서 5px 아래
      dropdownStyle.value.left = `${rectbar.left}px` // wrap 좌측 위치에 맞춤
    }

    const selectLocation = (location) => {
      localName.value = location
    }

    const closeDropdown = () => {
      isDropdownOpen.value = false
    }

    onMounted(() => {
      if (bar.value) {
        setPosition()
      }
    })

    return {
      localName,
      isDropdownOpen,
      dropdownStyle,
      toggleDropdown,
      setPosition,
      selectLocation,
      closeDropdown,
      bar,
    }
  },
}
</script>

<style scoped>
/* 기존 스타일 유지 */
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
  font-size: 0.8rem;
}

.bar div {
  border-radius: inherit;
  padding: 0.8rem 1.5rem;
  transition: background 250ms ease;
}

/* 수정된 스타일 */
.localBtn,
.check-in,
.check-out,
.guests {
  width: 22%;
  height: 55px;
   /* bar의 높이와 동일하게 설정 */
}

.localBtn:hover,
.check-in:hover,
.check-out:hover,
.guests:hover {
  background: #ffdd00; /* 다크 그레이 색상 */
  color: white;
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

/* .guests span {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  background: #ff385c;
  color: white;
  font-size: 0.8rem;
  padding: 0.7rem;
  border-radius: 50%;
} */

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

.searchBtn {
            width: 50px; /* 너비 */
            height: 50px; /* 높이 */
            background-color: #ffdd00; /* 배경색 */
            border: none; /* 테두리 없애기 */
            border-radius: 50%; /* 원형 버튼으로 만들기 */
            padding: 0; /* 내부 여백 조정 */
            display: inline-flex; /* Flexbox를 사용하여 아이콘을 중앙에 위치시킴 */
            align-items: center; /* 수직 중앙 정렬 */
            justify-content: center; /* 수평 중앙 정렬 */
        }

/* .dropdown-menu {
  position: absolute;
  z-index: 1000;
} */
</style>
