package top.alexmmd.service;

import top.alexmmd.domain.RespEntity;

/**
 * @author 汪永晖
 */
public interface GetMyUserInfoService {

    /**
     * 查询所有角色信息
     *
     * @return
     */
    RespEntity queryRoles();
}
