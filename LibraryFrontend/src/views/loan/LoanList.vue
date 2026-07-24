<script setup lang="ts">
import { computed, ref, onMounted } from "vue";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";

interface LoanList {
  loanNo: number;
  writer: string;
  bookNumber: number;
  memberNo: number;
  bookTitle: string;
  loanDate: string;
  dueDate: string;
  returnDate: string;
  extension: boolean;
  requestState: number;
}

const auth = useAuthStore();
const memberNo = auth.memberNo;
const PLACEHOLDER_IMAGE: string = "/images/books-media/list-view/book-media-01.jpg";

const loans = ref<LoanList[]>([]);
const isEmpty = computed(() => loans.value.length === 0);

async function getLoanData() {
  try {
    const res = await axios.get<LoanList[]>(`http://localhost:8099/member/loans/${memberNo}`, {
      params: { state: 2 },
    });
    loans.value = res.data;
  } catch (error) {
    console.error("貸出一覧の取得に失敗しました:", error);
  }
}

async function requestExtension(loanNo: number) {
  await axios.put(`http://localhost:8099/member/loans/${loanNo}`, null, {
    params: { state: 2 },
  });
  await getLoanData();
}

onMounted(() => {
  getLoanData();
});
</script>

<template>
  <div class="cart-main woocommerce">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="cart-title">貸出中の本</h2>

          <div v-if="isEmpty" class="cart-empty">
            <p>貸出中の本はありません</p>
          </div>

          <table v-else class="table shop_table">
            <thead>
              <tr>
                <th>図書</th>
                <th>貸出日</th>
                <th>延長</th>
                <th></th>
              </tr>
            </thead>

            <tbody>
              <tr v-for="item in loans" :key="item.loanNo">
                <td class="product-name">
                  <span class="book-cover">
                    <img :src="PLACEHOLDER_IMAGE" :alt="item.bookTitle" />
                  </span>
                  <span class="product-detail">
                    <span
                      ><strong>{{ item.bookTitle }}</strong></span
                    >
                    <span><strong>著者:</strong> {{ item.writer }}</span>
                  </span>
                </td>

                <td class="product-loan-date">{{ item.loanDate }}{{ " ~ " }} {{ item.dueDate }}</td>
                <td v-if="item.extension">延長可能</td>
                <td v-else>延長不可能</td>
                <td>
                  <button @click="requestExtension(item.loanNo)">延長</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.book-cover img {
  width: 80px;
  height: 100px;
  object-fit: cover;
  border: 1px solid #000000;
}
</style>
