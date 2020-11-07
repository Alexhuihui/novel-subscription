package top.alexmmd.service.impl;

import org.springframework.stereotype.Service;
import top.alexmmd.domain.entity.OrderItems;
import top.alexmmd.domain.entity.Orders;
import top.alexmmd.domain.vo.OrderItemsVo;
import top.alexmmd.domain.vo.OrdersVo;
import top.alexmmd.repository.OrdersDao;
import top.alexmmd.service.OrdersService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单表;(Orders)表服务实现类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:17
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(String id) {
        return this.ordersDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Orders> queryAllByLimit(int offset, int limit) {
        return this.ordersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders insert(Orders orders) {
        this.ordersDao.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders update(Orders orders) {
        this.ordersDao.update(orders);
        return this.queryById(orders.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.ordersDao.deleteById(id) > 0;
    }

    /**
     * 查询订单详情
     *
     * @param id 订单 id
     * @return
     */
    @Override
    public OrdersVo queryOrdersDetail(String id) {
        // 查询订单表
        OrdersVo ordersVo = ordersDao.queryOrdersDetail(id);

        // 查询订单商品表
        List<OrderItemsVo> orderItemsVoList = ordersDao.queryOrderItemsVoByOrderId(id);
        ordersVo.setOrderItemsVoList(orderItemsVoList);
        return ordersVo;
    }

    /**
     * 模糊查询所有订单主表信息
     *
     * @param orders
     * @return
     */
    @Override
    public List<Orders> queryAllOrder(Orders orders) {
        return ordersDao.queryAll(orders);
    }

    /**
     * 查询所有购买了此商品的人
     *
     * @param itemId
     * @return
     */
    @Override
    public List<Orders> queryOrdersByItemId(String itemId) {
        List<Orders> ordersList = ordersDao.queryOrdersByItemId(itemId);
        return ordersList;
    }
}