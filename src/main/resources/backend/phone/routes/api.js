// routes/api.js
const express = require('express');
const router = express.Router();
const Product = require('../models/product');

// 获取所有产品
router.get('/products', (req, res) => {
    Product.getAllProducts((err, products) => {
        if (err) {
            console.error(err);
            res.status(500).json({ error: 'Internal Server Error' });
            return;
        }
        res.json(products);
    });
});

module.exports = router;
