import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    memberNo: 0,
    memberId: '',
    role: ''
  })
})
