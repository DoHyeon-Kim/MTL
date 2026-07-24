<template>
  <div id="content" class="site-content">
    <div id="primary" class="content-area">
      <main id="main" class="site-main">
        <div class="signin-main">
          <div class="container">
            <div class="woocommerce">
              <div class="woocommerce-login">
                <div class="company-info signin-register">
                  <div class="col-md-5 col-md-offset-1 border-dark-left">
                    <div class="row">
                      <div class="col-md-12">
                        <div class="company-detail bg-dark margin-left">
                          <div class="signin-head">
                            <h2>Sign in</h2>
                            <span class="underline left"></span>
                          </div>

                          <form class="login" @submit.prevent="login">

                            <p class="form-row form-row-first input-required">
                              <input
                                v-model="memberId"
                                type="text"
                                id="username"
                                name="username"
                                class="input-text"
                                placeholder="ID"
                              >
                            </p>

                            <p class="form-row form-row-last input-required">

                              <input
                                v-model="memberPw"
                                type="password"
                                id="password"
                                name="password"
                                class="input-text"
                                placeholder="PW"
                              >
                            </p>

                            <p v-if="message">
                              {{ message }}
                            </p>

                            <div class="clear"></div>

                            <div class="password-form-row">
                              <p class="form-row input-checkbox">
                                <input
                                  type="checkbox"
                                  value="forever"
                                  id="rememberme"
                                  name="rememberme"
                                >
                              </p>

                            </div>

                            <input
                              type="submit"
                              value="Login"
                              name="login"
                              class="button btn btn-default"
                            >

                            <button style="float:right; height: 38px;" @click="sign">登録</button>


                          </form>

                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const auth = useAuthStore()

const memberId = ref('')
const memberPw = ref('')
const message = ref('')

async function login() {
  try {
     const res = await axios.post(
      'http://localhost:8099/login',
      {
        memberId: memberId.value,
        memberPw: memberPw.value
      },
      {
        withCredentials: true
      }
    )

    auth.memberNo = res.data.memberNo
    auth.memberId = res.data.memberId
    auth.role = res.data.role

    message.value = ''
    alert('login完了')

    router.push('/bookList')

  } catch (error) {
    console.error(error)

    message.value = 'IDまたはpwが異なります。'
  }
}


function sign() {
    router.push("/signup")
}
</script>
