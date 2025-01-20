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

// Type voor BackendStatus
interface BackendStatus {
  message: string;
}

export default defineComponent({
  components: {
    navbar,
  },
  data() {
    return {
      backendStatus: null as BackendStatus | null, // Definieer de backendStatus type
    };
  },
  mounted() {
    this.fetchBackendStatus(); // Haal de backend status op wanneer de component is gemonteerd
  },
  methods: {
    // Gebruik Axios om backend status op te halen
    async fetchBackendStatus() {
      try {
        const response = await axios.get<BackendStatus>('https://election-backend-latest.onrender.com/api/status');  // Gebruik het juiste pad
        this.backendStatus = response.data;
      } catch (error) {
        console.error('Error fetching backend status:', error);
        this.backendStatus = { message: 'Error connecting to backend' };
      }
    },
  },
});
</script>
