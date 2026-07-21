<template>
  <MainHeader v-if="showMainHeader" />

  <div class="container-fluid px-0">
    <div class="row g-0">
      <div v-if="showMainSidebar" class="col-md-3">
        <MainSidebar />
      </div>

      <div v-if="showbookSidebar" class="col-md-3">
        <BookSidebar />
      </div>

      <main :class="mainContentClass">
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
import BookSidebar from "@/components/bookSidebar.vue";

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

const showbookSidebar = computed(() => {
  return route.meta.showbookSidebar === true;
});

const mainContentClass = computed(() => {
  if (showMainSidebar.value || showbookSidebar.value) {
    return "col-md-9";
  }
  return "col-md-12";
});
</script>

<style scoped>
main {
  padding: 20px;
}
.main-layout {
  display: flex;
}

.main-content {
  flex: 1;
}
</style>
