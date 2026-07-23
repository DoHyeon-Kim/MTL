<template>
  <div class="col-md-12" style="margin-left: 5%; margin-top: 5%;">
    <div class="page type-page status-publish hentry">
      <div class="entry-content">
        <div class="woocommerce table-tabs">
          <div>
            <div>
              <form>
                <div class="search-area">
                  <select name="catalog" id="catalog" class="form-control" v-model="memberStatusFind">
                    <option value="">全て</option>
                    <option value="STOP">停止中</option>
                  </select>
                  <input type="text" v-model="memberIdFind" class="form-control" maxlength="10"
                    placeholder="IDを入力してください。">
                  <button type="button" @click="selectMemberList">
                    検索
                  </button>
                </div>
                <table class="table table-bordered shop_table cart">
                  <colgroup>
                    <col style="width: 40%">
                    <col style="width: 15%">
                    <col style="width: 15%">
                  </colgroup>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>現在状況</th>
                      <th>処理</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-if="searched && memberList.length === 0">
                      <td colspan="3" class="text-center">
                        検索結果がありません。
                      </td>
                    </tr>
                    <tr v-for="member in AdminMamberList" :key="member.memberNo">
                      <td><div><p>{{ member.memberId }}</p>
                         <p>備考：{{ member.etc }}</p></div>
                      </td>
                      <td >
                        <div v-if="member.memberStatus == 'STOP'">停止会員</div>
                        <div v-else>通常利用会員</div>
                      </td>
                      <td class="action-cell">
                        <div class="action-buttons">
                          <button v-if="member.memberStatus == 'STOP'" class="action-btn release-btn" @click="releaseMember(member.memberNo)">
                            停止解除
                          </button>
                          <button class="action-btn delete-btn" @click="adminDeleteMember(member.memberNo)">
                            退会
                          </button>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue'
const memberList = ref([])
const searched = ref(false)
const memberIdFind = ref('')
const memberStatusFind = ref('')

const AdminMamberList = ref<adminMemberList[]>([])

interface adminMemberList {
  memberNo: number
  memberId: string
  memberStatus: string,
  etc : string
}


async function selectMemberList() {
  const res = await axios.get('http://localhost:8099/admin/adminMemberList',
    {
      params: {
        memberId: memberIdFind.value,
        memberStatus: memberStatusFind.value

      },
      withCredentials: true
    }
  )
  AdminMamberList.value = res.data
}

async function adminDeleteMember(memberNo: number) {
    await axios.delete(
        'http://localhost:8099/admin/adminDeleteMember',
        {
            params: { memberNo },
            withCredentials: true
        }
    )
}

async function releaseMember(memberNo:number){
    await axios.put(
      'http://localhost:8099/admin/releaseMember',{
        memberNo:memberNo,
        memberStatus:'ACTIVE',
        etc : ''
      }

    )
}

</script>

<style lang="css">
.search-area {
  display: flex;
  align-items: center;
  gap: 12px;
  margin: 30px 0;
}

.search-area select {
  width: 180px;
  height: 52px;
  flex: 0 0 180px;
  margin: 0;
}

.search-area input {
  flex: 1;
  height: 52px;
  margin: 0;
}

.search-area>button {
  width: 120px;
  height: 52px;
  margin: 0;
  padding: 0;
  border: none;
  background: #ff7138;
  color: white;
  cursor: pointer;
}

.search-area>button:hover {
  opacity: 0.85;
}

.action-cell {
  vertical-align: middle;
  text-align: center;
}

.action-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 12px;
}

.action-btn {
  min-width: 110px;
  height: 46px;
  margin: 0;
  padding: 0 20px;
  border: none;
  cursor: pointer;
}
</style>
