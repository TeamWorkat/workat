<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <div class="place_list_tableview">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">이름</th>
              <th scope="col">카테고리</th>
              <th scope="col">주소</th>
              <th scope="col">전화번호</th>
              <th scope="col">체크인</th>
              <th scope="col">체크아웃</th>
              <th scope="col">지역</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in state.items" :key="item.place_id" @click="touchUpInsideTableCell(item.place_id)">
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ item.place_nm }}</td>
              <td>{{ item.category }}</td>
              <td>{{ item.place_addr }}</td>
              <td>{{ item.place_tel }}</td>
              <td>{{ item.place_in }}</td>
              <td>{{ item.place_out }}</td>
              <td>{{ item.loc_id }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import SideBar from '@/views/SideBar.vue'
import axios from 'axios'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: 'PartnerPlaceList',
  components: {
    SideBar,
  },
  
  setup(){
    const state = reactive({
        items: []
    })
    
    axios.get('/api/partner/placelist').then((res) => {
        console.log(res.data)
        state.items = res.data
    })

    const router = useRouter()
    function touchUpInsideTableCell(placeid){
        // console.log(placeid)

        router.push({
                name: 'PartnerPlaceDetail',
                params:{
                    placeid
                }
            })

        // axios.get('/api/partner/placeDetail',{
        //     params:{
        //         placeid: placeid
        //     }
        // }).then((res) => {
            
        //     console.log(res.data)
        // }).catch((err) => {
        //     console.error(err)
        // })
    }

    return {
        state,
        touchUpInsideTableCell
    }
  }
}
</script>
<style></style>
