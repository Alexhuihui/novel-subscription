package top.alexmmd.dao;

import org.apache.ibatis.annotations.Param;
import top.alexmmd.model.NovelUser;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author 汪永晖
 * @since 2020-06-04 15:25:38
 */
public interface NovelUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    NovelUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<NovelUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<NovelUser> queryAll(NovelUser user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(NovelUser user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(NovelUser user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    NovelUser findByUsername(String username);

}