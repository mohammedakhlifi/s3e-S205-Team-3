<template>
  <div class="home">


    <!-- Banner Sectie -->
    <section class="banner">
      <div class="banner-content">
        <h1>Jouw Politieke Gids</h1>
        <p>Blijf geïnformeerd, neem de juiste beslissing, en volg de stemmen per partij en provincie in real-time.</p>
        <router-link to="/election-result">
          <button class="cta-btn">Bekijk Verkiezingsresultaten</button>
        </router-link>
      </div>
    </section>


    <div class="snowflakes" aria-hidden="true">
      <div class="intro"> Find 250+ Ready to use demo at <a href="https://codeconvey.com">Codeconvey.com</a></div>
      <div class="snowflake">
        ❅
      </div>
      <div class="snowflake">
        ❅
      </div>
      <div class="snowflake">
        ❆
      </div>
      <div class="snowflake">
        ❄
      </div>
      <div class="snowflake">
        ❅
      </div>
      <div class="snowflake">
        ❆
      </div>
      <div class="snowflake">
        ❄
      </div>
      <div class="snowflake">
        ❅
      </div>
      <div class="snowflake">
        ❆
      </div>
      <div class="snowflake">
        ❄
      </div>
    </div>

    <!-- Info Secties -->
    <section class="info">
      <div class="info-section">
        <h2>Ontdek Politieke Partijen</h2>
        <p>Leer meer over de partijen die deelnemen aan de verkiezingen, hun ideologieën en kernbeleid.</p>
        <router-link to="/parties">
          <button class="info-btn">Bekijk Partijen</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Volg Stemmen per Provincie</h2>
        <p>Krijg real-time inzicht in hoe elke partij presteert in verschillende provincies.</p>
        <router-link to="/election-result">
          <button class="info-btn">Volg Stemmen</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Doe Mee met de Gemeenschap</h2>
        <p>Doe mee aan discussies en debatten over belangrijke politieke onderwerpen, en deel je mening.</p>
        <router-link to="/forum/overview">
          <button class="info-btn">Doe Mee aan de Discussie</button>
        </router-link>
      </div>
    </section>

  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HomePage',
});
</script>

<style scoped>
/* Styling voor de Home Pagina */

/* Algemene Layout */
.home {
  font-family: 'Arial', sans-serif;
}

/* Banner Sectie */
.banner {
  background-image: url('@/assets/img/tweedekamer.jpg'); /* Zorg ervoor dat het pad naar je afbeelding klopt */
  background-size: cover;
  background-position: center;
  height: 70vh; /* Pas de hoogte aan als dat nodig is */
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: white;
}

body{
  background:red;
}
.intro,
.intro a{
  color:#fff;
  font-family:
  }
/* customizable snowflake styling */
.snowflake {
  color: #fff;
  font-size: 1em;
  font-family: Arial;
  text-shadow: 0 0 1px #000;
}

@-webkit-keyframes snowflakes-fall{0%{top:-10%}100%{top:100%}}@-webkit-keyframes snowflakes-shake{0%{-webkit-transform:translateX(0px);transform:translateX(0px)}50%{-webkit-transform:translateX(80px);transform:translateX(80px)}100%{-webkit-transform:translateX(0px);transform:translateX(0px)}}@keyframes snowflakes-fall{0%{top:-10%}100%{top:100%}}@keyframes snowflakes-shake{0%{transform:translateX(0px)}50%{transform:translateX(80px)}100%{transform:translateX(0px)}}.snowflake{position:fixed;top:-10%;z-index:9999;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;cursor:default;-webkit-animation-name:snowflakes-fall,snowflakes-shake;-webkit-animation-duration:10s,3s;-webkit-animation-timing-function:linear,ease-in-out;-webkit-animation-iteration-count:infinite,infinite;-webkit-animation-play-state:running,running;animation-name:snowflakes-fall,snowflakes-shake;animation-duration:10s,3s;animation-timing-function:linear,ease-in-out;animation-iteration-count:infinite,infinite;animation-play-state:running,running}.snowflake:nth-of-type(0){left:1%;-webkit-animation-delay:0s,0s;animation-delay:0s,0s}.snowflake:nth-of-type(1){left:10%;-webkit-animation-delay:1s,1s;animation-delay:1s,1s}.snowflake:nth-of-type(2){left:20%;-webkit-animation-delay:6s,.5s;animation-delay:6s,.5s}.snowflake:nth-of-type(3){left:30%;-webkit-animation-delay:4s,2s;animation-delay:4s,2s}.snowflake:nth-of-type(4){left:40%;-webkit-animation-delay:2s,2s;animation-delay:2s,2s}.snowflake:nth-of-type(5){left:50%;-webkit-animation-delay:8s,3s;animation-delay:8s,3s}.snowflake:nth-of-type(6){left:60%;-webkit-animation-delay:6s,2s;animation-delay:6s,2s}.snowflake:nth-of-type(7){left:70%;-webkit-animation-delay:2.5s,1s;animation-delay:2.5s,1s}.snowflake:nth-of-type(8){left:80%;-webkit-animation-delay:1s,0s;animation-delay:1s,0s}.snowflake:nth-of-type(9){left:90%;-webkit-animation-delay:3s,1.5s;animation-delay:3s,1.5s}
/* Demo Purpose Only*/
.demo {
  font-family: 'Raleway', sans-serif;
  color:#fff;
  display: block;
  margin: 0 auto;
  padding: 15px 0;
  text-align: center;
}
.demo a{
  font-family: 'Raleway', sans-serif;
  color: #000;
}

.banner-content {
  background-color: rgba(0, 0, 0, 0.6); /* Donkere overlay */
  padding: 30px;
  border-radius: 10px;
}

.banner-content h1 {
  font-size: 3rem;
  margin-bottom: 20px;
}

.banner-content p {
  font-size: 1.2rem;
  margin-bottom: 30px;
}

.cta-btn {
  background-color: #ff4500;
  color: white;
  border: none;
  padding: 15px 30px;
  font-size: 1.2rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.cta-btn:hover {
  background-color: #ff5714;
}

/* Info Secties */
.info {
  display: flex;
  justify-content: space-around;
  margin: 50px 0;
  padding: 0 30px;
}

.info-section {
  text-align: center;
  width: 30%;
}

.info-section h2 {
  font-size: 1.8rem;
  margin-bottom: 15px;
}

.info-section p {
  font-size: 1rem;
  margin-bottom: 20px;
}

.info-btn {
  background-color: #000054;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.info-btn:hover {
  background-color: #1a1a99;
}

/* Mobiel Responsief */
@media (max-width: 900px) {
  .banner-content h1 {
    font-size: 2.5rem;
  }

  .info {
    flex-direction: column;
    align-items: center;
  }

  .info-section {
    width: 90%;
    margin-bottom: 30px;
  }
}
</style>
