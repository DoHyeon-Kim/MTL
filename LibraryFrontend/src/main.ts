import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

//model
import PrimeVue from 'primevue/config'
import Aura from '@primeuix/themes/aura'

//pinia
import { createPinia } from 'pinia'

const app = createApp(App)

app.use(router)

//model
app.use(PrimeVue, {
  theme: {
    preset: Aura
  }
})

//pinia
app.use(createPinia())
app.mount('#app')


import axios from 'axios'
axios.defaults.withCredentials = true
