export default [
   {
    path: '/signup',
    component: () => import('@/views/member/SignUpView.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
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
