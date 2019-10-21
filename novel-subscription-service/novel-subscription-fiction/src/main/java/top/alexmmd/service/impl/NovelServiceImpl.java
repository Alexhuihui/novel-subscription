package top.alexmmd.service.impl;

import org.springframework.stereotype.Service;
import top.alexmmd.domain.NovelInfo;
import top.alexmmd.service.NovelService;

/**
 * @author 汪永晖
 */
@Service
public class NovelServiceImpl implements NovelService {

    /**
     * 根据 ID 查找对应的小说信息
     * @param id
     * @return
     */
    @Override
    public NovelInfo findNovelById(Long id) {
        return null;
    }
}
