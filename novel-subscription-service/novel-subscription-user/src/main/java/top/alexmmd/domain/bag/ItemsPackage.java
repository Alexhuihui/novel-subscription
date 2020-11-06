package top.alexmmd.domain.bag;

import lombok.*;

/**
 * Items的前端传输对象
 *
 * @author 汪永晖
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemsPackage {

    /**
     * 商品名称 商品名称
     */
    private String itemName;
    /**
     * 分类外键id 分类id
     */
    private Integer catId;
    /**
     * 一级分类外键id
     */
    private Integer rootCatId;
    /**
     * 商品内容 商品内容
     */
    private String content;
}
