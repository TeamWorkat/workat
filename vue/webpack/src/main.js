import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createNaverMap } from "vue3-naver-maps";

createApp(App)
    .use(router)
    .use(createNaverMap, {
        clientId: "bgjjxkqpe7", // Required
        category: "ncp", // Optional
        subModules: [], // Optional
        })
    .mount('#app');