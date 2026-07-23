<script setup lang="ts">
import { onMounted, ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

interface BookDetail {
  bookNumberInfo: number;
				bookImg: string;
				bookTitle: string;
				Writer: string;
				Publisher: string;
				Category: string;
				bookContent: string;
				loanAvailable: boolean;
				publicationDate: Date;
				registrationDate: Date;
}

const route = useRoute();
const book = ref<BookDetail[]>([]);

async function DetailData() {
  const bookNumberInfo = route.params.bookNumberInfo;
  const res = await axios.get<BookDetail[]>(`http://localhost:8099/bookdetail/${bookNumberInfo}`);
  book.value = res.data;

  console.log(book.value);
}
onMounted(() => {
  DetailData();
});
</script>

<template>
  <div v-if="book[0]">
    <button>{{ book[0].loanAvailable ? '카트에 추가' : '알람 예약' }}</button>
    <img :src="`/book/${book[0].bookTitle}.jpg`" />
    <h2>{{ book[0].bookTitle }}</h2>
    <p>{{ book[0].bookContent }}</p>
  </div>
</template>