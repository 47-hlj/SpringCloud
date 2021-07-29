package cn.tedu.account.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * @author 47HLJ
 * @date 2021/7/28 15:51
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总金额
     */
    private BigDecimal total;
    /**
     * 已使用金额
     */
    private BigDecimal used;
    /**
     * 可用金额
     */
    private BigDecimal residue;
    /**
     * 冻结金额
     */
    private BigDecimal frozen;
}
