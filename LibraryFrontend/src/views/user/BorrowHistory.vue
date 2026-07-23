<template>
  <div class="col-md-12" style="margin-left: 5%; margin-top: 5%;">
    <div class="page type-page status-publish hentry">
      <div class="entry-content">
        <div class="woocommerce table-tabs">
          <div>
            <div>
              <form>
                <div class="search-area">
                  <input type="date" v-model="firstDay" style="width: 20%;">

                  <span> ~ </span>

                  <input type="date" v-model="secondDay" style="width: 20%;">
                  <input type="text" v-model="titlesearch" style="width: 40%;">

                  <button type="button" @click="getBookList" style="margin-left: 5%;">
                    検索
                  </button>
                </div>

                <table class="table table-bordered shop_table cart">
                  <colgroup>
                    <col style="width: 40%">
                    <col style="width: 15%">
                    <col style="width: 15%">
                    <col style="width: 15%">
                  </colgroup>

                  <thead>

                    <tr>
                      <th>書籍名</th>
                      <th>貸出日</th>
                      <th>返却日</th>
                      <th>レビュー</th>

                    </tr>
                  </thead>

                  <tbody>
                    <tr v-if="bookList.length === 0">
                      <td colspan="6">貸出履歴がありません。</td>
                    </tr>
                    <tr v-for="book in bookList" :key="book.loanNo">
                      <td class="book-info">
                        <img :src="book.bookImg" class="book-img">

                        <div class="book-detail">
                          <div class="book-title">{{ book.bookTitle }}</div>
                          <div class="book-writer">{{ book.writer }}</div>
                        </div>
                      </td>
                      <td>{{ book.loanDate }}</td>
                      <td>{{ book.returnDate }}</td>

                      <td class="review-cell">
                        <button v-if="book.stars === 0" type="button" class="review-button" @click="openReview(book)">
                          レビュー
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <Dialog v-model:visible="visible" modal :header="selectedBook?.bookTitle" :style="{ width: '30rem' }">
    <div class="review-modal-body">
      <div class="book-summary">
        <img :src="selectedBook?.bookImg" class="modal-book-img">

        <div>
          <div class="modal-book-writer">
            {{ selectedBook?.writer }}
          </div>
        </div>
      </div>

      <div class="rating-section">
        <div style="font-size: large;">この本はいかがでしたか？</div>
        <Rating v-model="stars" :cancel="false" class="review-rating" />
      </div>
    </div>
    <br />
    <div class="review-section">
      <label>レビュー内容</label>


      <Textarea v-model="review" class="review-textarea" placeholder="レビューを入力してください(文字：３００以下)" rows="5" maxlength="300"
        autoResize />
    </div>

    <template #footer>
      <Button label="登録" @click="insertReview" class="review-btn" />

      <Button label="キャンセル" severity="secondary" outlined class="review-btn" @click="visible = false" />
    </template>
  </Dialog>

</template>


<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'

const firstDay = ref('')
const secondDay = ref('')
const titlesearch = ref('')

const bookList = ref<Book[]>([])

interface Book {
  loanNo: number
  bookTitle: string
  loanDate: string
  returnDate: string
  stars: number
  writer: string
  bookImg: string
  bookNumber: string
}

//model
import Button from 'primevue/button'
import Dialog from 'primevue/dialog'
import Rating from 'primevue/rating'

const visible = ref(false);
const stars = ref(0)
const review = ref('');

const selectedBook = ref<MemberInfoBookDTO | null>(null)

interface MemberInfoBookDTO {
  loanNo: number
  bookTitle: string
  loanDate: string
  returnDate: string
  stars: number
  writer: string
  bookImg: string
  bookNumber: string
}

function openReview(book: MemberInfoBookDTO) {
  selectedBook.value = book
  visible.value = true
}
//
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const loginMemberNo = auth.memberNo




//logintest, Calendar

onMounted(async () => {

  const today = new Date()
  secondDay.value = today.toISOString().slice(0, 10)

  const weekAgo = new Date(today)
  weekAgo.setDate(weekAgo.getDate() - 7)
  firstDay.value = weekAgo.toISOString().slice(0, 10)

  getBookList();

})


//BookList検索
async function getBookList() {
  const res = await axios.get(
    'http://localhost:8099/user/mypageBookList',
    {
      params: {
        memberNo: loginMemberNo,
        firstDay: firstDay.value,
        secondDay: secondDay.value,
        titlesearch: titlesearch.value
      },
      withCredentials: true
    }
  )

  bookList.value = res.data
}

//review 登録
async function insertReview() {
  await axios.post("http://localhost:8099/user/review", {
    loanNo: selectedBook.value?.loanNo,
    bookNumber: selectedBook.value?.bookNumber,
    memberNo: loginMemberNo,
    stars: stars.value,
    review: review.value
  })
  visible.value = false
  getBookList();
}

</script>

<style lang="css">
.book-info {
  display: flex;
  align-items: center;
  gap: 20px;
}

.book-img {
  width: 70px;
  height: 100px;
  object-fit: cover;
}

.book-detail {
  display: flex;
  flex-direction: column;
}

.book-title {
  font-size: 18px;
  font-weight: 700;
  margin-bottom: 8px;
}

.book-writer {
  font-size: 14px;
  color: #777;
}

.borrow-table td {
  vertical-align: middle;
}

.borrow-table td:nth-child(2),
.borrow-table td:nth-child(3) {
  text-align: center;
  font-size: 15px;
  white-space: nowrap;
}

.review-cell {
  text-align: center;
  vertical-align: middle;
}

.review-button {
  width: 120px;
  height: 46px;
}





/*model*/
.review-modal-body {
  display: flex;
  flex-direction: column;
  gap: 22px;
}

.book-summary {
  display: flex;
  align-items: center;
  gap: 16px;
}

.modal-book-img {
  width: 75px;
  height: 105px;
  object-fit: cover;
  border-radius: 4px;
  background: #f3f3f3;
}

.modal-book-title {
  font-size: 17px;
  font-weight: 700;
  margin-bottom: 6px;
}

.modal-book-writer {
  font-size: 14px;
  color: #777;
}

.review-rating {
  display: flex;
  align-items: center;
  gap: 16px;
}

.rating-label {
  font-size: 15px;
  font-weight: 600;
}

.review-textarea {
  width: 100%;
  resize: none;
}

.review-rating .p-rating-icon {
  width: 30px;
  height: 30px;
}

.review-btn {
  width: 140px;
  height: 42px;
}
</style>
