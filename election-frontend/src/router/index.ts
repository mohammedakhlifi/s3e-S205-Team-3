import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/components/pages/HomePage.vue";
import ElectionResultPage from "@/components/pages/ElectionResultPage.vue";
import Login from "@/components/pages/Login.vue";
import Register from "@/components/pages/Register.vue";
import Quiz from "@/components/pages/Quiz.vue";
import ContactUs from "@/components/pages/ContactUs.vue";
import ForumPage from "@/components/pages/ForumPage.vue";
import OverviewPage from "@/components/pages/OverviewPage.vue"; // Correct import
import TopicDetailPage from "@/components/pages/TopicDetailPage.vue";
import AdminPanel from "@/components/pages/AdminPanel.vue";
import NotFound from "@/components/pages/NotFound.vue";
import Parties from "@/components/pages/Parties.vue";
import PersonalProfile from "@/components/pages/PersonalProfile.vue";

const routes = [
    { path: '/', component: HomePage },
    { path: '/election-result', component: ElectionResultPage },
    { path: '/parties', component: Parties },
    { path: '/quiz', component: Quiz },
    { path: '/contact-us', component: ContactUs },
    { path: '/forum', name: 'ForumPage', component: ForumPage },
    { path: '/forum', component: ForumPage }, // Route for forum page
    { path: '/forum/overview', component: OverviewPage },
    { path: '/forum/topic/:id', name: 'TopicDetailPage', component: TopicDetailPage, props: true }, // Route voor de detailpagina
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/admin', component: AdminPanel },
    { path: '/:pathMatch(.*)*', component: NotFound },  // Wildcard for unmatched routes
    { path: '/personal-profile', component: PersonalProfile},
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
