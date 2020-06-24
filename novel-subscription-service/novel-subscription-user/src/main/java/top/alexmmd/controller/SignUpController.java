package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.service.NovelUserService;
import top.alexmmd.service.SignUpService;

/**
 * @author 汪永晖
 */
@RestController
@RequestMapping("/signUp")
@Slf4j
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private NovelUserService novelUserService;

    /**
     * 注册新用户
     *
     * @param user
     */
    @PostMapping("/record")
    public RespEntity signUp(@RequestBody @Validated NovelUser user) {
        if (usernameExist(user.getUsername())) {
            return new RespEntity(500, "该用户名已经被使用");
        }
        return signUpService.signUp(user);
    }

    /**
     * 判断用户名是否被使用
     *
     * @param userName
     * @return true -> exist ; false -> notExist
     */
    private boolean usernameExist(String userName) {
        if (null == novelUserService.findByUsername(userName)) {
            return false;
        }
        return true;
    }

}
