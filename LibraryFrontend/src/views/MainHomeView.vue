<template>
  <div class="home" style="border: 1px solid red;">
    <h1>メイン仮面です。
    </h1>
    <div class="quick-links">
      <router-link class="quick-link" to="/announcement">おしらせ一覧へ</router-link>
      <router-link class="quick-link" to="/qanda">Q&amp;A一覧へ</router-link>
    </div>
  </div>
</template>

<style scoped>
.home {
  min-height: 500px;
}

.quick-links {
  display: flex;
  gap: 12px;
  padding: 16px;
}

.quick-link {
  padding: 8px 16px;
  border: 1px solid #333;
  border-radius: 4px;
  text-decoration: none;
  color: #333;
}

.quick-link:hover {
  background: #282828;
  color: #fff;
}
</style>
