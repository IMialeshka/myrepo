import Vue from 'vue'
import VueRouter from 'vue-router'
import BooksView from '../components/BooksView.vue'
import ChoseFile from '../components/LoadBook.vue'

Vue.use(VueRouter)

export default new VueRouter({
    //mode: 'history',
    //base: process.env.BASE_URL,
    mode: 'history',
    routes: [
       /* {   path: '/',
            name: 'books',
            component: BooksView
        },*/
        {   path: '/',
            name: '/',
            component: ChoseFile
        }
    ]
})