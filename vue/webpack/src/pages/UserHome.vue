<template>
  <MainSearch/>
  <div class="user_home">
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div class="col" v-for="(item, idx) in state.items" :key="idx">
            <UserCard :item="item" />
          </div>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
import MainSearch from '@/components/MainSearch.vue'
import UserCard from '@/components/UserCard.vue'
import axios from 'axios'
import { reactive } from 'vue'

export default {
  components: {
    MainSearch,
    UserCard,
  },
  setup() {
    const state = reactive({
      items: [],
    })

    axios.get('/api/items').then((res) => {
      state.items = res.data
      console.log(res)
    })

    return { state }
  },
}
</script>

<style scoped>

</style>
