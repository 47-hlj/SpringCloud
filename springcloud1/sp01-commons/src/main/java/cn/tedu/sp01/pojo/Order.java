package cn.tedu.sp01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/19 11:31
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
    字符串类型id*
     */
    private String id;
    /**
     * 这个订单所属的用户
     */
    private User user;
    /**
     * 订单中包含的商品列表
     */
    private List<Item> items;

}
