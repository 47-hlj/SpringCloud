package cn.tedu.order.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/29 15:35
 * @description
 * 这个接口
 */
@LocalTCC
public interface OrderTccAction {
    /**
     * 第一阶段 try
     * @return
     * 为了避开 seata 的一个bug，这里不使用封装对象，
     * 而是一个一个的接收订单数据
     */
    @TwoPhaseBusinessAction(name = "OrderTccAction")
    boolean prepare(BusinessActionContext context,
                    // 把id放入上下文对象，向第二阶段传递
                    // 把id放入上下文对象，向第二阶段传递
                    @BusinessActionContextParameter(paramName = "id") Long id,
                    Long userId,
                    Long productId,
                    Integer count,
                    BigDecimal money);

    /**
     * Confirm
     * @return
     */
    boolean commit(BusinessActionContext context);

    /**
     * Cancel
     * @return
     */
    boolean rollback(BusinessActionContext context);
}
