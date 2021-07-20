package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/19 14:13
 * @description
 */
@RestController
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 获取订单商品列表
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        /*
        JsonResult - 响应结构的封装对象
                   - code：响应的状态码，可以是http状态，或者也可以自己任意定义
                   - msg：提示信息
                   - data：发回的响应数据对象
         */
        List<Item> items=itemService.getItems(orderId);
        return JsonResult.ok().data(items);
    }


    /**
     * 减少商品库存
     * 客户端发送的请求协议，商品集合要包含在请求协议体中，
     * @RequsetBody 是完整接收请求协议体数据
     * @param items
     * @return
     */
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items){
        itemService.decreaseNumber(items);
        return JsonResult.ok().msg("减少商品库存成功");
    }


}
