<script setup lang="ts">
import { computed, ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

interface LoanLists {
  loanNo: number
  bookTitle: string
  bookNumber: number
}

const loans = ref<LoanLists[]>([])
const route = useRoute()
const memberNo = route.params.memberNo
const isEmpty = computed(() => loans.value.length === 0)

//const PLACEHOLDER_IMAGE: string = '/images/books-media/list-view/book-media-01.jpg'

async function getLoanData() {
  try {
    const res = await axios.get<LoanLists[]>(`http://localhost:8099/loans/${memberNo}`)
    loans.value = res.data
  } catch (error) {
    console.error('カートエラー:', error)
  }
}

onMounted(() => {
  getLoanData()
})


</script>

<template>
  <div class="cart-main woocommerce">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="cart-title">レンタルカート</h2>

          <div v-if="isEmpty" class="cart-empty">
            <p>カートには何もありません</p>
          </div>

          <template v-else>

            <table class="table shop_table">
              <thead>
                <tr>
                  <th>一覧</th>
                  <th></th>
                </tr>
              </thead>

              <tbody>
                <tr v-for="item in loans" :key="item.loanNo">
                  <td class="product-name">
                    <span class="book-cover">
                      <div>{{ item.bookNumber }}</div>
                    </span>


                  </td>

                  <td class="product-remove">

                  </td>
                </tr>
              </tbody>
            </table>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>
