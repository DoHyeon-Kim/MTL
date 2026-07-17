<script setup lang="ts">
import { computed, ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

// 수정사항 - 버튼위치 / Delete함수 로직수정

interface CartItem {
  cartItemNo: number
  bookTitle: string
  writer: string
  loanAvailable: boolean
}

const route = useRoute()
const memberNo = route.params.memberNo
const PLACEHOLDER_IMAGE: string = '/images/books-media/list-view/book-media-01.jpg'

const carts = ref<CartItem[]>([])
const checkedItems = ref<number[]>([])

const isEmpty = computed(() => carts.value.length === 0)
const cartItems = computed(() => carts.value)

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

async function removeItem(cartItemNo: number) {
  try {
    await axios.delete(`http://localhost:8099/carts/${cartItemNo}`)
  } catch (error) {
    console.error('削除失敗:', error)
    alert('削除失敗')
  }
  getCartData()
}

async function multSelect(selectedIds: number[]) {
  if (selectedIds.length === 0) {
    alert('削除する項目を選択してください')
    return
  }
  await axios.delete('http://localhost:8099/carts', {
    data: selectedIds,
  })

  checkedItems.value = []
  await getCartData()
}

async function resvItem() {
  // 処理loan
}

function selectAll() {
  checkedItems.value =
    checkedItems.value.length === cartItems.value.length
      ? []
      : cartItems.value.map(item => item.cartItemNo)
}
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
            <div>
              <button class="select-all" @click.stop="selectAll">
                all select
              </button>

              <div class="select-devdel">
                <button @click.stop="multSelect(checkedItems)">
                  select del
                </button>

                <button @click.stop="multSelect(checkedItems)">
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
                <tr v-for="item in cartItems" :key="item.cartItemNo">
                  <td class="product-name">
                    <input
                      type="checkbox"
                      class="book-checkbox"
                      :value="item.cartItemNo"
                      v-model="checkedItems"
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
                    <button @click.stop="removeItem(item.cartItemNo)">
                      予約
                    </button>

                    <button
                      @click.stop="removeItem(item.cartItemNo)"
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
  color: #6b6b6b;
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
  border-bottom: 2px solid #ddd;
}

.shop_table td {
  padding: 15px;
  border-bottom: 1px solid #ddd;
  vertical-align: middle;
}

.product-name {
  width: 80%;
  display: flex;
  align-items: center;
  gap: 15px;
}

.book-cover {
  flex-shrink: 0;
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
