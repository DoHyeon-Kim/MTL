<template>
  <div class="qanda-list-page">
    <div class="page-head">
      <h2 class="page-title">Q&amp;A</h2>
      <RouterLink class="btn-new" :to="{ name: 'qanda-new' }">＋ 質問する</RouterLink>
    </div>

    <p v-if="loading" class="state-message">読み込み中...</p>
    <p v-else-if="errorMessage" class="state-message error">{{ errorMessage }}</p>
    <p v-else-if="qandaList.length === 0" class="state-message">Q&amp;Aはまだありません。</p>

    <table v-else class="qanda-table">
      <thead>
        <tr>
          <th class="col-content">内容</th>
          <th class="col-status">状態</th>
          <th class="col-date">作成日</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in qandaList"
          :key="item.id"
          class="clickable-row"
          @click="goToDetail(item.id)"
        >
          <td class="col-content">{{ item.content }}</td>
          <td class="col-status">
            <span class="status-badge" :class="item.status === '答え完了' ? 'done' : 'pending'">
              {{ item.status }}
            </span>
          </td>
          <td class="col-date">{{ formatDate(item.createdAt) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import { qandaApi, type Qanda } from "@/api/qandaApi";

const router = useRouter();

const qandaList = ref<Qanda[]>([]);
const loading = ref(true);
const errorMessage = ref("");

function formatDate(value: string): string {
  if (!value) return "";
  return value.slice(0, 10);
}

function goToDetail(id: number) {
  router.push({ name: "qanda-detail", params: { id } });
}

async function fetchQandaList() {
  loading.value = true;
  errorMessage.value = "";
  try {
    qandaList.value = await qandaApi.list();
  } catch (e) {
    errorMessage.value = "Q&Aの取得に失敗しました。バックエンドが起動しているか確認してください。";
    console.error(e);
  } finally {
    loading.value = false;
  }
}

onMounted(fetchQandaList);
</script>

<style scoped>
.qanda-list-page {
  max-width: 720px;
  margin: 40px auto;
  padding: 24px;
  border: 1px solid #333;
  background: #fff;
}

.page-head {
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  margin-bottom: 24px;
}

.page-title {
  text-align: center;
  font-weight: 700;
  letter-spacing: 0.2em;
}

.btn-new {
  position: absolute;
  right: 0;
  padding: 6px 14px;
  border: 1px solid #2f8f4e;
  color: #2f8f4e;
  border-radius: 4px;
  text-decoration: none;
  font-size: 13px;
  white-space: nowrap;
}

.btn-new:hover {
  background: #2f8f4e;
  color: #fff;
}

.state-message {
  text-align: center;
  color: #666;
  padding: 24px 0;
}

.state-message.error {
  color: #c0392b;
}

.qanda-table {
  width: 100%;
  border-collapse: collapse;
}

.qanda-table th,
.qanda-table td {
  border-bottom: 1px solid #ccc;
  padding: 12px 10px;
  text-align: left;
}

.qanda-table th {
  font-weight: 600;
  color: #333;
  border-bottom: 2px solid #333;
}

.col-content {
  width: 50%;
}

.col-status {
  width: 25%;
}

.col-date {
  width: 25%;
  color: #555;
}

.status-badge {
  font-size: 13px;
  padding: 2px 8px;
  border-radius: 3px;
}

.status-badge.done {
  color: #2f8f4e;
  border: 1px solid #2f8f4e;
}

.status-badge.pending {
  color: #b7791f;
  border: 1px solid #b7791f;
}

.clickable-row {
  cursor: pointer;
}

.clickable-row:hover {
  background-color: #f5f5f5;
}
</style>
