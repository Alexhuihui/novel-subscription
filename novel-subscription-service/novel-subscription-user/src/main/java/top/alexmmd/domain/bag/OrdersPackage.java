package top.alexmmd.domain.bag;

import lombok.*;
import top.alexmmd.domain.entity.OrderItems;

import java.util.Date;
import java.util.List;

/**
 * 订单前端传输对象
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrdersPackage {

    /**
     * 订单主键;同时也是订单编号
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
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

    private List<Integer> itemIdList;
}
