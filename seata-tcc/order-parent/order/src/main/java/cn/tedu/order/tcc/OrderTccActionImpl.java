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

    @Transactional
    @Override
    public boolean prepare(BusinessActionContext context, Long id, Long userId, Long productId, Integer count, BigDecimal money) {
        orderMapper.create(new Order(id,userId,productId,count,money,0));
        return true;
    }

    @Transactional
    @Override
    public boolean commit(BusinessActionContext context) {
        Long id = Long.valueOf(context.getActionContext("id").toString());

        orderMapper.updateStatus(id, 1);
        return true;
    }

    @Transactional
    @Override
    public boolean rollback(BusinessActionContext context) {
        Long id = Long.valueOf(context.getActionContext("id").toString());
        orderMapper.deleteById(id);
        return true;
    }
}
