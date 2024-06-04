<template>
  <div class="container">
    <footer class="footer d-flex justify-content-center align-items-center py-3 mt-4 border-top">
      <div class="footer-left">
        <i class="bi bi-house-door"></i> <!-- 로고 추가 예정 -->
      </div>
      <div class="footer-center">
        <p class="mb-0">Copyright © Workat. All Rights Reserved.</p>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'UserFooter'
}
</script>

<style scoped>
.footer {
  padding: 20px 50px;
  border-top: 1px solid #d3d3d3;
}

.footer .container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.footer-center {
  text-align: center;
}
</style>
