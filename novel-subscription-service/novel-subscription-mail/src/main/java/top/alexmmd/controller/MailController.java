package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alexmmd.domain.CommonReply;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.service.MailService;

/**
 * @author 汪永晖
 */
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送纯文本邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @PostMapping("/sendText")
    public CommonReply sendTextMail(@RequestBody MailInfo mailInfo) {

        mailService.sendTextMail(mailInfo.getToAddr(), mailInfo.getTitle(), mailInfo.getContent());

        return new CommonReply("200", "Mail has been send successful.");
    }
}
