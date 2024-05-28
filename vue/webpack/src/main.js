import { createApp } from 'vue';
import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router';
import UserHome from './pages/UserHome.vue';
import UserRooms from './pages/UserRooms.vue';

// Import Bootstrap and BootstrapVueNext CSS files
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css';

// Define routes
const routes = [
  { path: '/', component: UserHome },
  { path: '/rooms', component: UserRooms },
];

// Create router instance
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Create Vue app instance and use router and BootstrapVueNext
const app = createApp(App);

// Install the BootstrapVueNext plugin
import BootstrapVueNext from 'bootstrap-vue-next';
app.use(BootstrapVueNext);

app.use(router);
app.mount('#app');
