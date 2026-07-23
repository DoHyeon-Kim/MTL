export default [
  {
    path: '/userMypagePut',
    component: () => import('@/views/member/PutMemberView.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: 'mypage',
      showMainFooter: true,
    },
  },{
    path: '/borrowHistory',
    component: () => import('@/views/user/BorrowHistory.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: 'mypage',
      showMainFooter: true,
    },
  },{
    path: '/DeleteMember',
    component: () => import('@/views/user/DeleteMember.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: 'mypage',
      showMainFooter: true,
    },
  },
]
