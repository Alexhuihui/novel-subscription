package top.alexmmd.repository;

import org.apache.ibatis.annotations.Param;
import top.alexmmd.domain.entity.Category;

import java.util.List;

/**
 * 商品分类 (Category)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-06 14:27:47
 */
public interface CategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Category> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    List<Category> queryAll(Category category);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insert(Category category);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Category> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Category> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Category> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Category> entities);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}