import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";
import LoginPage from "@/components/pages/LoginPage.vue";
import RegisterPage from "@/components/pages/RegisterPage.vue";  // Updated to RegisterPage.vue
import Quiz from "@/components/pages/Quiz.vue";

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: HomePage },
    { path: '/quiz', component: Quiz },
    { path: '/contact-us', component: HomePage },
    { path: '/login', component: LoginPage },
    { path: '/register', component: RegisterPage },  // Updated component
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;