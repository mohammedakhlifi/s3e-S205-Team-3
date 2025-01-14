<template>

  <div class="userProfile">
<div id="mainInfo">
  <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
  <div id="infoDetails">
    <h2 class="details">Naam: {{user.name}}</h2>
  <h3 class="details">Voorstander van de partij: {{user.voorstander}}</h3>
  <h3 class="details">{{user.city}}, {{user.province}}</h3>
  <p class="details" id="description">{{user.profielOmschrijving}}</p>
  </div>
</div>
  </div>
  <div id="platforms" class="userProfile">
    <div class="left-platforms">
      <p>{{user.social1}}</p>
      <p>{{user.social2}}</p>
    </div>
    <div class="right-platforms">
      <p>{{user.social3}}</p>
      <p>{{user.social4}}</p>
    </div>

    <!-- Footer Sectie -->
    <footer class="footer">
      <div class="footer-content">
        <div class="footer-section">
          <h3>Contact</h3>
          <p>Email: info@politiekgids.nl</p>
          <p>Telefoon: +31 20 123 4567</p>
        </div>
        <div class="footer-section">
          <h3>Volg Ons</h3>
          <ul>
            <li><a href="#" class="social-link">Facebook</a></li>
            <li><a href="#" class="social-link">Twitter</a></li>
            <li><a href="#" class="social-link">Instagram</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>Informatie</h3>
          <ul>
            <li><a href="#" class="footer-link">Privacybeleid</a></li>
            <li><a href="#" class="footer-link">Algemene Voorwaarden</a></li>
            <li><a href="#" class="footer-link">Cookiebeleid</a></li>
          </ul>
        </div>
      </div>
      <div class="footer-bottom">
        <p>&copy; 2025 Politieke Gids. Alle rechten voorbehouden.</p>
      </div>
    </footer>
  </div>

</template>

<script>
import axios from 'axios';
import {defineComponent} from "vue";
export default defineComponent({

  data() {
    return {
      user: {
        name: "",
        voorstander: "",
        province: "",
        city: "",
        partij: "",
        profielOmschrijving: "",
        platformen: "",
        social1: "",
        social2: "",
        social3: "",
        social4: "",
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

.details {
  color: #555;
  padding: 10px 0;
  border-bottom: 1px solid #e0e0e0;
}

#platforms {
  display: flex;
  justify-content: space-between; /* Zorgt voor ruimte tussen de twee groepen */
  width: 50%; /* Brede container */
}

.left-platforms,
.right-platforms {
  display: flex; /* Horizontale uitlijning */
  flex-direction: column;
  gap: 20px; /* Horizontale ruimte tussen items */
  font-size: 21px;
}

.left-platforms {
  justify-content: flex-start; /* Links uitlijnen */
}

.right-platforms {
  justify-content: flex-end; /* Rechts uitlijnen */
}




</style>