package cn.zjut.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSale {
    private Integer productId;
    private Integer totalSale; // Ensure this matches the database and query
    private Double totalAmount;
}
