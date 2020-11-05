package top.alexmmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.annotation.AutoIdempotent;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.bag.CustomerPackage;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.bag.MagazinePackage;
import top.alexmmd.service.MagazineAdminService;

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
    @GetMapping("/fuzzySearchCustomer")
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
    public RespEntity addMagazine(@RequestBody MagazinePackage magazinePackage) {
        return magazineAdminService.addMagazine(magazinePackage);
    }

}
