<template>
  <div class="card shadow-sm" @click="goDetailPage(item.place_id)">
    <span
      class="img"
      :style="{ backgroundImage: `url(${item.picture_source})` }"
    />
    <div class="card-body">
      <p class="card-text">{{ item.place_nm }}</p>
      <div class="d-flex justify-content-between align-items-center">
        <!-- <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div> -->

        <small class="text-muted"> 설명 </small>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { useRouter } from 'vue-router' 

export default {
  name: "UserCard",
  props: {
    item: Object
  },
  setup() {
    const router = useRouter();


    //MARK:: 윤빈이 수정해야할곳
    //temporaryURL로 placeId를 넘기므로 여기 부분을 수정해서 데이터를 서버에서 받아와야합니다.
    //이후 성공하면 /rooms에 위치한 곳으로 이동하는 곳이고 router 설정해야합니다.
    const goDetailPage = (placeid) => {
      // axios로 POST 요청을 보냅니다. 여기서는 예시 URL을 사용합니다.
      axios.post('/api/temporaryURL', { placeId: placeid })
        .then(response => {
          // 요청이 성공적으로 처리되면, 여기에 성공 처리 로직을 작성합니다.
          console.log(response.data);
          // 요청 성공 후 원하는 페이지로 라우팅할 수 있습니다.
          router.push('/rooms');
        })
        .catch(error => {
          // 요청 처리 중 오류가 발생하면, 여기에 오류 처리 로직을 작성합니다.
          console.error(error);
          console.log(placeid);
        });
    };

    return {
      goDetailPage
    };
    
    // const router = useRouter(); // 여기서 useRouter를 사용합니다.

    // const goDetailPage = () => {
    //   router.push('/rooms'); // '/rooms'로 라우팅 합니다.
    // };

    // return {
    //   goDetailPage
    // };
  }
}
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}
</style>
