<template>
  <div>
    <!-- Container voor het overzicht van vragen -->
    <div class="overview-container">
      <h1>Overzicht van vragen</h1>

      <!-- Lijst van onderwerpen -->
      <div class="topics-list">
        <!-- Itereer over de topics en toon elk topic -->
        <div v-for="topic in topics" :key="topic.id" class="topic-item">
          <div class="topic-header">
            <div class="topic-info">
              <h4>{{ topic.title }}</h4>
              <p>{{ topic.content }}</p>
              <p class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p>
            </div>
            <!-- Knop aan de rechterkant voor het bekijken van reacties -->

          </div>

          <!-- Reageer-knop en invoerveld voor reactie -->
          <button @click="toggleReplyInput(topic.id)" class="reply-button">Reageer</button>

          <!-- Invoerveld alleen tonen als showReplyInput gelijk is aan het topic ID -->
          <div v-if="showReplyInput === topic.id" class="reply-input-container">
            <textarea v-model="newReplyContent" placeholder="Schrijf je reactie..." rows="3"></textarea>
            <button @click="submitReply(topic.id)" class="submit-reply-button">Plaats reactie</button>
          </div>

          <!-- Reacties tonen wanneer de knop is ingedrukt -->
          <div v-if="showReplies[topic.id]" class="replies-container">
            <div v-if="topic.replies && topic.replies.length">
              <h3>Reacties</h3>
              <div v-for="reply in topic.replies" :key="reply.id" class="reply-item">
                <p>{{ reply.content }}</p>
                <p class="reply-date">Antwoord gepost op: {{ formatDate(reply.createdAt) }}</p>
              </div>
            </div>
            <div v-else>
              <p>Er zijn nog geen reacties.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      topics: [],
      showReplies: {}, // Object om de zichtbaarheid van reacties per topic te controleren
      showReplyInput: null, // Controleert of het invoerveld zichtbaar is voor een specifiek topic
      newReplyContent: "", // Inhoud van de nieuwe reactie
    };
  },
  methods: {
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest");
        console.log("Opgehaalde topics:", response.data);
        this.topics = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error);
      }
    },

    formatDate(dateString) {
      if (!dateString) return 'Datum niet beschikbaar';
      const date = new Date(dateString);
      if (isNaN(date)) return 'Ongeldige datum';

      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return date.toLocaleDateString('nl-NL', options);
    },

    toggleReplies(topicId) {
      this.$set(this.showReplies, topicId, !this.showReplies[topicId]);
    },

    toggleReplyInput(topicId) {
      this.showReplyInput = this.showReplyInput === topicId ? null : topicId; // Toon/verberg invoerveld voor specifieke topic
    },

    async submitReply(topicId) {
      try {
        const response = await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, {
          content: this.newReplyContent,
        });
        console.log("Geplaatste reactie:", response.data); // Controleer de respons van de nieuwe reactie

        // Vind het topic en voeg de nieuwe reactie toe aan de replies-array
        const topic = this.topics.find(t => t.id === topicId);
        if (topic) {
          topic.replies.push(response.data); // Voeg de nieuwe reactie toe aan de replies van het topic
        }

        // Reset het invoerveld en open de uitklapsectie
        this.newReplyContent = "";
        this.showReplyInput = null;
        this.showReplies[topicId] = true;
      } catch (error) {
        console.error("Fout bij het plaatsen van de reactie:", error);
      }
    }
  },
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

.topic-item {
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  position: relative;
}

.topic-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.topic-info {
  flex-grow: 1;
}

.reply-button, .toggle-replies-button {
  margin-top: 10px;
  padding: 8px 12px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.reply-button {
  display: block;
  margin-top: 15px;
}

.replies-container {
  margin-top: 20px;
}

.reply-item {
  background-color: #f1f1f1;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border-left: 4px solid #4caf50;
}

.topic-date, .reply-date {
  font-size: 0.9rem;
  color: #888;
  margin-top: 10px;
}

.submit-reply-button{
  margin-top: 10px;
  padding: 8px 12px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

</style>
