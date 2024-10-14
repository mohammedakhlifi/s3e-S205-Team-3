<template>
  <div class="forum-container">
    <h1>Forum</h1>

    <!-- Formulier om een nieuw onderwerp te plaatsen -->
    <div class="new-topic-form">
      <h3>Plaats een nieuw onderwerp</h3>
      <form @submit.prevent="postNewTopic" class="form">
        <div class="form-group">
          <label for="title">Titel</label>
          <input type="text" v-model="newTopic.title" required placeholder="Voer de titel in" />
        </div>
        <div class="form-group">
          <label for="content">Inhoud</label>
          <textarea v-model="newTopic.content" required placeholder="Voer de inhoud in"></textarea>
        </div>
        <button type="submit" class="submit-btn">Plaats Onderwerp</button>
      </form>
    </div>

    <!-- Lijst van Onderwerpen -->
    <div class="topics-list">
      <h3>Onderwerpen</h3>
      <div v-for="topic in topics" :key="topic.id" class="topic-item">
        <h4>{{ topic.title }}</h4>
        <p>{{ topic.content }}</p>

        <!-- Reacties voor elk onderwerp -->
        <div class="replies-list">
          <h5>Reacties:</h5>
          <ul>
            <li v-for="reply in topic.replies" :key="reply.id">{{ reply.content }}</li>
          </ul>
        </div>

        <!-- Formulier om een reactie te plaatsen -->
        <div class="reply-form">
          <h5>Plaats een reactie</h5>
          <form @submit.prevent="postReply(topic.id)" class="form">
            <textarea v-model="replyContent[topic.id]" placeholder="Schrijf je reactie" required></textarea>
            <button type="submit" class="submit-btn">Reageer</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      topics: [],
      newTopic: {
        title: "",
        content: "",
      },
      replyContent: {}, // Object om reacties voor elk onderwerp op te slaan
    };
  },
  methods: {
    // Haal alle onderwerpen van de server op
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics/latest")
        this.topics = response.data;
      } catch (error) {
        console.error("Fout bij het ophalen van onderwerpen:", error);
      }
    },

    // Plaats een nieuw onderwerp
    async postNewTopic() {
      try {
        await axios.post("http://localhost:8080/api/forum/topics", this.newTopic);
        this.newTopic.title = "";
        this.newTopic.content = "";
        this.fetchTopics(); // Herlaad de onderwerpen na het plaatsen
      } catch (error) {
        console.error("Fout bij het plaatsen van het onderwerp:", error);
      }
    },

    // Plaats een reactie op een onderwerp
    async postReply(topicId) {
      try {
        const reply = { content: this.replyContent[topicId] };
        await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, reply);
        this.replyContent[topicId] = ""; // Wis de reactie-inhoud
        this.fetchTopics(); // Herlaad de onderwerpen na het plaatsen van de reactie
      } catch (error) {
        console.error("Fout bij het plaatsen van de reactie:", error);
      }
    },
  },
  created() {
    this.fetchTopics(); // Haal de onderwerpen op wanneer de component wordt aangemaakt
  },
};
</script>

<style scoped>
.forum-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  max-width: 800px;
  margin: 50px auto; /* Center horizontally with margin at top */
  padding: 20px;
  background-color: #fff; /* Background color */
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

h1, h3, h4, h5 {
  color: #002f6c; /* Header color */
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
}

.new-topic-form,
.topics-list {
  margin-top: 20px;
  display: flex;
  flex-direction: column; /* Stack items vertically */
  align-items: center; /* Center the items horizontally */
  width: 100%; /* Allow full width for the forms */
}

.new-topic-form {
  padding: 20px; /* Padding to match contact form */
  width: 100%; /* Ensure full width for the form */
}

.new-topic-form h3 {
  margin-bottom: 10px;
}

.form {
  width: 100%; /* Allow form to fill container */
}

.form-group {
  margin-bottom: 15px;
  width: 100%; /* Ensure full width for form group */
}

.form-group label {
  font-weight: bold;
  color: #002f6c; /* Label color */
  display: block;
  margin-bottom: 5px;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 2px solid #002f6c; /* Border color */
  border-radius: 5px;
  background-color: #fefefe;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05);
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #ffd700; /* Focus border color */
}

textarea {
  min-height: 100px; /* Minimum height */
}

.submit-btn {
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  background-color: #ffd700; /* Button background color */
  color: #002f6c; /* Button text color */
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%; /* Full width button */
}

.submit-btn:hover {
  background-color: #002f6c; /* Button hover background */
  color: white; /* Button hover text color */
}

.topics-list h3 {
  font-size: 1.5rem;
  margin-bottom: 15px;
  text-align: center; /* Center align the topics list heading */
}

.topic-item {
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  width: 100%; /* Ensure full width for topic items */
}

.topic-item h4 {
  color: #002f6c; /* Topic title color */
  font-size: 1.3rem;
  margin-bottom: 10px;
}

.topic-item p {
  color: #555;
  font-size: 1rem;
  line-height: 1.5;
}

.replies-list h5 {
  margin-top: 20px;
  font-size: 1.1rem;
  color: #002f6c; /* Reply header color */
}

.replies-list ul {
  list-style-type: none;
  padding-left: 0;
}

.replies-list ul li {
  padding: 10px;
  background-color: #f2f2f2;
  border-radius: 5px;
  margin-bottom: 10px;
}

.reply-form {
  margin-top: 15px;
}

.reply-form textarea {
  min-height: 80px; /* Minimum height for reply textarea */
}
</style>

