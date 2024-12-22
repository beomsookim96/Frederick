<template>
  <header class="app-header">
    <div class="logo">
      <h1>Frederick</h1>
    </div>
    <nav class="nav-links">
      <router-link to="/">Home</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/contact">Contact</router-link>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'AppHeader',
};
</script>

<style scoped>
.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #333;
  color: #fff;
}

.logo h1 {
  font-size: 24px;
}

.nav-links a {
  margin: 0 10px;
  color: #fff;
  text-decoration: none;
}

.nav-links a:hover {
  text-decoration: underline;
}
</style>
