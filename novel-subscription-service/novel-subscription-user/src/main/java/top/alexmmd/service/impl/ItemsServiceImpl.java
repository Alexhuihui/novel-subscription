package top.alexmmd.service.impl;

import org.springframework.stereotype.Service;
import top.alexmmd.domain.entity.Items;
import top.alexmmd.domain.vo.ItemsVo;
import top.alexmmd.repository.ItemsDao;
import top.alexmmd.service.ItemsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表(Items)表服务实现类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:36:16
 */
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {
    @Resource
    private ItemsDao itemsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Items queryById(Integer id) {
        return this.itemsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Items> queryAllByLimit(int offset, int limit) {
        return this.itemsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param items 实例对象
     * @return 实例对象
     */
    @Override
    public Items insert(Items items) {
        this.itemsDao.insert(items);
        return items;
    }

    /**
     * 修改数据
     *
     * @param items 实例对象
     * @return 实例对象
     */
    @Override
    public Items update(Items items) {
        this.itemsDao.update(items);
        return this.queryById(items.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.itemsDao.deleteById(id) > 0;
    }

    /**
     * 根据 id 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public ItemsVo queryDetailById(Integer id) {
        return itemsDao.queryDetailById(id);
    }

    /**
     * 模糊查询所有商品
     *
     * @param items
     * @return
     */
    @Override
    public List<ItemsVo> fuzzySearchMagazine(Items items) {
        return itemsDao.fuzzySearchMagazine(items);
    }
}