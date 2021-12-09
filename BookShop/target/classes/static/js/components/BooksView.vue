<template>
  <v-data-table
      :headers="headers"
      :items = "books"
      :current-items = "booksLoad"
      :items-per-page="5"
      class="elevation-1"
      :search="search"
      :custom-filter="filterOnlyCapsText"
  >
    <template v-slot:top>
      <v-container fluid
                   v-if="$store.getters.isAdmin">
        <v-checkbox
            v-model="checkbox">
          <template v-slot:label>
              Just downloaded by me
          </template>
        </v-checkbox>
      </v-container>
      <v-text-field
          v-model="search"
          label="Search"
          class="mx-4"
      ></v-text-field>
      <v-dialog
          v-model="dialog"
          max-width="1000px"
          max-height="1000px">
        <v-card>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
              >
                <template>
                  <v-icon
                      small
                      class="mr-2"
                      @click="back"
                  >
                    mdi-chevron-left
                  </v-icon>
                  <v-icon
                      small
                      class="mr-2"
                      @click="forward"
                  >
                    mdi-chevron-right
                  </v-icon>
                  <pdf :src="pdfdata" :page = numPage >
                    <template slot="loading">
                      loading content here...
                    </template>
                  </pdf>
                </template>
              </v-col>
            </v-row>
          </v-container>
          <v-card-actions>
            <v-btn
                color="blue darken-1"
                text
                @click="exit">
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>
    <template v-slot:item.read="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="readBook(item)"
      >
        mdi-book-open
      </v-icon>
    </template>
  </v-data-table>
</template>


<script>
import pdfvuer from 'pdfvuer'
import {getAxios} from '../modules/httpComon.js';
export default {
  data () {
    return {
      headers: [
        { text: 'Name', value: 'name' },
        { text: 'Writer', value: 'writer' },
        { text: 'Genre', value: 'genre'},
        { text: 'File', value: 'fileName'},
        { text: 'Read', value: 'read', sortable: false }
      ],
      books: [],
      errored: false,
      search: '',
      checkbox: false,
      dialog: false,
      bookFileRead : null,
      numPage: 1,
      pdfdata: undefined
    }
  },
  components:{
    pdf: pdfvuer
  },
  computed: {
    booksLoad() {
      if(!this.$store.getters.ident){
        this.checkbox = false;
      }
      let newBook = this.$store.getters.savedBook;

      if(newBook == null) {
        let result = new Array();
        if(this.checkbox == false){
          getAxios(null).get('/booksList')
              .then(response =>
              {
                for (let i = 0; i < response.data.length; i++){
                  result.push(response.data[i]);
                }
              })
              .catch(error => {
                console.log(error);
                this.errored = true;
              });
        }
        else
        {
          getAxios(this.$cookie.get("jwtToken")).get("/justUserBooksList").
          then(response =>
          {
            for (let i = 0; i < response.data.length; i++){
              result.push(response.data[i]);
            }
          })
              .catch(error => {
                console.log(error);
                this.errored = true;
              });
        }
        this.books = result;
      }
      else{
        this.books.push(newBook);
        this.$store.commit("savedBook", null);
      }
    }
  },
  methods: {
    filterOnlyCapsText (value, search, item) {
      return value != null &&
          search != null &&
          typeof value === 'string' &&
          value.toString().toLocaleUpperCase().indexOf(search.toString().toLocaleUpperCase()) !== -1
    },
    readBook (item) {
      this.bookFileRead = Object.assign({}, item).fileName;
      this.pdfdata = pdfvuer.createLoadingTask("/readBook/"+this.bookFileRead);
      this.pdfdata.then(pdf => {
        //this.numPages = pdf.numPages
      })
      this.dialog = true;
    },
    forward(){
      this.numPage++;
    },
    back(){
      if(this.numPage != 1){
        this.numPage--;
      }
    },
    exit(){
      this.numPage = 1;
      this.bookFileRead = null;
      this.dialog = false;
    }
  },
}
</script>
<style>
</style>