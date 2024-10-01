<template>
  <div class="register">
    <h2>Register</h2>
    <form @submit.prevent="registerUser">
      <div>
        <label for="name">Name</label>
        <input type="text" v-model="user.name" id="name" required />
      </div>
      <div>
        <label for="email">Email</label>
        <input type="email" v-model="user.email" id="email" required />
      </div>
      <div>
        <label for="password">Password</label>
        <input type="password" v-model="user.password" id="password" required />
      </div>
      <button type="submit">Register</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user: {
        name: '',
        email: '',
        password: '',
      },
      message: '',
    };
  },
  methods: {
    async registerUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/register', this.user);
        this.message = 'User registered successfully!';
      } catch (error) {
        this.message = 'Error registering user. Please try again.';
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
/* Add your styles here */
</style>
