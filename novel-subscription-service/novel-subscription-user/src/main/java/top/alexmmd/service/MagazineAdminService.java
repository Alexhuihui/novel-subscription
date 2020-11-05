package top.alexmmd.service;

import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.bag.CustomerPackage;
import top.alexmmd.domain.bag.MagazinePackage;

/**
 * @author 汪永晖
 */
public interface MagazineAdminService {

    /**
     * 新增顾客
     *
     * @param customerPackage 顾客信息
     * @return
     */
    RespEntity addNewCustomer(CustomerPackage customerPackage);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    RespEntity deleteCustomer(Integer id);

    /**
     * 修改用户
     *
     * @param customerPackage
     * @return
     */
    RespEntity updateCustomer(CustomerPackage customerPackage);

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    RespEntity queryCustomer(Integer id);

    /**
     * 模糊查询所有用户
     *
     * @param novelUser
     * @return
     */
    RespEntity fuzzySearchCustomer(NovelUser novelUser);

    /**
     * 新增杂志
     *
     * @param magazinePackage 杂志信息
     * @return
     */
    RespEntity addMagazine(MagazinePackage magazinePackage);
}
