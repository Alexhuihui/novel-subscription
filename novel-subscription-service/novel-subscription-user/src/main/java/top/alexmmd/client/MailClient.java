package top.alexmmd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.alexmmd.domain.CommonReply;
import top.alexmmd.domain.MailInfo;

/**
 * 通过 Feign 访问邮件微服务
 *
 * @author 汪永晖
 */
@FeignClient(value = "novel-subscription-mail",
        fallback = MailClientHystrix.class)
public interface MailClient {

    /**
     * 发送纯文本邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @RequestMapping(value = "/novel-subscription-mail/mail/sendText",
            method = RequestMethod.POST)
    CommonReply sendTextMail(@RequestBody MailInfo mailInfo);
}
