package top.alexmmd.service;


import top.alexmmd.domain.entity.Category;

import java.util.List;

/**
 * 商品分类 (Category)表服务接口
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:14
 */
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Category> queryAllByLimit(int offset, int limit);

    /**
     * 查询所有类别
     *
     * @return
     */
    List<Category> queryAll(Category category);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}