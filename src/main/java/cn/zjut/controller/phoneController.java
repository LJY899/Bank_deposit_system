package cn.zjut.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.zjut.common.R;
import cn.zjut.entity.Product;
import cn.zjut.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class phoneController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public R<Product> get(@PathVariable Long productId ) {
        Product product = productService.getByIdWithFlavor(productId);
        return R.success(product);
    }
}

