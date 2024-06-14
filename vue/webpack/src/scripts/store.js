import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            account: {
                user_id: sessionStorage.getItem('user_id') || 0,
            },
            token: sessionStorage.getItem('token') || ''
        }
    },
    mutations: {
        setAccount(state, user_id) {
            state.account.user_id = user_id;
        },
        setToken(state, token){
          state.token = token;
          sessionStorage.setItem('token', token);
        },
        clearAccount(state) {
            state.account.user_id = 0;
            state.token = '';
            sessionStorage.removeItem('user_id');
            sessionStorage.removeItem('token')
        }
    }
})

export default store;
