package cn.tedu.storage.service.impl;

import cn.tedu.storage.service.StorageService;
import cn.tedu.storage.tcc.StorageTccAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:07
 * @description
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageTccAction storageTccAction;

    @Override
    public void decrease(Long productId, Integer count) {
        //storageMapper.decrease(productId, count);
        storageTccAction.prepare(null,productId,count);

    }
}
