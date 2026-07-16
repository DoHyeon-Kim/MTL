import { createRouter, createWebHistory } from 'vue-router'

import member from './member'
import loan from './loan'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...member,
    ...loan,

  ],
})

export default router
