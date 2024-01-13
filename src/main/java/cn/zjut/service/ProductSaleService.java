package cn.zjut.service;

import cn.zjut.entity.ProductSale;
import cn.zjut.mapper.ProductSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSaleService {
    @Autowired
    private ProductSaleMapper productSaleMapper;

    public List<ProductSale> getAllSales() {
        return productSaleMapper.selectList(null);
    }
}
