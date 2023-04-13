const { createApp } = Vue;

createApp({
  data() {
    return {
      message: "Hello Vue",
      apiUrl: "http://localhost:8080/api/pizzas",
      pizzas: [],
      keyword: "",
      name: "",
      description: "",
      price: 0,
      showForm: false
    };
  },

  methods: {
    loadData() {
      axios.get(this.apiUrl + `?q=${this.keyword}`).then((response) => this.pizzas = response.data);
    },
    savePizza() {
      axios
        .post(this.apiUrl, {
          name: this.name,
          description: this.description,
          price: this.price
        })
        .then(() => this.loadData());
        this.showForm = !this.showForm;
    },
    deletePizza(id) {
      axios.delete(this.apiUrl + `/${id}`).then((response) => {
        console.log(response);
        this.loadData();
        
      });
    },

    toggleForm(){
      this.showForm = !this.showForm;
    }
  },

  mounted() {
    this.loadData();
  }
}).mount("#app");