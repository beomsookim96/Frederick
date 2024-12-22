<template>
  <div class="login-container">
    <h1>Login</h1>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="id">ID : </label>
        <input
            id="id"
            type="id"
            v-model="id"
            placeholder="Enter your ID"
            required
        />
      </div>
      <div>
        <label for="password">PW : </label>
        <input
            id="password"
            type="password"
            v-model="password"
            placeholder="Enter your password"
            required
        />
      </div>
      <SubmitBtn label="login"/>
    </form>
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script>
import SubmitBtn from "@/components/common/SubmitBtn.vue";
import { login } from '../api/auth';

export default {
  components: {SubmitBtn},
  data() {
    return {
      id: '',
      password: '',
      error: null,
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await login(this.id, this.password);
        localStorage.setItem('token', response.data.token);
        this.$router.push({ name: 'dashboard' });
      } catch (error) {
        this.error = error.response?.data?.message || 'Login failed.';
      }
    }
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}
.error {
  color: red;
  margin-top: 10px;
}
</style>
