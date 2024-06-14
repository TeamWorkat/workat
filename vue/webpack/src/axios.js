import axios from 'axios';
import store from '@/scripts/store';

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: true,
});

instance.interceptors.request.use(config => {
    const token = store.state.account.token;
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

export default instance;
