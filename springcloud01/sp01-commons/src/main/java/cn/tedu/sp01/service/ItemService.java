package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.Item;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/19 11:34
 * @description
 */
public interface ItemService {
    /**
     * 获取订单商品列表
     * @param orderId
     * @return
     */

    List<Item> getItems(String orderId);

    /**
     * 减少商品库存
     * @param items
     */
    void decreaseNumber(List<Item> items);

}
