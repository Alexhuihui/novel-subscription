package top.alexmmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.alexmmd.annotation.AutoIdempotent;
import top.alexmmd.domain.bag.CustomerPackage;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.bag.MagazinePackage;
import top.alexmmd.service.MagazineAdminService;

/**
 * 杂志后台管理
 *
 * @author 汪永晖
 */
@RestController(value = "/admin/magazine")
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
