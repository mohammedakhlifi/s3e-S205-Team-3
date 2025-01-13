<template>
  <div>
    <!-- Overzicht van vragen -->
    <div class="overview-container">
      <h1>Overzicht van vragen.</h1>

      <!-- Lijst van onderwerpen -->
      <div class="topics-list">
        <!-- Itereer over de topics en toon elk topic -->
        <div v-for="topic in topics" :key="topic.id" class="topic-item">
          <div class="topic-header">
            <div class="topic-info">
              <h4>{{ topic.title }}</h4>
              <p>{{ topic.content }}</p>
              <p class="topic-date">Gepost door <router-link :to="{ name: 'UserProfile', params: { username: topic.createdBy } }" class="custom-router-link">
                {{ topic.createdBy }}
              </router-link> op: {{ formatDate(topic.createdAt) }}</p>

              <!-- Klikbaar aantal reacties -->
              <p
                  class="topic-reply-count clickable"
                  @click="openRepliesModal(topic)"
              >
                Reacties: {{ topic.replies ? topic.replies.length : 0 }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Plus-knop -->
    <div class="icon-container" @click="goToForum">
      <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="60" height="60" viewBox="0 0 48 48">
        <path fill="#393EC2" d="M44,24c0,11.045-8.955,20-20,20S4,35.045,4,24S12.955,4,24,4S44,12.955,44,24z"></path>
        <path fill="#fff" d="M21,14h6v20h-6V14z"></path>
        <path fill="#fff" d="M14,21h20v6H14V21z"></path>
      </svg>
    </div>

    <!-- Pop-up Modal -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <h2>Reacties voor: {{ currentTopic?.title }}</h2>

        <!-- Scrollbare reactiescontainer -->
        <div class="replies-container">
          <div v-if="currentTopic?.replies && currentTopic.replies.length">
            <div v-for="reply in currentTopic.replies" :key="reply.id" class="reply-item">
              <p>{{ reply.content }}</p>
              <p class="reply-date">Antwoord gepost op: {{ formatDate(reply.createdAt) }}</p>
            </div>
          </div>
          <div v-else>
            <p>Er zijn nog geen reacties.</p>
          </div>
        </div>

        <!-- Reactie invoerveld -->
        <div class="reply-input-container">
          <textarea
              v-model="newReplyContent"
              placeholder="Schrijf je reactie..."
              rows="3"
          ></textarea>
          <button @click="submitReply(currentTopic.id)" class="submit-reply-button">
            Plaats reactie
          </button>
        </div>

        <!-- Sluitknop -->
        <button class="close-button" @click="closeModal">Sluiten</button>
      </div>
    </div>
    <!-- Footer Sectie -->
    <footer class="footer">
      <div class="footer-content">
        <div class="footer-section">
          <h3>Contact</h3>
          <p>Email: info@politiekgids.nl</p>
          <p>Telefoon: +31 20 123 4567</p>
        </div>
        <div class="footer-section">
          <h3>Volg Ons</h3>
          <ul>
            <li><a href="#" class="social-link">Facebook</a></li>
            <li><a href="#" class="social-link">Twitter</a></li>
            <li><a href="#" class="social-link">Instagram</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>Informatie</h3>
          <ul>
            <li><a href="#" class="footer-link">Privacybeleid</a></li>
            <li><a href="#" class="footer-link">Algemene Voorwaarden</a></li>
            <li><a href="#" class="footer-link">Cookiebeleid</a></li>
          </ul>
        </div>
      </div>
      <div class="footer-bottom">
        <p>&copy; 2025 Politieke Gids. Alle rechten voorbehouden.</p>
      </div>
    </footer>
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
      showModal: false, // Toont de pop-up modal
      currentTopic: null, // Huidig geselecteerde topic
      newReplyContent: "", // Reactie-inhoud
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

    // Open de pop-up modal en stel het geselecteerde topic in
    openRepliesModal(topic) {
      this.currentTopic = { ...topic }; // Maak een kopie van het topic
      this.showModal = true;
    },

    // Sluit de pop-up modal
    closeModal() {
      this.showModal = false;
      this.currentTopic = null;
      this.newReplyContent = "";
    },

    // Plaats een nieuwe reactie
    async submitReply(topicId) {
      try {
        const content = this.newReplyContent.trim();
        if (!content) {
          alert("De reactie mag niet leeg zijn!");
          return;
        }

        // Verstuur de reactie naar de backend
        const response = await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, {
          content,
        });

        // Voeg de nieuwe reactie toe aan de huidige lijst (alleen in de modal)
        if (this.currentTopic) {
          if (!this.currentTopic.replies) this.currentTopic.replies = [];
          this.currentTopic.replies.push(response.data);
        }

        // Reset het invoerveld
        this.newReplyContent = "";
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


<style scoped>
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
  position: relative;
}

.topic-info h4 {
  font-weight: bold;
  font-size: 1.2rem;
  color: #393EC2;
  margin-bottom: 5px;
}

.topic-reply-count {
  font-size: 0.9rem;
  color: #555;
  margin-top: 5px;
  cursor: pointer;
}

.topic-reply-count:hover {
  color: #393EC2;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  max-width: 600px;
  width: 100%;
  max-height: 80vh;
  overflow-y: auto;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

.replies-container {
  flex-grow: 1;
  max-height: 300px;
  overflow-y: auto;
  margin-bottom: 15px;
}

.reply-item {
  background-color: #f1f1f1;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border-left: 4px solid #4caf50;
}

.reply-input-container textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.close-button {
  margin-top: 15px;
  padding: 8px 12px;
  background-color: red;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.close-button:hover {
  background-color: darkred;
}

/* Styling voor de Plaats reactie-knop */
.submit-reply-button {
  margin-top: 10px;
  padding: 10px 15px;
  background-color: #393EC2; /* Groene kleur */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.submit-reply-button:hover {
  background-color: #45a049; /* Iets donkerder groen bij hover */
}

.submit-reply-button:active {
  background-color: #3e8e41; /* Nog iets donkerder bij klik */
}

.topic-item p,
.topic-info p {
  word-wrap: break-word; /* Breekt lange woorden af */
  overflow-wrap: break-word; /* Compatibiliteit met moderne browsers */
  white-space: normal; /* Zorg dat tekst meerdere regels kan gebruiken */
  max-width: 100%; /* Houd de tekst binnen de container */
}

.topic-item {
  word-wrap: break-word; /* Lange woorden afbreken */
  overflow-wrap: break-word;
}

/* Footer Styling met Donkerblauwe Achtergrond */
.footer {
  background-color: #002f6c;
  padding: 50px 0;
  color: white;
  font-size: 1rem;
}

.footer-content {
  display: flex;
  justify-content: space-around;
  margin-bottom: 30px;
}

.footer-section {
  width: 30%;
}

.footer-section h3 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.footer-section p,
.footer-section ul {
  font-size: 1rem;
}

.footer-section ul {
  list-style: none;
  padding-left: 0;
}

.footer-section ul li {
  margin-bottom: 10px;
}

.footer-section ul li a {
  color: white;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-section ul li a:hover {
  color: #ffd700;
}

.footer-bottom {
  text-align: center;
  border-top: 1px solid #ddd;
  padding-top: 20px;
}

.footer-bottom p {
  font-size: 0.9rem;
  color: #ddd;
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
  }

  .footer-content {
    flex-direction: column;
    align-items: center;
  }

  .footer-section {
    width: 80%;
    text-align: center;
    margin-bottom: 20px;
  }
}

.custom-router-link {
  text-decoration: none;
  color: inherit;
  cursor: pointer;
  font-weight: normal;
}

.custom-router-link:hover {
  color: #4caf50;
  text-decoration: underline;

  .custom-router-link:focus {
    outline: none;
  }

  .icon-container {
    position: fixed; /* Of 'absolute' als hij binnen de container moet blijven */
    right: 20px;     /* Afstand vanaf de rechterkant */
    bottom: 20px;    /* Afstand vanaf de onderkant */
    cursor: pointer;
    z-index: 1000;   /* Zorg dat de knop boven andere elementen blijft */
  }

}

</style>