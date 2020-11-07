package top.alexmmd.repository;

import top.alexmmd.domain.NovelChapterDetail;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (NovelChapterDetail)表数据库访问层
 *
 * @author 汪永晖
 * @since 2020-06-20 12:50:44
 */
public interface NovelChapterDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param contentId 主键
     * @return 实例对象
     */
    NovelChapterDetail queryById(Integer contentId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<NovelChapterDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param novelChapterDetail 实例对象
     * @return 对象列表
     */
    List<NovelChapterDetail> queryAll(NovelChapterDetail novelChapterDetail);

    /**
     * 新增数据
     *
     * @param novelChapterDetail 实例对象
     * @return 影响行数
     */
    int insert(NovelChapterDetail novelChapterDetail);

    /**
     * 修改数据
     *
     * @param novelChapterDetail 实例对象
     * @return 影响行数
     */
    int update(NovelChapterDetail novelChapterDetail);

    /**
     * 通过主键删除数据
     *
     * @param contentId 主键
     * @return 影响行数
     */
    int deleteById(Integer contentId);

}