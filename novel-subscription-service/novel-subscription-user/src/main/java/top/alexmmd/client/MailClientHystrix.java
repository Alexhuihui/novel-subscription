package top.alexmmd.client;

import org.springframework.stereotype.Component;
import top.alexmmd.domain.CommonReply;
import top.alexmmd.domain.MailInfo;

/**
 * @author 汪永晖
 */
@Component
public class MailClientHystrix implements MailClient {

    /**
     * 发送纯文本邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @Override
    public CommonReply sendTextMail(MailInfo mailInfo) {
        System.out.println("test1");
        return new CommonReply("500", "服务降级策略生效");
    }

    /**
     * 发送 Html 邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @Override
    public CommonReply sendHtmlMail(MailInfo mailInfo) {
        return null;
    }
}
