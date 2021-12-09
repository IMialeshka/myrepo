<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="700px"
        max-height="1000px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
            v-show="$store.getters.isAdmin"
        >
          Load book
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Book load</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
                  v-if="this.save_dialog"
              >
              <v-img
                  :lazy-src="/showCover/+bookCover"
                  max-height="900"
                  max-width="600"
                  :src="/showCover/+bookCover"
              ></v-img>
              </v-col>
              <v-col
                  cols="12"
                  v-if="!this.save_dialog"
              >
                <v-file-input
                    truncate-length="15"
                    label="Chose file"
                    v-model="file"
                ></v-file-input>
              </v-col>
              <v-col
                  cols="12"
                  v-if="this.save_dialog"
              >
                <v-text-field
                    label="Name book"
                    v-model="name"
                ></v-text-field>
              </v-col>
                <v-col
                    cols="12"
                    v-if="this.save_dialog"
                >
                  <v-text-field
                      label="Writer"
                      v-model="writer"
                  ></v-text-field>
                </v-col>
                  <v-col
                      cols="12"
                      v-if="this.save_dialog"
                  >
                    <v-text-field
                        label="Genre"
                        v-model="genre"
                    ></v-text-field>
                  </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="parseBook"
              v-show="!this.save_dialog"
          >
            Ok
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              @click="save"
              v-show="this.save_dialog"
          >
            Save
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              @click="exit"
          >
            Exit
          </v-btn>
        </v-card-actions>
        {{message}}
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import {getAxios} from '../modules/httpComon.js';
export default {
  data: () => ({
    dialog: false,
    file: null,
    message: null,
    name: null,
    writer: null,
    bookFile: null,
    bookCover: null,
    genre: null,
    save_dialog: false
  }),
  methods: {
    parseBook: function() {
      let bodyFormData = new FormData();
      bodyFormData.append("file", this.file);
      getAxios(this.$cookie.get("jwtToken")).post("/getmetainfo", bodyFormData)
          .then(response =>
          {this.message = response.data.ErrInfo;
            if(this.message == null){
               this.name = response.data.name;
               this.writer = response.data.writer;
               this.bookFile = response.data.bookFile;
               this.bookCover = response.data.bookCover;
               this.genre = null;
               this.save_dialog = true;
            }
          })
          .catch(error => {
            console.log(error);
          });
    },
    save: function (){
      let bodyFormData = new FormData();
      bodyFormData.append("name", this.name);
      bodyFormData.append("writer", this.writer);
      bodyFormData.append("genre", this.genre);
      bodyFormData.append("bookFile", this.bookFile);
      bodyFormData.append("bookCover", this.bookCover);
      getAxios(this.$cookie.get("jwtToken"))
      .post("/saveNewBook", bodyFormData)
          .then(response =>
          {let savedBook = response.data;
            this.$store.commit("savedBook", savedBook);
            this.installDefaultValue();
          })
          .catch(error => {
            console.log(error);
          });

    },
    exit: function (){
      this.installDefaultValue();
    },
    installDefaultValue: function () {
      this.save_dialog = false;
      this.name = null;
      this.writer = null;
      this.bookFile = null;
      this.bookCover = null;
      this.file = null;
      this.genre = null;
      this.dialog = false;
    }
  }
}
</script>