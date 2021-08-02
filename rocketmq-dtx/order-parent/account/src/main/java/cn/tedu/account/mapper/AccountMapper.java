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

    void decrease(Long userId, BigDecimal money);
}
