<script setup lang="ts">
import { onMounted, ref } from "vue";
import axios from "axios";

interface Book {
  bookNumber: number;
  bookTitle: string;
}

const Search = ref("");
const book = ref<Book[]>([]);

async function getData() {
  const res = await axios.get<Book[]>("http://localhost:8099/api/book");
  book.value = res.data;
  console.log(res.data);
}

async function searchData(){
    const res=await axios.get<Book[]>(`http://localhost:8099/api/book/${Search.value}`);
    book.value = res.data;
    console.log(res.data);
}

onMounted(() => {
  getData();
});
</script>

<template>
  <div>
    <input
      v-model="Search"
      type="text"
      placeholder="검색어를 입력하세요"
      id="search"
    />
    <button @click="searchData">검색</button>
    
    <div class="bookList">
      <div id="book" v-for="bookNo in book" :key="bookNo.bookNumber">
        <img
          @click="$router.push({ path: '/bookDetail' })"
          :src="`/book/${bookNo.bookTitle}.jpg`"
          :alt="bookNo.bookTitle"
        />
        <p>{{ bookNo.bookTitle }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
#search {
  display: flex;
  margin: 20px auto;
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