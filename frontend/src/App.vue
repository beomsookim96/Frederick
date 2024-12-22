<script setup>
import Header from "@/components/common/Header.vue";
import Footer from "@/components/common/Footer.vue";
</script>

<template>
  <div id="app">
    <Header/>
    <!-- 라우터 랜더링 영역-->
    <router-view/>
    <Footer/>
  </div>
</template>

<style scoped>
</style>
