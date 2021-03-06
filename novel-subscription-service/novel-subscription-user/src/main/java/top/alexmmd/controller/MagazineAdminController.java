package top.alexmmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.annotation.AutoIdempotent;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.bag.*;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.entity.Category;
import top.alexmmd.domain.entity.Items;
import top.alexmmd.domain.entity.Orders;
import top.alexmmd.service.MagazineAdminService;

import java.util.ArrayList;

/**
 * 杂志后台管理
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/admin/magazine")
public class MagazineAdminController {

    @Autowired
    private MagazineAdminService magazineAdminService;

    /**
     * 新增顾客
     *
     * @return
     */
    @PostMapping("/addNewCustomer")
    @AutoIdempotent
    public RespEntity addNewCustomer(@RequestBody CustomerPackage customerPackage) {
        return magazineAdminService.addNewCustomer(customerPackage);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @DeleteMapping("/deleteCustomer/{id}")
    public RespEntity deleteCustomer(@PathVariable Integer id) {
        return magazineAdminService.deleteCustomer(id);
    }

    /**
     * 修改用户
     *
     * @param customerPackage
     * @return
     */
    @PutMapping("/updateCustomer")
    public RespEntity updateCustomer(@RequestBody CustomerPackage customerPackage) {
        return magazineAdminService.updateCustomer(customerPackage);
    }

    /**
     * 查询用户详情
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/queryCustomer/{id}")
    public RespEntity queryCustomer(@PathVariable Integer id) {
        return magazineAdminService.queryCustomer(id);
    }

    /**
     * 模糊查询所有用户
     *
     * @param novelUser
     * @return
     */
    @PostMapping("/fuzzySearchCustomer")
    public RespEntity fuzzySearchCustomer(@RequestBody NovelUser novelUser) {
        return magazineAdminService.fuzzySearchCustomer(novelUser);
    }

    /**
     * 新增杂志
     *
     * @return
     */
    @PostMapping("/addMagazine")
    @AutoIdempotent
    public RespEntity addMagazine(@RequestBody ItemsPackage magazinePackage) {
        return magazineAdminService.addMagazine(magazinePackage);
    }

    /**
     * 删除杂志
     *
     * @param id 用户id
     * @return
     */
    @DeleteMapping("/deleteMagazine/{id}")
    public RespEntity deleteMagazine(@PathVariable Integer id) {
        return magazineAdminService.deleteMagazine(id);
    }

    /**
     * 修改杂志
     *
     * @param items
     * @return
     */
    @PutMapping("/updateMagazine")
    public RespEntity updateMagazine(@RequestBody Items items) {
        return magazineAdminService.updateMagazine(items);
    }

    /**
     * 查询杂志详情
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/queryMagazine/{id}")
    public RespEntity queryMagazine(@PathVariable Integer id) {
        return magazineAdminService.queryMagazine(id);
    }

    /**
     * 模糊查询所有杂志
     *
     * @param items
     * @return
     */
    @PostMapping("/fuzzySearchMagazine")
    public RespEntity fuzzySearchMagazine(@RequestBody Items items) {
        return magazineAdminService.fuzzySearchMagazine(items);
    }

    /**
     * 新增类别
     *
     * @return
     */
    @PostMapping("/addCategory")
    @AutoIdempotent
    public RespEntity addCategory(@RequestBody Category category) {
        return magazineAdminService.addCategory(category);
    }

    /**
     * 删除类别
     *
     * @param id 类别id
     * @return
     */
    @DeleteMapping("/deleteCategory/{id}")
    public RespEntity deleteCategory(@PathVariable Integer id) {
        return magazineAdminService.deleteCategory(id);
    }

    /**
     * 修改类别
     *
     * @param category
     * @return
     */
    @PutMapping("/updateCategory")
    public RespEntity updateCategory(@RequestBody Category category) {
        return magazineAdminService.updateCategory(category);
    }

    /**
     * 查询类别
     *
     * @param id 类别id
     * @return
     */
    @GetMapping("/queryCategory/{id}")
    public RespEntity queryCategory(@PathVariable Integer id) {
        return magazineAdminService.queryCategory(id);
    }

    /**
     * 查询所有类别
     *
     * @return
     */
    @PostMapping("/queryAllCategory")
    public RespEntity queryAllCategory(@RequestBody Category category) {
        return magazineAdminService.queryAllCategory(category);
    }

    /**
     * 新增订单
     *
     * @return
     */
    @PostMapping("/addOrder")
    @AutoIdempotent
    public RespEntity addOrder(@RequestBody OrdersPackage ordersPackage) {
        return magazineAdminService.addOrder(ordersPackage);
    }

    /**
     * 删除订单
     *
     * @param id 订单id
     * @return
     */
    @DeleteMapping("/deleteOrder/{id}")
    public RespEntity deleteOrder(@PathVariable Integer id) {
        return magazineAdminService.deleteOrder(id);
    }

    /**
     * 修改订单
     *
     * @param ordersPackage
     * @return
     */
    @PutMapping("/updateOrder")
    public RespEntity updateOrder(@RequestBody OrdersPackage ordersPackage) {
        return magazineAdminService.updateOrder(ordersPackage);
    }

    /**
     * 查询订单详情
     *
     * @param id 类别id
     * @return
     */
    @GetMapping("/queryOrder/{id}")
    public RespEntity queryOrder(@PathVariable Integer id) {
        return magazineAdminService.queryOrder(id);
    }

    /**
     * 模糊查询所有订单主表信息
     *
     * @return
     */
    @PostMapping("/queryAllOrder")
    public RespEntity queryAllOrder(@RequestBody Orders orders) {
        return magazineAdminService.queryAllOrder(orders);
    }

    /**
     * 发送更新邮件
     *
     * @param updateMailPackage
     * @return
     */
    @PostMapping("/sendUpdateMail")
    public RespEntity sendUpdateMail(@RequestBody UpdateMailPackage updateMailPackage) {
        return magazineAdminService.sendUpdateMail(updateMailPackage);
    }

    /**
     * 发送通知邮件
     *
     * @param mailPackage
     * @return
     */
    @PostMapping("/notifyAllMail")
    public RespEntity notifyAllMail(@RequestBody MailPackage mailPackage) {
        return magazineAdminService.notifyAllMail(mailPackage);
    }

}
