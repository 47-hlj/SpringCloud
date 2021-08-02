package cn.tedu.account.service.impl;

import cn.tedu.account.mapper.AccountMapper;
import cn.tedu.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/28 16:34
 * @description
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId,money);
    }
}
