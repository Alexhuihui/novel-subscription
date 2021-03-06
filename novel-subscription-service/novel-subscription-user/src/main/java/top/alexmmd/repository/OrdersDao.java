package top.alexmmd.repository;

import org.apache.ibatis.annotations.Param;
import top.alexmmd.domain.entity.Orders;
import top.alexmmd.domain.vo.OrderItemsVo;
import top.alexmmd.domain.vo.OrdersVo;

import java.util.List;

/**
 * 订单表;(Orders)表数据库访问层
 *
 * @author 汪永晖
 * @since 2020-11-06 14:27:49
 */
public interface OrdersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Orders> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orders 实例对象
     * @return 对象列表
     */
    List<Orders> queryAll(Orders orders);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(Orders orders);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Orders> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Orders> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Orders> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Orders> entities);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    OrdersVo queryOrdersDetail(Integer id);

    /**
     * 查询订单商品表
     *
     * @param id
     * @return
     */
    List<OrderItemsVo> queryOrderItemsVoByOrderId(Integer id);

    /**
     * 查询所有购买了此商品的人
     *
     * @param itemId
     * @return
     */
    List<Orders> queryOrdersByItemId(Integer itemId);
}