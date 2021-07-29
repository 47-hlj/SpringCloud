package cn.tedu.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 47HLJ
 * @date 2021/7/28 23:16
 * @description
 */
@FeignClient(name = "EASY-ID-GENERATOR")
public interface EasyIdClient {
    @GetMapping("/segment/ids/next_id")
    String nextId(@RequestParam("businessType") String businessType);
}
