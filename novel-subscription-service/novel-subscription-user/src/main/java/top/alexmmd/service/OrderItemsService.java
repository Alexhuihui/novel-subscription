package top.alexmmd.service;

import org.apache.ibatis.annotations.Param;
import top.alexmmd.domain.entity.OrderItems;

import java.util.List;

/**
 * 订单商品关联表 (OrderItems)表服务接口
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:16
 */
public interface OrderItemsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderItems queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderItems> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderItems 实例对象
     * @return 实例对象
     */
    OrderItems insert(OrderItems orderItems);

    /**
     * 修改数据
     *
     * @param orderItems 实例对象
     * @return 实例对象
     */
    OrderItems update(OrderItems orderItems);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 批量插入
     *
     * @param entities
     * @return
     */
    int insertBatch(List<OrderItems> entities);

    /**
     * 根据订单 id 删除订单商品
     *
     * @param orderId 订单 id
     * @return
     */
    int deleteByOrdersId(Integer orderId);

    /**
     * 根据订单 id 查询订单商品
     *
     * @param id
     * @return
     */
    List<OrderItems> queryByOrderId(Integer id);
}