<template>
  <div class="register-container">
    <div class="register-box">
      <h2>Registreren</h2>
      <form @submit.prevent="registerUser">
        <div class="form-group">
          <label for="name">Naam</label>
          <input type="text" v-model="user.name" id="name" required />
        </div>

        <div class="form-group">
          <label for="email">E-mail</label>
          <input type="email" v-model="user.email" id="email" required />
        </div>

        <div class="form-group">
          <label for="password">Wachtwoord</label>
          <input type="password" v-model="user.password" id="password" required />
        </div>

        <div class="form-group">
          <label for="birthDate">Geboortedatum</label>
          <input type="date" v-model="user.birthDate" id="birthDate" required />
        </div>

        <div class="form-group">
          <label for="province">Provincie</label>
          <select v-model="user.province" id="province" @change="fetchCities" required>
            <option value="" disabled>Selecteer een provincie</option>
            <option v-for="province in provinces" :key="province" :value="province">{{ province }}</option>
          </select>
        </div>

        <div class="form-group" v-if="cities.length > 0">
          <label for="city">Stad</label>
          <select v-model="user.city" id="city" required>
            <option value="" disabled>Selecteer een stad</option>
            <option v-for="city in cities" :key="city" :value="city">{{ city }}</option>
          </select>
        </div>

        <div class="form-group">
          <label for="role">Rol</label>
          <select v-model="user.role" id="role" required>
            <option value="" disabled>Selecteer een rol</option>
            <option value="visitor">Bezoeker</option>
            <option value="user">Gebruiker</option>
            <option value="admin">Beheerder</option>
          </select>
        </div>

        <button type="submit">Registreren</button>
      </form>
      <p v-if="message">{{ message }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: {
        name: "",
        email: "",
        password: "",
        birthDate: "",
        province: "",
        city: "",
        role: ""
      },
      message: "",
      provinces: ["Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen", "Limburg", "Noord-Brabant", "Noord-Holland", "Overijssel", "Utrecht", "Zeeland", "Zuid-Holland"],
      cities: [],
    };
  },
  methods: {
    async registerUser() {
      try {
        const response = await axios.post("http://localhost:8080/api/register", this.user);
        this.message = "Gebruiker succesvol geregistreerd!";
      } catch (error) {
        this.message = "Fout bij het registreren. Probeer het opnieuw.";
        console.error(error);
      }
    },
    fetchCities() {
      const citiesByProvince = {
        "Drenthe": ["Assen", "Emmen", "Hoogeveen"],
        "Flevoland": ["Almere", "Lelystad", "Emmeloord"],
        "Friesland": ["Leeuwarden", "Sneek", "Drachten"],
        "Gelderland": ["Arnhem", "Nijmegen", "Apeldoorn"],
        "Groningen": ["Groningen", "Delfzijl", "Winschoten"],
        "Limburg": ["Maastricht", "Venlo", "Roermond"],
        "Noord-Brabant": ["Eindhoven", "Breda", "Tilburg"],
        "Noord-Holland": ["Amsterdam", "Haarlem", "Alkmaar"],
        "Overijssel": ["Zwolle", "Enschede", "Deventer"],
        "Utrecht": ["Utrecht", "Amersfoort", "Nieuwegein"],
        "Zeeland": ["Middelburg", "Vlissingen", "Goes"],
        "Zuid-Holland": ["Rotterdam", "Den Haag", "Leiden"]
      };
      this.cities = citiesByProvince[this.user.province] || [];
    },
  },
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70vh;
  background-color: white;
}

.register-box {
  background-color: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 100%;
  max-width: 500px;
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

input,
select {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 2px solid rgba(0, 47, 108, 0.2);
  border-radius: 5px;
  outline: none;
  transition: border 0.3s ease;
}

input:focus,
select:focus {
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
