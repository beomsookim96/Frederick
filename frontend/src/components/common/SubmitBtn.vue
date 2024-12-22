<template>
  <button
      class="submit-button"
      @click="handleClick"
      :disabled="disabled"
  >
    {{ label }}
  </button>
</template>

<script>
export default {
  name: "SubmitButton",
  props: {
    label: {
      type: String,
      default: "Submit", // 기본 문구
    },
    disabled: {
      type: Boolean,
      default: false, // 버튼 활성화 여부
    },
  },
  emits: ["click"], // 부모 컴포넌트로 클릭 이벤트 전달
  methods: {
    handleClick() {
      if (!this.disabled) {
        this.$emit("click"); // 클릭 이벤트를 부모 컴포넌트에 전달
      }
    },
  },
};
</script>

<style scoped>
.submit-button {
  margin-top: 5px;
  padding: 5px 20px;
  font-size: 16px;
  color: #fff;
  background-color: black;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: gray;
}

.submit-button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}
</style>
