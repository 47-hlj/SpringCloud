package cn.tedu.storage.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author 47HLJ
 * @date 2021/7/29 17:21
 * @description
 */
@LocalTCC
public interface StorageTccAction {
    /**
     * 第一阶段 try
     * @param context
     * @param productId
     * @param count
     * @return
     */
    @TwoPhaseBusinessAction(name = "StorageTccAction")
    boolean prepare(BusinessActionContext context,
                    @BusinessActionContextParameter(paramName = "productId") Long productId,
                    @BusinessActionContextParameter(paramName = "count") Integer count);

    /**
     * Confirm
     * @param context
     * @return
     */
    boolean commit(BusinessActionContext context);

    /**
     * Cancel
     * @param context
     * @return
     */
    boolean rollback(BusinessActionContext context);
}
