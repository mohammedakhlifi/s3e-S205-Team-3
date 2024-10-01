<template>
  <div class="login">
    <h2>Login</h2>
    <form @submit.prevent="loginUser">
      <div>
        <label for="email">Email</label>
        <input type="email" v-model="login.email" id="email" required />
      </div>
      <div>
        <label for="password">Password</label>
        <input type="password" v-model="login.password" id="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      login: {
        email: '',
        password: '',
      },
      message: '',
    };
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/login', this.login);
        this.message = 'Login successful!';
        // Handle storing JWT token or other login success actions
      } catch (error) {
        this.message = 'Login failed. Please try again.';
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
/* Add your styles here */
</style>
