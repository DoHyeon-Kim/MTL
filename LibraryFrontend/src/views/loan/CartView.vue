<script setup>
import { computed, ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const carts = ref([])

const memberNo = route.params.memberNo
const PLACEHOLDER_IMAGE = '/images/books-media/list-view/book-media-01.jpg'
const isEmpty = computed(() => carts.value.length === 0)
const cartItems = computed(() => carts.value)

async function getCartData() {
  try {
    const res = await axios.get(
      `http://localhost:8099/carts/${memberNo}`
    )
    carts.value = res.data
  } catch (error) {
    console.error(':', error)
  }
}

onMounted(() => {
  getCartData()
})


async function removeItem(cartItemNo) {
  try {
    await axios.delete(
      `http://localhost:8099/carts/${cartItemNo}`
    )
    await getCartData()
  } catch (error) {
    console.error('삭제 실패:', error)
    alert('삭제 실패')
  }
}
</script>

<template>
  <div class="cart-main woocommerce">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="cart-title">장바구니</h2>

          <!-- 빈 카트 -->
          <div v-if="isEmpty" class="cart-empty">
            <p>장바구니에 담긴 도서가 없습니다.</p>
          </div>

          <!-- 카트 항목 -->
          <template v-else>
            <table class="table table-bordered shop_table cart">

              <thead>
                <tr>
                  <th class="product-name">情報</th>
                  <th class="product-test">管理</th>
                </tr>
              </thead>

              <tbody>
                <tr
                  v-for="item in cartItems"
                  :key="item.cartItemNo"
                  class="cart_item"
                >
                  <td class="product-name">
                    <span class="product-thumbnail">
                      <img
                        :src="PLACEHOLDER_IMAGE"
                        :alt="item.bookTitle"
                      />
                    </span>
                    <span class="product-detail">
                      <span><strong>{{ item.bookTitle }}</strong></span>
                      <span><strong>저자（수정:</strong> {{ item.writer }}</span>
                    </span>
                  </td>

                  <td class="product-remove">
                    <button @click.prevent="removeItem(item.cartItemNo)" >
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
.cart-title {
  margin-bottom: 30px;
  font-size: 28px;
  color: black;
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

.shop_table thead {
  background-color: hsl(280, 100%, 50%);
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

.product-thumbnail {
  flex-shrink: 0;
}

.product-thumbnail img {
  width: 80px;
  height: 120px;
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

.product-detail strong {
  color: #282828;
}

.product-remove {
  width: 20%;
  text-align: center;
}

.remove-link {
  display: inline-block;
  color: #999;
  text-decoration: underline;
  font-size: 13px;
  cursor: pointer;
  transition: color 0.2s;
}



</style>
