<template>
  <div class="col-md-12" style="margin-left: 5%; margin-top: 5%;">
    <div class="page type-page status-publish hentry">
      <div class="entry-content">
        <div class="woocommerce table-tabs">
          <div>
            <div>
              <form>
                <div class="search-area">
                  <select name="catalog" id="catalog" class="form-control">
                    <option>全て</option>
                    <option>中正</option>
                  </select>

                  <input type="text" class="form-control">

                  <button type="button">
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
                    <tr>
                      <td>
                      </td>
                      <td></td>

                      <td>
                        <button>
                          中正
                        </button>
                        <button>
                          中正解除
                        </button>
                        <button>
                          脱退
                        </button>
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


</script>

<style lang="css">
.search-area {
  display: flex;
  gap: 10px;
}

.search-area select,
.search-area input,
.search-area button {
  height: 56px;
  box-sizing: border-box;
}

.search-area select {
  width: 180px;
  flex: none;
}

.search-area input {
  flex: 1;
}

.search-area button {
  width: 125px;
  margin: 0;
}

.search-area button {
  width: 125px;
  height: 56px;
  margin: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.search-area {
  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 20px;
}

.search-area button,
table button {
  height: 56px;
  padding: 0 35px;
  margin: 0;
}
</style>
