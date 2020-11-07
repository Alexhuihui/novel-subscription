package top.alexmmd.domain.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表(Items)实体类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:26:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Items implements Serializable {
    private static final long serialVersionUID = -99555360075904871L;
    /**
     * 商品主键id
     */
    private String id;
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