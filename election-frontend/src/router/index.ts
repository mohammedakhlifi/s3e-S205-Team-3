import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";
import login from "@/components/pages/Login.vue";
import register from "@/components/pages/Register.vue";

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: HomePage },
    { path: '/chat', component: HomePage },
    { path: '/contact-us', component: HomePage },
    { path: '/login', component: login },
    { path: '/register', component: register },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
