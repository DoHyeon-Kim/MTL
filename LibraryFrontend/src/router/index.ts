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
        showMainFooter: true,
      },
    },

    // お知らせ(Announcement)
    {
      path: '/announcement',
      name: 'announcement-list',
      component: () => import('@/views/announcement/AnnouncementListView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: '/announcement/new',
      name: 'announcement-new',
      component: () => import('@/views/announcement/AnnouncementFormView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: '/announcement/:id/edit',
      name: 'announcement-edit',
      component: () => import('@/views/announcement/AnnouncementFormView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: '/announcement/:id',
      name: 'announcement-detail',
      component: () => import('@/views/announcement/AnnouncementDetailView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },

    // Q&A
    {
      path: '/qanda',
      name: 'qanda-list',
      component: () => import('@/views/qanda/QandaListView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: '/qanda/new',
      name: 'qanda-new',
      component: () => import('@/views/qanda/QandaFormView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: '/qanda/:id',
      name: 'qanda-detail',
      component: () => import('@/views/qanda/QandaFormView.vue'),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
  ],
})

export default router
