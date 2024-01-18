package cn.zjut.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zjut.entity.Product;
import cn.zjut.mapper.ProductMapper;
import cn.zjut.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    /**
     * 新增产品
     * @param product
     */
    @Transactional
    public void saveWithFlavor(Product product) {
        //保存产品的基本信息到产品表
        this.save(product);

    }

    /**
     * 根据id查询产品信息
     * @param productId
     * @return
     */
    public Product getByIdWithFlavor(Long productId) {
        //查询菜品基本信息，从dish表查询
        Product product = this.getById(productId);
        return product;
    }

    /**
     * 根据id删除产品
     * @param ids
     */
    @Transactional
    public void removeWithProduct(List<Long> ids) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Product::getProductId,ids);
        this.removeByIds(ids);
    }


    @Override
    @Transactional
    public void updateWithFlavor(Product product) {
        //更新product表基本信息
        this.updateById(product);


    }
}
