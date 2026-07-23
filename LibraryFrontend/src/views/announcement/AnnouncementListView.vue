<template>
  <div class="announcement-list-page">
    <div class="page-head">
      <h2 class="page-title">おしらせ</h2>
      <RouterLink class="btn-new" :to="{ name: 'announcement-new' }">＋ 新規作成</RouterLink>
    </div>

    <p v-if="loading" class="state-message">読み込み中...</p>
    <p v-else-if="errorMessage" class="state-message error">{{ errorMessage }}</p>
    <p v-else-if="announcements.length === 0" class="state-message">お知らせはまだありません。</p>

    <table v-else class="announcement-table">
      <thead>
        <tr>
          <th class="col-content">内容</th>
          <th class="col-date">作成日</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in announcements"
          :key="item.id"
          class="clickable-row"
          @click="goToDetail(item.id)"
        >
          <td class="col-content">{{ item.title }}</td>
          <td class="col-date">{{ formatDate(item.createdAt) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { RouterLink, useRouter } from 'vue-router'
import { announcementApi, type Announcement } from '@/api/announcementApi'

const router = useRouter()

const announcements = ref<Announcement[]>([])
const loading = ref(true)
const errorMessage = ref('')

function formatDate(value: string): string {
  if (!value) return ''
  return value.slice(0, 10)
}

function goToDetail(id: number) {
  router.push({ name: 'announcement-detail', params: { id } })
}

async function fetchAnnouncements() {
  loading.value = true
  errorMessage.value = ''
  try {
    announcements.value = await announcementApi.list()
  } catch (e) {
    errorMessage.value = 'お知らせの取得に失敗しました。'
    console.error(e)
  } finally {
    loading.value = false
  }
}

onMounted(fetchAnnouncements)
</script>

<style scoped>
.announcement-list-page {
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

.announcement-table {
  width: 100%;
  border-collapse: collapse;
}

.announcement-table th,
.announcement-table td {
  border-bottom: 1px solid #ccc;
  padding: 12px 10px;
  text-align: left;
}

.announcement-table th {
  font-weight: 600;
  color: #333;
  border-bottom: 2px solid #333;
}

.col-content {
  width: 70%;
}

.col-date {
  width: 30%;
  color: #555;
}

.clickable-row {
  cursor: pointer;
}

.clickable-row:hover {
  background-color: #f5f5f5;
}
</style>
