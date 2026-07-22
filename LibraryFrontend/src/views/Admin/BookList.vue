<template>
  <div class="book-list-container">
    <div class="list-header">
      <h1>Book List</h1>
      <div class="header-actions">
        <button class="btn-create" @click="createBook">図書登録</button>
      </div>
    </div>

    <div class="search-section">
      <input
        v-model="searchTitle"
        type="text"
        class="search-input"
        placeholder="タイトルで検索..."
        @keyup.enter="searchBooks"
      />
      <button class="btn-search" @click="searchBooks">検索</button>
    </div>

    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>

    <div v-if="searchPerformed && books.length === 0" class="no-results">検索結果がありません</div>

    <table v-if="searchPerformed && books.length > 0" class="books-table">
      <thead>
        <tr>
          <th>タイトル</th>
          <th>著者</th>
          <th>出版日</th>
          <th>登録日</th>
          <th>アクション</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.bookNumber">
          <td class="title-col">
            <a href="#" @click.prevent="goToDetail(book.bookNumberInfo)">{{ book.bookTitle }}</a>
          </td>
          <td>{{ book.writer }}</td>
          <td>{{ book.publicationDate }}</td>
          <td>{{ book.registrationDate }}</td>
          <td class="action-buttons">
            <button class="btn-edit" @click="goToEdit(book.bookNumberInfo || book.bookNumber)">
              編集
            </button>
            <button class="btn-delete" @click="deleteBook(book.bookNumberInfo)">削除</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
import "./BookList.css";

const router = useRouter();
const route = useRoute();
const books = ref<
  {
    bookNumber: string;
    bookTitle: string;
    writer: string;
    publicationDate: string;
    registrationDate: string;
    bookNumberInfo: string;
  }[]
>([]);

const searchTitle = ref("");
const searchPerformed = ref(false);
const errorMessage = ref("");

const createBook = () => {
  router.push("/admin/booklist/createbook");
};

const goToEdit = (bookNumberInfo: string) => {
  router.push(`/admin/booklist/editbook/${bookNumberInfo}`);
};

const goToDetail = (bookNumberInfo: string) => {
  router.push({
    path: `/admin/booklist/detail/${bookNumberInfo}`,
    query: { title: searchTitle.value },
  });
};

onMounted(() => {
  const q = route.query.title as string | undefined;
  if (q && q.trim()) {
    searchTitle.value = q;
    searchBooks();
  }
});


const searchBooks = async () => {
  if (!searchTitle.value.trim()) {
    errorMessage.value = "タイトルを入力してください";
    books.value = [];
    searchPerformed.value = false;
    return;
  }

  errorMessage.value = "";
  try {
    const response = await axios.get(`http://localhost:8099/booklist?title=${searchTitle.value}`);
    books.value = response.data;
    searchPerformed.value = true;
  } catch (error) {
    books.value = [];
    searchPerformed.value = true;
    console.error(error);
  }
};

const deleteBook = async (bookNumberInfo: string) => {
  if (confirm("この図書を削除してもよろしいですか？")) {
    try {
      await axios.put(`http://localhost:8099/bookdelete/${bookNumberInfo}`);
      books.value = books.value.filter((book) => book.bookNumberInfo !== bookNumberInfo);
      alert("削除しました");
    } catch (error) {
      alert("削除に失敗しました");
      console.error(error);
    }
  }
};

</script>
