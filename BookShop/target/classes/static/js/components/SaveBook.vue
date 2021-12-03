<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="400px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">Book save</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
              >
                <v-file-input
                    truncate-length="15"
                    label="Name book"
                    v-model="name"
                ></v-file-input>
                <v-file-input
                    truncate-length="15"
                    label="Writer"
                    v-model="writer"
                ></v-file-input>
                <v-file-input
                    truncate-length="15"
                    label="Genre"
                    v-model="genre"
                ></v-file-input>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="save"
          >
            Ok
          </v-btn>
        </v-card-actions>
        {{message}}
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import axios from "axios";
export default {
  data: () => ({
    dialog: false,
    name: null,
    message: null,
    writer: null,
    genre: null
  }),
  computed:{
    digest: function () {
      return $store.getters.book_save_dialog();
    }
  },
  methods: {
    save: function() {
    }
  }
}
</script>