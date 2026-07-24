<script setup lang="ts">
import { computed, onMounted, ref, watch } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

interface Book {
  bookNumber: number;
  bookNumberInfo: number;
  bookTitle: string;
  category: string;
}

const route = useRoute();
const Search = ref("");
const book = ref<Book[]>([]);

async function getData() {
  const res = await axios.get<Book[]>("http://localhost:8099/booklist");
  book.value = res.data;
}

async function searchData() {
  const res = await axios.get<Book[]>(`http://localhost:8099/booklist/search/${Search.value}`);
  book.value = res.data;
}

const selectedCategory = computed(() => {
  return typeof route.params.category === "string" ? decodeURIComponent(route.params.category) : "";
});

const filteredBooks = computed(() => {
  if (!selectedCategory.value) {
    return book.value;
  }

  return book.value.filter((item) => item.category === selectedCategory.value);
});

watch(
  () => route.params.category,
  () => {
    Search.value = "";
    getData();
  },
);

onMounted(() => {
  getData();
});
</script>

<template>
  <div class="content">
    <div id="search">
      <input v-model="Search" type="text" placeholder="Please enter a search" id="sinput" />
      <button @click="searchData">검색</button>
    </div>

    <h3 v-if="selectedCategory" class="category-title">
      {{ selectedCategory }}
    </h3>

    <div class="bookList">
      <div id="book" v-for="bookNo in filteredBooks" :key="bookNo.bookNumber">
        <img
          @click="$router.push({ path: `/bookdetail/${bookNo.bookNumberInfo}` })"
          :src="`/book/${bookNo.bookTitle}.jpg`"
          :alt="bookNo.bookTitle"
        />
        <p>{{ bookNo.bookTitle }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content {
  width: 100%;
  min-width: 0;
}

#search {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin: 20px 0;
}

#sinput {
  width: 70%;
  max-width: 400px;
}

.category-title {
  margin: 10px 0 20px 0;
}

.bookList {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

#book {
  text-align: center;
}

#book img {
  width: 120px;
  height: 160px;
  object-fit: cover;
  display: block;
  margin: 0 auto 8px;
  cursor: pointer;
}
</style>
