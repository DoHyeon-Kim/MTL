<template>
  <div class="signup-container">
    <form class="login" :class="{ 'guest-form': !isAdmin }">

      <!--権限を確認し権限情報を見えるようにしあｍす。-->
      <div v-if="isAdmin" class="form-row">
        <label style="margin-top: 30px;">権限</label>
        <select v-model="memberRole" style="margin-top: 40px;">
          <option value="ROLE_USER">一般会員</option>
          <option value="ROLE_SUBADMIN">管理者</option>
        </select>
      </div>

      <!--ID-->
      <div class="form-row">
        <label>ID</label>
        <div class="input-box">
          <input type="text" v-model="memberId" placeholder="IDを入力してください (6文字以上10文字以内)">
          <span v-if="idMessage" class="error">
            {{ idMessage }}
          </span>
        </div>
      </div>

      <!--pass-->
      <div class="form-row">
        <label>パスワード</label>
        <div class="input-box">
          <input type="password" v-model="memberPassword" placeholder="パスワードを入力してください (6文字以上20文字以内)">
          <span v-if="passwordMessage" class="error">
            {{ passwordMessage }}
          </span>
        </div>
      </div>

      <!--pass2-->
      <div class="form-row">
        <label>パスワード確認</label>
        <div class="input-box">
          <input type="password" v-model="memberPasswordConfirm" placeholder="もう一度パスワードを入力してください">
          <span v-if="passwordConfirmMessage" class="error">
            {{ passwordConfirmMessage }}
          </span>
        </div>
      </div>

      <!--名前ひらから-->
      <div class="form-row">
        <label>ひらがな</label>
        <div class="input-box">
          <input type="text" v-model="memberHiragana" placeholder="例: たろう">
          <span v-if="hiraganaMessage" class="error">
            {{ hiraganaMessage }}
          </span>
        </div>
      </div>

      <!--名前カタカナ-->
      <div class="form-row">
        <label>カタカナ</label>
        <div class="input-box">
          <input type="text" v-model="memberKatakana" placeholder="例: タロウ">
          <span v-if="katakanaMessage" class="error">
            {{ katakanaMessage }}
          </span>
        </div>
      </div>

      <!--電話番号-->
      <div class="form-row">
        <label>携帯電話</label>
        <div class="input-box">
          <input type="text" v-model="memberPhone" placeholder="例: 09012345678">
          <span v-if="phoneMessage" class="error">
            {{ phoneMessage }}
          </span>
        </div>
      </div>

      <!--メール-->
      <div class="form-row">
        <label>メールアドレス</label>

        <div class="input-box">
          <input type="text" v-model="memberEmail" placeholder="例: example@example.com">

          <span v-if="emailMessage" class="error">
            {{ emailMessage }}
          </span>
        </div>
      </div>

      <!--郵便番号-->
      <div class="form-row">
        <label>郵便番号</label>
        <input type="text" v-model="zipCode" maxlength="7" placeholder="例: 1234567">
      </div>

      <div class="form-row">
        <label>住所</label>
        <input type="text" v-model="address" placeholder="例: 東京都港区1-1">
      </div>

      <!--詳細住所-->
      <div class="form-row">
        <label>詳細住所</label>
        <input type="text" v-model="memberDetailAddress" placeholder="例: マンション名・部屋番号">
      </div>

      <div class="button-group" style="margin-bottom: 30px;">
        <input @click="insertMember" value="登録" class="button btn btn-default">
        <input @click="returnpage" value="キャンセル" class="button btn btn-default">
      </div>
    </form>
  </div>
</template>


<script setup lang="ts">
import { ref, watch, computed } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()

// 郵便番号
const zipCode = ref('')
const address = ref('')
// 条件確認
const memberId = ref('')
const memberPassword = ref('')
const memberPasswordConfirm = ref('')
const memberHiragana = ref('')
const memberKatakana = ref('')
const memberPhone = ref('')
const memberEmail = ref('')
const memberDetailAddress = ref('')
const memberRole = ref('ROLE_USER')

//login test
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const loginRole = auth.role

//権限確認
const isAdmin = computed(() => {
  return (
    loginRole === 'ROLE_ADMIN'
  )
})



//test


//登録
const insertMember = async () => {
  const role = isAdmin.value
    ? memberRole.value
    : 'ROLE_USER'
  // 必須項目の確認
  if (
    memberId.value === '' ||
    memberPassword.value === '' ||
    memberPasswordConfirm.value === '' ||
    memberHiragana.value === '' ||
    memberKatakana.value === '' ||
    memberPhone.value === '' ||
    memberEmail.value === '' ||
    zipCode.value === '' ||
    address.value === '' ||
    memberEmail.value === ''
  ) {
    alert('必須項目を入力してください。')
    return
  }

  // 既存 computed 検査結果の確認
  if (
    idMessage.value !== '' ||
    passwordMessage.value !== '' ||
    passwordConfirmMessage.value !== '' ||
    hiraganaMessage.value !== '' ||
    katakanaMessage.value !== '' ||
    phoneMessage.value !== '' ||
    emailMessage.value !== ''
  ) {
    alert('入力内容を確認してください。')
    return
  }

  //登録
  try {
    const response = await axios.post(
      'http://localhost:8099/sign',
      {
        role,
        memberId: memberId.value,
        memberPw: memberPassword.value,
        nameH: memberHiragana.value,
        nameK: memberKatakana.value,
        phone: memberPhone.value,
        email: memberEmail.value,
        addNumber: zipCode.value,
        adds: address.value,
        infoAdd: memberDetailAddress.value,
        memberStatus: 'ACTIVE',
        bookCount: '3'
      }
    )
    //権限によって処理を変える
    if ("ROLE_ADMIN" == loginRole) {
      if (response.data === 1) {
        alert('会員登録が完了しました。')
        router.push('/signup')

      } else if (response.data === 0) {
        alert('情報、URL 操作されました。')
      } else if (response.data === 2) {
        alert('存在するIDです。')
      } else if (response.data === 3) {
        alert('存在するメールアドレスです。')
      } else {
        alert('会員登録に失敗しました。')
      }
    }else if ("ROLE_SUBADMIN" == loginRole) {
      if (response.data === 1) {
        alert('会員登録が完了しました。')
        router.push('/signup')

      } else if (response.data === 0) {
        alert('情報、URL 操作されました。')
      } else if (response.data === 2) {
        alert('存在するIDです。')
      } else if (response.data === 3) {
        alert('存在するメールアドレスです。')
      } else {
        alert('会員登録に失敗しました。')
      }
    }else{
      if (response.data === 1) {
        alert('会員登録が完了しました。')
        router.push('/home')

      } else if (response.data === 0) {
        alert('情報、URL 操作されました。')
      } else if (response.data === 2) {
        alert('存在するIDです。')
      } else if (response.data === 3) {
        alert('存在するメールアドレスです。')
      } else {
        alert('会員登録に失敗しました。')
      }
    }

  } catch (error) {
    console.error('会員登録エラー:', error)
    alert('会員登録中にエラーが発生しました。')
  }
}

function returnpage() {
  router.push('/home')
}

// 郵便番号
watch(zipCode, (newValue) => {
  zipCode.value = newValue.replace(/\D/g, '')

  if (zipCode.value.length === 7) {
    const url = `https://zipcloud.ibsnet.co.jp/api/search?zipcode=${zipCode.value}`

    fetch(url)
      .then(response => response.json())
      .then(data => {
        if (data.results && data.results.length > 0) {
          const resultAddress = data.results[0]
          const fullAddress =
            `${resultAddress.address1}${resultAddress.address2}${resultAddress.address3}`
          address.value = fullAddress
        } else {
          alert('住所が見つかりませんでした。')
        }
      })
      .catch(error => {
        console.error('住所検索中にエラーが発生しました:', error)
      })
  }
})

// 条件確認
//ID
const idMessage = computed(() => {
  if (memberId.value === '') {
    return ''
  }
  if (!/^[a-zA-Z0-9]{6,10}$/.test(memberId.value)) {
    return 'IDは英数字6～10文字で入力してください。英数字のみ入力可能です.'
  }
  return ''
})
//pass
const passwordMessage = computed(() => {
  const password =
    /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{6,20}$/
  if (memberPassword.value === '') {
    return ''
  }
  if (!password.test(memberPassword.value)) {
    return 'パスワードは英字・特殊文字を含む6～20文字で入力してください。'
  }
  return ''
})
//pass2
const passwordConfirmMessage = computed(() => {
  if (memberPassword.value !== memberPasswordConfirm.value) {
    return 'パスワードが一致しません。'
  }
  return ''
})

//名前
const hiraganaMessage = computed(() => {
  const hiragana = /^[ぁ-ゖー]+$/
  if (memberHiragana.value.length === 0) {
    return ''
  }

  if (!hiragana.test(memberHiragana.value)) {
    return 'ひらがなのみ入力してください。'
  }

  return ''
})


const katakanaMessage = computed(() => {
  const katakana = /^[ァ-ヶー]+$/
  if (memberKatakana.value.length === 0) {
    return ''
  }

  if (!katakana.test(memberKatakana.value)) {
    return 'カタカナのみ入力してください。'
  }

  return ''
})

//phon
const phoneMessage = computed(() => {
  const phoneRegex = /^0\d{9,10}$/
  if (memberPhone.value === '') {
    return ''
  }
  if (!phoneRegex.test(memberPhone.value)) {
    return '携帯電話番号は0から始まる10桁または11桁の数字で入力してください。'
  }
  return ''
})


//email
const emailMessage = computed(() => {
  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/

  if (memberEmail.value === '') {
    return ''
  }

  if (!emailRegex.test(memberEmail.value)) {
    return 'メールアドレスの形式が正しくありません。'
  }

  return ''
})
</script>

<style scoped>
.guest-form {
  padding-top: 80px;
}

.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.login {
  width: 650px;
}

.form-row {
  display: flex;
  align-items: flex-start;
  margin-bottom: 18px;
}

.form-row label {
  width: 170px;
  font-weight: bold;
  line-height: 40px;
}

.form-row>input,
.input-box {
  flex: 1;
}

.form-row>input,
.input-box input {
  width: 100%;
  height: 40px;
  padding: 0 10px;
  box-sizing: border-box;
}

.input-box {
  display: flex;
  flex-direction: column;
}

.error {
  color: #ff0000;
  font-size: 13px;
  margin-top: 5px;
}

.button-group {
  margin-top: 30px;
  text-align: center;
}

.button-group input {
  margin: 0 10px;
  min-width: 120px;
}
</style>
