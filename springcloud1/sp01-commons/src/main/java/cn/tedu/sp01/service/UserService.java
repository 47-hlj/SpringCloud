package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.User;

/**
 * @author 47HLJ
 * @date 2021/7/19 11:34
 * @description
 */
public interface UserService {
    /**
     * 获取用户
     * @param userId
     * @return
     */
    User getUser(Integer userId);

    /**
     * 增加用户积分
     * @param userId
     * @param score
     */
    void addScore(Integer userId, Integer score);
}
