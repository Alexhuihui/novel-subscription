package top.alexmmd.service.impl;

import org.springframework.stereotype.Service;
import top.alexmmd.domain.entity.Category;
import top.alexmmd.repository.CategoryDao;
import top.alexmmd.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类 (Category)表服务实现类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:14
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return this.categoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Category> queryAllByLimit(int offset, int limit) {
        return this.categoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有类别
     *
     * @param category
     * @return
     */
    @Override
    public List<Category> queryAll(Category category) {
        return categoryDao.queryAll(category);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryDao.insert(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.categoryDao.deleteById(id) > 0;
    }
}