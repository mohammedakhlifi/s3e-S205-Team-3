import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";
import Login from "@/components/pages/Login.vue";
import Register from "@/components/pages/Register.vue";
import Quiz from "@/components/pages/Quiz.vue";
import ContactUs from "@/components/pages/ContactUs.vue";
import ForumPage from "@/components/pages/ForumPage.vue"; // Import ForumPage
import AdminPanel from "@/components/pages/AdminPanel.vue"; // Import AdminPanel
import NotFound from "@/components/pages/NotFound.vue"; // 404 Not Found Page

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: HomePage },
    { path: '/quiz', component: Quiz },
    { path: '/contact-us', component: ContactUs },
    { path: '/forum', component: ForumPage },
    { path: '/login', component: Login },
    { path: '/register', component: Register },

    // Newly added routes
    { path: '/admin', component: AdminPanel },        // Admin Panel route
    // Catch-all route for 404 Not Found
    { path: '/:pathMatch(.*)*', component: NotFound }  // Wildcard for unmatched routes
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;