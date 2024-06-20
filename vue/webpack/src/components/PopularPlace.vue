<template>
  <div class="container">
    <div class="album py-5">
      <h2>인기 TOP 5 숙소</h2>
      <div class="menuButtonArea">
        <ul role="tablist" class="tablist">
          <li
            role="tab"
            tabindex="0"
            :aria-selected="state.activeTab === 'hotel'"
            class="tab hotel_btn"
            @click="handleTabChange('hotel')"
          >
            호텔
          </li>
          <li
            role="tab"
            tabindex="1"
            :aria-selected="state.activeTab === 'stay'"
            class="tab stay_btn"
            @click="handleTabChange('stay')"
          >
            스테이
          </li>
          <li
            role="tab"
            tabindex="2"
            :aria-selected="state.activeTab === 'camping'"
            class="tab camping_btn"
            @click="handleTabChange('camping')"
          >
            캠핑
          </li>
        </ul>
      </div>
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-5 g-6">
        <div class="col" v-for="(item, idx) in paginatedItems" :key="idx">
          <PictureSlide
            :pictureList="item.picture_sources"
            @click="goDetailPage(item.place_id)"
          ></PictureSlide>
          <div v-if="item.starScore">
            <div class="place_name">
              {{ item.place_nm }}
            </div>
            <img
              src="/img/별점.png"
              alt="Empty Star"
              style="width: 10%; height: 10%"
            />
            {{ item.starScore }} ({{ item.reviewCount }})
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/axios'
import { reactive, computed, onMounted, onUnmounted } from 'vue'
import PictureSlide from './myPage/PictureSlide.vue'

export default {
  name: 'PopularPlace',
  components: {
    PictureSlide,
  },
  methods: {
    goDetailPage(placeId) {
      this.$router.push({ name: 'PlaceDetail', params: { placeId } })
    },
  },
  setup() {
    const state = reactive({
      items: [],
      currentPage: 1,
      itemsPerPage: 5,
      activeTab: 'hotel',
      cache: {}, // 각 카테고리 데이터를 저장하는 캐시 객체
    })

    const fetchItems = (category) => {
      // 캐시에 데이터가 있는지 확인
      if (state.cache[category]) {
        state.items = state.cache[category]
      } else {
        axios
          .get('/api/place/popularPlace', {
            params: {
              category: category,
            },
          })
          .then((res) => {
            state.items = res.data
            state.cache[category] = res.data // 데이터를 캐시에 저장
          })
          .catch((error) => {
            console.error(error)
          })
      }
    }

    fetchItems(state.activeTab)

    const paginatedItems = computed(() => {
      const start = (state.currentPage - 1) * state.itemsPerPage
      const end = start + state.itemsPerPage
      return state.items.slice(start, end)
    })

    const prevBtn = () => {
      if (state.currentPage > 1) {
        state.currentPage--
      }
    }

    const nextBtn = () => {
      if (state.currentPage * state.itemsPerPage < state.items.length) {
        state.currentPage++
      }
    }

    const changeTab = (tabName) => {
      state.activeTab = tabName
      fetchItems(tabName)
    }

    let intervalId
    const resetInterval = () => {
      if (intervalId) {
        clearInterval(intervalId)
      }
      intervalId = setInterval(() => {
        switch (state.activeTab) {
          case 'hotel':
            changeTab('stay')
            break
          case 'stay':
            changeTab('camping')
            break
          case 'camping':
            changeTab('hotel')
            break
          default:
            break
        }
      }, 5000)
    }

    const handleTabChange = (tabName) => {
      changeTab(tabName)
      resetInterval()
    }

    onMounted(() => {
      resetInterval()
    })

    onUnmounted(() => {
      if (intervalId) {
        clearInterval(intervalId)
      }
    })

    return {
      state,
      paginatedItems,
      prevBtn,
      nextBtn,
      handleTabChange,
    }
  },
}
</script>

<style scoped>
.tablist {
  list-style-type: none;
  padding: 0;
  display: flex;
}

.tab {
  padding: 10px 20px;
  margin: 5px;
  cursor: pointer;
  border: 2px solid #ccc;
  border-radius: 20px;
  background-color: #f9f9f9;
  transition: background-color 0.3s ease, border-color 0.3s ease;
}

.tab[aria-selected='true'] {
  background-color: #ffdd00;
  color: white;
  border-color: #ffdd00;
}

.tab:hover {
  background-color: #ffdd00;
  color: white;
  border-color: #ffdd00;
}

.nav-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.nav-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #ffdd00;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
  color: #fff;
  font-size: 20px;
}

.nav-btn:hover {
  background-color: #e6c300;
}

.nav-btn.prev-btn {
  margin-left: 10px; /* 왼쪽 마진 추가 */
}

.nav-btn.next-btn {
  margin-right: 10px; /* 오른쪽 마진 추가 */
}
</style>
