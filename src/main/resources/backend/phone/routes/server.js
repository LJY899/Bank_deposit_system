// server.js
const mysql = require('mysql');
const express = require('express');
const mongoose = require('mongoose');
const apiRoutes = require('./routes/api');

const app = express();
const PORT = process.env.PORT || 8080;

// 连接数据库
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Wxd327234859',
    database: 'bank'
});

connection.connect((err) => {
    if (err) {
        console.error('Error connecting to MySQL:', err);
        return;
    }
    console.log('Connected to MySQL database');
});


// 使用 API 路由
app.use('/api', apiRoutes);

// 启动服务器
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
