// src/App.vue
<template>
  <div id="app">
    <header>
      <h1>在售产品</h1>
    </header>
    <product-list></product-list>
  </div>
</template>

<script>
import ProductList from '@/components/ProductList.vue';

export default {
  components: {
    ProductList
  }
};
</script>

<style>
/* 样式可以根据需要添加 */
</style>
