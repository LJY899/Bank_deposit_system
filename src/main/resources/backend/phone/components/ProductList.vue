<!-- src/components/ProductList.vue -->
<template>
  <div>
    <h1>Product List</h1>
    <ul>
      <li v-for="product in products" :key="product.productId">
        {{ product.name }} - {{ product.tenure }} - {{ product.annualInterestRate }} - {{ product.minDepositAmount }} - {{product.singleLimitAmount}} - {{product.dailyLimitAmount}} - {{product.riskLevel}} - {{product.startDate}} - {{product.maturityDate}} - {{product.interestPayment}} - {{product.productStatus}}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      products: []
    };
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await fetch('/api/products');
        const data = await response.json();
        this.products = data;
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>
/* 样式可以根据需要添加 */
</style>
