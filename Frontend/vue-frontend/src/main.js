// File: src/main.js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/index.css'
import vuetify from "./plugins/vuetify";


const app = createApp(App)

app.use(router)
app.use(vuetify) // use vuetify here

app.mount('#app')