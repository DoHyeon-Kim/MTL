<template>
  <div class="announcement-form-page">
    <h2 class="page-title">{{ isEdit ? 'おしらせ編集' : 'おしらせ新規作成' }}</h2>

    <p v-if="loading" class="state-message">読み込み中...</p>

    <form v-else class="form" @submit.prevent="onSubmit">
      <label class="field">
        <span class="field-label">タイトル</span>
        <input v-model="form.title" type="text" required maxlength="255" />
      </label>

      <label class="field">
        <span class="field-label">内容欄</span>
        <textarea v-model="form.content" rows="10" required></textarea>
      </label>

      <p v-if="errorMessage" class="state-message error">{{ errorMessage }}</p>

      <div class="action-row">
        <RouterLink class="btn-catalog" :to="{ name: 'announcement-list' }">目録ボタン</RouterLink>
        <button class="btn-submit" type="submit" :disabled="submitting">
          {{ submitting ? '送信中...' : isEdit ? '更新する' : '登録する' }}
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import { announcementApi } from '@/api/announcementApi'

const route = useRoute()
const router = useRouter()

const isEdit = computed(() => route.name === 'announcement-edit')

const form = reactive({
  title: '',
  content: '',
})

const loading = ref(false)
const submitting = ref(false)
const errorMessage = ref('')

async function loadForEdit() {
  if (!isEdit.value) return
  loading.value = true
  try {
    const data = await announcementApi.get(route.params.id as string)
    form.title = data.title
    form.content = data.content
  } catch (e) {
    errorMessage.value = 'お知らせの取得に失敗しました。'
    console.error(e)
  } finally {
    loading.value = false
  }
}

async function onSubmit() {
  submitting.value = true
  errorMessage.value = ''
  try {
    if (isEdit.value) {
      const id = route.params.id as string
      await announcementApi.update(id, { title: form.title, content: form.content })
      router.push({ name: 'announcement-detail', params: { id } })
    } else {
      const created = await announcementApi.create({ title: form.title, content: form.content })
      router.push({ name: 'announcement-detail', params: { id: created.id } })
    }
  } catch (e) {
    errorMessage.value = '保存に失敗しました。'
    console.error(e)
  } finally {
    submitting.value = false
  }
}

onMounted(loadForEdit)
</script>

<style scoped>
.announcement-form-page {
  max-width: 720px;
  margin: 40px auto;
  padding: 24px;
  border: 1px solid #333;
  background: #fff;
}

.page-title {
  text-align: center;
  margin-bottom: 24px;
}

.state-message {
  text-align: center;
  color: #666;
}

.state-message.error {
  color: #c0392b;
}

.field {
  display: block;
  margin-bottom: 16px;
}

.field-label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
  color: #333;
}

.field input,
.field textarea {
  width: 100%;
  box-sizing: border-box;
  padding: 8px 10px;
  border: 2px solid #e67e22;
  font-family: inherit;
  font-size: 14px;
}

.action-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 16px;
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

.btn-submit {
  padding: 8px 20px;
  background: #282828;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.btn-submit:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
