import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from "@/scripts/store"
import VueDatePicker from '@vuepic/vue-datepicker';
import BootstrapVueNext from 'bootstrap-vue-next';
import { createNaverMap } from "vue3-naver-maps";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css';
import '@vuepic/vue-datepicker/dist/main.css'

createApp(App)
    .use(router)
    .use(store)
    .use(BootstrapVueNext)
    .use(createNaverMap, {
        clientId: "bgjjxkqpe7", // Required
        category: "ncp", // Optional
        subModules: [], // Optional
        })
        .component('VueDatePicker', VueDatePicker)
        .mount('#app');
