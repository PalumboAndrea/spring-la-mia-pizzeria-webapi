<script>
import axios from 'axios'

const url = "http://localhost:8080/api/";

export default {
    data() {

        return {

        pizza: {
            name: "",
            description: "",
            imgUrl: "",
            price: ""
        }
        };
    },
    methods: {
        pizzaCreate() {

            axios.post(url + "pizze", this.pizza)
            .then(res => {

                this.$router.push('/home');
            })
            .catch(err => console.log(err));
        }
    },
    mounted() {

    }
}


</script>

<template>

    <div class="container">

        <h1>
			Crea la tua pizza!
		</h1>
		
		<form class="row g-3" @submit.prevent="pizzaCreate">
		
		  <div class="col-12">
		    <label for="inputEmail4" class="form-label">Nome</label>
		    <input type="text" class="form-control" name="name" v-model="pizza.name">
		  </div>
		   
		  <div class="col-12">
		  	<label for="inputEmail4" class="form-label">Descrizione</label>
		  	<textarea class="form-control" name="description" v-model="pizza.description"></textarea>
		  </div>
		  
		  <div class="col-6">
		    <label for="inputAddress" class="form-label">Url dell'immagine</label>
		    <input type="text" class="form-control" name="imgUrl" v-model="pizza.imgUrl">
		  </div>
		  
		  <div class="col-6">
		    <label for="inputAddress2" class="form-label">Prezzo</label>
		    <input type="number" class="form-control" name="price" v-model="pizza.price">
		  </div>
		  
		  <div class="col-12 text-center">
		  	<input type="submit" value="Creala!" class="btn btn-success">
		  </div>
		
		</form>

    </div>

        

</template>

<style lang="">
    
</style>