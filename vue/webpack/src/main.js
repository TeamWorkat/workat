import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css';
import BootstrapVueNext from 'bootstrap-vue-next';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import { createNaverMap } from "vue3-naver-maps";

createApp(App)
    .use(router)
    .use(BootstrapVueNext)
    .use(createNaverMap, {
        clientId: "bgjjxkqpe7", // Required
        category: "ncp", // Optional
        subModules: [], // Optional
        })
        .component('VueDatePicker', VueDatePicker)
        .mount('#app');
