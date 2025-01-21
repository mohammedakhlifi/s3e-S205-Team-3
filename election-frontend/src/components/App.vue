<script lang="ts">
import { defineComponent } from 'vue';
import navbar from "@/components/NavBar.vue";
import axios from 'axios';

interface BackendStatus {
  message: string;
}

export default defineComponent({
  components: {
    navbar,
  },
  data() {
    return {
      backendStatus: null as BackendStatus | null,
      backendURL:  "https://election-backend-latest.onrender.com"
    };
  },
  mounted() {
    this.fetchBackendStatus();
  },
  methods: {
    async fetchBackendStatus() {
      try {
        const response = await axios.get<BackendStatus>(`${this.backendURL}/test`);
        this.backendStatus = response.data;
      } catch (error) {
        console.error('Error fetching backend status:', error);
        this.backendStatus = { message: 'Error connecting to backend' };
      }
    },
  },
});
</script>