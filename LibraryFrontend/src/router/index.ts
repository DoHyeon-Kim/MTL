import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
       {
      path: '/',
      component: () => import('@/views/MainHomeView.vue'),
      meta: {
        showMainHeader: true,
        showMainSidebar: true,
        showMainFooter: true
      }
    },
  ],

})

export default router
