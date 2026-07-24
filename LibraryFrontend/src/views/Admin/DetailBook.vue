<template>
  <div class="detail-container">
    <div class="detail-actions">
      <button class="back-btn" @click="goBack">戻る</button>
      <button class="edit-btn" @click="goToEdit">編集</button>
    </div>

    <div class="detail-card">
      <div class="image-side">
        <img v-if="book.bookImg" :src="book.bookImg" alt="book image" />
        <div v-else class="placeholder">No Image</div>
      </div>

      <div class="info-side">
        <h2 style="color: #000">{{ book.bookTitle }}</h2>
        <p><strong>著者:</strong> {{ book.writer }}</p>
        <p><strong>出版社:</strong> {{ book.publisher }}</p>
        <p><strong>出版日:</strong> {{ book.publicationDate }}</p>
        <p><strong>登録日:</strong> {{ book.registrationDate }}</p>
        <p><strong>カテゴリー:</strong> {{ book.category }}</p>
      </div>
    </div>
    <div class="content">
      <h3>説明</h3>
      <p>{{ book.bookContent }}</p>
    </div>

    <div class="stock-section">
      <div class="stock-actions">
        <h3>在庫 / レンタル状況</h3>
        <button
          class="btn-delete"
          v-if="selectedStockItems.length > 0"
          @click="deleteSelectedStocks"
        >
          選択削除
        </button>
      </div>

      <table class="stock-table">
        <thead>
          <tr>
            <th class="checkbox-col">
              <input type="checkbox" :checked="isAllStockSelected" @change="selectAllStockItems" />
            </th>
            <th>コピー番号</th>
            <th>ステータス</th>
            <th>借用者</th>
            <th>貸出日</th>
            <th>返却予定日</th>
            <th>アクション</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in book.stockList" :key="item.bookNumberInfo">
            <td class="checkbox-col">
              <input type="checkbox" :value="item.bookNumber" v-model="selectedStockItems" />
            </td>
            <td>{{ item.bookNumber }}</td>
            <td>{{ item.loanAvailable ? "貸出可能" : "貸出中" }}</td>
            <td>{{ item.nameH || "-" }}</td>
            <td>{{ item.loanDate || "-" }}</td>
            <td>{{ item.dueDate || "-" }}</td>
            <td>
              <button class="btn-delete" @click="deleteStock(item.bookNumber)">削除</button>
            </td>
          </tr>
          <tr v-if="book.stockList.length === 0">
            <td colspan="7" class="empty-row">在庫データがありません</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed, reactive, ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";

const route = useRoute();
const router = useRouter();
const bookNumber = route.params.id as string;
const selectedStockItems = ref<string[]>([]);

const book = reactive({
  bookImg: "",
  bookTitle: "",
  writer: "",
  publisher: "",
  category: "",
  bookContent: "",
  publicationDate: "",
  registrationDate: "",
  stockList: [] as Array<{
    bookNumber: string;
    bookNumberInfo: string;
    loanAvailable: boolean;
    nameH: string;
    loanDate: string;
    dueDate: string;
  }>,
});

const fetchBook = async () => {
  try {
    const res = await axios.get(`http://localhost:8099/bookmanagedetail/${bookNumber}`);
    const data = res.data;

    book.bookImg = data.bookImg || data.imageUrl || data.image || "";
    book.bookTitle = data.bookTitle || "";
    book.writer = data.writer || "";
    book.publisher = data.publisher || "";
    book.category = data.category || "";
    book.bookContent = data.bookContent || "";
    book.publicationDate = data.publicationDate || "";
    book.registrationDate = data.registrationDate || "";

    await fetchBookStock(data.bookNumberInfo);
  } catch (err) {
    console.error(err);
  }
};
const fetchBookStock = async (infoNum: number | string) => {
  try {
    const res = await axios.get(`http://localhost:8099/bookmanagedetail/${infoNum}/stock`);
    const data = res.data;

    book.stockList = Array.isArray(data)
      ? data.map(
          (item: {
            bookNumber: string;
            bookNumberInfo: string;
            loanAvailable: boolean;
            nameH: string;
            loanDate: string;
            dueDate: string;
          }) => ({
            bookNumber: item.bookNumber || "-",
            bookNumberInfo: item.bookNumberInfo || "-",
            loanAvailable: item.loanAvailable ?? true,
            nameH: item.nameH || "",
            loanDate: item.loanDate || "",
            dueDate: item.dueDate || "",
          }),
        )
      : [];
    selectedStockItems.value = [];
  } catch (err) {
    console.error("在庫確認失敗:", err);
  }
};

onMounted(() => {
  if (bookNumber) fetchBook();
});

const isAllStockSelected = computed(
  () => book.stockList.length > 0 && selectedStockItems.value.length === book.stockList.length,
);

const selectAllStockItems = () => {
  if (isAllStockSelected.value) {
    selectedStockItems.value = [];
  } else {
    selectedStockItems.value = book.stockList.map((item) => item.bookNumber);
  }
};

const deleteStock = async (bookNumber: string) => {
  if (!bookNumber) return;
  if (!confirm("この在庫を削除してもよろしいですか？")) return;

  try {
    await axios.put(`http://localhost:8099/bookDeleteStock/${bookNumber}`);
    book.stockList = book.stockList.filter((item) => item.bookNumber !== bookNumber);
    selectedStockItems.value = selectedStockItems.value.filter((id) => id !== bookNumber);
    alert("削除しました");
  } catch (err) {
    console.error(err);
    alert("削除に失敗しました");
  }
};

const deleteSelectedStocks = async () => {
  if (selectedStockItems.value.length === 0) return;
  if (!confirm(`${selectedStockItems.value.length}件の在庫を削除してもよろしいですか？`)) return;

  try {
    for (const bookNumber of selectedStockItems.value) {
      await axios.put(`http://localhost:8099/bookDeleteStock/${bookNumber}`);
    }
    book.stockList = book.stockList.filter(
      (item) => !selectedStockItems.value.includes(item.bookNumber),
    );
    selectedStockItems.value = [];
    alert("削除しました");
  } catch (err) {
    console.error(err);
    alert("削除に失敗しました");
  }
};

const goBack = () => {
  const prevTitle = route.query.title as string | undefined;
  if (prevTitle && prevTitle.trim()) {
    router.push({ path: "/admin/bookmanagelist", query: { title: prevTitle } });
  } else {
    router.back();
  }
};

const goToEdit = () => {
  router.replace(`/admin/bookmanagelist/editbook/${bookNumber}`);
};
</script>

<style scoped>
.detail-container {
  max-width: 960px;
  margin: 24px auto;
  padding: 16px;
}
.back-btn {
  background-color: #0b7dda;
  border: 1px solid #ccc;
  padding: 6px 10px;
  border-radius: 6px;
  cursor: pointer;
}
.detail-card {
  display: flex;
  gap: 20px;
  margin-top: 16px;
}
.image-side {
  flex: 0 0 320px;
  min-height: 320px;
  border: 1px dashed #e2e8f0;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.image-side img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.placeholder {
  color: #94a3b8;
}
.info-side {
  flex: 1;
}
.detail-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
.back-btn,
.edit-btn {
  border: 1px solid white;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
  background: #ffa600;
}
.edit-btn {
  background: #0b7dda;
  color: white;
  border-color: #0a68b5;
}
.content {
  margin-top: 12px;
  background: #f8fafc;
  padding: 12px;
  border-radius: 6px;
}
.stock-section {
  margin-top: 24px;
}
.stock-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 12px;
}
.stock-table th,
.stock-table td {
  border: 1px solid #e2e8f0;
  padding: 10px;
  text-align: left;
}
.stock-table th {
  background: #eef2ff;
}
.empty-row {
  text-align: center;
  color: #64748b;
}
</style>
