package cn.tedu.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 47HLJ
 * @date 2021/7/28 21:28
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;
}
