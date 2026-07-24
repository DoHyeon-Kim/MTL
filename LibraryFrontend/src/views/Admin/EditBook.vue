<template>
  <div class="book-create-layout">
    <div class="image-panel">
      <div class="image-preview">
        <img v-if="previewImage" :src="previewImage" alt="プレビュー" />
        <div v-else class="placeholder">
          <span>Image</span>
          <small>image.</small>
        </div>
      </div>

      <label class="image-upload">
        イメージ選択
        <input type="file" accept="image/*" @change="handleImageSelect" />
      </label>
    </div>

    <div class="book-form">
      <h2>図書編集</h2>

      <form @submit.prevent="submitForm">
        <div class="field">
          <label>タイトル *</label>
          <input v-model="form.bookTitle" type="text" required />
        </div>

        <div class="field">
          <label>著者 *</label>
          <input v-model="form.writer" type="text" required />
        </div>

        <div class="field">
          <label>出版社</label>
          <input v-model="form.publisher" type="text" />
        </div>

        <div class="field">
          <label>出版日</label>
          <input v-model="form.publicationDate" type="date" />
        </div>

        <div class="field">
          <label>カテゴリー</label>
          <input v-model="form.category" type="text" />
        </div>

        <div class="field">
          <label>説明</label>
          <textarea v-model="form.bookContent" rows="4"></textarea>
        </div>

        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

        <div class="actions">
          <button type="button" @click="cancel">取り消し</button>
          <button type="submit" class="btn-primary">保存</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";

const router = useRouter();
const route = useRoute();
const bookId = route.params.id as string;
const errorMessage = ref("");
const previewImage = ref<string | null>(null);
const rawFile = ref<File | null>(null);

const cancel = () => {
  router.push("/admin/booklist");
};

const form = reactive({
  bookImg: "",
  bookTitle: "",
  writer: "",
  publisher: "",
  category: "",
  bookContent: "",
  publicationDate: "",
  bookNumberInfo: 0,
  registrationDate: "",
});

const handleImageSelect = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const file = target.files?.[0];

  if (!file) return;

  rawFile.value = file;

  const reader = new FileReader();
  reader.onload = () => {
    previewImage.value = reader.result as string;
  };
  reader.readAsDataURL(file);
};

const loadBook = async () => {
  try {
    const res = await axios.get(`http://localhost:8099/bookmanagedetail/${bookId}`);
    const data = res.data;

    form.bookImg = data.bookImg || "";
    form.bookTitle = data.bookTitle || "";
    form.writer = data.writer || "";
    form.publisher = data.publisher || "";
    form.category = data.category || "";
    form.bookContent = data.bookContent || "";
    form.publicationDate = data.publicationDate || "";
    form.bookNumberInfo = data.bookNumberInfo || 0;
    form.registrationDate = data.registrationDate || "";

    if (data.bookImg) {
      previewImage.value = data.bookImg;
    }
  } catch (err) {
    console.error(err);
    errorMessage.value = "データの読み込みに失敗しました";
  }
};

const submitForm = async () => {
  const formData = new FormData();
  const bookData = JSON.stringify({
    bookTitle: form.bookTitle,
    writer: form.writer,
    publisher: form.publisher,
    category: form.category,
    bookContent: form.bookContent,
    publicationDate: form.publicationDate,
    bookNumberInfo: form.bookNumberInfo,
    bookImg: form.bookImg,
  });
  formData.append("book", new Blob([bookData], { type: "application/json" }));

  if (rawFile.value) {
    formData.append("image", rawFile.value);
  }

  try {
    await axios.put(`http://localhost:8099/bookupdate/${bookId}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    router.replace(`/admin/bookmanagelist/detail/${bookId}`);
  } catch (err) {
    console.error(err);
    errorMessage.value = "更新に失敗しました";
  }
};

onMounted(() => {
  if (bookId) loadBook();
});
</script>

<style scoped>
.book-create-layout {
  display: flex;
  gap: 24px;
  align-items: flex-start;
  max-width: 960px;
  margin: 0 auto;
  padding: 24px;
}
.image-panel {
  flex: 0 0 280px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.image-preview {
  min-height: 280px;
  border: 1px dashed #cbd5e1;
  border-radius: 12px;
  background: #f8fafc;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.image-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.placeholder {
  display: flex;
  flex-direction: column;
  gap: 6px;
  text-align: center;
  color: #64748b;
  padding: 20px;
}
.image-upload {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  padding: 10px 14px;
  border: 1px solid #2563eb;
  color: #2563eb;
  border-radius: 6px;
  cursor: pointer;
}
.image-upload input {
  display: none;
}
.book-form {
  flex: 1;
  max-width: 480px;
  margin: 0;
}
.field {
  margin-bottom: 14px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}
.field label {
  font-weight: 600;
  font-size: 14px;
}
.field input,
.field textarea {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.error {
  color: #dc2626;
  font-size: 14px;
}
.actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
  margin-top: 20px;
}
.btn-primary {
  background: #2563eb;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

@media (max-width: 768px) {
  .book-create-layout {
    flex-direction: column;
  }

  .image-panel {
    flex-basis: auto;
    width: 100%;
  }
}
</style>
