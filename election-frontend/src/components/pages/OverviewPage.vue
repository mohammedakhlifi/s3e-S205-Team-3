<template>
  <div>
    <!-- Overzicht van vragen -->
    <div class="overview-container">
      <h1>Overzicht van vragen</h1>

      <!-- Lijst van onderwerpen -->
      <div class="topics-list">

        <div v-for="topic in topics" :key="topic.id" class="topic-item">
          <!-- v-for: Itereert over de lijst met topics die in data() zijn opgeslagen, voor elke topic maakt het een nieuwe HTML-blok aan. -->
          <div class="topic-header">
            <div class="topic-info">
              <h4 @click="$router.push(`/forum/topics/${topic.id}`)" class="clickable">{{ topic.title }}</h4>
              <!-- @click: Het klik-attribuut is verbonden aan een functie die de router gebruikt om te navigeren. -->
              <p>{{ topic.content }}</p>
              <p class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p>
              <!-- formatDate: Zet een onbewerkte datum om naar een leesbaar formaat. -->

              <!-- Klikbaar aantal reacties -->
              <p
                  class="topic-reply-count clickable"
                  @click="toggleReplies(topic.id)"
              >
                Reacties: {{ topic.replies ? topic.replies.length : 0 }}
              </p>
              <!-- @click: Klik op aantal reacties wisselt tussen tonen en verbergen van de reacties. -->
            </div>
          </div>

          <!-- Reacties container -->
          <div v-if="showReplies[topic.id]" class="replies-container">
            <!-- v-if: Controleert of showReplies[topic.id] waar is. Als dat klopt, worden de reacties van het topic getoond. -->
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
            <!-- v-model: Het inputveld is verbonden met repliesContent[topic.id]. Wat de gebruiker typt, wordt opgeslagen in dit object. -->
            <!-- @keydown.enter.prevent: Roept submitReply(topic.id) aan. Prevent voorkomt dat Enter een nieuwe regel toevoegt. -->
            <button
                class="reply-submit-button"
                @click="submitReply(topic.id)"
            >
              Verstuur
            </button>
            <!-- De knop stuurt de reactie naar de backend door submitReply(topic.id) aan te roepen. -->
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
    <!-- SVG-knop: Bij klikken voert deze de goToForum-methode uit, die de gebruiker naar de forumpagina navigeert. -->
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      topics: [], // Topics: Bevat een lijst van topics, opgehaald via de fetchTopics()-functie.
      showReplies: {}, // showReplies: Object dat controleert welke reacties per topic zichtbaar zijn.
      repliesContent: {}, // repliesContent: Slaat de tekst op die een gebruiker invoert als reactie per topic.
    };
  },
  methods: {
    // fetchTopics(): Stuurt een HTTP GET-verzoek naar de backend om de laatste topics op te halen. De data wordt opgeslagen in topics.
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
    // formatDate(dateString): Formatteert een datum naar een leesbare Nederlandse stijl.

    // toggleReplies(topicId): Wisselt tussen tonen en verbergen van reacties door showReplies[topicId] te toggelen.
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
        // submitReply(topicId): Controleert of het inputveld niet leeg is, stuurt een HTTP POST-verzoek naar de backend en voegt de nieuwe reactie toe aan de topics-lijst.
        this.repliesContent[topic.id] = "";
        this.showReplies[topicId] = true; // Zorg dat reacties blijven staan
      } catch (error) {
        console.error("Fout bij het plaatsen van de reactie:", error);
      }
    },

    // goToForum(): Navigeert naar de algemene forumpagina via de Vue-router.
    goToForum() {
      this.$router.push("/forum");
    },

  },
  // created(): Wordt uitgevoerd bij het laden van de component en roept fetchTopics() aan om topics op te halen.
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

.icon-container {
  position: fixed; /* Zorg ervoor dat het icoon op een vaste positie blijft bij scrollen */
  bottom: 20px; /* Plaats het icoon 20px van de onderkant */
  right: 20px; /* Plaats het icoon 20px van de rechterkant */
  cursor: pointer; /* Zorg voor een pointer-cursor bij hover */
}



.reply-submit-button:focus {
  outline: none;
  box-shadow: 0 0 4px rgba(76, 175, 80, 0.8);
}
</style>
