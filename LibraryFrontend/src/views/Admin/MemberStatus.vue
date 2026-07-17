<template>
  <div class="member-status-container">
    <div class="list-header">
      <h1>Member Loan Status</h1>
    </div>

    <div class="search-section">
      <input
        v-model="searchMemberId"
        type="text"
        class="search-input"
        placeholder="会員IDで検索..."
        @keyup.enter="searchMember"
      />
      <button class="btn-search" @click="searchMember">検索</button>
    </div>

    <div v-if="searchPerformed && memberList.length === 0" class="no-results">
      検索結果がありません
    </div>

    <table v-if="searchPerformed && memberList.length > 0" class="members-table">
      <thead>
        <tr>
          <th>会員ID</th>
          <th>会員名</th>
          <th>貸出回数</th>
          <th>延滞回数</th>
          <th>アクション</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="member in memberList" :key="member.memberId">
          <td>{{ member.memberId }}</td>
          <td>{{ member.nameH }}</td>
          <td>{{ member.loanCount }}</td>
          <td>{{ member.entiCount }}</td>
          <td class="action-buttons">
            <button class="btn-history" @click="viewHistory(member.memberId)">履歴確認</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import "./MemberStatus.css";

const router = useRouter();
const searchMemberId = ref("");
const searchPerformed = ref(false);
const memberList = ref<
  {
    memberId: string;
    nameH: string;
    loanCount: number;
    entiCount: number;
  }[]
>([]);

const searchMember = async () => {
  if (!searchMemberId.value.trim()) {
    alert("会員IDを入力してください");
    return;
  }

  try {
    const response = await axios.get(
      `http://localhost:8099/userlist?memberId=${searchMemberId.value}`,
    );
    memberList.value = [response.data];
    searchPerformed.value = true;
    console.log(memberList.value);
  } catch (error) {
    memberList.value = [];
    searchPerformed.value = true;
    console.error(error);
  }
};

const viewHistory = (memberId: string) => {
  router.push(`/admin/memberstatus/history/${memberId}`);
};
</script>
