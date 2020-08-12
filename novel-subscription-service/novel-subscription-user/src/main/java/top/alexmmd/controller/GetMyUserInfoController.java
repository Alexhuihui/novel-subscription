package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.service.GetMyUserInfoService;

/**
 * 得到用户的角色信息（登录后调用）
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/getMyUserInfo")
@Slf4j
public class GetMyUserInfoController {

    private final GetMyUserInfoService getMyUserInfoService;

    public GetMyUserInfoController(GetMyUserInfoService getMyUserInfoService) {
        this.getMyUserInfoService = getMyUserInfoService;
    }

    /**
     * 查询用户的角色信息
     *
     * @return
     */
    @GetMapping("/roles")
    public RespEntity queryRoles() {
        return getMyUserInfoService.queryRoles();
    }
}
