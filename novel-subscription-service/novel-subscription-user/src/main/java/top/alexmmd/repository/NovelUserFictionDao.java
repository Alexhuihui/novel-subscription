package top.alexmmd.repository;

import top.alexmmd.domain.NovelUserFiction;
import org.apache.ibatis.annotations.Param;
import top.alexmmd.domain.NovelUserFictionVo;

import java.util.List;

/**
 * 用户订阅小说表(NovelUserFiction)表数据库访问层
 *
 * @author 汪永晖
 * @since 2020-06-20 12:50:44
 */
public interface NovelUserFictionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    NovelUserFiction queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<NovelUserFiction> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param novelUserFiction 实例对象
     * @return 对象列表
     */
    List<NovelUserFiction> queryAll(NovelUserFiction novelUserFiction);

    /**
     * 新增数据
     *
     * @param novelUserFiction 实例对象
     * @return 影响行数
     */
    int insert(NovelUserFiction novelUserFiction);

    /**
     * 修改数据
     *
     * @param novelUserFiction 实例对象
     * @return 影响行数
     */
    int update(NovelUserFiction novelUserFiction);

    /**
     * 通过主键删除数据
     *
     * @param novelId 主键
     * @return 影响行数
     */
    int deleteById(@Param("novelId") Long novelId, @Param("username") String username);

    /**
     * 根据username查询所有订阅小说
     *
     * @param username
     * @return
     */
    List<NovelUserFictionVo> queryAllSubscription(String username);
}