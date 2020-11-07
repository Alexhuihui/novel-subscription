package top.alexmmd.repository;

import org.apache.ibatis.annotations.Param;
import top.alexmmd.domain.entity.Items;
import top.alexmmd.domain.vo.ItemsVo;

import java.util.List;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表(Items)表数据库访问层
 *
 * @author 汪永晖
 * @since 2020-11-06 14:27:49
 */
public interface ItemsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Items queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Items> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param items 实例对象
     * @return 对象列表
     */
    List<Items> queryAll(Items items);

    /**
     * 新增数据
     *
     * @param items 实例对象
     * @return 影响行数
     */
    int insert(Items items);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Items> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Items> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Items> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Items> entities);

    /**
     * 修改数据
     *
     * @param items 实例对象
     * @return 影响行数
     */
    int update(Items items);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 根据 id 查询详情
     *
     * @param id
     * @return
     */
    ItemsVo queryDetailById(String id);

    /**
     * 模糊查询所有商品
     *
     * @param items
     * @return
     */
    List<ItemsVo> fuzzySearchMagazine(Items items);
}