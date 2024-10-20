<template>
  <div class="overview-container">
    <h1>Overzicht van vragen</h1>

    <!-- Lijst van onderwerpen -->
    <div class="topics-list">
      <div v-for="topic in topics" :key="topic.id" class="topic-item">
        <h4>{{ topic.title }}</h4>
        <p>{{ topic.content }}</p>
        <p class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      topics: [], // Opslag voor de opgehaalde topics
    };
  },
  methods: {
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest");
        this.topics = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error);
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString('nl-NL', options);
    }
  },
  created() {
    this.fetchTopics(); // Haal de onderwerpen op wanneer de component wordt aangemaakt
  }
};
</script>

<style scoped>
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
</style>
