console.log("ciao");

const { createApp } = Vue;

createApp({
  data() {
    return {
      message: "ciao vue",
      apiUrl: "http://localhost:8080/api/pizza"
    };
  },

  methods: {
    loadData() {
      axios.get(this.apiUrl).then((response) => console.log(response));
    }
  },

  mounted() {
    this.loadData();
  }
}).mount("#app");