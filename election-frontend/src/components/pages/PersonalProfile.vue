<template>
<h3>Persoonlijk Profiel</h3>

  <!--ProfielFoto sectie-->
  <div id="PFS">

    <img src="../../assets/img/defpic3%20(2).jpg" alt="default profile picture">
    <p>Naam</p>
    <p>Voorstander</p>
    <p>Provincie, stad</p>
  </div>

  <!--Platformen sectie-->
  <div id="platformen">
   Platformen
  </div>

  <!--Gegevens sectie-->
<div id="gegevens">
  Mijn Gegevens
  <p>Naam</p>
  <p>Achternaam</p>
  <p>Gebruikersnaam</p>
  <p>Email</p>
  <p>Stad</p>
  <p>Provincie</p>
  <p>Voorstander van</p>
  <button id="edit">Bewerken</button>
</div>

  <!--Omschrijving sectie-->
  <div id="omschrijving">

  </div>


</template>


<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PersonalProfile',
});
</script>




<style scoped>

#PFS {
  border: 1px solid #1a1a99;
}



</style>