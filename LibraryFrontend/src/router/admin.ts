export default [
  {
    path: '/MemberInfoFind',
    component: () => import('@/views/admin/MemberInfoFind.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: 'mypage',
      showMainFooter: true,
    },
  }, {
    path: '/subadmin/userMypagePut/:memberId',
    name: '/putMember',
    component: () => import('@/views/member/PutMemberView.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: 'mypage',
      showMainFooter: true,
    }
  }, {
    path: '/Management',
    name: '/Management',
    component: () => import('@/views/admin/MemberManagementView.vue'),
    meta: {
      showMainHeader: true,
      sidebarType: 'mypage',
      showMainFooter: true,
    }
  }
]
