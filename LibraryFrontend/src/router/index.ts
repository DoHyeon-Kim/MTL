import { createRouter, createWebHistory } from "vue-router";

import member from "./member";
import cart from "./cart";
import loanMember from "./loanMember";
import loanAdmin from "./loanAdmin";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [...member, ...cart, ...loanMember, ...loanAdmin],
});

export default router;
