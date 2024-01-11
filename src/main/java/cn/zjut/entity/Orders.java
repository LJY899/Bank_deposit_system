package cn.zjut.entity;

import lombok.Data;//实现setter和getter和tostring方法
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 */
// 使用 @TableField 注解指定数据库列名
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    //订单号
    private String number;

    //产品ID
    private Long productId;

    //下单用户手机号
    private String userPhone;

    //存款
    private BigDecimal amount;

    //下单时间
    private LocalDateTime time;

    //订单状态 1未到期 2已到期
    private Integer status;


}
