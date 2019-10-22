package top.alexmmd.client;

import org.springframework.stereotype.Component;
import top.alexmmd.domain.Fiction;
import top.alexmmd.domain.NovelInfo;

import java.util.Collections;
import java.util.List;

/**
 * 服务熔断降级策略
 *
 * @author 汪永晖
 */
@Component
public class FictionClientHystrix implements FictionClient {

    /**
     * 笔趣阁根据novel_id来获取图书详情
     *
     * @param id novel_id
     * @return 详细信息
     */
    @Override
    public Fiction findFiction(Long id) {
        return new Fiction();
    }

    /**
     * 从笔趣阁根据关键字来查询图书详情
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Fiction> getFictions(String keyword) {
        return Collections.emptyList();
    }

    /**
     * 从数据库中根据 novel_id 来获取图书详情
     *
     * @param id novel_id
     * @return 小说详细信息
     */
    @Override
    public NovelInfo findNovel(Long id) {
        return new NovelInfo();
    }

    /**
     * 创建一条新的小说信息
     *
     * @param novelInfo 详细信息
     * @return 创建好的小说或者之前存在的小说
     */
    @Override
    public NovelInfo addNovel(NovelInfo novelInfo) {
        return new NovelInfo();
    }

    /**
     * 根据 novel_id 删除小说
     *
     * @param id novel_id
     * @return 空的 novel_info
     */
    @Override
    public NovelInfo deleteNovel(Long id) {
        return new NovelInfo();
    }
}
