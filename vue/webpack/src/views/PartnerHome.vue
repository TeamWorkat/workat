<template>
  <div class="d-flex">
    <SideBar />
    <div class="flex-grow-1 p-3">
      <h1 class="page-title">📢 공지사항</h1>
      <br/>
      <h5>안녕하세요,사장님 여러분!</h5>
      <h6>여러분의 숙소 관리에 도움이 될 공지사항을 안내드립니다.</h6>
      <h6>아래 내용을 참고하여 숙소 등록, 수정, 예약 취소, 객실 관리, 리뷰 관리를 효율적으로 진행해 주세요.</h6>

      <!-- 공지사항 목록 -->
      <div class="notice-list">
        <div class="notice-item">
          <h3 class="notice-title">📌 숙소 관련</h3>
          <p class="notice-content">
            - 숙소 사진과 설명을 상세히 작성하여 손님들이 쉽게 찾을 수 있도록 도와주세요.<br/>
            - 변경 사항을 적용하기 전에 정확한 정보를 입력해 주세요.<br/>
            - 취소 정책을 명확히 설정하여 손님들이 예약 취소 규정을 이해할 수 있도록 도와주세요.
          </p>
        </div>
        <div class="notice-item">
          <h3 class="notice-title">📌 예약 관련</h3>
          <p class="notice-content">
            - 손님이 예약을 취소하는 경우, 예약 관리 페이지에서 확인하고 적절히 처리해 주세요.<br/>
            - 취소 정책을 명확히 설정하여 손님들이 예약 취소 규정을 이해할 수 있도록 도와주세요.
          </p>
        </div>
        <div class="notice-item">
          <h3 class="notice-title">📌 리뷰 관련</h3>
          <p class="notice-content">
            - 손님들의 리뷰를 주의 깊게 살펴보고, 긍정적인 리뷰에는 감사의 표시를 전하며,<br/>
            - 부정적인 리뷰에는 적절히 대응해 주세요.<br/>
            - 손님들의 피드백을 통해 숙소 서비스의 개선점을 파악하고, 지속적으로 품질을 개선해 나가는 데 기여해 주세요.
          </p>
        </div>
      </div>

      <p>위 공지사항을 참고하여 숙소 운영을 원활히 진행하시길 바랍니다.</p>
      <p>추가로 궁금한 사항이나 도움이 필요하신 경우 언제든지 저희 팀에 문의해 주세요.</p>
      <p>감사합니다.</p>
    </div>
  </div>
</template>

<script>
import SideBar from "@/views/SideBar.vue";

export default {
  name: "PartnerHome",
  components: {
    SideBar,
  },
};
</script>

<style scoped>
.flex-grow-1 {
  display: flex;
  flex-direction: column;
}

.page-title {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  color: #333;
}

.notice-list {
  margin-top: 2rem;
}

.notice-item {
  background-color: #f8f9fa;
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.notice-title {
  font-size: 1.8rem;
  margin-bottom: 0.5rem;
  color: #333;
}

.notice-content {
  color: #555;
  line-height: 1.6;
}
</style>
