<script setup lang="ts">
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

interface BookCategory {
  category: string;
}

const list = ref<BookCategory[]>([]);
const router = useRouter();

async function getCategory() {
  const res = await axios.get<BookCategory[]>("http://localhost:8099/bookcategory");
  list.value = res.data;
  console.log(res.data);
}

function moveCategory(category: string) {
  router.push(`/booklist/${encodeURIComponent(category)}`);
}

onMounted(() => {
  getCategory();
});
</script>

<template>
  <aside class="sidebar">
    <div class="widget">
      <h4 class="widget-title">Category</h4>

      <div class="widget_categories">
        <ul>
          <li v-for="item in list" :key="item.category">
            <a href="#" @click.prevent="moveCategory(item.category)">
            {{ item.category }}
          </a>
          </li>
        </ul>
      </div>
    </div>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 220px;
  background: #282828;
  color: white;
  padding: 20px;
  min-height: 100vh;
}

.widget-title {
  margin-bottom: 16px;
  color: white;
  font-size: 20px;
}

.widget_categories ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.widget_categories li {
  margin-bottom: 10px;
}

.sidebar .widget_categories li a,
.sidebar .widget_categories li a:link,
.sidebar .widget_categories li a:visited {
  color: white;
  text-decoration: none;
}

.sidebar .widget_categories li a:hover,
.sidebar .widget_categories li a:focus {
  color: #FF7236;
}
</style>