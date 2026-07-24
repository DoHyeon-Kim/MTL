export default [
  {
    path: "/adminMainPage",
    component: () => import("@/views/loan/LoanAdmin.vue"),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
];
