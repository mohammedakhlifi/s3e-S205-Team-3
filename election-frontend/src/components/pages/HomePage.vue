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
        <router-link to="/vote-tracking">
          <button class="info-btn">Volg Stemmen</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Doe Mee met de Gemeenschap</h2>
        <p>Doe mee aan discussies en debatten over belangrijke politieke onderwerpen, en deel je mening.</p>
        <router-link to="/quiz">
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
  background-image: url('https://example.com/banner-image.jpg'); /* Vervang door een echte banner afbeelding */
  background-size: cover;
  background-position: center;
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: white;
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
