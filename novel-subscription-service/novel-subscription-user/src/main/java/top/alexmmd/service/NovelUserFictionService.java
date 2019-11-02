package top.alexmmd.service;

import top.alexmmd.domain.NovelUserFiction;

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
    NovelUserFiction add(Long novelId, Long userId);

    /**
     * 查询该用户订阅的所有小说
     *
     * @param id user_id
     * @return
     */
    List<NovelUserFiction> selectNovelUserFiction(Long id);
}
