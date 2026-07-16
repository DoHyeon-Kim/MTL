<template>
  <div class="admin-layout">
    <div class="admin-sidebar-container">
      <AdminSidebar />
    </div>
    <div class="admin-content-container">
      <RouterView />
    </div>
  </div>
</template>

<script setup lang="ts">
import AdminSidebar from "@/components/AdminSidebar.vue";
</script>

<style scoped>
.admin-layout {
  display: flex;
  gap: 0;
  min-height: 100vh;
}

.admin-sidebar-container {
  flex: 0 0 250px;
  background-color: #f5f5f5;
  overflow-y: auto;
}

.admin-content-container {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background-color: #ffffff;
}
</style>
