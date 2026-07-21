<template>
  <div>
    <button type="button" @click="login">
      임시 로그인
    </button>

    <button type="button" @click="checkSession">
      세션 확인
    </button>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios'

async function login() {
  const response = await axios.post(
    'http://localhost:8099/login',
    {},
    {
      withCredentials: true,
    },
  )

  console.log('로그인 결과:', response.data)
}

async function checkSession() {
  const response = await axios.get(
    'http://localhost:8099/session',
    {
      withCredentials: true,
    },
  )

  console.log('세션 정보:', response.data)
}
</script>
