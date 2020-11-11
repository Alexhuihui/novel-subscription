package top.alexmmd.domain.vo;

import lombok.*;

import java.util.Date;

/**
 * 商品表对应的前端视图
 *
 * @author 汪永晖
 * @Date 2020/11/6 14:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemsVo {

    /**
     * 商品主键id
     */
    private Integer id;
    /**
     * 商品名称 商品名称
     */
    private String itemName;
    /**
     * 分类外键id 分类id
     */
    private Integer catId;

    private String catName;
    /**
     * 一级分类外键id
     */
    private Integer rootCatId;

    private String rootCatName;

    /**
     * 累计销售 累计销售
     */
    private Integer sellCounts;
    /**
     * 上下架状态 上下架状态,1:上架 2:下架
     */
    private Integer onOffStatus;
    /**
     * 商品内容 商品内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
