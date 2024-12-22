// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

const routes = [
    {
        path: '/home',
        name: 'home',
        component: HomeView,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
