import Vue  from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        ident: true,
        saveDialog: false
    },

    getters: {
        ident: state => {
            return state.ident;
        },
        saveDialog: state => {
            return state.saveDialog;
        }
    },
    mutations: {
        setIdent(state, setValue) {
            state.ident = setValue;
        },
        bookSaveDialog(state, setValue) {
            state.saveDialog = setValue;
        },
    }
})