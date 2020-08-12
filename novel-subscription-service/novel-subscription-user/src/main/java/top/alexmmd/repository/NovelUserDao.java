package top.alexmmd.repository;

import top.alexmmd.domain.NovelUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户信息表(NovelUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-20 12:50:44
 */
public interface NovelUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    NovelUser queryById(Long id);

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
     * @param novelUser 实例对象
     * @return 对象列表
     */
    List<NovelUser> queryAll(NovelUser novelUser);

    /**
     * 新增数据
     *
     * @param novelUser 实例对象
     * @return 影响行数
     */
    int insert(NovelUser novelUser);

    /**
     * 修改数据
     *
     * @param novelUser 实例对象
     * @return 影响行数
     */
    int update(NovelUser novelUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    NovelUser findByUsername(String username);
}