package top.alexmmd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.bag.CustomerPackage;
import top.alexmmd.domain.bag.ItemsPackage;
import top.alexmmd.domain.entity.Category;
import top.alexmmd.domain.entity.Items;
import top.alexmmd.domain.vo.ItemsVo;
import top.alexmmd.repository.NovelUserDao;
import top.alexmmd.service.CategoryService;
import top.alexmmd.service.ItemsService;
import top.alexmmd.service.MagazineAdminService;
import top.alexmmd.service.SignUpService;

import java.util.Date;
import java.util.List;

/**
 * @author 汪永晖
 */
@Service
public class MagazineAdminServiceImpl implements MagazineAdminService {

    @Value("${admin.customer.password}")
    private String password;

    private final NovelUserDao novelUserDao;

    private final SignUpService signUpService;

    private final ItemsService itemsService;

    private final CategoryService categoryService;

    public MagazineAdminServiceImpl(SignUpService signUpService, NovelUserDao novelUserDao, ItemsService itemsService, CategoryService categoryService) {
        this.signUpService = signUpService;
        this.novelUserDao = novelUserDao;
        this.itemsService = itemsService;
        this.categoryService = categoryService;
    }

    /**
     * 新增顾客
     *
     * @param customerPackage 顾客信息
     * @return
     */
    @Override
    public RespEntity addNewCustomer(CustomerPackage customerPackage) {
        // 使用后台管理系统注册用户
        return signUpService.signUp(NovelUser.builder()
                .username(customerPackage.getUsername())
                .password(password)
                .email(customerPackage.getEmail())
                .build());
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity deleteCustomer(Integer id) {
        return novelUserDao.deleteById(Long.valueOf(id)) > 0 ? new RespEntity(101, "删除成功") : new RespEntity(500, "删除失败");
    }

    /**
     * 修改用户
     *
     * @param customerPackage
     * @return
     */
    @Override
    public RespEntity updateCustomer(CustomerPackage customerPackage) {
        return novelUserDao.updateByUsername(NovelUser.builder()
                .username(customerPackage.getUsername())
                .email(customerPackage.getEmail())
                .updateTime(new Date())
                .build()) > 0 ? new RespEntity(101, "修改成功") : new RespEntity(500, "修改失败");
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity queryCustomer(Integer id) {
        NovelUser novelUser = novelUserDao.queryById(Long.valueOf(id));
        return new RespEntity(100, "成功查询用户详情", novelUser);
    }

    /**
     * 模糊查询所有用户
     *
     * @param novelUser
     * @return
     */
    @Override
    public RespEntity fuzzySearchCustomer(NovelUser novelUser) {
        List<NovelUser> novelUserList = novelUserDao.queryAll(novelUser);
        return new RespEntity(100, "成功查询所有用户", novelUserList);
    }

    /**
     * 新增杂志
     *
     * @param magazinePackage 杂志信息
     * @return
     */
    @Override
    public RespEntity addMagazine(ItemsPackage magazinePackage) {
        Items items = itemsService.insert(Items.builder()
                .catId(magazinePackage.getCatId())
                .content(magazinePackage.getItemName())
                .itemName(magazinePackage.getItemName())
                .rootCatId(magazinePackage.getRootCatId())
                .onOffStatus(1)
                .sellCounts(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build());
        return items != null ? new RespEntity(101, "新增成功", items) : new RespEntity(500, "新增失败");
    }

    @Override
    public RespEntity deleteMagazine(String id) {
        return itemsService.deleteById(id) ? new RespEntity(101, "删除成功") : new RespEntity(500, "删除失败");
    }

    @Override
    public RespEntity updateMagazine(Items items) {
        items.setUpdateTime(new Date());
        Items newItems = itemsService.update(items);
        return new RespEntity(101, "修改成功", newItems);
    }

    @Override
    public RespEntity fuzzySearchMagazine(Items items) {
        List<ItemsVo> itemsVos = itemsService.fuzzySearchMagazine(items);
        return new RespEntity(100, "成功查询所有商品详情", itemsVos);
    }

    @Override
    public RespEntity queryMagazine(String id) {
        ItemsVo itemsVo = itemsService.queryDetailById(id);
        return new RespEntity(100, "成功查询商品详情", itemsVo);
    }

    /**
     * 新增类别
     *
     * @param category
     * @return
     */
    @Override
    public RespEntity addCategory(Category category) {
        Category newCategory = categoryService.insert(category);
        return newCategory != null ? new RespEntity(101, "成功新增类别", newCategory) : new RespEntity(500, "新增类别失败");
    }

    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity deleteCategory(Integer id) {
        return categoryService.deleteById(id) ? new RespEntity(101, "删除类别成功") : new RespEntity(500, "删除类别失败");
    }

    /**
     * 修改类别
     *
     * @param category
     * @return
     */
    @Override
    public RespEntity updateCategory(Category category) {
        Category newCategory = categoryService.update(category);
        return newCategory != null ? new RespEntity(101, "成功修改类别", newCategory) : new RespEntity(500, "修改类别失败");
    }

    /**
     * 查询类别
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity queryCategory(Integer id) {
        return new RespEntity(100, "成功查询查询类别", categoryService.queryById(id));
    }

    /**
     * 查询所有类别
     *
     * @return
     */
    @Override
    public RespEntity queryAllCategory(Category category) {
        List<Category> categoryList = categoryService.queryAll(category);
        return new RespEntity(100, "成功查询查询所有类别", categoryList);
    }


}
