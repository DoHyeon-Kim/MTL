export default [
  {
    path: "/MemberInfoFind",
    component: () => import("@/views/Admin/MemberInfoFind.vue"),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
  {
    path: "/subadmin/userMypagePut/:memberId",
    name: "/putMember",
    component: () => import("@/views/member/PutMemberView.vue"),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
  {
    path: "/Management",
    name: "/Management",
    component: () => import("@/views/Admin/MemberManagementView.vue"),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
];
