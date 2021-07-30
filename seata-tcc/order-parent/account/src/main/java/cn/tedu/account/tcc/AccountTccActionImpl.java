package cn.tedu.account.tcc;

import cn.tedu.account.entity.Account;
import cn.tedu.account.mapper.AccountMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/30 9:44
 * @description
 */
@Component
public class AccountTccActionImpl implements AccountTccAction{

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    @Override
    public boolean prepare(BusinessActionContext context, Long userId, BigDecimal money) {
        Account account=accountMapper.selectByUserId(userId);
        if(account.getResidue().compareTo(money)<0){
            throw new RuntimeException("可用金额不足");
        }
        accountMapper.updateResidueToFrozen(userId,money);
        ResultHolder.setResult(AccountTccAction.class,context.getXid(),"p");
        return true;
    }

    @Transactional
    @Override
    public boolean commit(BusinessActionContext context) {
        if(ResultHolder.getResult(AccountTccAction.class,context.getXid())==null){
            return true;
        }
        Long userId = Long.valueOf(context.getActionContext("userId").toString());
        BigDecimal money = new BigDecimal(context.getActionContext("money").toString());

        accountMapper.updateFrozenToUsed(userId,money);
        ResultHolder.removeResult(AccountTccAction.class,context.getXid());
        return true;
    }

    @Transactional
    @Override
    public boolean rollback(BusinessActionContext context) {
        if(ResultHolder.getResult(AccountTccAction.class,context.getXid())==null){
            return true;
        }
        Long userId = Long.valueOf(context.getActionContext("userId").toString());
        BigDecimal money = new BigDecimal(context.getActionContext("money").toString());

        accountMapper.updateFrozenToResidue(userId,money);
        ResultHolder.removeResult(AccountTccAction.class,context.getXid());
        return true;
    }
}
