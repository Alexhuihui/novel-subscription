package top.alexmmd.repository;

import top.alexmmd.domain.NovelChapter;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (NovelChapter)表数据库访问层
 *
 * @author 汪永晖
 * @since 2020-06-20 12:50:38
 */
public interface NovelChapterDao {

    /**
     * 通过ID查询单条数据
     *
     * @param chapterId 主键
     * @return 实例对象
     */
    NovelChapter queryById(Integer chapterId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<NovelChapter> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param novelChapter 实例对象
     * @return 对象列表
     */
    List<NovelChapter> queryAll(NovelChapter novelChapter);

    /**
     * 新增数据
     *
     * @param novelChapter 实例对象
     * @return 影响行数
     */
    int insert(NovelChapter novelChapter);

    /**
     * 修改数据
     *
     * @param novelChapter 实例对象
     * @return 影响行数
     */
    int update(NovelChapter novelChapter);

    /**
     * 通过主键删除数据
     *
     * @param chapterId 主键
     * @return 影响行数
     */
    int deleteById(Integer chapterId);

}