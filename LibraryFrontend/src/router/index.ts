import { createRouter, createWebHistory } from 'vue-router'

import member from './member'
import user from './user'
import admin from './admin'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...member,
    ...user,
    ...admin,

  ],
})

export default router
