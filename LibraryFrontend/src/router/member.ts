export default [
  {
    path: '/login',
    component: () => import('@/views/member/loginView.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: null,
      showMainFooter: true,
    },
  },
]
