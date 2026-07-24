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

async function changeloanstate(loanNo: number, state: number) {
  try {
    await axios.put(
      `http://localhost:8099/member/loans/${loanNo}`,
      null,
      { params: { state } }
    );
    await getLoanData(userId.value);
  } catch (error) {
    console.error("貸出状態の変更に失敗しました:", error);
    alert("貸出状態の変更に失敗しました");
  }
}

async function getLoanData(memberId: string) {
  try {
    const res = await axios.get<LoanList[]>("http://localhost:8099/member/loan", {
  params: { memberId },
});
    loans.value = res.data;
  } catch (error) {
    console.error("貸出一覧の取得に失敗しました:", error);
    alert("貸出一覧の取得に失敗しました");
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
              @keyup.enter="getLoanData(String(userId))"
            />
          </div>

          <table class="table shop_table">
            <thead>
              <tr>
                <th>種類</th>
                <th>メンバーID</th>
                <th>本</th>
                <th>期間</th>
                <th>処理</th>
              </tr>
            </thead>

            <tbody>
              <tr v-for="item in loans" :key="item.loanNo">

                <td>
                  <span v-if="item.requestState === 1">貸出申請</span>
                  <span v-else-if="item.requestState === 2">貸出中</span>
                  <span v-else-if="item.requestState === 3">延長申請</span>
                </td>
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
                  <button v-if="item.requestState === 1" @click="changeloanstate(item.loanNo, 2)">承認</button>
                  <button v-else-if="item.requestState === 2" @click="changeloanstate(item.loanNo, 4)">返却</button>
                  <button v-else-if="item.requestState === 3" @click="changeloanstate(item.loanNo, 3)">延長申請</button>
                  <button v-else>error</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

