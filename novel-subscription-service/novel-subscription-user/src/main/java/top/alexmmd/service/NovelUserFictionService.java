package top.alexmmd.service;

import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.NovelUserFiction;
import top.alexmmd.domain.RespEntity;

import java.util.List;

/**
 * @author 汪永晖
 */
public interface NovelUserFictionService {

    /**
     * 增加订阅
     *
     * @param novelId
     * @param userId
     * @return
     */
    RespEntity add(Long novelId, Long userId);

    /**
     * 查询该用户订阅的所有小说
     *
     * @param id user_id
     * @return
     */
    RespEntity selectNovelUserFiction(Long id);

    /**
     * 新增用户
     *
     * @param novelUser
     * @return
     */
    RespEntity insert(NovelUser novelUser);
}
