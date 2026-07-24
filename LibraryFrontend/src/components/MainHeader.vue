<style scoped>
.navbar-wrapper {
  position: static;
  background-color: #282828;
}
</style>
<template>
  <header id="header-v1" class="navbar-wrapper">
    <div class="container">
      <div class="row">
        <nav class="navbar navbar-default">
          <div class="row">
            <div class="col-md-3">
              <div class="navbar-header">
                <div class="navbar-brand">
                  <h1>
                    <img src="/images/libraria-logo-v1.png" alt="LIBRARIA" />
                  </h1>
                </div>
              </div>
            </div>
            <div class="col-md-9">
              <!-- Header Topbar -->
              <div class="header-topbar hidden-sm hidden-xs">
                <div class="row">
                  <div class="col-sm-6">
                    <div class="topbar-info">
                      <a href="tel:+03-5829-6357"><i class="fa fa-phone"></i>+03-5829-6357</a>
                      <span>/</span>
                      <a href="mailto:support@mountain-info.com"><i
                          class="fa fa-envelope"></i>support@mountain-info.com</a>
                    </div>
                  </div>
                  <div class="col-sm-6">
                    <div class="topbar-links">
                      <div class="topbar-links">
                        <RouterLink v-if="!isLogin" to="/login">
                          <i class="fa fa-lock"></i>Login
                        </RouterLink>

                        <template v-else>
                          <RouterLink v-if="!isAdmin" :to="`/userMypage/${auth.memberNo}`">
                            MyPage
                          </RouterLink>

                          <RouterLink v-else to="/adminMainPage">
                            MyPage
                          </RouterLink>

                          <span>|</span>
                          <a href="#" @click.prevent="logout">Logout</a>
                        </template>


                        <a v-if="!isAdmin" data-toggle="dropdown" class="dropdown-toggle" :href="`/carts/${auth.memberNo}`"
                          style="margin-left: 10px;">
                          <i class="fa fa-shopping-cart"></i>
                        </a>


                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="navbar-collapse hidden-sm hidden-xs">
                <ul class="nav navbar-nav" style="float: right">
                  <li><a href="/booklist">Home</a></li>
                  <li><a href="/qanda">Q&A</a></li>
                  <li><a href="/announcement">Contact</a></li>
                </ul>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </div>
  </header>
</template>
<script setup lang="ts">
import { computed } from "vue";
import { useAuthStore } from "@/stores/auth";
import axios from "axios";
import { useRouter } from "vue-router";

const auth = useAuthStore();
const router = useRouter();

const isLogin = computed(() => auth.memberNo > 0);
const isAdmin = computed(() => {
  return auth.role === "ROLE_ADMIN" || auth.role === "ROLE_SUBADMIN";
});

async function logout() {
  try {
    await axios.post("http://localhost:8099/logout", {}, { withCredentials: true });

    auth.memberNo = 0;
    auth.memberId = "";
    auth.role = "";

    await router.push("/login");
  } catch (error) {
    console.error(error);
  }
}
</script>
