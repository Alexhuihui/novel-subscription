package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alexmmd.client.FictionClient;
import top.alexmmd.repository.NovelUserDao;
import top.alexmmd.repository.NovelUserFictionDao;
import top.alexmmd.domain.*;
import top.alexmmd.service.NovelUserFictionService;

import java.util.List;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class NovelUserFictionServiceImpl implements NovelUserFictionService {

    @Autowired
    private FictionClient fictionClient;

    @Autowired
    private NovelUserDao novelUserDao;

    @Autowired
    private NovelUserFictionDao novelUserFictionDao;

    /**
     * 增加订阅
     *
     * @param novelId
     * @param userId
     * @return
     */
    @Override
    public RespEntity add(Long novelId, Long userId) {

        // 根据 novelId 调用笔趣阁API查询小说的详细信息
        Fiction fiction = fictionClient.findFiction(novelId);
        log.info("<novel-subscription-user>: select fiction -> {}", fiction.toString());

        // 构造 novel_info 表中的数据信息
        NovelInfo novelInfo = new NovelInfo();
        novelInfo.setNovelAuthor(fiction.getAuthor());
        novelInfo.setNovelIcon(fiction.getImgUrl());
        novelInfo.setNovelIntro(fiction.getNovelDesc());
        novelInfo.setNovelName(fiction.getNovelName());
        novelInfo.setNovelType(fiction.getCategory());
        novelInfo.setNovelUrl(fiction.getNovelUrl());
        novelInfo.setId(novelId);
        novelInfo.setNovelStatus(0);

        // 调用 fictionClient 尝试向数据库 novel_info 表中增加一条记录
        log.info("<novel-subscription-user>: insert into novel_info -> {}", novelInfo.toString());

        fictionClient.addNovel(novelInfo);

        // 根据 userId 查询读者的相关信息
        NovelUser novelUser = novelUserDao.queryById(userId);
        log.info("<novel-subscription-user>: select user -> {}", novelUser.toString());

        // 构造 novel_user_fiction 表的对应的实体类的数据
        NovelUserFiction novelUserFiction = new NovelUserFiction();
        novelUserFiction.setNovelId(novelId);
        novelUserFiction.setEmail(novelUser.getEmail());
        novelUserFiction.setNovelName(fiction.getNovelName());
        novelUserFiction.setUsername(novelUser.getUsername());
        novelUserFiction.setUserId(userId);

        // 向 novel_user_fiction 表插入一条数据
        log.info("<novel-subscription-user>: insert into novel_user_fiction -> {}", novelUserFiction.toString());

        return novelUserFictionDao.insert(novelUserFiction) > 0 ? new RespEntity(101, "成功新增订阅") : new RespEntity(500, "新增订阅失败");
    }

    /**
     * 查询该用户订阅的所有小说
     *
     * @param id user_id
     * @return
     */
    @Override
    public RespEntity selectNovelUserFiction(Long id) {
        List<NovelUserFiction> novelUserFictions = novelUserFictionDao.queryAll(NovelUserFiction.builder()
                .userId(id)
                .build());
        return new RespEntity(100, "成功查询该用户订阅的所有小说", novelUserFictions);
    }

    /**
     * 新增用户
     *
     * @param novelUser
     * @return
     */
    @Override
    public RespEntity insert(NovelUser novelUser) {
        novelUserDao.insert(novelUser);
        return new RespEntity(101, "成功新增用户");
    }
}
