<template>
  <footer class="app-footer">
    <div class="footer-content">
      <p>&copy; 2024 My App. All Rights Reserved.</p>
      <nav class="footer-links">
        <router-link to="/">Home</router-link>
        <router-link to="/privacy">Privacy Policy</router-link>
        <router-link to="/terms">Terms of Service</router-link>
      </nav>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'AppFooter',
};
</script>

<style scoped>
.app-footer {
  border: 2px solid black;
  color: #000000;
  padding: 20px 0;
  text-align: center;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
}

.footer-links a {
  margin: 0 10px;
  color: #000000;
  text-decoration: none;
}

.footer-links a:hover {
  text-decoration: underline;
}
</style>
