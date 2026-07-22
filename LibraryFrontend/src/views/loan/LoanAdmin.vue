<script setup lang="ts">
import { ref } from "vue";
import axios from "axios";

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
  memberId: string;
}

const loans = ref<LoanList[]>([]);
const userId = ref("");

async function getLoanData(memberNo: number) {
  try {
    const res = await axios.get<LoanList[]>(`http://localhost:8099/member/loans/${memberNo}`, {
      params: { state: 1 },
    });
    loans.value = res.data;
  } catch (error) {
    console.error("貸出一覧の取得に失敗しました:", error);
  }
}
</script>

<template>
  <div class="cart-main woocommerce">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="cart-header">
            <h2 class="cart-title">貸出中の本</h2>
            <input
              v-model="userId"
              type="text"
              placeholder="会員番号"
              @keyup.enter="getLoanData(Number(userId))"
            />
          </div>

          <table class="table shop_table">
            <thead>
              <tr>
                <th>メンバーID</th>
                <th>本</th>
                <th>期間</th>
                <th>処理</th>
              </tr>
            </thead>

            <tbody>
              <tr v-for="item in loans" :key="item.loanNo">
                <td class="product-name">
                  <span class="book-cover"> </span>
                  <span class="product-detail">
                    <span
                      ><strong>{{ item.memberNo }}{{ "_" }}{{ item.memberId }}</strong></span
                    >
                  </span>
                </td>

                <td class="product-loan-date">
                  {{ item.bookNumber }}{{ "_" }}{{ item.bookTitle }}
                </td>

                <td>{{ item.dueDate }}</td>

                <td>
                  <button></button>
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
.cart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.book-cover img {
  width: 80px;
  height: 100px;
  object-fit: cover;
  border: 1px solid #000000;
}
</style>
