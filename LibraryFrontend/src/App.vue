<template>
  <MainHeader v-if="showMainHeader" />

  <div class="container">
    <div class="row">
      <div v-if="sidebarType" class="col-md-3">
        <AdminSidebar v-if="sidebarType === 'admin'" />
        <UserSidebar v-else-if="sidebarType === 'user'" />
        <SubAdminSidebar v-else-if="sidebarType === 'subadmin'" />
      </div>

      <main class="main-content" :class="sidebarType ? 'col-md-9' : 'col-md-12'">
        <RouterView />
      </main>
    </div>
  </div>

  <MainFooter v-if="showMainFooter" />
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { RouterView, useRoute } from 'vue-router'

import MainHeader from '@/components/MainHeader.vue'
import MainFooter from '@/components/MainFooter.vue'
import AdminSidebar from '@/components/AdminSidebar.vue'
import UserSidebar from '@/components/UserSidebar.vue'
import SubAdminSidebar from './components/SubAdminSiderbar.vue'

const route = useRoute()

const showMainHeader = computed(() => {
  return route.meta.showMainHeader !== false
})

const showMainFooter = computed(() => {
  return route.meta.showMainFooter !== false
})

const sidebarType = computed(() => {

  if (route.meta.sidebarType !== 'mypage') {
    return route.meta.sidebarType
  }

  switch (auth.role) {
    case 'ROLE_ADMIN':
      return 'admin'

    case 'ROLE_SUBADMIN':
      return 'subadmin'

    case 'ROLE_USER':
      return 'user'

    default:
      return null
  }
})


//Pinia
import axios from 'axios'
import { onMounted } from 'vue'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()

onMounted(async () => {

  const res = await axios.get(
    'http://localhost:8099/session',
    {
      withCredentials: true
    }
  )

  auth.memberNo = res.data.memberNo
  auth.memberId = res.data.memberId
  auth.role = res.data.role

})

</script>

<style scoped>
.main-content {
  min-width: 0;
}
</style>
