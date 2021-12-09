import Vue  from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        ident: true,
        savedBook: null,
        isAdmin: false
    },

    getters: {
        ident: state => {
            return state.ident;
        },
        savedBook: state => {
            return state.savedBook;
        },
        isAdmin: state => {
            return state.isAdmin;
        }
    },
    mutations: {
        setIdent(state, setValue) {
            state.ident = setValue;
        },
        savedBook(state, setValue) {
            state.savedBook = setValue;
        },
        isAdmin(state, setValue) {
            state.isAdmin = setValue;
        }
    }
})