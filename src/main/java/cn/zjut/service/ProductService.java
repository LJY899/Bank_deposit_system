package cn.zjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.zjut.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product> {


    //根据id查询菜品信息和对应的口味信息
    public Product getByIdWithFlavor(Long productId);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithProduct(List<Long> ids);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(Product product);

    //保存产品信息到产品表
    public void saveWithFlavor(Product product);
}
