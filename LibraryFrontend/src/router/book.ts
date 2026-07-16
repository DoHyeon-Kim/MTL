import BookList from "@/views/book/bookList.vue";

export default [

  /*{
    path: '/signup',
    component: () => import('@/views/member/SignUpView.vue'),
    meta: {
      showMainHeader: true,
      showMainSidebar: false,
      showMainFooter: true,
    },
  },*/
    {
    path: '/booklist',
    component: BookList,
    
    meta: {
      showMainHeader: true,
      showMainSidebar: false,
      showMainFooter: true,
    },
  },
]