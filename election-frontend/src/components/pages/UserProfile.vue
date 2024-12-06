<template>

  <div class="userProfile">
<div id="mainInfo">
  <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
  <div id="infoDetails">
    <h2>{{user.name}}</h2>
  <h3>{{user.voorstander}}</h3>
  <h3>{{user.city}}, {{user.province}}</h3>
  <p id="description">{{user.profielOmschrijving}}</p>
  </div>
</div>
  </div>
  <div id="platforms" class="userProfile">
    <h2>Volg {{user.name}}</h2>
  </div>

</template>

<script>
import axios from 'axios';
import {defineComponent} from "vue";
export default defineComponent({

  data() {
    return {
      user: {
        name: "", // Haal de gebruikersnaam uit de route-parameters,
        voorstander: "",
        province: "",
        city: "",
        partij: "",
        profielOmschrijving: "",
        platformen: "",
      },
      message: "",
    }
  },
  mounted() {
    this.fetchUserInfoByName();
  },
  methods: {
    async fetchUserInfoByName() {
    try {
      const username = this.$route.params.username;
      const response = await axios.get(`http://localhost:8080/api/user/name`, {
        params: { name: username }
      })

      this.user = response.data;
      console.log("user: ", this.user)
      console.log("Gebruikersinformatie: ", response.data);

        console.log("response: ", response.data)
      console.log("username: ", username)
    }catch (error) {
      console.error('its an error:', error.response.status);
    }

      }
    }


})


</script>

<style>

.userProfile {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  background-color: #f5f8fc;
  max-width: 1200px;
  margin: 80px auto;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.25);
  border-radius: 15px;
}

#platforms {
  max-width: 700px ;
}

#mainInfo {
  display: flex;
  align-items: flex-start;
  background-color: white;
  width: 800px;
  padding: 40px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.10);
  border-radius: 10px;
  gap: 12vw;
}

#infoDetails {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 10px;
}




</style>