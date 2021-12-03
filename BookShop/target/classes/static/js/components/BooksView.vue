<template>
  <v-data-table
      :headers="headers"
      :items="books"
      :items-per-page="5"
      class="elevation-1"
  ></v-data-table>
</template>
<script>
import axios from 'axios'
export default {
  data () {
    return {
      headers: [
        { text: 'Name', value: 'name' },
        { text: 'Writer', value: 'writer' },
        { text: 'Genre', value: 'genre' },
      ],
      books: [],
      errored: false
    }
  },
  mounted () {
    axios
        .get('/booksList')
        .then(response =>
        {
          for (let i = 0; i < response.data.length; i++){
            this.books.push(response.data[i]);
          }
        })
        .catch(error => {
          console.log(error);
          this.errored = true;
        });
  }
}
</script>
<style>
</style>