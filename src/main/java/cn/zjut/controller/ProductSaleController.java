package cn.zjut.controller;

import cn.zjut.entity.ProductSale;
import cn.zjut.service.ProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class ProductSaleController {
    @Autowired
    private ProductSaleService productSaleService;

    @GetMapping
    public List<ProductSale> getAllSales() {
        return productSaleService.getAllSales();
    }
}
