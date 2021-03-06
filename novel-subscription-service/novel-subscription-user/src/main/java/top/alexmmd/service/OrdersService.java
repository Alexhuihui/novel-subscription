package top.alexmmd.service;

import top.alexmmd.domain.entity.Orders;
import top.alexmmd.domain.vo.OrdersVo;

import java.util.List;

/**
 * 订单表;(Orders)表服务接口
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:17
 */
public interface OrdersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Orders> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询订单详情
     *
     * @param id 订单 id
     * @return
     */
    OrdersVo queryOrdersDetail(Integer id);

    /**
     * 模糊查询所有订单主表信息
     *
     * @param orders
     * @return
     */
    List<Orders> queryAllOrder(Orders orders);

    /**
     * 查询所有购买了此商品的人
     *
     * @param itemId
     * @return
     */
    List<Orders> queryOrdersByItemId(Integer itemId);
}