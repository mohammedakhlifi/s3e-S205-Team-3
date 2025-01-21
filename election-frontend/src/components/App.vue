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
      backendURL: "https://election-backend-latest.onrender.com"
    };
  },
  mounted() {
    this.fetchBackendStatus(); // Fetch the backend status when the component is mounted
  },
  methods: {
    // Use Axios to fetch backend status
    async fetchBackendStatus() {
      try {
        apiClient = axios.create({   baseURL: 'https://election-backend-latest.onrender.com', });
        const response = await apiClient.get<BackendStatus>('/test');
        this.backendStatus = response.data;
      } catch (error) {
        console.error('Error fetching backend status:', error);
        this.backendStatus = { message: 'Error connecting to backend' };
      }
    },
  },
});
</script>
