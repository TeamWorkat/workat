import axios from '@/axios';
import store from '@/scripts/store';

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.withCredentials = true;

axios.interceptors.request.use(config => {
    const token = store.state.account.token;
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});
