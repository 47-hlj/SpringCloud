package cn.tedu.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 47HLJ
 * @date 2021/7/28 23:19
 * @description
 */
@FeignClient(name = "STORAGE")
public interface StorageClient {
    @GetMapping("/decrease")
    String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
