package cn.tedu.sp02.item.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/19 12:02
 * @description  ItemService实现类
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getItems(String orderId) {
        log.info("获取商品， orderId="+orderId);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, "商品1", 1));
        items.add(new Item(2, "商品2", 4));
        items.add(new Item(3, "商品3", 2));
        items.add(new Item(4, "商品4", 5));
        items.add(new Item(5, "商品5", 1));
        return items;
    }

    @Override
    public void decreaseNumber(List<Item> items) {
        log.info("减少商品库存");
        for (Item item : items) {
            log.info("商品： "+item);
        }
    }

}
