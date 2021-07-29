package cn.tedu.account.service;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/28 16:32
 * @description
 */

public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
