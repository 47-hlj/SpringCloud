package cn.tedu.account.mapper;

import cn.tedu.account.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/28 16:23
 * @description
 */

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 减少账户库存
     * @param userId
     * @param money
     */
    void decrease(Long userId, BigDecimal money);

    /**
     * 查询账户金额
     * @param userId
     * @return
     */
    Account selectByUserId(Long userId);

    /**
     * 可用 --> 冻结
     * @param userId
     * @param money
     */
    void updateResidueToFrozen(Long userId,BigDecimal money);

    /**
     * 冻结 --> 已使用
     * @param userId
     * @param money
     */
    void updateFrozenToUsed(Long userId,BigDecimal money);

    /**
     * 冻结 --> 可用
     * @param userId
     * @param money
     */
    void updateFrozenToResidue(Long userId,BigDecimal money);

}
