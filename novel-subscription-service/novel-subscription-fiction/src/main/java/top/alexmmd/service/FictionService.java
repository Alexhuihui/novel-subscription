package top.alexmmd.service;

import top.alexmmd.domain.Fiction;
import top.alexmmd.domain.RespEntity;

import java.util.List;

/**
 * @author 汪永晖
 */
public interface FictionService {


    Fiction findFictionById(Long id);

    List<Fiction> searchByKeyword(String keyword);

    /**
     * 查询最近订阅（热榜）
     *
     * @return
     */
    RespEntity hotSubscription();

    /**
     * 获取图书章节列表
     *
     * @param id
     * @return
     */
    RespEntity getChapterList(Long id);

    /**
     * 获取章节具体内容
     *
     * @param novelId
     * @param chapterId
     * @return
     */
    RespEntity getChapterContent(Long novelId, Long chapterId);
}
