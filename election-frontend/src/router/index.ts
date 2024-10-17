import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";
import Login from "@/components/pages/Login.vue";
import Register from "@/components/pages/Register.vue";
import Quiz from "@/components/pages/Quiz.vue";
import ContactUs from "@/components/pages/ContactUs.vue";
import ForumPage from "@/components/pages/ForumPage.vue";
import OverviewPage from "@/components/pages/OverviewPage.vue";
import AdminPanel from "@/components/pages/AdminPanel.vue";
import NotFound from "@/components/pages/NotFound.vue";
import Parties from "@/components/pages/Parties.vue";

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: Parties },
    { path: '/quiz', component: Quiz },
    { path: '/contact-us', component: ContactUs },
    { path: '/forum', component: ForumPage },
    { path: '/forum/overview', component: OverviewPage }, // Route naar overzichtspagina
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    {
        path: '/admin',
        component: AdminPanel,
        beforeEnter: (to, from, next) => {
            // Controleer authenticatie
            const isAuthenticated = true; // Pas deze controle aan naar jouw authenticatielogica
            if (isAuthenticated) {
                next(); // Ga door naar de adminpagina
            } else {
                next('/login'); // Omleiden naar login als de gebruiker niet ingelogd is
            }
        }
    },
    { path: '/:pathMatch(.*)*', component: NotFound }  // Wildcard voor niet-bestaande routes
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
