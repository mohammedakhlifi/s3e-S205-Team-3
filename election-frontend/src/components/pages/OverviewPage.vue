<template>
  <div>
    <!-- Overzicht van vragen -->
    <div class="overview-container">
      <h1>Overzicht van vragen.</h1>

      <!-- Lijst van onderwerpen -->
      <div class="topics-list">
        <div v-for="topic in topics" :key="topic.id" class="topic-item">
          <div class="topic-header">
            <div class="topic-info">
              <h4>{{ topic.title }}</h4>
              <p>{{ topic.content }}</p>
              <p class="topic-date">Gepost op: {{ formatDate(topic.createdAt) }}</p>

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
      <!-- SVG Icon -->
    </div>

    <!-- De ReplyModal component -->

    **Nieuwe versie**:
    ```html
    <ReplyModel
        :showModal="showModal"
        :currentTopic="currentTopic"
        :modelValue="newReplyContent"
        @update:modelValue="newReplyContent = $event"
        @closeModal="closeModal"
        @submitReply="submitReply"
    />

  </div>
</template>

<script>
import axios from "axios";
import ReplyModel from "@/components/pages/ReplyModel.vue";



export default {
  components: {
    ReplyModel,
  },
  data() {
    return {
      topics: [],
      showModal: false,
      currentTopic: null,
      newReplyContent: "", // Dit is de data die via v-model wordt gebonden
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

    openRepliesModal(topic) {
      this.currentTopic = { ...topic };
      this.showModal = true;
    },

    closeModal() {
      this.showModal = false;
      this.currentTopic = null;
      this.newReplyContent = "";
    },

    async submitReply(topicId, content) {
      try {
        const trimmedContent = content.trim();
        if (!trimmedContent) {
          alert("De reactie mag niet leeg zijn!");
          return;
        }

        const response = await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, {
          content: trimmedContent,
        });

        if (this.currentTopic) {
          if (!this.currentTopic.replies) this.currentTopic.replies = [];
          this.currentTopic.replies.push(response.data);
        }

        this.newReplyContent = "";
      } catch (error) {
        console.error("Fout bij het plaatsen van de reactie:", error);
      }
    },

    goToForum() {
      this.$router.push("/forum");
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
  color: #4caf50;
  margin-bottom: 5px;
}

.topic-reply-count {
  font-size: 0.9rem;
  color: #555;
  margin-top: 5px;
  cursor: pointer;
}

.topic-reply-count:hover {
  color: #4caf50;
}

.icon-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background-color: #4caf50;
  border-radius: 50%;
  padding: 15px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  cursor: pointer;
}

.icon-container svg {
  width: 30px;
  height: 30px;
  color: white;
}
</style>
