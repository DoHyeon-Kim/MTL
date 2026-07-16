<script setup lang="ts">
import { ref } from "vue";

const loanAvailable=ref(true);
const booktitle = ref("");

</script>

<template>
  <table>
    <tr>
      <button> {{ loanAvailable ? '카트에 추가' : '알람 예약' }}</button>
    </tr>
    <tr>
      <img  :src="`/books/${booktitle}.jpg`" />
        {{ booktitle }}
    </tr>
  </table>
</template>

<style></style>
