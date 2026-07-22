<template>
  <div class="widget widget_recent_releases">
    <h4 class="widget-title">New Releases</h4>
    <ul>
      <li>
        <a><RouterLink to="/admin/bookList">図書リスト</RouterLink></a>
      </li>
      <li>
        <a><RouterLink to="/admin/checkoutstatus">貸出情報</RouterLink></a>
      </li>
      <li>
        <a><RouterLink to="/admin/memberstatus">会員貸出情報</RouterLink></a>
      </li>
      <li><a href="#">Magazines</a></li>
      <li><a href="#">Audio</a></li>
      <li><a href="#">eAudio</a></li>
    </ul>
    <div class="clearfix"></div>
  </div>
</template>
