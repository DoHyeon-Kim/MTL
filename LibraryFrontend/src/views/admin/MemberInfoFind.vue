<template>
  <div class="signup-container">

    <h1>会員情報検索</h1>

    <div class="form-row">

      <label>ID</label>

      <div class="input-box">
        <input type="text" v-model="idFind" placeholder="IDを入力してください（6～10文字）">
        <button @click="idCheck">検索</button>

      </div>

    </div>

  </div>
</template>


<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const idFind = ref('')
const router = useRouter()

const idCheck = () => {
    router.push({
      name: '/putMember',
      params: {
        memberId: idFind.value
      }
    })
}

</script>

<style scoped>
.signup-container {
  width: 700px;
  margin: 50px auto;
  padding: 35px 40px;
  background: #fff;
  border-radius: 12px;

}

.signup-container h1 {
  margin-bottom: 35px;
  text-align: center;
  font-size: 34px;
  color: #333;
}

.form-row {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.form-row label {
  font-size: 18px;
  font-weight: bold;
  color: #444;
}

.input-box {
  display: flex;
  gap: 15px;
}

.input-box input {
  flex: 1;
  height: 48px;
  padding: 0 15px;
  font-size: 16px;

  border: 1px solid #dcdcdc;
  border-radius: 8px;

  transition: .2s;
}

.input-box input:focus {
  outline: none;
  border-color: #ff6b35;
  box-shadow: 0 0 5px rgba(255, 107, 53, .25);
}

.input-box button {
  width: 120px;
  border: none;
  border-radius: 8px;

  background: #ff6b35;
  color: white;

  font-size: 17px;
  font-weight: bold;

  cursor: pointer;

  transition: .2s;
}

.input-box button:hover {
  background: #e75b2c;
}
</style>
