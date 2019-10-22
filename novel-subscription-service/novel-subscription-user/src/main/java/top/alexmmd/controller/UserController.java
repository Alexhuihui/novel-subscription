package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alexmmd.client.FictionClient;
import top.alexmmd.client.MailClient;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.domain.NovelInfo;

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

    @GetMapping("/test")
    public String test() {
        log.info(mailClient.sendTextMail(new MailInfo("2930807240@qq.com", "Test SendText Mail", "This is a test mail, don't need reply")).toString());
//        log.info(fictionClient.findNovel(36681L).toString());
//        log.info(fictionClient.addNovel(new NovelInfo(36681)).toString());
        return "ok";
    }
}
