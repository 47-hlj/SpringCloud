package cn.tedu.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 47HLJ
 * @date 2021/8/2 12:40
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxInfo {
    private String xid;
    private Integer status;
    private Long created;
}
