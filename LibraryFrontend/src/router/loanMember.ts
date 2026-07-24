export default [
  {
    path: "/userMypage/:memberNo",
    component: () => import("@/views/loan/LoanList.vue"),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
];
