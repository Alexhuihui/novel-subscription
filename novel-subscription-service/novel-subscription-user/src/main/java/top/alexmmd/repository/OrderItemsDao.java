package top.alexmmd.repository;

import org.apache.ibatis.annotations.Param;
import top.alexmmd.domain.entity.OrderItems;

import java.util.List;

/**
 * 订单商品关联表 (OrderItems)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-06 14:27:49
 */
public interface OrderItemsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderItems queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderItems> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderItems 实例对象
     * @return 对象列表
     */
    List<OrderItems> queryAll(OrderItems orderItems);

    /**
     * 新增数据
     *
     * @param orderItems 实例对象
     * @return 影响行数
     */
    int insert(OrderItems orderItems);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderItems> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderItems> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderItems> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderItems> entities);

    /**
     * 修改数据
     *
     * @param orderItems 实例对象
     * @return 影响行数
     */
    int update(OrderItems orderItems);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}