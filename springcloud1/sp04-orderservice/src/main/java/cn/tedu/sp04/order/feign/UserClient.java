package cn.tedu.sp04.order.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 47HLJ
 * @date 2021/7/20 14:18
 * @description
 */
@FeignClient(name = "user-service")
public interface UserClient {
    /**
     * 获取用户
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    /**
     * 增加积分
     * @param userId
     * @param score
     * @return
     *     @RequestParam("score")
     *     在 controller 中可以省略
     *     在feign调用接口中省略可能会有问题
     */
    @GetMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable Integer userId,
                           @RequestParam("score") Integer score);
}
