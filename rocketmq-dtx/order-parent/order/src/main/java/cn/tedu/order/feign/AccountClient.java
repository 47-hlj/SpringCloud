package cn.tedu.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/28 23:21
 * @description
 */
@FeignClient(name = "ACCOUNT")
public interface AccountClient {
    @GetMapping("/decrease")
    String decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
