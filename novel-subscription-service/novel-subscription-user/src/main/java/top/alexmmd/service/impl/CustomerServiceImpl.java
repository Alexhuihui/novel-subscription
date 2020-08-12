package top.alexmmd.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.alexmmd.client.FictionClient;
import top.alexmmd.domain.*;
import top.alexmmd.repository.NovelUserDao;
import top.alexmmd.repository.NovelUserFictionDao;
import top.alexmmd.service.CustomerService;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final FictionClient fictionClient;

    private final NovelUserDao novelUserDao;

    private final NovelUserFictionDao novelUserFictionDao;

    public CustomerServiceImpl(NovelUserFictionDao novelUserFictionDao, NovelUserDao novelUserDao, FictionClient fictionClient) {
        this.novelUserFictionDao = novelUserFictionDao;
        this.novelUserDao = novelUserDao;
        this.fictionClient = fictionClient;
    }

    /**
     * 用户订阅小说
     *
     * @param novelId
     * @return
     */
    @Override
    public RespEntity subscription(Long novelId) {
        // 获取当前登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        NovelUser novelUser = novelUserDao.findByUsername(username);

        if (isSubscription(username, novelId)) {
            return new RespEntity(500, "不能重复订阅该小说");
        }

        // 根据 novelId 调用笔趣阁API查询小说的详细信息
        Fiction fiction = fictionClient.findFiction(novelId);

        // 调用 fictionClient 尝试向数据库 novel_info 表中增加一条记录
        NovelInfo novelInfo = new NovelInfo();
        novelInfo.setNovelAuthor(fiction.getAuthor());
        novelInfo.setNovelIcon(fiction.getImgUrl());
        novelInfo.setNovelIntro(fiction.getNovelDesc());
        novelInfo.setNovelName(fiction.getNovelName());
        novelInfo.setNovelType(fiction.getCategory());
        novelInfo.setNovelUrl(fiction.getNovelUrl());
        novelInfo.setId(novelId);
        novelInfo.setNovelStatus(0);
        log.info("<novel-subscription-user>: insert into novel_info -> {}", novelInfo.toString());
        fictionClient.addNovel(novelInfo);

        // 构造 novel_user_fiction 表的对应的实体类的数据
        NovelUserFiction novelUserFiction = new NovelUserFiction();
        novelUserFiction.setNovelId(novelId);
        novelUserFiction.setEmail(novelUser.getEmail());
        novelUserFiction.setNovelName(fiction.getNovelName());
        novelUserFiction.setUsername(novelUser.getUsername());
        novelUserFiction.setUserId(novelUser.getId());
        novelUserFiction.setCreateTime(new Date());
        novelUserFiction.setUpdateTime(new Date());

        // 向 novel_user_fiction 表插入一条数据
        log.info("<novel-subscription-user>: insert into novel_user_fiction -> {}", novelUserFiction.toString());

        return novelUserFictionDao.insert(novelUserFiction) > 0 ? new RespEntity(101, "成功新增订阅") : new RespEntity(500, "新增订阅失败");
    }

    /**
     * 查询所有订阅小说
     *
     * @return
     */
    @Override
    public RespEntity queryAllSubscription() {
        // 获取当前登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 根据username去查询该用户订阅的所有小说
        List<NovelUserFictionVo> novelUserFictionVos = novelUserFictionDao.queryAllSubscription(username);
        return new RespEntity(100, "成功查询所有订阅", novelUserFictionVos);
    }

    /**
     * 取消订阅
     *
     * @param novelId
     * @return
     */
    @Override
    public RespEntity delete(Long novelId) {
        // 获取当前登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (!isSubscription(username, novelId)) {
            return new RespEntity(500, "还未订阅该小说");
        }
        return novelUserFictionDao.deleteById(novelId, username) > 0 ? new RespEntity(101, "成功取消订阅") : new RespEntity(500, "取消订阅失败");
    }

    /**
     * 检查用户是否已经订阅该小说
     *
     * @param novelId
     * @return
     */
    @Override
    public RespEntity checkSubscription(Long novelId) {
        // 获取当前登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return isSubscription(username, novelId) ? new RespEntity(100, "用户已经订阅该小说", 1) : new RespEntity(100, "用户还没有订阅该小说", 0);
    }

    /**
     * 判断该用户是否已经订阅该小说
     *
     * @param username
     * @param novelId
     * @return true -> 已订阅 ； false -> 没有订阅
     */
    private boolean isSubscription(String username, Long novelId) {
        List<NovelUserFiction> novelUserFictionList = novelUserFictionDao.queryAll(NovelUserFiction.builder()
                .username(username)
                .build());
        List<Long> novelIds = novelUserFictionList.stream()
                .map(NovelUserFiction::getNovelId)
                .collect(toList());
        return novelIds.contains(novelId);
    }

}
