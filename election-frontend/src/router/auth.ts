import { reactive } from 'vue';

// Create a reactive state for authentication
export const authState = reactive({
    isAuthenticated: !!localStorage.getItem('authToken'),  // Check if token exists
    login(token: string) {
        this.isAuthenticated = true;
        localStorage.setItem('authToken', token);  // Save token to localStorage
    },
    logout() {
        this.isAuthenticated = false;
        localStorage.removeItem('authToken');  // Remove token from localStorage
    }
});
