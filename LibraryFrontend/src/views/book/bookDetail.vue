<script setup lang="ts">
import { onMounted, ref, computed } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const auth = useAuthStore();
//const memberNo=ref(auth.memberNo);

interface BookDetail {
  bookNumber: number;
  bookNumberInfo: number;
  bookImg: string;
  bookTitle: string;
  writer: string;
  publisher: string;
  category: string;
  bookContent: string;
  loanAvailable: boolean;
}

const route = useRoute();
const book = ref<BookDetail[]>([]);
const message = ref("");

const bookNumberInfo = route.params.bookNumberInfo;
const bookNumber = computed(
  () => book.value.find((b) => b.loanAvailable)?.bookNumber ?? book.value[0]?.bookNumber,
);

async function DetailData() {
  const res = await axios.get<BookDetail[]>(`http://localhost:8099/bookdetail/${bookNumberInfo}`);
  book.value = res.data;
}

async function AddtoCart() {
  const res = await axios.post(
    `http://localhost:8099/user/carts/${auth.memberNo}/${bookNumber.value}`,
    {
      memberNo: auth.memberNo,
      bookNumber: bookNumber.value,
    },
  );
  message.value = res.data;
}

onMounted(() => {
  DetailData();
});
</script>

<template>
  <div v-if="book[0]" class="Detail">
    <div class="cartbutton">
      <button @click="AddtoCart">
        {{ book[0].loanAvailable ? "Add to Cart" : "Schedule alarm" }}
      </button>
    </div>

    <div class="Detail-Top">
      <div class="book-image">
        <img :src="`/book/${book[0].bookTitle}.jpg`" :alt="book[0].bookTitle" />
      </div>

      <div class="book-info">
        <h2>{{ book[0].bookTitle }}</h2>
        <p><span>bookNumber:</span> {{ book[0].bookNumber }}</p>
        <p><span>Writer:</span> {{ book[0].writer }}</p>
        <p><span>Publisher:</span> {{ book[0].publisher }}</p>
        <p><span>Catrgory:</span> {{ book[0].category }}</p>
      </div>
    </div>

    <div class="book-content">
      <p>{{ book[0].bookContent }}</p>
    </div>
  </div>
</template>

<style scoped>
.Detail {
  width: 100%;
}

.cartbutton {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
  margin-top: 20px;
}

.Detail-Top {
  display: flex;
  align-items: flex-start;
  gap: 30px;
  margin-bottom: 20px;
}

.book-image img {
  width: 180px;
  height: 240px;
  object-fit: cover;
  display: block;
}

.book-info {
  flex: 1;
}

.book-info h2 {
  margin: 0 0 16px 0;
  color: black;
}

.book-info p {
  margin: 0 0 10px 0;
  line-height: 1.5;
}

.book-info span {
  font-weight: bold;
}

.book-content p {
  margin: 0;
  line-height: 1.6;
  margin-bottom: 20px;
  font-size: 18px;
}
</style>
