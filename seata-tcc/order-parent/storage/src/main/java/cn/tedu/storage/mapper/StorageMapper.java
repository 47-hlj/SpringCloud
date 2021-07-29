package cn.tedu.storage.mapper;

import cn.tedu.storage.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:06
 * @description
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
    /**
     * 减少库存
     * @param productId
     * @param count
     */
    void decrease(Long productId, Integer count);

    /**
     * 查询库存
     * @param productId
     * @return
     */
    Storage selectByProductId(Long productId);

    /**
     * 库存可用到冻结
     * @param productId
     * @param count
     */
    void updateResidueToFrozen(Long productId,Integer count);

    /**
     * 冻结到可用
     * @param productId
     * @param count
     */
    void updateFrozenToUsed(Long productId,Integer count);

    /**
     * 冻结->可用
     * @param productId
     * @param count
     */
    void updateFrozenToResidue(Long productId,Integer count);
}
