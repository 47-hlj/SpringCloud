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
    void decrease(Long productId, Integer count);
}
