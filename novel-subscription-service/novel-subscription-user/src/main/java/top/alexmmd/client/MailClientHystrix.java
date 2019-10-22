package top.alexmmd.client;

import org.springframework.stereotype.Component;
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
    public void sendTextMail(MailInfo mailInfo) {

    }
}
