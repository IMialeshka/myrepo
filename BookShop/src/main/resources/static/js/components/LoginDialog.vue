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
import {getAxios} from '../modules/httpComon.js';

export default {
    data: () => ({
    dialog: false,
    username: '',
    password: '',
    message: ''
  }),
 mounted() {
      if(this.$cookie.get("jwtToken") == null){
        this.$store.commit("setIdent", false);
        this.$store.commit("isAdmin", false);
      }
      else
      {
        this.$store.commit("setIdent", true);
        this.isAdmin(this.$cookie.get("jwtToken"));
      }
 },
  methods: {
    logIn: function (){
      let bodyFormData = new FormData();
      bodyFormData.append("username", this.username);
      bodyFormData.append("password", this.password);
      getAxios(null).post("/login", bodyFormData)
      .then(response =>
          {let jwt = response.data.jwt;
            this.isAdmin(jwt);
            this.$cookie.set("jwtToken", jwt, 1)
            this.$store.commit("setIdent", true);
            this.dialog = false;
          })
          .catch(error => {
            this.message = "Incorrect login or password";
          });

    },
    logOut: function (){
      getAxios(null).post("/logout")
          .then(response =>
          { this.password = null;
            this.$cookie.delete("jwtToken")
            this.$store.commit("setIdent", false);
            this.$store.commit("isAdmin", false);
          })
          .catch(error => {
            console.log(error);
          });
    },
    exit: function (){
      this.password = null;
      this.dialog = false;
    },
    isAdmin: function (jwt){
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      for (let i = 0; i < JSON.parse(decodedJwtJsonData).roles.length; i++){
        let role = JSON.parse(decodedJwtJsonData).roles[i].name;
        if(role == "ROLE_ADMIN"){
          this.$store.commit("isAdmin", true);
          break;
        }
      }
    }
  }
}
</script>