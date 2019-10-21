package top.alexmmd.service;

import top.alexmmd.domain.NovelInfo;

/**
 * @author 汪永晖
 */
public interface NovelService {

    NovelInfo findNovelById(Long id);

    NovelInfo addNovel(NovelInfo novelInfo);

    NovelInfo deleteNovelById(Long id);
}
