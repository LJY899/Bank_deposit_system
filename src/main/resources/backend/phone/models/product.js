// models/product.js
const mysql = require('mysql');

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Wxd327234859',
    database: 'bank'
});

const Product = {
    getAllProducts: (callback) => {
        const query = 'SELECT * FROM product';
        connection.query(query, (err, results) => {
            if (err) {
                callback(err, null);
                return;
            }
            callback(null, results);
        });
    }
};

module.exports = Product;
