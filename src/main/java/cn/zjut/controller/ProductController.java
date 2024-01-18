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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page")
    public R<Page<Product>> page(int page, int pageSize, String name) {
        Page<Product> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Product::getName, name);//模糊查询
        queryWrapper.orderByDesc(Product::getUpdateTime);
        productService.page(pageInfo, queryWrapper);

        // 直接返回分页对象
        return R.success(pageInfo);
    }
    /**
     * 新增产品
     * @param product 包含产品信息的请求体
     * @return 新增成功的产品信息
     */
    @PostMapping
    public R<String> saveProduct(@RequestBody Product product) {
        log.info("新增产品，产品信息：{}", product.toString());

        boolean result = productService.save(product);

        if (result) {
            return R.success("新增产品成功");
        } else {
            return R.error("新增产品失败");
        }
    }
    @GetMapping("/{productId}")
    public R<Product> get(@PathVariable Long productId ) {
        Product product = productService.getByIdWithFlavor(productId);
        return R.success(product);
    }

    @PutMapping
    public R<String> update(@RequestBody Product product) {
        log.info(product.toString());
        productService.updateWithFlavor(product);
        return R.success("修改产品成功");
    }

    @GetMapping("/list")
    public R<List<Product>> list(Product product) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductStatus, "上线");
        queryWrapper.orderByAsc(Product::getSort).orderByDesc(Product::getUpdateTime);
        List<Product> list = productService.list(queryWrapper);

        // 直接返回列表
        return R.success(list);
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:{}",ids);
        productService.removeWithProduct(ids);
        return R.success("删除成功");
    }

    @PostMapping("/productStatus/{productStatus}")
    public R changeStatus(@PathVariable String productStatus, String ids) {
        if (ids != null && !ids.isEmpty()) {
            String[] idList = ids.split(",");
            for (String productId : idList) {
                // 添加空值检查，避免删除时出现空指针异常
                if (productId != null && !productId.trim().isEmpty()) {
                    Product product = new Product();
                    product.setProductId(Long.parseLong(productId));

                    // 先通过 ID 查询数据库中的记录
                    Product existingProduct = productService.getById(Long.parseLong(productId));
                    if (existingProduct != null) {
                        // 将数据库中的记录属性复制到新对象中
                        BeanUtils.copyProperties(existingProduct, product);
                        // 设置新的状态
                        product.setProductStatus(productStatus);
                        // 更新数据库记录
                        productService.updateById(product);
                    }
                }
            }
        }
        return R.success("更新状态成功");
    }
}
