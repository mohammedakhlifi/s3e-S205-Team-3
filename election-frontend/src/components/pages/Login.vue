<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Inloggen</h2>
      <form @submit.prevent="loginUser">
        <div class="form-group">
          <label for="email">E-mail</label>
          <input type="email" v-model="login.email" id="email" required />
        </div>
        <div class="form-group">
          <label for="password">Wachtwoord</label>
          <input type="password" v-model="login.password" id="password" required />
        </div>
        <button type="submit">Inloggen</button>
      </form>
      <p v-if="message">{{ message }}</p>
    </div>
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
        const { token, role } = response.data;

        localStorage.setItem('token', token);
        localStorage.setItem('role', role);
        localStorage.setItem('email', this.login.email)
        this.message = 'Inloggen geslaagd!';
        console.log("Ingelogd met e-mailadres:", this.login.email); // Console loggen van de e-mail
        if (role === 'admin') {
          this.$router.push('/admin');
        } else {
          this.$router.push('/');
        }
        console.log("u bent ingelogd!", this.login)
      } catch (error) {
        this.message = 'Inloggen mislukt. Probeer het opnieuw.';
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70vh;
  background-color: white;
}

.login-box {
  background-color: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 100%;
  max-width: 400px;
}

h2 {
  margin-bottom: 20px;
  color: #002f6c;
  font-size: 1.8em;
  font-weight: bold;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  font-size: 1.1em;
  color: #002f6c;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 2px solid rgba(0, 47, 108, 0.2);
  border-radius: 5px;
  outline: none;
  transition: border 0.3s ease;
}

input:focus {
  border-color: #002f6c;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #002f6c;
  color: white;
  font-size: 1.2em;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #004494;
}

p {
  margin-top: 20px;
  color: #ff4c4c;
  font-size: 1em;
}
</style>
