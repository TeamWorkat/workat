<template>
    <div>
      <h4 class="review-title">리뷰수정</h4>
    </div>
    <hr class="long-divider">
    <div class="row">
      <div class="container col-md-1">
        <div
          class="file-upload-container border p-3"
          @dragenter="onDragenter"
          @dragover="onDragover"
          @dragleave="onDragleave"
          @drop="onDrop"
          @click="onClick"
        >
          <div class="file-upload" :class="{ dragged: isDragged }">
            <div v-if="reviewPictureList.length" class="file-preview-container">
              <div class="file-preview">
                <img :src="reviewPictureList[reviewPictureList.length - 1]" class="file-preview-image"/>
              </div>
            </div>
            <div v-else>
              +이미지를 가져오거나 추가해주세요.
            </div>
          </div>
        </div>
        <input type="file" ref="fileInput" class="file-upload-input" @change="onFileChange" multiple />
        <div class="file-upload-list">
          <div class="file-upload-list__item" v-for="(file, index) in reviewPictureList" :key="index">
            <div class="file-upload-list__item__data">
              <img class="file-upload-list__item__data-thumbnail" :src="typeof file === 'string' ? file : file.src" />
              <div class="file-upload-list__item__data-name">
                {{ file.name }}
              </div>
            </div>
            <div class="file-upload-list__item__btn-remove" @click="handleRemove(index)">
              삭제
            </div>
          </div>
        </div>
        </div>
      
      <div id="app" class="col-md-5 review" style="background-color: #f2f2f2; padding: 20px; border-radius: 5px;">
  <div class="form-group review-group">
    <div class="form-group">
      <h4>{{ placeName }}</h4>
    </div>
    <div class="form-group">
      <label for="check-in">방문일:</label>
      <h6>{{ formatDate(check_in) }} ~ {{ formatDate(check_out) }}</h6>
    </div>
    <label for="rating">별점:</label>
    <div class="rating">
            <span v-for="index in 5" :key="index">
              <img v-if="index <= computedRating" @click="setRating(index)" src="/img/별점.png" alt="YellowStar" style="width: 50px; height: 50px;">
              <img v-else @click="setRating(index)" src="/img/빈별점.png" alt="GrayStar" style="width: 50px; height: 50px;">
            </span>
          </div>
  </div>  
  <div class="form-group review-group" style="margin-top: 20px;">
    <label for="review">여행후기:</label>
    <textarea id="review" :value="reviewVO.content" rows="4" style="width: 100%; border: 2; border-radius: 5px; padding: 10px;" placeholder="고객님의 소중한 여행리뷰를 입력해주세요~"></textarea>
  </div>
  <div class="reservation-form">  
    <button @click="uploadFiles" style="margin-top: 20px;">수정</button>
      <button>삭제</button>
      <button>목록</button>
    </div></div></div>
  </template>

  
  
  <script setup>
  import { ref, computed, onMounted } from 'vue'
  import axios from 'axios'
  import { useRoute } from 'vue-router';
  import router from "@/router/index.js"

  const items = ref(null);
  const route = useRoute();
  const isDragged = ref(false)
  const rating = ref(0)
  const reviewId = computed(() => route.params.review_id);
  const review = ref('')
  const reviewVO = computed(() => items.value?.reviewVO || {});
  const placeName = computed(()=>items.value?.place_nm || null);
  const check_in = computed(()=>items.value?.check_in || null);
  const check_out = computed(()=>items.value?.check_out || null);
  const reviewPictureList = computed(()=>items.value?.review_picture_source || {} );

  const computedRating = computed(() => {
  return items.value && items.value.reviewVO ? items.value.reviewVO.rating : 0;
});

  const fetchReviewDetails = async (reviewId) => {
      await axios.get(`/api/review/detail?review_id=${reviewId}`)
        .then(res => {
          items.value = res.data;
        })
        .catch(error => {
          console.error("There was an error fetching the review details:", error);
        });
    };


  const setRating = (index) => {
    rating.value = index
  }
  
  const onClick = () => {
    fileInput.value.click()
  }
  
  const onDragenter = () => {
    isDragged.value = true
  }
  
  const onDragleave = () => {
    isDragged.value = false
  }
  
  const onDragover = (event) => {
    event.preventDefault()
  }
  
  const onDrop = (event) => {
    event.preventDefault()
    isDragged.value = false
    const files = event.dataTransfer.files
    addFiles(files)
  }
  
  const onFileChange = (event) => {
    const files = event.target.files
    addFiles(files)
  }

  const addFiles = async (files) => {
    for (let i = 0; i < files.length; i++) {
      const src = await readFiles(files[i])
      files[i].src = src
      reviewPictureList.value.push(files[i])
    }
  }
  
  const readFiles = (file) => {
    return new Promise((resolve) => {
      const reader = new FileReader()
      reader.onload = (e) => {
        resolve(e.target.result)
      }
      reader.readAsDataURL(file)
    })
  }
  
  const handleRemove = (index) => {
    reviewPictureList.value.splice(index, 1)
  }
  
  const uploadFiles = async () => {
    let formData = new FormData()
    reviewPictureList.value.forEach(file => {
      formData.append('files', file)
    })
    formData.append('res_id', reviewVO.value.res_id);
    formData.append('rating', rating.value);
    formData.append('content', review.value);
    formData.append('place_id', reviewVO.value.place_id);
    console.log(formData)
    try {
      let response = await axios.post('/api/review/insert', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      const reviewId = response.data;

      console.log('Review ID returned:', reviewId);
      console.log('Files uploaded successfully:', response.data)
      router.push({ name: 'ReviewDetail', params: { review_id: reviewId } });    } catch (error) {
      console.error('Error uploading files:', error)
    }
  }

  const formatDate = (dateString) => {
  const date = new Date(dateString);
  
  // 원하는 포맷 형식으로 변환
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const formattedDate = `${year}-${month}-${day}`;
  
  return formattedDate;
};

onMounted(() => {
      fetchReviewDetails(reviewId.value);
    });
  
  const fileInput = ref(null)
  </script>
  
  <style scoped>
  .container {
    min-height: 300px;
    width: 500px;
  }
  .file-upload {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    border: transparent;
    border-radius: 20px;
    cursor: pointer;
  }
  .file-upload.dragged {
    border: 1px dashed powderblue;
    opacity: 0.6;
  }
  .file-upload-container {
    height: 300px;
    padding: 20px;
    margin: 0 auto;
    box-shadow: 0 0.625rem 1.25rem #0000001a;
    border-radius: 20px;
  }
  .file-upload-input {
    display: none;
  }
  .file-upload-list {
    margin-top: 10px;
    width: 100%;
  }
  .file-upload-list__item {
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .file-upload-list__item__data {
    display: flex;
    align-items: center;
  }
  .file-upload-list__item__data-thumbnail {
    margin-right: 10px;
    border-radius: 20px;
    width: 120px;
    height: 120px;
  }
  .file-upload-list__item__btn-remove {
    cursor: pointer;
    border: 1px solid powderblue;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 5px;
    border-radius: 6px;
  }
  .file-preview-container {
    display: flex;
    gap: 10px;
    align-items: center;
  }
  .file-preview {
    display: flex;
    flex-direction: column;
    border: 1px solid black;
    border-radius: 10px;
    max-width: 400px;
    max-height: 400px;
    overflow: hidden;
  }
  /* .file-preview-image {
    max-width: 200px;
    max-height: 200px;
    border-radius: 10px;
    object-fit: contain;
  } */
  .request-group {
    background-color: #f7f7f7;
    padding: 10px;
  }
  .review-textarea {
    width: 100%; /* Set the width to 100% of its container */
    resize: horizontal; /* Allow horizontal resizing */
}
  .review-title{
    margin-left: 100px;
  }

  .review{
    margin-right: 50px;
  }
  </style>