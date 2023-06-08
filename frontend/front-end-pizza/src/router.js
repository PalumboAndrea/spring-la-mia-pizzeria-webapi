import { createRouter, createWebHistory } from 'vue-router';

import HomePage from './pages/HomePage.vue';
import CreatePizza from './pages/CreatePizza.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/home',
            name: 'home',
            component: HomePage
        },
        {
            path: '/create',
            name: 'create',
            component: CreatePizza
        }
    ]
});

export { router };