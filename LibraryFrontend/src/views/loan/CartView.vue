<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

interface CartItem {
  bookNumber: number
  cartItemNo: number
  bookTitle: string
  writer: string
  loanAvailable: boolean
}

const router = useRouter()
const route = useRoute()
const memberNo = route.params.memberNo
const PLACEHOLDER_IMAGE: string = '/images/books-media/list-view/book-media-01.jpg'

const carts = ref<CartItem[]>([])
const checkedItems = ref<number[]>([])

onMounted(() => {
  getCartData()
})

async function getCartData() {
  try {
    const res = await axios.get<CartItem[]>(`http://localhost:8099/carts/${memberNo}`)
    carts.value = res.data
  } catch (error) {
    console.error('カートエラー:', error)
  }
}

async function deleteItems(ids: number[]) {
  if (ids.length === 0) {
    alert('削除する項目を選択してください')
    return
  }
  try {
    await axios.delete('http://localhost:8099/carts', { data: ids })
    checkedItems.value = []
    getCartData()
  } catch (error) {
    console.error('削除失敗:', error)
    alert('削除失敗')
  }
}

async function reserve(cartItemNos: number[]) {
  if (cartItemNos.length === 0) {
    alert('予約する項目を選択してください')
    return
  }
  const targets = carts.value.filter(item =>
    cartItemNos.includes(item.cartItemNo)
  )
  try {
    await axios.post(
      `http://localhost:8099/loans/${memberNo}`,
      targets.map(item => item.bookNumber)
    )
    deleteItems(cartItemNos)
  } catch (error) {
    console.error('予約失敗:', error)
    alert('予約失敗' + error)
  }
}

function selectAll() {
  const availableItems = carts.value.filter(item => item.loanAvailable)

  checkedItems.value =
    checkedItems.value.length === availableItems.length
      ? []
      : availableItems.map(item => item.cartItemNo)
}

function bookshosai(bookNumber: number) {
  router.push(`/${bookNumber}`)
}
</script>

<template>
  <div class="cart-main woocommerce">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="cart-title">レンタルカート</h2>

          <div v-if="carts.length === 0" class="cart-empty">
            <p>カートには何もありません</p>
          </div>

          <template v-else>
            <div>
              <button class="select-all" @click.stop="selectAll">
                all select
              </button>

              <div class="select-devdel">
                <button @click.stop="deleteItems(checkedItems)">
                  select del
                </button>

                <button @click.stop="reserve(checkedItems)">
                  select rev
                </button>
              </div>
            </div>

            <table class="table shop_table">
              <thead>
                <tr>
                  <th>一覧</th>
                  <th></th>
                </tr>
              </thead>

              <tbody>
                <tr v-for="item in carts" :key="item.cartItemNo" @click="bookshosai(item.bookNumber)">
                  <td class="product-name">
                    <input
                      type="checkbox"
                      class="book-checkbox"
                      :value="item.cartItemNo"
                      v-model="checkedItems"
                      style="margin-right: 15px;"
                      :disabled="!item.loanAvailable"
                      @click.stop
                    />
                    <span class="book-cover">
                      <img :src="PLACEHOLDER_IMAGE" :alt="item.bookTitle" />
                    </span>
                    <span class="product-detail">
                      <span><strong>{{ item.bookTitle }}</strong></span>
                      <span><strong>著者:</strong> {{ item.writer }}</span>
                    </span>
                  </td>

                  <td class="product-remove">
                    <button
                      @click.stop="reserve([item.cartItemNo])"
                      :disabled="!item.loanAvailable"
                    >
                      予約
                    </button>

                    <button
                      @click.stop="deleteItems([item.cartItemNo])"
                      style="margin-top: 10px;"
                    >
                      削除
                    </button>
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

<style scoped>
.book-checkbox {
  flex-shrink: 0;
  width: 16px;
  height: 16px;
}

.cart-title + div {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
  margin-top: 10px;
  margin-bottom: 3px;
}

.select-all,
.select-devdel button {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  height: 36px;
  min-width: 100px;
  padding: 0 16px;
  font-size: 13px;
  line-height: 1;
  border: none;
}

.select-devdel {
  display: flex;
  align-items: center;
  gap: 10px;
  width: auto;
  float: none;
}

.cart-title {
  margin-bottom: 30px;
  font-size: 28px;
  color: rgb(0, 0, 0);
  font-weight: bold;
}

.cart-empty {
  padding: 60px 0;
  text-align: center;
  color: #000000;
  font-size: 16px;
}

.shop_table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 30px;
}

.shop_table th {
  padding: 15px;
  text-align: left;
  font-weight: 600;
}

.shop_table td {
  padding: 15px;
  vertical-align: middle;
}

.book-cover img {
  width: 80px;
  height: 100px;
  object-fit: cover;
  border: 1px solid #ddd;
}

.product-detail {
  display: flex;
  flex-direction: column;
  gap: 5px;
  flex: 1;
}

.product-detail span {
  font-size: 14px;
  line-height: 1.5;
}

.product-remove {
  width: 20%;
  text-align: center;
}
</style>
