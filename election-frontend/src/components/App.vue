<template>
  <div class="app-container">
    <!-- Navbar Component -->
    <navbar></navbar>

    <!-- Main Content Area (dynamically changing based on route) -->
    <div class="content">
      <router-view></router-view>
    </div>

    <!-- Display Backend Status -->
    <div class="backend-status">
      <div v-if="backendStatus">
        <p>Backend Status: {{ backendStatus.message }}</p>
      </div>
      <div v-else>
        <p>Loading backend status...</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import navbar from "@/components/NavBar.vue";
import axios from 'axios';

export default defineComponent({
  components: {
    navbar,
  },
  data() {
    return {
      backendStatus: null, // Holds the backend status response
    };
  },
  mounted() {
    // Use Axios to fetch backend status
    axios.get('http://localhost:8080/test')
        .then(response => {
          this.backendStatus = response.data;
        })
        .catch(error => {
          console.error('Error fetching backend status:', error);
          this.backendStatus = { message: 'Error connecting to backend' };
        });
  }
});
</script>
