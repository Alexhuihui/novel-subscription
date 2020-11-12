package top.alexmmd.service.impl;

import org.springframework.stereotype.Service;
import top.alexmmd.domain.entity.OrderItems;
import top.alexmmd.repository.OrderItemsDao;
import top.alexmmd.service.OrderItemsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单商品关联表 (OrderItems)表服务实现类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:16
 */
@Service("orderItemsService")
public class OrderItemsServiceImpl implements OrderItemsService {
    @Resource
    private OrderItemsDao orderItemsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderItems queryById(Integer id) {
        return this.orderItemsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderItems> queryAllByLimit(int offset, int limit) {
        return this.orderItemsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orderItems 实例对象
     * @return 实例对象
     */
    @Override
    public OrderItems insert(OrderItems orderItems) {
        this.orderItemsDao.insert(orderItems);
        return orderItems;
    }

    /**
     * 修改数据
     *
     * @param orderItems 实例对象
     * @return 实例对象
     */
    @Override
    public OrderItems update(OrderItems orderItems) {
        this.orderItemsDao.update(orderItems);
        return this.queryById(orderItems.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.orderItemsDao.deleteById(id) > 0;
    }

    /**
     * 批量插入
     *
     * @param entities
     * @return
     */
    @Override
    public int insertBatch(List<OrderItems> entities) {
        return orderItemsDao.insertBatch(entities);
    }

    /**
     * 根据订单 id 删除订单商品
     *
     * @param orderId 订单 id
     * @return
     */
    @Override
    public int deleteByOrdersId(Integer orderId) {
        return orderItemsDao.deleteByOrdersId(orderId);
    }

    /**
     * 根据订单 id 查询订单商品
     *
     * @param id
     * @return
     */
    @Override
    public List<OrderItems> queryByOrderId(Integer id) {
        return orderItemsDao.queryByOrderId(id);
    }
}