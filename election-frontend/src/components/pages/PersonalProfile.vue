<template>
<h3>Persoonlijk Profiel</h3>
<div id="wrapper">
  <!--ProfielFoto sectie-->
  <div class="row">
  <div  id="PFS">
    <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
    <p>Naam: {{user.name}}</p>
    <p>Voorstander</p>
    <p>Provincie, stad }</p>
  </div>

  <!--Gegevens sectie-->
  <div id="gegevens">
    <p>Mijn Gegevens</p>
    <p>Naam</p>
    <p>Achternaam</p>
    <p>Gebruikersnaam</p>
    <p>Email</p>
    <p>Stad</p>
    <p>Provincie</p>
    <p>Voorstander van</p>
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



</style>