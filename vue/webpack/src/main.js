import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css';
import BootstrapVueNext from 'bootstrap-vue-next';

createApp(App)
    .use(router)
    .use(BootstrapVueNext)
    .mount('#app');