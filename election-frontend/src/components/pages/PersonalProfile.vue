<template>
  <h3 class="page-title">Persoonlijk Profiel</h3>
  <div id="wrapper">
    <!--ProfielFoto sectie-->
    <div class="row">
      <div id="PFS">
        <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
        <p class="info">Naam: {{ editMode ? editedUser.name : user.name }}</p>
        <p class="info">Voorstander</p>
        <p class="info">Provincie, Stad: {{ editMode ? editedUser.province : user.province }}, {{ editMode ? editedUser.city : user.city }}</p>
      </div>

      <!--Gegevens sectie-->
      <div id="gegevens">
        <p class="section-title">Mijn Gegevens</p>

        <div v-if="editMode">
          <label>Naam: <input v-model="editedUser.name" /></label>
          <label>Achternaam: <input v-model="editedUser.lastname" /></label>
          <label>Gebruikersnaam: <input v-model="editedUser.username" /></label>
          <label>Email: <input v-model="editedUser.email" /></label>
          <label>Stad: <input v-model="editedUser.city" /></label>
          <label>Provincie: <input v-model="editedUser.province" /></label>
        </div>
        <div v-else>
          <p class="details">Naam: {{ user.name }}</p>
          <p class="details">Achternaam</p>
          <p class="details">Gebruikersnaam</p>
          <p class="details">Email: {{ email }}</p>
          <p class="details">Stad: {{ user.city }}</p>
          <p class="details">Provincie: {{ user.province }}</p>
        </div>

        <button id="edit" v-if="!editMode" @click="toggleEdit">Bewerken</button>
        <button v-if="editMode" @click="saveChanges">Opslaan</button>
        <button v-if="editMode" @click="cancelEdit">Annuleren</button>
      </div>
    </div>

    <div class="row">
      <!--Platformen sectie-->
      <div id="platformen">
        <p class="section-title">Platformen</p>
      </div>

      <!--Omschrijving sectie-->
      <div id="omschrijving">
        <p class="section-title">Omschrijving</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PersonalProfile',
  data() {
    return {
      email: localStorage.getItem('email'),
      user: {},
      editedUser: {},
      editMode: false,
    };
  },
  mounted() {
    this.fetchUserInfoByEmail();
  },
  methods: {
    async fetchUserInfoByEmail() {
      try {
        const response = await axios.get(`http://localhost:8080/api/user`, {
          params: { email: this.email }
        });
        this.user = response.data;
        this.editedUser = { ...response.data };
      } catch (error) {
        console.error('User not found:', error.response.status);
      }
    },
    toggleEdit() {
      this.editMode = true;
    },
    cancelEdit() {
      this.editedUser = { ...this.user };
      this.editMode = false;
    },
    async saveChanges() {
      try {
        const response = await axios.put(`http://localhost:8080/api/user/update`, this.editedUser);
        this.user = { ...this.editedUser };
        this.editMode = false;
      } catch (error) {
        console.error('Error updating user:', error.response.status);
      }
    }
  }
});
</script>

<style scoped>
/* Wrapper and General Styling */
#wrapper {
  font-family: 'Arial', sans-serif;
  padding: 40px;
  max-width: 1200px;
  margin: 0 auto;
  background-color: #f5f8fc;
  border-radius: 15px;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
}

/* Title Styling */
.page-title {
  text-align: center;
  color: #1a1a99;
  font-size: 2.5rem;
  margin-bottom: 20px;
}

/* Profile Picture Section */
#PFS {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  width: 30%;
  text-align: center;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

#PFS img {
  width: 80%;
  height: auto;
  border-radius: 50%;
  margin-bottom: 15px;
}

.info {
  color: #000054;
  font-weight: bold;
  margin-bottom: 5px;
}

/* User Details Section */
#gegevens {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  width: 65%;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.section-title {
  font-size: 1.8rem;
  color: #1a1a99;
  font-weight: bold;
  margin-bottom: 15px;
}

.details {
  color: #555;
  padding: 10px 0;
  border-bottom: 1px solid #e0e0e0;
}

/* Edit Button */
#edit {
  background-color: #ff4500;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 15px;
}

#edit:hover {
  background-color: #ff5714;
}

/* Platform and Description Sections */
#platformen, #omschrijving {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  width: 45%;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.row {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  margin-bottom: 20px;
}

/* Responsive Design */
@media (max-width: 900px) {
  .row {
    flex-direction: column;
    align-items: center;
  }

  #PFS, #gegevens, #platformen, #omschrijving {
    width: 90%;
  }
}
</style>
