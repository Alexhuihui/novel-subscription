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
 * 普通用户注册(所有人都能访问)
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/signUp")
@Slf4j
public class SignUpController {

    private final SignUpService signUpService;

    private final NovelUserService novelUserService;

    public SignUpController(SignUpService signUpService, NovelUserService novelUserService) {
        this.signUpService = signUpService;
        this.novelUserService = novelUserService;
    }

    /**
     * 注册新用户
     *
     * @param user
     */
    @PostMapping("/record")
    public RespEntity signUp(@RequestBody @Validated NovelUser user) {
        return signUpService.signUp(user);
    }

}
