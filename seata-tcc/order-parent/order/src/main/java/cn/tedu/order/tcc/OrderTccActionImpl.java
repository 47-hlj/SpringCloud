package cn.tedu.order.tcc;

import cn.tedu.order.entity.Order;
import cn.tedu.order.mapper.OrderMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/29 16:02
 * @description
 */
@Component
public class OrderTccActionImpl implements OrderTccAction{
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public boolean prepare(BusinessActionContext context, Long id, Long userId, Long productId, Integer count, BigDecimal money) {
        orderMapper.create(new Order(id,userId,productId,count,money,0));
        //添加一阶段成功标记
        ResultHolder.setResult(OrderTccAction.class,context.getXid(),"P");
        return true;
    }

    @Override
    @Transactional
    public boolean commit(BusinessActionContext context) {
        // 标记不存在，二阶段不再重复执行
        if(ResultHolder.getResult(OrderTccAction.class,context.getXid())==null){
            return true;
        }
        Long id = Long.valueOf(context.getActionContext("id").toString());

        orderMapper.updateStatus(id, 1);

        //二阶段成功删除标记
        ResultHolder.removeResult(OrderTccAction.class,context.getXid());
        return true;
    }

    @Override
    @Transactional
    public boolean rollback(BusinessActionContext context) {
        // 标记不存在，回滚不再重复执行
        if(ResultHolder.getResult(OrderTccAction.class,context.getXid())==null){
            return true;
        }
        Long id = Long.valueOf(context.getActionContext("id").toString());
        orderMapper.deleteById(id);
        ResultHolder.removeResult(OrderTccAction.class,context.getXid());
        return true;
    }
}
