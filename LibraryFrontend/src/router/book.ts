import BookDetail from "@/views/book/bookDetail.vue";
import BookList from "@/views/book/bookList.vue";

export default [

  {
    path: '/booklist',
    component: BookList,
    
    meta: {
      showMainHeader: true,
      sidebarType: 'book',
      showMainFooter: true,
    },
  },

{
    path: '/booklist/search/:Search',
    component: BookList,
    
    meta: {
      showMainHeader: true,
      sidebarType: 'book',
      showMainFooter: true,
    },
  },

    {
    path: '/booklist/category/:category',
    component: BookList,
    
    meta: {
      showMainHeader: true,
      sidebarType: 'book',
      showMainFooter: true,
    },
  },

  {
    path: '/bookdetail/:bookNumberInfo',
    component: BookDetail,
    
    meta: {
      showMainHeader: true,
      showMainFooter: true,
    },
  },
]