package top.alexmmd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.NovelInfo;
import top.alexmmd.repository.NovelInfoRepository;
import top.alexmmd.service.NovelService;

import java.util.Optional;

/**
 * @author 汪永晖
 */
@Service
public class NovelServiceImpl implements NovelService {

    @Autowired
    private NovelInfoRepository novelInfoRepository;

    /**
     * 根据 ID 查找对应的小说信息
     *
     * @param id novel_id
     * @return NovelInfo
     */
    @Override
    public NovelInfo findNovelById(Long id) {
        return novelInfoRepository.findById(id).orElse(new NovelInfo());
    }

    /**
     * 新增小说，如果之前存在这本小说，就反回详细信息
     *
     * @param novelInfo 详细信息
     * @return 数据库中的信息
     */
    @Override
    public NovelInfo addNovel(NovelInfo novelInfo) {

        Optional<NovelInfo> oldNovelInfo = novelInfoRepository.findById(novelInfo.getId());

        if (oldNovelInfo.isPresent()) {
            return oldNovelInfo.get();
        }

        return novelInfoRepository.save(novelInfo);
    }

    /**
     * 根据小说 id 删除小说
     *
     * @param id novel_id
     * @return 空的 novel_info
     */
    @Override
    public NovelInfo deleteNovelById(Long id) {
        novelInfoRepository.deleteById(id);
        return new NovelInfo();
    }
}
