<template>
    <div class="wrap" @click="closeModalOutside">
    <div class="bar" ref="bar" @click.stop.prevent>
      <div ref="dropdownButton" @click="toggleDropdown" class="localBtn">
        <p>여행지</p>
        <p>{{ localName }}</p>

        <ul
          v-if="isDropdownOpen"
          :style="dropdownStyle"
          class="dropdown-menu position-absolute d-grid gap-1 p-2 rounded-3 mx-0 shadow w-220px"
          data-bs-theme="light"
        >
          <li v-for="location in locations" :key="location">
            <a
              class="dropdown-item rounded-2"
              @click="selectLocation(location)"
              >{{ location }}</a
            >
          </li>
        </ul>
      </div>

      <div class="check-in">
        <p>일정</p>
        
      </div>

      <div class="guests" @click="pushModal">
        <p>게스트</p>
        {{ guestCount }} 명
        
        <div v-if="popState" >
          <div class="modal-content">
            <GuestModal
            v-model:guestcount="guestCount"
              
              @updateCount="handleUpdateCount"
              class="dropdown-menu position-absolute d-grid gap-1 p-2 rounded-3 mx-0 shadow w-220px"
            >
            </GuestModal>
          </div>
        </div>
      </div>

      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css"
      />
      <button type="submit" class="searchBtn btn-primary" @click="search">
        <i class="bi bi-search"></i>
      </button>
    </div>
    
  </div>
  <UserCalendar @update-date="handleDateUpdate"> </UserCalendar>
  
</template>

<script>
import { ref, onMounted, nextTick } from 'vue'
import UserCalendar from '@/components/UserCalendar.vue'
import axios from 'axios'
import GuestModal from '@/components/GuestModal.vue'
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'



export default {
  name: 'MainSearch',
  components: {
    UserCalendar,
    GuestModal,
  },

  setup() {
    const guestCount = ref(1)
    const datepick = ref(['', ''])
    const popState = ref(false)
    const localName = ref('지역')
    const localValue = ref(0)
    const isDropdownOpen = ref(false)
    const dropdownStyle = ref({ top: '0px', left: '0px' })
    
    const bar = ref(null)
    const dropdownButton = ref(null)
    const category = ref('All')
    const locations = [
      '서울',
      '경기도',
      '강원도',
      '경상도',
      '전라도',
      '충청도',
      '제주도',
    ]

    const pushModal = () => {
      console.log('여기')
      popState.value = true;
    }

    // const changePopState =  async () => {
    //   popState.value = !popState.value
    //   if (popState.value) {
    //     await nextTick()
    //     setModalPosition()
    //   }
    // }

    const toggleDropdown = async () => {
      isDropdownOpen.value = !isDropdownOpen.value
      if (isDropdownOpen.value) {
        await nextTick()
        setPosition()
      }
    }


    const setPosition = () => {
      const rectbar = bar.value.getBoundingClientRect()
      dropdownStyle.value.top = `${rectbar.top}`
      dropdownStyle.value.left = `0px`
    }

    function defaultDate() {
      const startDate = new Date()
      const endDate = new Date(new Date().setDate(startDate.getDate() + 1))

      const startYear = startDate.getFullYear()
      const startMonth = (startDate.getMonth() + 1).toString().padStart(2, '0')
      const startDay = startDate.getDate().toString().padStart(2, '0')

      datepick.value[0] = `${startYear}-${startMonth}-${startDay}`

      const endYear = endDate.getFullYear()
      const endMonth = (endDate.getMonth() + 1).toString().padStart(2, '0')
      const endDay = endDate.getDate().toString().padStart(2, '0')

      datepick.value[1] = `${endYear}-${endMonth}-${endDay}`
    }
    defaultDate()


    const selectLocation = (location) => {
      localName.value = location
      switch (location) {
        case '지역':
          localValue.value = 0
          break
        case '서울':
          localValue.value = 1
          break
        case '경기도':
          localValue.value = 2
          break
        case '강원도':
          localValue.value = 3
          break
        case '경상도':
          localValue.value = 4
          break
        case '전라도':
          localValue.value = 5
          break
        case '충청도':
          localValue.value = 6
          break
        case '제주도':
          localValue.value = 7
          break
        default:
          break
      }
      !isDropdownOpen.value
    }

    const handleDateUpdate = (newDate) => {
      datepick.value = newDate
      console.log(datepick.value[0], datepick.value[1])
    }

    const handleUpdateCount = (newCount) => {
      guestCount.value = newCount
    }

    const router = useRouter()
    const route = useRoute()

    // 라우트 메타 타입에 따라 category 변경
    switch (route.meta.type) {
      case 'home':
        category.value = 'All'
        console.log(category.value)
        break
      case 'hotel':
        category.value = 'hotel'
        console.log(category.value)
        break
      case 'stay':
        category.value = 'stay'
        console.log(category.value)
        break
      case 'camping':
        category.value = 'camping'
        console.log(category.value)
        break
    }

    const search = () => {
      axios
        .post('/api/place/search', {
          category: category.value,
          location: localValue.value,
          startDate: datepick.value[0],
          endDate: datepick.value[1],
          guests: guestCount.value,
        })
        .then((res) => {
          console.log(res)
          router.push({
            name: 'UserSearch',
            query: { data: JSON.stringify(res.data) },
          })
        })
        .catch((err) => {
          console.log(err)
        })
    }

    onMounted(() => {
      if (bar.value) {
        setPosition()
      }
    })

    const closeModalOutside = (event) => {
      if (!event.target.closest('.modal-sheet') && !event.target.closest('.dropdown-menu')) {
        popState.value = false;
        isDropdownOpen.value = false;
      }
    };
    return {
      
      closeModalOutside,
      pushModal,
      guestCount,
      datepick,
      popState,
      localName,
      isDropdownOpen,
      dropdownStyle,
      bar,
      dropdownButton,

      toggleDropdown,
      selectLocation,
      handleDateUpdate,
      handleUpdateCount,
      search,
      locations,
    }
  },
}
</script>

<style scoped>
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
  position: relative;
}

.bar {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 900px;
  background: white;
  box-shadow: 0 0 5px hsl(0, 0%, 78%);
  height: 60px;
  border-radius: 100vw;
  font-size: 0.8rem;
}

.bar div {
  border-radius: inherit;
  padding: 0.8rem 1.5rem;
  transition: background 250ms ease;
}

.localBtn,
.check-in,
.guests {
  width: 22%;
  height: 55px;
}

.localBtn:hover,
.check-in:hover,
.guests:hover {
  background: #ffdd00;
  color: white;
}





.guests {
  position: relative;
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

.searchBtn {
  width: 50px;
  height: 50px;
  background-color: #ffdd00;
  border: none;
  border-radius: 50%;
  padding: 0;
  display: inline-flex;
  align-items: center;
  justify-content: center;
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
}

.modal-content {
  background: white;
  border-radius: 8px;
  width: 300px;
  /* max-width: 80%; */
}
</style>
