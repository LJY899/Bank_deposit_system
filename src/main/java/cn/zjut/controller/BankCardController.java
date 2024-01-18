package cn.zjut.controller;
import cn.zjut.common.R;
import cn.zjut.entity.BankCard;
import cn.zjut.entity.User;
import cn.zjut.service.BankCardService;
import cn.zjut.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/bankcard")
@Slf4j//简化日志记录
public class BankCardController {
    @Autowired
    private BankCardService bankcardService;

    @GetMapping("/byPhoneNumber")
    public R<BankCard> getByPhoneNumber(@RequestParam String phoneNumber){
        log.info("根据电话号码查询用户信息，phoneNumber={}", phoneNumber);

        // 构造条件构造器
        LambdaQueryWrapper<BankCard> queryWrapper = new LambdaQueryWrapper<>();
        // 添加过滤条件，根据电话号码精确匹配
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            queryWrapper.eq(BankCard::getPhoneNumber, phoneNumber);
        }

        // 执行查询
        BankCard bankcard = bankcardService.getOne(queryWrapper);

        if (bankcard != null) {
            return R.success(bankcard);
        }

        return R.error("没有查询到银行卡信息");
    }

    @PostMapping("/updateBalance")
    public R<String> updateBalance(@RequestBody Map<String, Object> params) {
        String phoneNumber = (String) params.get("phoneNumber");
        BigDecimal newBalance = new BigDecimal(params.get("newBalance").toString());


        log.info("更新用户余额，phoneNumber={}, newBalance={}", phoneNumber, newBalance);

        try {
            // 构造条件构造器
            LambdaQueryWrapper<BankCard> queryWrapper = new LambdaQueryWrapper<>();
            // 添加过滤条件，根据电话号码精确匹配
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                queryWrapper.eq(BankCard::getPhoneNumber, phoneNumber);
            }

            // 查询用户银行卡信息
            BankCard bankCard = bankcardService.getOne(queryWrapper);

            if (bankCard != null) {
                // 更新余额
                bankCard.setBalance(newBalance);
                // 更新数据库
                bankcardService.updateById(bankCard);

                return R.success("余额更新成功");
            } else {
                return R.error("未找到用户银行卡信息");
            }

        } catch (Exception e) {
            log.error("更新用户余额时出错", e);
            return R.error("更新用户余额时出错");
        }
    }
}
