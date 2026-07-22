<template>
  <MainHeader v-if="showMainHeader" />

  <div class="container">
    <div class="row">
      <div v-if="showMainSidebar" class="col-md-3">
        <MainSidebar />
      </div>

      <main class="main-content" :class="showMainSidebar ? 'col-md-9' : 'col-md-12'">
        <RouterView />
      </main>
    </div>
  </div>

  <MainFooter v-if="showMainFooter" />
</template>

<script setup lang="ts">
import { computed } from "vue";
import { RouterView, useRoute } from "vue-router";

import MainHeader from "@/components/MainHeader.vue";
import MainFooter from "@/components/MainFooter.vue";
import MainSidebar from "@/components/MainSidebar.vue";

const route = useRoute();

const showMainHeader = computed(() => {
  return route.meta.showMainHeader !== false;
});

const showMainSidebar = computed(() => {
  return route.meta.showMainSidebar === true;
});

const showMainFooter = computed(() => {
  return route.meta.showMainFooter !== false;
});
</script>

<style scoped>
.main-layout {
  display: flex;
}

.main-content {
  flex: 1;
}
</style>
