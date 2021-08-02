package cn.tedu.order.mapper;

import cn.tedu.order.entity.TxInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 47HLJ
 * @date 2021/8/2 12:40
 * @description
 */
@Mapper
public interface TxMapper extends BaseMapper<TxInfo> {
    // 存储事务状态，用继承的 insert()

    // 查询事务状态，用继承的 selectById()
}
