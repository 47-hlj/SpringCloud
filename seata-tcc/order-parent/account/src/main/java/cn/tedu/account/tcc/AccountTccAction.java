package cn.tedu.account.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/30 9:44
 * @description
 */
@LocalTCC
public interface AccountTccAction {

    /**
     * 第一阶段 try
     * @param context
     * @param userId
     * @param money
     * @return
     */
    @TwoPhaseBusinessAction(name = "AccountTccAction")
    boolean prepare(BusinessActionContext context,
                    @BusinessActionContextParameter(paramName = "userId") Long userId,
                    @BusinessActionContextParameter(paramName = "money") BigDecimal money);

    /**
     * Confirm 第二阶段 提交
     * @param context
     * @return
     */
    boolean commit(BusinessActionContext context);

    /**
     * Cancel 回滚
     * @param context
     * @return
     */
    boolean rollback(BusinessActionContext context);
}
