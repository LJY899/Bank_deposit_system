package cn.zjut.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSale {
    private Integer productId;
    @TableField("name")
    private String productName;
    private Integer totalSale; // Ensure this matches the database and query
    private Double totalAmount;
}
