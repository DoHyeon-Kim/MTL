import BookDetail from "@/views/book/bookDetail.vue";
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
      showbookSidebar:true,
    },
  },

  /*{
    path: '/booklist/:Search',
    component: BookList,
    
    meta: {
      showMainHeader: true,
      showMainSidebar: false,
      showMainFooter: true,
      showbookSidebar:true,
    },
  },*/

    {
    path: '/booklist/:category',
    component: BookList,
    
    meta: {
      showMainHeader: true,
      showMainSidebar: false,
      showMainFooter: true,
      showbookSidebar:true,
    },
  },

  {
    path: '/bookdetail/:bookNumberInfo',
    component: BookDetail,
    
    meta: {
      showMainHeader: true,
      showMainSidebar: false,
      showMainFooter: true,
      showbookSidebar:false,
    },
  },
]