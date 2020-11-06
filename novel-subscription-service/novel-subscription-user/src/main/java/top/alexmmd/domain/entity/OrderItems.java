package top.alexmmd.domain.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 订单商品关联表 (OrderItems)实体类
 *
 * @author makejava
 * @since 2020-11-06 14:26:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderItems implements Serializable {
    private static final long serialVersionUID = -96553204653801287L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 归属订单id
     */
    private String orderId;
    /**
     * 商品id
     */
    private String itemId;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 成交价格
     */
    private Integer price;
    /**
     * 购买数量
     */
    private Integer buyCounts;

}