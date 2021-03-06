package top.alexmmd.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.util.CollectionUtils;
import top.alexmmd.client.MailClient;
import top.alexmmd.repository.NovelChapterDao;
import top.alexmmd.repository.NovelChapterDetailDao;
import top.alexmmd.repository.NovelUserFictionDao;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.domain.NovelChapter;
import top.alexmmd.domain.NovelChapterDetail;
import top.alexmmd.domain.NovelUserFiction;

import java.util.List;

/**
 * <h1>消息监听器</h1>
 */
@Slf4j
public class SubscribeListener implements MessageListener {

    @Autowired
    private NovelChapterDao novelChapterDao;

    @Autowired
    private NovelChapterDetailDao novelChapterDetailDao;

    @Autowired
    private NovelUserFictionDao novelUserFictionDao;

    @Autowired
    private MailClient mailClient;

    /**
     * <h2>消息回调</h2>
     * <h3>如果是 ChannelTopic, 则 channel 字段与 pattern 字段值相同</h3>
     *
     * @param message {@link Message} 消息体 + ChannelName
     * @param pattern 订阅的 pattern, ChannelName 的模式匹配
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String body = new String(message.getBody());
        Long novelId = Long.valueOf(body);
        log.info("<novel-subscription-user>: update novel detail -> {}", novelId);

        /**
         * 从数据库中查询该 novel_id 的更新信息
         * */


        // 根据 novel_id 和 chapterStatus = 0 查出更新的章节
        List<NovelChapter> novelChapterList = novelChapterDao.queryAll(NovelChapter.builder()
                .novelId(novelId)
                .chapterStatus(0)
                .build());

        log.info("<novel-subscription-user>: latest novel chapter -> {}", CollectionUtils.lastElement(novelChapterList).toString());

        List<NovelChapterDetail> novelChapterDetailList = null;

        if (!CollectionUtils.isEmpty(novelChapterList)) {
            for (NovelChapter novelChapter : novelChapterList) {
                // 查询更新章节的内容
                novelChapterDetailList = novelChapterDetailDao.queryAll(NovelChapterDetail.builder()
                        .novelId(novelChapter.getNovelId())
                        .chapterId(Long.valueOf(novelChapter.getChapterRealId()))
                        .build());
                // 更新一下 chapterStatus 字段
                novelChapter.setChapterStatus(1);
                novelChapterDao.update(novelChapter);
            }
        } else {
            return;
        }

        log.info("<novel-subscription-user>: latest novel chapter detail -> {}", CollectionUtils.lastElement(novelChapterDetailList).getTitle());


        // 查询订阅了该 novel_id 的读者
        List<NovelUserFiction> novelUserFictionList = novelUserFictionDao.queryAll(NovelUserFiction.builder()
                .novelId(novelId)
                .build());

        log.info("<novel-subscription-user>: subscription novel user -> {}", CollectionUtils.lastElement(novelUserFictionList).toString());

        // 调用 mail 接口发送邮件
        for (NovelUserFiction novelUserFiction : novelUserFictionList) {

            String userName = novelUserFiction.getUsername();
            String novelName = novelUserFiction.getNovelName();
            String email = novelUserFiction.getEmail();

            for (NovelChapterDetail novelChapterDetail : novelChapterDetailList) {

                String chapterTitle = novelChapterDetail.getTitle();

                log.info("<novel-subscription-user>: send email username -> {}, novelName -> {}, chapterTitle -> {}", userName, novelName, chapterTitle);

                MailInfo mailInfo = new MailInfo();
                mailInfo.setContent(novelChapterDetail.getContent());
                mailInfo.setTitle(chapterTitle + "——" + novelName);
                mailInfo.setToAddr(email);

                mailClient.sendTextMail(mailInfo);
            }
        }
    }
}
