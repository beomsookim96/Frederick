<template>
  <header class="app-header">
    <div class="logo">
      <router-link to="/">
        <img src="../../assets/logo.png">
      </router-link>
    </div>
    <nav class="nav-links">
      <router-link to="/login">login</router-link>
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
  color: #000000;
  border: 2px solid black;
}

.logo img{
  width : 50px;
  height : 50px;
}

.nav-links a {
  margin: 0 10px;
  color: #000000;
  text-decoration: none;
}

.nav-links a:hover {
  text-decoration: underline;
}
</style>
