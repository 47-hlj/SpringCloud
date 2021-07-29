package cn.tedu.storage.tcc;

import cn.tedu.storage.entity.Storage;
import cn.tedu.storage.mapper.StorageMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.ibatis.annotations.Lang;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 47HLJ
 * @date 2021/7/29 17:27
 * @description
 */
@Component
public class StorageTccActionImpl implements StorageTccAction{

    @Autowired
    private StorageMapper storageMapper;

    @Override
    @Transactional
    public boolean prepare(BusinessActionContext context, Long productId, Integer count) {
        // 查询库存，判断是否有足够库存
        Storage s=storageMapper.selectByProductId(productId);
        if(s.getResidue()<count){
            throw new RuntimeException("库存不足");
        }
        // 可用 --> 冻结
        storageMapper.updateResidueToFrozen(productId,count);
        return true;
    }

    @Override
    @Transactional
    public boolean commit(BusinessActionContext context) {
        // 冻结 --> 已使用
        Long productId = Long.valueOf(context.getActionContext("productId").toString());
        Integer count =Integer.valueOf(context.getActionContext("count").toString());
        storageMapper.updateFrozenToUsed(productId,count);
        return true;
    }

    @Override
    @Transactional
    public boolean rollback(BusinessActionContext context) {
        Long productId = Long.valueOf(context.getActionContext("productId").toString());
        Integer count =Integer.valueOf(context.getActionContext("count").toString());
        // 冻结 -> 可用
        storageMapper.updateFrozenToResidue(productId,count);
        return true;
    }
}
