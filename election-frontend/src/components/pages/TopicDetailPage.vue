<template>
  <div class="topic-detail-container">
    <h2 v-if="topic">{{ topic.title }}</h2>
    <p v-if="topic">{{ topic.content }}</p>
    <p v-if="topic" class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p>
    <p v-else>Geen gegevens beschikbaar voor dit topic.</p>

    <!-- Weergave van reacties -->
    <div v-if="topic && topic.replies && topic.replies.length" class="replies-container">
      <h3>Reacties</h3>
      <div v-for="reply in topic.replies" :key="reply.id" class="reply-item">
        <p>{{ reply.content }}</p>
        <p class="reply-date">Antwoord gepost op: {{ formatDate(reply.createdAt) }}</p>
      </div>
    </div>
    <div v-else-if="topic">
      <p>Er zijn nog geen reacties.</p>
    </div>
  </div>
</template>



<script>
import axios from 'axios';

export default {
  props: ['id'],
  data() {
    return {
      topic: null,
    };
  },
  methods: {
    async fetchTopic() {
      try {
        const response = await axios.get(`http://localhost:8080/api/forum/topics/${this.id}`);
        console.log("Respons van de server:", response.data); // Log de volledige respons
        this.topic = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van het topic:", error);
      }
    }

    ,
    formatDate(dateString) {
      if (!dateString) return 'Datum niet beschikbaar';
      const date = new Date(dateString);
      if (isNaN(date)) return 'Ongeldige datum';

      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return date.toLocaleDateString('nl-NL', options);
    }
  },
  created() {
    this.fetchTopic();
  }
};
</script>
