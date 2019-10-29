package top.alexmmd.service;

import top.alexmmd.domain.NovelUserFiction;

/**
 * @author 汪永晖
 */
public interface NovelUserFictionService {

    /**
     * 增加订阅
     * @param novelId
     * @param userId
     * @return
     */
    NovelUserFiction add(Long novelId, Long userId);
}
