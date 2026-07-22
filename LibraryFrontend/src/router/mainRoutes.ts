import type { RouteRecordRaw } from "vue-router";

export const mainRoutes: RouteRecordRaw[] = [
  {
    path: "/",
    component: () => import("@/views/MainHomeView.vue"),
    meta: {
      showMainHeader: true,
      showMainSidebar: true,
      showMainFooter: true,
    },
  },
];
