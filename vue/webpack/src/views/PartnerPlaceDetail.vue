<template>
    <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
        <div v-if="items">
        {{items.place_nm}}
        </div>
    </div>
  </div>
  
</template>
<script>
import axios from 'axios'
import SideBar from '@/views/SideBar.vue'


export default {
  name: 'PartnerPlaceDetail',
  components: {
    SideBar,
  },

  data(){
    return{
        items: null
    }
  },

  computed: {
    placeId() {
      return this.$route.params.placeid;
    },
  },
    created() {
      console.log(this.$route.params.placeid)
      this.fetchPlaceInfo(this.placeId)
    },
    methods: {
        fetchPlaceInfo(placeId){
            axios
      .get('/api/partner/placeDetail', {
        params: {
          placeid: placeId,
        },
      })
      .then((res) => {
        this.items = res.data
        console.log(res.data)
      })
      .catch((err) => {
        console.error(err)
      })
        }
    }
}
</script>
<style></style>
