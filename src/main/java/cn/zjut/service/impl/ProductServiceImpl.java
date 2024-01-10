package cn.zjut.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zjut.entity.Product;
import cn.zjut.mapper.ProductMapper;
import cn.zjut.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    /**
     * 根据id查询产品信息
     * @param id
     * @return
     */
    public Product getByIdWithFlavor(Long id) {
        //查询产品基本信息
        Product product = this.getById(id);
        return product;
    }

    @Override
    @Transactional
    public void updateWithFlavor(Product product) {
        //更新product表基本信息
        this.updateById(product);


    }


}
