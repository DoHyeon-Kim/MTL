<template>
  <div class="announcement-detail-page">
    <p v-if="loading" class="state-message">読み込み中...</p>
    <p v-else-if="errorMessage" class="state-message error">{{ errorMessage }}</p>

    <template v-else-if="announcement">
      <div class="title-row">タイトル：{{ announcement.title }}</div>

      <div class="content-box">
        <div class="content-box-label">内容欄</div>
        <p class="content-text">{{ announcement.content }}</p>
      </div>

      <div class="action-row">
        <div class="admin-actions">
          <RouterLink class="btn-edit" :to="{ name: 'announcement-edit', params: { id: announcement.id } }">
            編集
          </RouterLink>
          <button class="btn-delete" type="button" @click="onDelete">削除</button>
        </div>

        <RouterLink class="btn-catalog" :to="{ name: 'announcement-list' }">目録ボタン</RouterLink>
      </div>
    </template>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import { announcementApi, type Announcement } from '@/api/announcementApi'

const route = useRoute()
const router = useRouter()

const announcement = ref<Announcement | null>(null)
const loading = ref(true)
const errorMessage = ref('')

async function fetchAnnouncement() {
  loading.value = true
  errorMessage.value = ''
  try {
    announcement.value = await announcementApi.get(route.params.id as string)
  } catch (e) {
    errorMessage.value = 'お知らせの取得に失敗しました。'
    console.error(e)
  } finally {
    loading.value = false
  }
}

async function onDelete() {
  if (!announcement.value) return
  if (!confirm('このお知らせを削除しますか？')) return
  await announcementApi.remove(announcement.value.id)
  router.push({ name: 'announcement-list' })
}

onMounted(fetchAnnouncement)
</script>

<style scoped>
.announcement-detail-page {
  max-width: 720px;
  margin: 40px auto;
  padding: 24px;
  border: 1px solid #333;
  background: #fff;
}

.state-message {
  text-align: center;
  color: #666;
  padding: 24px 0;
}

.state-message.error {
  color: #c0392b;
}

.title-row {
  font-weight: 700;
  margin-bottom: 16px;
}

.content-box {
  border: 2px solid #e67e22;
  min-height: 260px;
  padding: 16px;
  margin-bottom: 16px;
}

.content-box-label {
  color: #999;
  font-size: 13px;
  margin-bottom: 8px;
}

.content-text {
  white-space: pre-wrap;
  line-height: 1.7;
}

.action-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.admin-actions {
  display: flex;
  gap: 8px;
}

.btn-edit,
.btn-delete {
  padding: 6px 14px;
  font-size: 13px;
  border-radius: 4px;
  border: 1px solid #999;
  background: #fff;
  color: #333;
  text-decoration: none;
  cursor: pointer;
}

.btn-delete {
  border-color: #c0392b;
  color: #c0392b;
}

.btn-catalog {
  border: 2px solid #2f8f4e;
  color: #2f8f4e;
  padding: 6px 16px;
  border-radius: 4px;
  text-decoration: none;
  font-size: 14px;
}

.btn-catalog:hover {
  background: #2f8f4e;
  color: #fff;
}
</style>
