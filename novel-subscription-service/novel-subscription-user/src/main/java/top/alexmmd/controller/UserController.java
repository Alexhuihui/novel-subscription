package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.client.FictionClient;
import top.alexmmd.client.MailClient;
import top.alexmmd.domain.*;
import top.alexmmd.service.NovelUserFictionService;

import java.util.List;

/**
 * 管理员功能（需要ADMIN角色才能访问）
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private FictionClient fictionClient;

    @Autowired
    private NovelUserFictionService novelUserFictionService;

    /**
     * 新增用户
     *
     * @param novelUser 用户信息
     * @return 用户信息
     */
    @PostMapping
    public RespEntity addUser(@RequestBody NovelUser novelUser) {
        return novelUserFictionService.insert(novelUser);
    }


    /**
     * 添加订阅
     *
     * @return 订阅详细信息
     */
    @PostMapping("/novelUserFiction")
    public RespEntity add(@RequestBody SubscriptionInfo subscriptionInfo) {
        return novelUserFictionService.add(subscriptionInfo.getNovelId(), subscriptionInfo.getUserId());
    }

    /**
     * 查询该用户订阅的所有小说
     *
     * @param id user_id
     * @return
     */
    @GetMapping("/{id}")
    public RespEntity selectNovelUserFiction(@PathVariable("id") Long id) {
        return novelUserFictionService.selectNovelUserFiction(id);
    }


}
