import Vue  from 'vue'
import App from './pages/App.vue'
import vuetify from './plugins/vuetify'
import store from './plugins/store'
import BooksView from './components/BooksView.vue'
import LoginDialog from './components/LoginDialog.vue'
import LoadBook from './components/LoadBook.vue'


Vue.component('component-books', BooksView );
Vue.component('component-login', LoginDialog );
Vue.component('component-load', LoadBook );

const cookie = require('vue-cookie');
Vue.use(cookie);


new Vue({
    el: '#app',
    vuetify,
    store,
    cookie,
   // router,
    render: a =>a(App)
})
