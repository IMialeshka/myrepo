import Vue  from 'vue'
import App from './pages/App.vue'
import vuetify from './plugins/vuetify'
import store from './plugins/store'
import BooksView from './components/BooksView.vue'
import LoginDialog from './components/LoginDialog.vue'
import LoadBook from './components/LoadBook.vue'
//import SaveBook from './components/SaveBook.vue'

Vue.component('component-books', BooksView );
Vue.component('component-login', LoginDialog );
Vue.component('component-load', LoadBook );
//Vue.component('component-save', SaveBook );





new Vue({
    el: '#app',
    vuetify,
    store,
   // router,
    render: a =>a(App)
})
