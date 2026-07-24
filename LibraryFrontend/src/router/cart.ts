export default [
  {
    path: "/carts/:memberNo",
    component: () => import("@/views/loan/CartView.vue"),
    meta: {
      showMainHeader: true,
      sidebarType: "mypage",
      showMainFooter: true,
    },
  },
];
