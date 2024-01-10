package cn.zjut.controller;
import cn.zjut.common.R;
import cn.zjut.entity.Product;
import cn.zjut.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

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

    /**
     * 产品信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){

        //构造分页构造器对象
        Page<Product> pageInfo = new Page<>(page,pageSize);

        //条件构造器
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null, Product::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Product::getUpdateTime);

        //执行分页查询
        productService.page(pageInfo,queryWrapper);

        // 直接返回分页对象即可，不需要额外操作
        return R.success(pageInfo);
    }

    /**
     * 根据id查询产品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Product> get(@PathVariable Long id){

        Product product = productService.getByIdWithFlavor(id);

        return R.success(product);
    }

    /**
     * 修改产品
     * @param product
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Product product){
        log.info(product.toString());

        productService.updateWithFlavor(product);

        return R.success("修改产品成功");
    }

    /**
     * 根据条件查询对应的产品数据
     * @param product
     * @return
     */

    @GetMapping("/list")
    public R<List<Product>> list(Product product){
        //构造查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件，查询状态为1（正在上线状态）的产品
        queryWrapper.eq(Product::getProductStatus,"1");

        //添加排序条件
        queryWrapper.orderByAsc(Product::getSort).orderByDesc(Product::getUpdateTime);

        List<Product> list = productService.list(queryWrapper);

        // 直接返回列表即可，不需要额外操作
        return R.success(list);
    }
    @DeleteMapping
    public R delete(String ids) {

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
