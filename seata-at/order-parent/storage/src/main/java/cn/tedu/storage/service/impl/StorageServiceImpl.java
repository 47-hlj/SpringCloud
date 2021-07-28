package cn.tedu.storage.service.impl;

import cn.tedu.storage.mapper.StorageMapper;
import cn.tedu.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:07
 * @description
 */
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;
    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }
}
