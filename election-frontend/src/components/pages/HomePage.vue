<template>
  <div class="home">

    <!-- Banner Section -->
    <section class="banner">
      <div class="banner-content">
        <h1>Your Political Guide</h1>
        <p>Stay informed, make the right decision, and track votes per party and province in real-time.</p>
        <router-link to="/election-result">
          <button class="cta-btn">Explore Election Results</button>
        </router-link>
      </div>
    </section>

    <!-- Info Sections -->
    <section class="info">
      <div class="info-section">
        <h2>Discover Political Parties</h2>
        <p>Learn more about the parties participating in the elections, their ideologies, and key policies.</p>
        <router-link to="/parties">
          <button class="info-btn">View Parties</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Track Votes by Province</h2>
        <p>Get real-time insights on how each party is performing across different provinces.</p>
        <router-link to="/vote-tracking">
          <button class="info-btn">Track Votes</button>
        </router-link>
      </div>

      <div class="info-section">
        <h2>Engage with the Community</h2>
        <p>Join discussions and debates on key political topics, and share your opinions.</p>
        <router-link to="/chat">
          <button class="info-btn">Join the Conversation</button>
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
/* Home Page Styling */

/* General Layout */
.home {
  font-family: 'Arial', sans-serif;
}

/* Banner Section */
.banner {
  background-image: url('https://example.com/banner-image.jpg'); /* Replace with an actual banner image */
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
  background-color: rgba(0, 0, 0, 0.6); /* Dark overlay */
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

/* Info Sections */
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

/* Mobile Responsive */
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
