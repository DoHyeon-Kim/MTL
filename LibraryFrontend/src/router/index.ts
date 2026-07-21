import { createRouter, createWebHistory } from 'vue-router'

//import member from './member'
import book from './book'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //...member,
    ...book,
  ],
})

export default router