<template>
  <div class="qanda-form-page">
    <p v-if="loading" class="state-message">読み込み中...</p>
    <p v-else-if="errorMessage" class="state-message error">{{ errorMessage }}</p>

    <template v-else>
      <!-- 新規質問モード -->
      <form v-if="!isDetail" class="new-question" @submit.prevent="onCreate">
        <h2 class="page-title">質問する</h2>

        <label class="field">
          <span class="field-label">タイトル</span>
          <input v-model="newQuestion.title" type="text" required maxlength="255" />
        </label>

        <label class="field">
          <span class="field-label">内容欄</span>
          <textarea v-model="newQuestion.content" rows="8" required></textarea>
        </label>

        <div class="action-row">
          <RouterLink class="btn-catalog" :to="{ name: 'qanda-list' }">目録</RouterLink>
          <button class="btn-submit" type="submit" :disabled="submitting">
            {{ submitting ? '送信中...' : '質問を送信する' }}
          </button>
        </div>
      </form>

      <!-- 詳細（質問＋回答）表示モード -->
      <div v-else-if="qanda">
        <div class="qa-block question-block">
          <div class="qa-heading">{{ qanda.title }}</div>
          <div class="qa-box rounded">
            <div class="qa-box-label">内容欄</div>
            <p class="qa-text">{{ qanda.content }}</p>
          </div>
        </div>

        <div class="qa-block answer-block">
          <div class="qa-heading">答え</div>
          <div class="qa-box">
            <div class="qa-box-label">内容欄</div>
            <p v-if="qanda.answer" class="qa-text">{{ qanda.answer }}</p>
            <p v-else class="qa-text pending">まだ回答がありません（答え待機）</p>
          </div>
        </div>

        <!-- 未回答の場合のみ、回答入力欄を表示（管理者用の簡易フォーム） -->
        <form v-if="!qanda.answer" class="answer-form" @submit.prevent="onAnswer">
          <label class="field">
            <span class="field-label">回答を入力する</span>
            <textarea v-model="answerText" rows="4" required></textarea>
          </label>
          <button class="btn-submit small" type="submit" :disabled="submitting">
            {{ submitting ? '送信中...' : '回答する' }}
          </button>
        </form>

        <div class="action-row">
          <RouterLink class="btn-catalog" :to="{ name: 'qanda-list' }">目録</RouterLink>
        </div>
      </div>
    </template>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import { qandaApi, type Qanda } from '@/api/qandaApi'

const route = useRoute()
const router = useRouter()

const isDetail = computed(() => route.name === 'qanda-detail')

const qanda = ref<Qanda | null>(null)
const newQuestion = reactive({ title: '', content: '' })
const answerText = ref('')

const loading = ref(false)
const submitting = ref(false)
const errorMessage = ref('')

async function fetchQanda() {
  if (!isDetail.value) return
  loading.value = true
  errorMessage.value = ''
  try {
    qanda.value = await qandaApi.get(route.params.id as string)
  } catch (e) {
    errorMessage.value = 'Q&Aの取得に失敗しました。'
    console.error(e)
  } finally {
    loading.value = false
  }
}

async function onCreate() {
  submitting.value = true
  errorMessage.value = ''
  try {
    const created = await qandaApi.create({ title: newQuestion.title, content: newQuestion.content })
    newQuestion.title = ''
    newQuestion.content = ''
    // 作成した質問の詳細ページに遷移
    router.push({ name: 'qanda-detail', params: { id: created.id } })
  } catch (e) {
    errorMessage.value = '質問の送信に失敗しました。'
    console.error(e)
  } finally {
    submitting.value = false
  }
}

async function onAnswer() {
  if (!qanda.value) return
  submitting.value = true
  errorMessage.value = ''
  try {
    qanda.value = await qandaApi.answer(qanda.value.id, answerText.value)
    answerText.value = ''
  } catch (e) {
    errorMessage.value = '回答の送信に失敗しました。'
    console.error(e)
  } finally {
    submitting.value = false
  }
}

onMounted(fetchQanda)
</script>

<style scoped>
.qanda-form-page {
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
  padding: 24px 0;
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
  border: 2px solid #333;
  font-family: inherit;
  font-size: 14px;
}

.qa-block {
  margin-bottom: 20px;
}

.qa-heading {
  font-weight: 700;
  margin-bottom: 8px;
}

.qa-box {
  border: 2px solid #333;
  padding: 14px;
  min-height: 100px;
}

.qa-box.rounded {
  border-radius: 24px;
}

.qa-box-label {
  color: #999;
  font-size: 13px;
  margin-bottom: 8px;
}

.qa-text {
  white-space: pre-wrap;
  line-height: 1.7;
}

.qa-text.pending {
  color: #b7791f;
}

.answer-form {
  border-top: 1px dashed #ccc;
  padding-top: 16px;
  margin-bottom: 16px;
}

.action-row {
  display: flex;
  align-items: center;
  justify-content: flex-end;
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

.btn-submit.small {
  padding: 6px 14px;
  font-size: 13px;
}

.btn-submit:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.new-question .action-row {
  justify-content: space-between;
}
</style>
