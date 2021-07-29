package cn.tedu.storage.service.impl;

import cn.tedu.storage.mapper.StorageMapper;
import cn.tedu.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:07
 * @description
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Transactional
    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }
}
