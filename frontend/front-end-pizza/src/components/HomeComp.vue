<script>
import axios from 'axios'

const url = "http://localhost:8080/api/";

export default {

  data(){
    return{
        pizze: [],
        deleteStatus: false,
        userSearch: "",
    }
  },
  methods: {
    pizzeIndex() {
        axios.get(url + "pizze")
          .then((response) => {

              this.pizze = response.data;
          })
          .catch(function (error) {
              console.log(error);
          })
          .finally(() => {

          });
    },
    pizzaDelete(id, index){
      axios.delete(url + "pizze/" + id)
          .then((response) => {

              this.pizze.splice(index, 1);
              this.deleteStatus = true;
          })
          .catch(function (error) {
              console.log(error);
          })
          .finally(() => {

          });
    },
    pizzeSearch() {
        axios.get(url + "pizze/by/name", {
            params: {
                name: this.userSearch
            }
        })
          .then((response) => {

              this.pizze = response.data;
          })
          .catch(function (error) {
              console.log(error);
          })
          .finally(() => {

          });
    },

  },
  mounted() {
    this.pizzeIndex();
  },
}

</script>

<template>

  <div class="container mt-2">

    <div>
      <input type="text" name="name" placeholder="Nome" v-model="userSearch" @keyup="pizzeSearch()">
      <button @click="pizzeSearch()">
        Cerca
      </button>
    </div>

    <h1>
      Ecco le nostre pizze:
    </h1>
    
    <ul>
      <li v-for="pizza in pizze" class="mb-2">
        <span>
          {{ pizza.name }}
        </span>
        <button @click="pizzaDelete(pizza.id, pizze.indexOf(pizza))" class="btn btn-danger ms-2">
          X
        </button>
      </li>
    </ul>

    <router-link :to="{ name: 'create' }">
      Crea la tua pizza
    </router-link>

  </div>

</template>

<style scoped>


</style>
