package cn.tedu.storage.controller;

import cn.tedu.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 47HLJ
 * @date 2021/7/28 21:07
 * @description
 */
@RestController
@Slf4j
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/decrease")
    public String decrease(Long productId,Integer count){
        storageService.decrease(productId, count);
        return "减少库存成功";
    }
}
