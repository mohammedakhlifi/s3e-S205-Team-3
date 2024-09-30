import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: HomePage },
    { path: '/chat', component: HomePage },
    { path: '/contact-us', component: HomePage },
    { path: '/login', component: HomePage },
    { path: '/register', component: HomePage },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
