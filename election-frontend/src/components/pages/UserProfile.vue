<template>

  <div class="userProfile">
<div id="mainInfo">
  <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
  <div id="infoDetails">
  <h3>Gebruikersnaam</h3>
  <h3>Voorstander</h3>
  <h3>Amsterdam, Noord-Holland</h3>
  <p id="description">Omschrijving</p>
  </div>
</div>
  </div>
  <div id="platforms" class="userProfile">

  </div>

</template>

<script>
import {defineComponent} from "vue";
export default defineComponent({
  name: "UserProfile",
  data() {
    return {
      username: {},
      voorstander: {},
      partij: {},
      omschrijving: {},
      platformen: {}
    }
  },

  methods: {
    async fetchUserInfo() {


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