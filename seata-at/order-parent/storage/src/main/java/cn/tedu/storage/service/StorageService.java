package cn.tedu.storage.service;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:07
 * @description
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
