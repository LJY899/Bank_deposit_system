package cn.zjut.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("bankcard")
@Data
public class BankCard implements Serializable{

    /**
     银行卡
     */

    private static final long serialVersionUID = 1L;

    // 银行卡ID
    @TableId(type= IdType.AUTO)
    private Long cardId;


    // 手机号
    private String phoneNumber;

    // 银行卡号
    private String cardNumber;

    // 支付密码
    private String paymentPassword;

    //余额
    private BigDecimal balance;


}

