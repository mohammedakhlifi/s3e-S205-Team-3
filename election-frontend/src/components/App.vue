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
        <p>{{ backendStatus.message }}</p>
      </div>
      <div v-else>
        <p>Loading backend status...</p>
      </div>
      <!-- Error message if backend connection fails -->
      <div v-if="errorMessage" class="error-message">
        <p>{{ errorMessage }}</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import navbar from "@/components/NavBar.vue";
import axios from 'axios';

// Type for BackendStatus
interface BackendStatus {
  message: string;
}

export default defineComponent({
  components: {
    navbar,
  },
  data() {
    return {
      backendStatus: null as BackendStatus | null, // Define backendStatus type
      errorMessage: null as string | null,  // Error message when fetching status
    };
  },
  mounted() {
    this.fetchBackendStatus(); // Fetch the backend status when the component is mounted
  },
  methods: {
    // Use Axios to fetch backend status
    async fetchBackendStatus() {
      try {
        // Update URL to reflect the Docker container name if needed
        const response = await axios.get<BackendStatus>('http://election-backend-container:8080/test');
        this.backendStatus = response.data;
        this.errorMessage = null; // Reset error message on success
      } catch (error) {
        console.error('Error fetching backend status:', error);
        this.backendStatus = null;
        this.errorMessage = 'Error connecting to backend. Please check if the backend is running.';
      }
    },
  },
});
</script>

<style>
.backend-status {
  margin-top: 20px;
}

.error-message {
  color: red;
  font-size: 1.2rem;
  margin-top: 10px;
}
</style>
