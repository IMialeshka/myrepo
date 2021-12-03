<template>
    <v-row justify="start">
      <v-btn
          color="primary"
          dark
          v-show="$store.getters.ident"
          @click="logOut"
      >
        Log out
      </v-btn>
    <v-dialog
        v-model="dialog"
        persistent
        max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
            v-show="!$store.getters.ident"
        >
          Log in
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">User</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                    label="Login"
                    required
                    v-model="username"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    label="Password*"
                    type="password"
                    required
                    v-model="password"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          {{message}}
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="logIn"
          >
            Log in
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              @click="exit"
          >
            Exit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import axios from "axios";

export default {
    data: () => ({
    dialog: false,
    username: '',
    password: '',
    message: ''
  }),
 mounted() {
      if(localStorage.getItem("jwtToken") == null){
        this.$store.commit("setIdent", false);
      }
      else
      {
        this.$store.commit("setIdent", true);
      }
 },
  methods: {
    logIn: function (){
      let bodyFormData = new FormData();
      bodyFormData.append("username", this.username);
      bodyFormData.append("password", this.password);
      axios({method: "post",
             url: "/login",
             data: bodyFormData,
             headers: { "Content-Type": "multipart/form-data" }})
          .then(response =>
          {localStorage.setItem("jwtToken", response.data.jwt);
            this.$store.commit("setIdent", true);
            this.dialog = false;
          })
          .catch(error => {
            this.message = "Incorrect login or password";
          });
    },
    logOut: function (){
      axios({method: "post",
        url: "/logout",
        headers: {"Content-Type": "multipart/form-data" }})
          .then(response =>
          { this.password = null;
            localStorage.removeItem("jwtToken");
            this.$store.commit("setIdent", false);
          })
          .catch(error => {
            console.log(error);
          });
    },
    exit: function (){
      this.password = null;
      this.dialog = false;
    }
  }
}
</script>