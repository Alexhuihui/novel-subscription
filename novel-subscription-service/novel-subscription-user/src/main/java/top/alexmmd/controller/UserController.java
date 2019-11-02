package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.client.FictionClient;
import top.alexmmd.client.MailClient;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.NovelUserFiction;
import top.alexmmd.domain.SubscriptionInfo;
import top.alexmmd.repository.NovelUserRepository;
import top.alexmmd.service.NovelUserFictionService;

import java.util.List;

/**
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
    private NovelUserRepository novelUserRepository;

    @Autowired
    private NovelUserFictionService novelUserFictionService;

    /**
     * 测试用
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
//        log.info(mailClient.sendTextMail(new MailInfo("2930807240@qq.com", "Test SendText Mail", "This is a test mail, don't need reply")).toString());
        log.info(fictionClient.findNovel(36681L).toString());
        log.info(fictionClient.findFiction(34197L).toString());
        log.info(fictionClient.getFictions("斗罗大陆").toString());
        return "ok";
    }

    /**
     * 注册用户
     *
     * @param novelUser 用户信息
     * @return 用户信息
     */
    @PostMapping
    public NovelUser addUser(@RequestBody NovelUser novelUser) {
        return novelUserRepository.save(novelUser);
    }


    /**
     * 添加订阅
     *
     * @return 订阅详细信息
     */
    @PostMapping("/novelUserFiction")
    public NovelUserFiction add(@RequestBody SubscriptionInfo subscriptionInfo) {
        return novelUserFictionService.add(subscriptionInfo.getNovelId(), subscriptionInfo.getUserId());
    }

    /**
     * 查询该用户订阅的所有小说
     *
     * @param id user_id
     * @return
     */
    @GetMapping("/{id}")
    public List<NovelUserFiction> selectNovelUserFiction(@PathVariable("id") Long id) {
        return novelUserFictionService.selectNovelUserFiction(id);
    }
}
