package top.alexmmd.service;

import top.alexmmd.domain.NovelUserFiction;

/**
 * @author 汪永晖
 */
public interface NovelUserFictionService {

    /**
     * 增加订阅
     * @param id
     * @return
     */
    NovelUserFiction add(Long id);
}
