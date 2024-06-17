import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            account: {
                user_id: sessionStorage.getItem('user_id') || null,
                token: sessionStorage.getItem('token') || null,
            }
        }
    },
    mutations: {
        setAccount(state, user_id) {
            state.account.user_id = user_id;
        },
        setToken(state, token) {
            state.account.token = token;
        },
        clearAccount(state) {
            state.account.user_id = null;
            state.account.token = null;
            sessionStorage.removeItem('user_id');
            sessionStorage.removeItem('token');
        }
    }
})

export default store;
