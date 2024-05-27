<template>
    <div class="card shadow-sm">
      <span class="img" :style="{backgroundImage: `url(${item.img_path})`}"/>
            <div class="card-body">
              <p class="card-text">{{ item.name }}</p>
              <div class="d-flex justify-content-between align-items-center">
                <!-- <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div> -->
            
                <small class="text-muted">
                  {{ item.price }}원
                </small>  
            </div>
        </div>
    </div>
              
</template>

<script>

export default {
    name: "UserCard",
    props:{
      item: Object
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