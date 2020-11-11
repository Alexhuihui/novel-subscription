package top.alexmmd.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.alexmmd.client.MailClient;
import top.alexmmd.domain.CommonReply;
import top.alexmmd.domain.MailInfo;

/**
 * @author 汪永晖
 * @Date 2020/11/11 15:14
 */
@Component
@Slf4j
public class MailUtil {

    @Autowired
    private MailClient mailClient;

    /**
     * 发送纯文本邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @Async("asyncTaskExecutor")
    public CommonReply sendTextMail(MailInfo mailInfo) {
        log.info("{} -> 线程在执行发送邮件的任务，邮件详细信息 -> {}", Thread.currentThread().getName(), mailInfo);
        return mailClient.sendTextMail(mailInfo);
    }

    /**
     * 发送 Html 邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @Async("asyncTaskExecutor")
    public CommonReply sendHtmlMail(MailInfo mailInfo) {
        log.info("{} -> 线程在执行发送邮件的任务，邮件详细信息 -> {}", Thread.currentThread().getName(), mailInfo);
        return mailClient.sendHtmlMail(mailInfo);
    }
}
