<template>
  <div class="book-list-container">
    <div class="list-header">
      <h1>Book List</h1>
      <div class="header-actions">
        <button
          v-if="selectedBooks.length > 0"
          class="btn-delete-multiple"
          @click="deleteSelectedBooks"
        >
          削除 ({{ selectedBooks.length }})
        </button>
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
          <th class="checkbox-col">
            <input
              type="checkbox"
              :checked="isAllSelected"
              @change="selectAll"
              class="select-all-checkbox"
            />
          </th>
          <th>タイトル</th>
          <th>著者</th>
          <th>出版日</th>
          <th>登録日</th>
          <th>状態</th>
          <th>アクション</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.bookNumber">
          <td class="checkbox-col">
            <input
              type="checkbox"
              :value="book.bookNumber"
              v-model="selectedBooks"
              class="row-checkbox"
            />
          </td>
          <td class="title-col">
            <a href="#" @click.prevent="goToDetail(book.bookNumberInfo)">{{ book.bookTitle }}</a>
          </td>
          <td>{{ book.writer }}</td>
          <td>{{ book.publicationDate }}</td>
          <td>{{ book.registrationDate }}</td>
          <td class="status-col">{{ book.loanAvailable ? "貸出可能" : "貸出中" }}</td>
          <td class="action-buttons">
            <button class="btn-edit" @click="goToEdit(book.bookNumberInfo || book.bookNumber)">
              編集
            </button>
            <button class="btn-delete" @click="deleteBook(book.bookNumber)">削除</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from "vue";
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
    loanAvailable: boolean;
    registrationDate: string;
    bookNumberInfo: string;
  }[]
>([]);

const selectedBooks = ref<string[]>([]);
const searchTitle = ref("");
const searchPerformed = ref(false);
const errorMessage = ref("");

const isAllSelected = computed(() => {
  return books.value.length > 0 && selectedBooks.value.length === books.value.length;
});

const createBook = () => {
  router.push("/admin/booklist/createbook");
};

const goToEdit = (bookId: string) => {
  router.push(`/admin/booklist/editbook/${bookId}`);
};

const goToDetail = (bookId: string) => {
  router.push({ path: `/admin/booklist/detail/${bookId}`, query: { title: searchTitle.value } });
};

onMounted(() => {
  const q = route.query.title as string | undefined;
  if (q && q.trim()) {
    searchTitle.value = q;
    searchBooks();
  }
});

const selectAll = () => {
  if (isAllSelected.value) {
    selectedBooks.value = [];
  } else {
    selectedBooks.value = books.value.map((book) => book.bookNumber);
  }
};

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
    selectedBooks.value = [];
  } catch (error) {
    books.value = [];
    searchPerformed.value = true;
    console.error(error);
  }
};

const deleteBook = async (bookId: string) => {
  if (confirm("この図書を削除してもよろしいですか？")) {
    try {
      await axios.delete(`http://localhost:8099/book/${bookId}`);
      books.value = books.value.filter((book) => book.bookNumber !== bookId);
      selectedBooks.value = selectedBooks.value.filter((id) => id !== bookId);
      alert("削除しました");
    } catch (error) {
      alert("削除に失敗しました");
      console.error(error);
    }
  }
};

const deleteSelectedBooks = async () => {
  if (selectedBooks.value.length === 0) return;

  if (confirm(`${selectedBooks.value.length}件の図書を削除してもよろしいですか？`)) {
    try {
      for (const bookId of selectedBooks.value) {
        await axios.delete(`http://localhost:8099/book/${bookId}`);
      }
      books.value = books.value.filter((book) => !selectedBooks.value.includes(book.bookNumber));
      selectedBooks.value = [];
      alert("削除しました");
    } catch (error) {
      alert("削除に失敗しました");
      console.error(error);
    }
  }
};
</script>
