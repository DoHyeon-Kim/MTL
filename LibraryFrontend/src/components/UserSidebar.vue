<template>
  <aside id="secondary" class="sidebar widget-area" data-accordion-group>
    <div class="widget widget_recent_releases" style="margin-top: 70px">
      <h4 class="widget-title">MYpage</h4>
      <ul>
        <li><a :href="`/userMypage/${auth.memberNo}`">現在貸出履歴</a></li>
        <li><a href="/borrowHistory">貸出履歴</a></li>
        <li><a href="/userMypagePut">プロフィール修正</a></li>
        <li><a href="/DeleteMember">退会</a></li>
      </ul>
      <div class="clearfix"></div>
    </div>
  </aside>
</template>
<script setup lang="ts">
import { useAuthStore } from "@/stores/auth";
const auth = useAuthStore();
</script>
