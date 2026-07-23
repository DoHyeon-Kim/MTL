import { createRouter, createWebHistory } from "vue-router";
import { mainRoutes } from "./mainRoutes";
import { managementRoutes } from "./management";

import member from "./member";
import user from "./user";
import admin from "./admin";
import cart from "./cart";
import loanMember from "./loanMember";
import loanAdmin from "./loanAdmin";
import book from "./book";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),

  routes: [
    ...mainRoutes,
    ...managementRoutes,
    ...member,
    ...user,
    ...admin,
    ...member,
    ...cart,
    ...loanMember,
    ...loanAdmin,
	...book,
    {
      path: "/announcement",
      name: "announcement-list",
      component: () => import("@/views/announcement/AnnouncementListView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: "/announcement/new",
      name: "announcement-new",
      component: () => import("@/views/announcement/AnnouncementFormView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: "/announcement/:id/edit",
      name: "announcement-edit",
      component: () => import("@/views/announcement/AnnouncementFormView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: "/announcement/:id",
      name: "announcement-detail",
      component: () => import("@/views/announcement/AnnouncementDetailView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },

    // Q&A
    {
      path: "/qanda",
      name: "qanda-list",
      component: () => import("@/views/qanda/QandaListView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: "/qanda/new",
      name: "qanda-new",
      component: () => import("@/views/qanda/QandaFormView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
    {
      path: "/qanda/:id",
      name: "qanda-detail",
      component: () => import("@/views/qanda/QandaFormView.vue"),
      meta: { showMainHeader: true, showMainSidebar: false, showMainFooter: true },
    },
  ],
});

export default router;
