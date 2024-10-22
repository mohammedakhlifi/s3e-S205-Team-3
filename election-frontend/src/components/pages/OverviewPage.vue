<template>
  <div>
    <!-- Container voor het overzicht van vragen -->
    <div class="overview-container">
      <h1>Overzicht van vragen</h1>

      <!-- Lijst van onderwerpen -->
      <div class="topics-list">
        <!-- Itereer over de topics en toon elk topic -->
        <div v-for="topic in topics" :key="topic.id" class="topic-item">
          <h4>{{ topic.title }}</h4> <!-- Titel van het topic -->
          <p>{{ topic.content }}</p> <!-- Inhoud van het topic -->
          <p class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p> <!-- Datum van plaatsing -->
        </div>
      </div>
    </div>

    <!-- Icoon rechtsonder voor navigatie naar forum -->
    <div class="icon-container" @click="goToForum">
      <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="60" height="60" viewBox="0 0 48 48">
        <path fill="#4caf50" d="M44,24c0,11.045-8.955,20-20,20S4,35.045,4,24S12.955,4,24,4S44,12.955,44,24z"></path>
        <path fill="#fff" d="M21,14h6v20h-6V14z"></path>
        <path fill="#fff" d="M14,21h20v6H14V21z"></path>
      </svg>
    </div>
  </div>
</template>

<script>
import axios from 'axios'; // Importeer axios voor API-aanroepen

export default {
  data() {
    return {
      topics: [], // Opslag voor de opgehaalde topics
    };
  },
  methods: {
    // Methode om de laatste topics van de API te halen
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest");
        this.topics = response.data; // Sla de opgehaalde topics op in de topics array
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error); // Toon foutmelding in de console
      }
    },
    // Methode om de datum te formatteren naar een leesbaar formaat
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString('nl-NL', options);
    },
    // Methode om naar de ForumPage te navigeren
    goToForum() {
      this.$router.push({ name: 'ForumPage' }); // Navigeer naar de ForumPage
    }
  },
  // Roep de fetchTopics-methode aan zodra de component is aangemaakt
  created() {
    this.fetchTopics();
  }
};
</script>

<style scoped>
/* Styling voor de overzichtspagina */
.overview-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
  color: #002f6c;
}

.topics-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* Styling voor elk topic item */
.topic-item {
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.topic-item h4 {
  color: #002f6c;
  margin-bottom: 10px;
}

.topic-item p {
  color: #555;
}

.topic-date {
  font-size: 0.9rem;
  color: #888;
  margin-top: 10px;
}

/* Styling voor het klikbare icoon rechtsonder */
.icon-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  cursor: pointer; /* Geef aan dat het icoon klikbaar is */
  z-index: 1000; /* Zorgt ervoor dat het icoon boven andere elementen staat */
}

/* Media queries voor responsiviteit */
@media (max-width: 768px) {
  .overview-container {
    margin: 20px;
    padding: 15px;
  }

  h1 {
    font-size: 1.5rem;
  }

  .topic-item {
    padding: 10px;
  }
}

@media (max-width: 480px) {
  .overview-container {
    margin: 10px;
    padding: 10px;
  }

  h1 {
    font-size: 1.2rem;
  }

  .topic-item {
    padding: 8px;
  }

  .icon-container {
    width: 50px;
    height: 50px;
    bottom: 15px;
    right: 15px;
  }
}
</style>
