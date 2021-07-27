package cn.tedu.sp03.user.service;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/20 15:47
 * @description
 * 只有添加了@RefreshScope 刷新到新的用户数据后，才能重新注入到这个对象
 */
@RefreshScope
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${sp.user-service.users}")
    private String userJson;

    /**
    从 demo 数据中查询,
    如果demo数据中没有，返回写死的数据
     */
    @Override
    public User getUser(Integer userId) {
        log.info("获取用户： userId="+userId);

        // userJson 转成 List<User>
        // import com.fasterxml.jackson.core.type.TypeReference;
        // 利用匿名内部类的继承语法，写泛型类型参数 <List<User>>
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});
        for (User u : list) {
            if (u.getId().equals(userId)) {
                return u;
            }
        }
        // 上面没有找到用户，这里返回一个写死的用户数据
        return new User(userId,"用户名"+userId,"密码"+userId);
    }

    @Override
    public void addScore(Integer userId, Integer score) {
        log.info("增加用户积分， userId="+userId+", score="+score);
    }
}
