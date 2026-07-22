import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: () => import("@/views/MainHomeView.vue"),
      meta: {
        showMainHeader: true,
        showMainSidebar: true,
        showMainFooter: true,
      },
    },
    {
      path: "/admin",
      component: () => import("@/views/Admin/AdminLayout.vue"),
      meta: { showMainHeader: true, showMainFooter: true, showAdminSidebar: true },
      children: [
        {
          path: "",
          component: () => import("@/views/Admin/AdminHomeView.vue"),
        },
        {
          path: "booklist",
          component: () => import("@/views/Admin/BookList.vue"),
        },
        {
          path: "booklist/createbook",
          component: () => import("@/views/Admin/CreateBook.vue"),
        },
        {
          path: "booklist/detail/:id",
          component: () => import("@/views/Admin/DetailBook.vue"),
        },
        {
          path: "booklist/editbook/:id",
          component: () => import("@/views/Admin/EditBook.vue"),
        },
        {
          path: "checkoutstatus",
          component: () => import("@/views/Admin/CheckoutStatus.vue"),
        },
        {
          path: "memberstatus",
          component: () => import("@/views/Admin/MemberStatus.vue"),
        },
      ],
    },
  ],
});

export default router;
