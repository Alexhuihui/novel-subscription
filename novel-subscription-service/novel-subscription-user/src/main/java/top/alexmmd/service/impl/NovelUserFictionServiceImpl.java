package top.alexmmd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alexmmd.client.FictionClient;
import top.alexmmd.domain.Fiction;
import top.alexmmd.domain.NovelInfo;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.NovelUserFiction;
import top.alexmmd.repository.NovelUserFictionRepository;
import top.alexmmd.repository.NovelUserRepository;
import top.alexmmd.service.NovelUserFictionService;

/**
 * @author 汪永晖
 */
@Service
public class NovelUserFictionServiceImpl implements NovelUserFictionService {

    @Autowired
    private FictionClient fictionClient;

    @Autowired
    private NovelUserRepository novelUserRepository;

    @Autowired
    private NovelUserFictionRepository novelUserFictionRepository;

    /**
     * 增加订阅
     *
     * @param novelId
     * @param userId
     * @return
     */
    @Override
    public NovelUserFiction add(Long novelId, Long userId) {

        // 根据 novelId 调用笔趣阁API查询小说的详细信息
        Fiction fiction = fictionClient.findFiction(novelId);

        // 构造 novel_info 表中的数据信息
        NovelInfo novelInfo = new NovelInfo();
        novelInfo.setNovelAuthor(fiction.getAuthor());
        novelInfo.setNovelIcon(fiction.getImgUrl());
        novelInfo.setNovelIntro(fiction.getNovelDesc());
        novelInfo.setNovelName(fiction.getNovelName());
        novelInfo.setNovelType(fiction.getCategory());
        novelInfo.setNovelUrl(fiction.getNovelUrl());
        novelInfo.setId(novelId);

        // 调用 fictionClient 尝试向数据库 novel_info 表中增加一条记录
        fictionClient.addNovel(novelInfo);

        // 根据 userId 查询读者的相关信息
        NovelUser novelUser = novelUserRepository.findById(userId).orElse(new NovelUser());

        // 构造 novel_user_fiction 表的对应的实体类的数据
        NovelUserFiction novelUserFiction = new NovelUserFiction();
        novelUserFiction.setNovelId(novelId);
        novelUserFiction.setEmail(novelUser.getEmail());
        novelUserFiction.setNovelName(fiction.getNovelName());
        novelUserFiction.setUsername(novelUser.getUsername());
        novelUserFiction.setUserId(userId);

        // 向 novel_user_fiction 表插入一条数据

        return novelUserFictionRepository.save(novelUserFiction);
    }
}
