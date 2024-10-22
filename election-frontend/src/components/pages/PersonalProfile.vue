<template>
<h3>Persoonlijk Profiel</h3>
<div id="wrapper">
  <!--ProfielFoto sectie-->
  <div class="row">
  <div  id="PFS">
    <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
    <p>Naam: {{user.name}}</p>
    <p>Voorstander</p>
    <p>Provincie, Stad: {{user.province}}, {{user.city}}</p>
  </div>

  <!--Gegevens sectie-->
  <div id="gegevens">
    <p class="details">Mijn Gegevens</p>
    <p class="details">Naam: {{user.name}}</p>
    <p class="details">Achternaam</p>
    <p class="details">Gebruikersnaam</p>
    <p class="details">Email: {{this.email}}</p>
    <p class="details">Stad: {{user.city}}</p>
    <p class="details">Provincie: {{user.province}} </p>
    <p class="details">Voorstander van</p>
    <br>
    <button id="edit">Bewerken</button>
  </div>
  </div>

  <div class="row">
  <!--Platformen sectie-->
  <div id="platformen">
   <p>Platformen</p>
  </div>

  <!--Omschrijving sectie-->
  <div id="omschrijving">
    <p>Omschrijving</p>
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
      user: '',
    };
  },

  mounted() {
    this.fetchUserInfoByEmail();
  },
  methods: {
    async fetchUserInfoByEmail() {
      try {

      const response = await axios.get(`http://localhost:8080/api/user`, {
        params: { email: this.email } // Send the email as a query parameter
      });
      console.log('User found:', response.data);
      console.log("response status", response.status);
      this.user = response.data;
    } catch (error) {
      console.error('User not found:', error.response.status);
    }

  },
  }


});

</script>




<style scoped>

#PFS {
  border: 3px solid #1a1a99;
  height: 40vh;
  width: 20vw;
  text-align: center;
}

#platformen {
  border: 3px solid #1a1a99;
  height: 30vh;
  width: 20vw;
}

#gegevens {
  border: 3px solid #1a1a99;
  height: 40vh;
  width: 50vw;
}

#omschrijving {
  border: 3px solid #1a1a99;
  height: 30vh;
  width: 50vw;
}

#wrapper {
  display: flex;
  flex-direction: column;
  gap: 20px;

  padding: 40px;

}

.row {
  display:flex;
  justify-content: space-evenly;
  gap: 10px;
}

.details {
  border: 1px solid #312e2e;
  padding: 5px;
}

#edit {
  background-color: #155fbc;
  color: #FFF;
  margin-left: 20px;
  padding: 10px;
  border-radius: 5px;
}


</style>