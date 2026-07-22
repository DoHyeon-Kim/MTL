<template>
  <div style="margin-top: 7%; margin-left: 7%;">
    <h1 style="margin-top: 30px;">会員退会</h1>
    <div style="margin-left: 5%;">
      <p style="margin-top: 20px;">
        退会すると、登録情報や利用履歴を確認できなくなります。<br>
        この操作は取り消せません。
      </p>
      <input v-model="password" type="password" placeholder="パスワードを入力してください" style="width: 50%; height: 40px;"><br>

      <label>
        <input v-model="agreement" type="checkbox" style="margin-top: 20px;">
        退会に関する注意事項を確認しました。
      </label><br>
    </div>
    <button :disabled="!agreement" @click="deleteMember" style="margin : 40px 0 40px 40%">
      退会する
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'


const router = useRouter()
const password = ref('')
const agreement = ref(false)



async function deleteMember() {

  if (password.value === '') {
    alert('パスワードを入力してください。')
    return
  }

  const confirmed = confirm(
    '本当に退会しますか？\nこの操作は取り消せません。'
  )

  if (!confirmed) {
    return
  }

  try {
    const res = await axios.delete(
      'http://localhost:8099/deleteMember',
      {
        data: {
          memberPw: password.value,
        },
        withCredentials: true
      }
    )
    if (res.data === 1) {
      alert('退会が完了しました。')
      router.push('/home')
    } else {
      alert('パスワードが正しくありません。')
    }
  } catch (error) {
    alert(error)
  }
}
</script>
