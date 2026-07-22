import { createRouter, createWebHistory } from "vue-router";
import { mainRoutes } from "./mainRoutes";
import { managementRoutes } from "./management";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [...mainRoutes, ...managementRoutes],
});

export default router;
