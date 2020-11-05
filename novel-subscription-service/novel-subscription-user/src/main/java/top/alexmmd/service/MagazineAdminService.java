package top.alexmmd.service;

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
     * 新增杂志
     *
     * @param magazinePackage 杂志信息
     * @return
     */
    RespEntity addMagazine(MagazinePackage magazinePackage);
}
