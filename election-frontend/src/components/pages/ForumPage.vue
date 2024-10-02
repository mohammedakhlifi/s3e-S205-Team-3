<template>
  <div class="forum-container">
    <h1>Forum</h1>

    <!-- Form to Post a New Topic -->
    <div class="new-topic-form">
      <h3>Post a New Topic</h3>
      <form @submit.prevent="postNewTopic">
        <div class="form-group">
          <label for="title">Title</label>
          <input type="text" v-model="newTopic.title" required />
        </div>
        <div class="form-group">
          <label for="content">Content</label>
          <textarea v-model="newTopic.content" required></textarea>
        </div>
        <button type="submit">Post Topic</button>
      </form>
    </div>

    <!-- List of Topics -->
    <div class="topics-list">
      <h3>Topics</h3>
      <div v-for="topic in topics" :key="topic.id" class="topic-item">
        <h4>{{ topic.title }}</h4>
        <p>{{ topic.content }}</p>

        <!-- Replies for each topic -->
        <div class="replies-list">
          <h5>Replies:</h5>
          <ul>
            <li v-for="reply in topic.replies" :key="reply.id">{{ reply.content }}</li>
          </ul>
        </div>

        <!-- Form to Post a Reply -->
        <div class="reply-form">
          <h5>Post a Reply</h5>
          <form @submit.prevent="postReply(topic.id)">
            <textarea v-model="replyContent[topic.id]" placeholder="Write your reply"></textarea>
            <button type="submit">Reply</button>
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
      replyContent: {}, // Object to store reply for each topic
    };
  },
  methods: {
    // Fetch all topics from the server
    async fetchTopics() {
      try {
        const response = await axios.get("http://localhost:8080/api/forum/topics");
        this.topics = response.data;
      } catch (error) {
        console.error("Error fetching topics:", error);
      }
    },

    // Post a new topic
    async postNewTopic() {
      try {
        await axios.post("http://localhost:8080/api/forum/topics", this.newTopic);
        this.newTopic.title = "";
        this.newTopic.content = "";
        this.fetchTopics(); // Reload topics after posting
      } catch (error) {
        console.error("Error posting topic:", error);
      }
    },

    // Post a reply to a topic
    async postReply(topicId) {
      try {
        const reply = { content: this.replyContent[topicId] };
        await axios.post(`http://localhost:8080/api/forum/topics/${topicId}/replies`, reply);
        this.replyContent[topicId] = ""; // Clear the reply content
        this.fetchTopics(); // Reload topics after posting reply
      } catch (error) {
        console.error("Error posting reply:", error);
      }
    },
  },
  created() {
    this.fetchTopics(); // Fetch topics when the component is created
  },
};
</script>

<style scoped>
.forum-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

h1, h3, h4, h5 {
  color: #333;
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
}

.new-topic-form,
.topics-list {
  margin-top: 20px;
}

.new-topic-form {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.new-topic-form h3 {
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  font-weight: bold;
  color: #555;
  display: block;
  margin-bottom: 5px;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fefefe;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05);
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #0066cc;
}

textarea {
  min-height: 100px;
}

button {
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  background-color: #0066cc;
  color: white;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #004a99;
}

.topics-list h3 {
  font-size: 1.5rem;
  margin-bottom: 15px;
}

.topic-item {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.topic-item h4 {
  color: #333;
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
  color: #333;
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
  min-height: 80px;
}

</style>