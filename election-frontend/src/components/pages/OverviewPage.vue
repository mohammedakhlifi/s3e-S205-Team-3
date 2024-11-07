<template>
  <div>
    <!-- Container voor het overzicht van vragen -->
    <div class="overview-container">
      <h1>Overzicht van vragen</h1>

      <!-- Lijst van onderwerpen -->
      <div class="topics-list">
        <!-- Itereer over de topics en toon elk topic -->
        <div v-for="topic in topics" :key="topic.id" class="topic-item">
          <h4>{{ topic.title }}</h4>
          <p>{{ topic.content }}</p>
          <p class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p>

          <!-- Reageer-knop en invoerveld voor reactie -->
          <button @click="toggleReplyInput(topic.id)" class="reply-button">Reageer</button>

          <!-- Invoerveld alleen tonen als showReplyInput gelijk is aan het topic ID -->
          <div v-if="showReplyInput === topic.id" class="reply-input-container">
            <textarea v-model="newReplyContent" placeholder="Schrijf je reactie..." rows="3"></textarea>
            <button @click="submitReply(topic.id)" class="submit-reply-button">Plaats reactie</button>
          </div>
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
import axios from 'axios';

export default {
  data() {
    return {
      topics: [],
      showReplyInput: null, // Controleert of het invoerveld zichtbaar is voor een specifiek topic
      newReplyContent: "", // Inhoud van de nieuwe reactie
    };
  },
  methods: {
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest");
        console.log("Opgehaalde topics:", response.data); // Log de hele data om te controleren
        this.topics = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error);
      }
    },


    formatDate(dateString) {
      console.log("Datum ontvangen door formatDate:", dateString); // Log de ontvangen datum
      if (!dateString) return "Datum niet beschikbaar";
      const date = new Date(dateString);
      if (isNaN(date)) return "Ongeldige datum";

      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return date.toLocaleDateString('nl-NL', options);
    }
    ,


    goToForum() {
      this.$router.push({ name: 'ForumPage' });
    },

    toggleReplyInput(topicId) {
      this.showReplyInput = this.showReplyInput === topicId ? null : topicId; // Toon/verberg invoerveld voor specifieke topic
    },

    async submitReply(topicId) {
      try {
        await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, {
          content: this.newReplyContent,
        });
        this.newReplyContent = ""; // Reset het invoerveld na het verzenden
        this.showReplyInput = null; // Verberg het invoerveld
        await this.fetchTopics(); // Haal de topics opnieuw op om data te verversen
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

/* Reageer-knop styling */
.reply-button {
  margin-top: 10px;
  padding: 8px 12px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* Invoerveld styling */
.reply-input-container {
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: vertical;
}

.submit-reply-button {
  align-self: flex-end;
  padding: 8px 12px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* Icoon rechtsonder styling */
.icon-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  cursor: pointer;
  z-index: 1000;
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
