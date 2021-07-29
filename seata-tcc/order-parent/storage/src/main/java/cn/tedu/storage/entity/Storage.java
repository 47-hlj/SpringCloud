package cn.tedu.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 47HLJ
 * @date 2021/7/28 17:06
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
    private Integer frozen;
}
