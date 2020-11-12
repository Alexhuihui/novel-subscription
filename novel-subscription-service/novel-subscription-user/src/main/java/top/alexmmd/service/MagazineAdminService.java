package top.alexmmd.service;

import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.bag.*;
import top.alexmmd.domain.entity.Category;
import top.alexmmd.domain.entity.Items;
import top.alexmmd.domain.entity.Orders;

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
    RespEntity addMagazine(ItemsPackage magazinePackage);

    /**
     * 删除杂志
     *
     * @param id
     * @return
     */
    RespEntity deleteMagazine(Integer id);

    /**
     * 修改杂志
     *
     * @param items
     * @return
     */
    RespEntity updateMagazine(Items items);

    /**
     * 模糊查询所有杂志
     *
     * @param items
     * @return
     */
    RespEntity fuzzySearchMagazine(Items items);

    /**
     * 查询杂志详情
     *
     * @param id
     * @return
     */
    RespEntity queryMagazine(Integer id);

    /**
     * 新增类别
     *
     * @param category
     * @return
     */
    RespEntity addCategory(Category category);

    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    RespEntity deleteCategory(Integer id);

    /**
     * 修改类别
     *
     * @param category
     * @return
     */
    RespEntity updateCategory(Category category);

    /**
     * 查询类别
     *
     * @param id
     * @return
     */
    RespEntity queryCategory(Integer id);

    /**
     * 查询所有类别
     *
     * @return
     */
    RespEntity queryAllCategory(Category category);

    /**
     * 新增订单
     *
     * @param ordersPackage
     * @return
     */
    RespEntity addOrder(OrdersPackage ordersPackage);

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    RespEntity deleteOrder(Integer id);

    /**
     * 修改订单
     *
     * @param ordersPackage
     * @return
     */
    RespEntity updateOrder(OrdersPackage ordersPackage);

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    RespEntity queryOrder(Integer id);

    /**
     * 模糊查询所有订单主表信息
     *
     * @param orders
     * @return
     */
    RespEntity queryAllOrder(Orders orders);

    /**
     * 发送更新邮件
     *
     * @param updateMailPackage
     * @return
     */
    RespEntity sendUpdateMail(UpdateMailPackage updateMailPackage);

    /**
     * 发送通知邮件
     *
     * @param mailPackage
     * @return
     */
    RespEntity notifyAllMail(MailPackage mailPackage);
}
