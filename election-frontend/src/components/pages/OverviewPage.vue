<template>
  <div>
    <!-- Overzicht van vragen -->
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

              <!-- Klikbaar aantal reacties -->
              <p
                  class="topic-reply-count clickable"
                  @click="toggleReplies(topic.id)"
              >
                Reacties: {{ topic.replies ? topic.replies.length : 0 }}
              </p>
            </div>
          </div>

          <!-- Reacties container -->
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

          <!-- Invoerveld en knop voor reacties -->
          <div class="reply-input-container">
            <textarea
                v-model="repliesContent[topic.id]"
                placeholder="Schrijf je reactie..."
                rows="3"
                @keydown.enter.prevent="submitReply(topic.id)"
                class="reply-textarea"
            ></textarea>
            <button
                class="reply-submit-button"
                @click="submitReply(topic.id)"
            >
              Verstuur
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Plus-knop -->
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
import axios from "axios";

export default {
  data() {
    return {
      topics: [], // Lijst met topics
      showReplies: {}, // Object om zichtbaarheid van reacties te controleren
      repliesContent: {}, // Inhoud van de reacties per topic
    };
  },
  methods: {
    // Haal topics op van de backend
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest");
        this.topics = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error);
      }
    },

    // Formatteer de datum
    formatDate(dateString) {
      if (!dateString) return "Datum niet beschikbaar";
      const date = new Date(dateString);
      if (isNaN(date)) return "Ongeldige datum";

      const options = {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      };
      return date.toLocaleDateString("nl-NL", options);
    },

    // Toon of verberg reacties
    toggleReplies(topicId) {
      this.$set(this.showReplies, topicId, !this.showReplies[topicId]);
    },

    // Plaats een nieuwe reactie
    async submitReply(topicId) {
      try {
        const content = this.repliesContent[topicId];
        if (!content || content.trim() === "") {
          console.warn("Reactie-inhoud is leeg");
          return;
        }

        const response = await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, {
          content,
        });

        // Voeg de nieuwe reactie toe aan de juiste topic
        const topic = this.topics.find((t) => t.id === topicId);
        if (topic) {
          topic.replies = topic.replies || [];
          topic.replies.push(response.data);
        }

        // Reset het invoerveld en open reactiesectie
        this.repliesContent[topic.id] = "";
        this.showReplies[topicId] = true; // Zorg dat reacties blijven staan
      } catch (error) {
        console.error("Fout bij het plaatsen van de reactie:", error);
      }
    },

    // Navigeer naar de forum pagina
    goToForum() {
      this.$router.push("/forum"); // Zorg dat deze route bestaat
    },
  },
  created() {
    this.fetchTopics();
  },
};
</script>

<style>
/* Overzicht container styling */
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

/* Reacties sectie */
.replies-container {
  margin-top: 20px;
}

.reply-item {
  padding: 10px;
  margin-bottom: 10px;
  background-color: #f1f1f1;
  border-radius: 5px;
  border-left: 4px solid #4caf50;
}

/* Invoerveld en knop styling */
.reply-input-container {
  display: flex;
  gap: 10px;
  margin-top: 10px;
  align-items: center;
}

.reply-textarea {
  flex: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  resize: none;
}

.reply-submit-button {
  padding: 8px 16px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.reply-submit-button:hover {
  background-color: #45a049;
}

.reply-submit-button:focus {
  outline: none;
  box-shadow: 0 0 4px rgba(76, 175, 80, 0.8);
}
</style>
