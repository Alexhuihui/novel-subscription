package top.alexmmd.domain.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表;(Orders)实体类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:26:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Orders implements Serializable {
    private static final long serialVersionUID = -48077349218254388L;
    /**
     * 订单主键;同时也是订单编号
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 收货人快照
     */
    private String receiverName;
    /**
     * 收货人手机号快照
     */
    private String receiverMobile;
    /**
     * 收货地址快照
     */
    private String receiverAddress;
    /**
     * 订单总价格
     */
    private Integer totalAmount;
    /**
     * 实际支付总价格
     */
    private Integer realPayAmount;
    /**
     * 邮费;默认可以为零，代表包邮
     */
    private Integer postAmount;
    /**
     * 支付方式
     */
    private Integer payMethod;
    /**
     * 逻辑删除状态;1: 删除 0:未删除
     */
    private Integer isDelete;
    /**
     * 创建时间（成交时间）
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}