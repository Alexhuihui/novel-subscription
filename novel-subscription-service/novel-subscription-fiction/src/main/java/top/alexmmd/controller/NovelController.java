package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.NovelInfo;
import top.alexmmd.service.NovelService;

/**
 * 对数据库中对 novel_info 表进行操作
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/novel")
@Slf4j
public class NovelController {

    @Autowired
    private NovelService novelService;

    /**
     * 根据 novel_id 来获取图书详情
     *
     * @param id novel_id
     * @return 小说详细信息
     */
    @GetMapping("/{id}")
    public NovelInfo findNovel(@PathVariable Long id) {
        log.info("<novel-subscription-fiction>: get novel info -> {}", id);

        NovelInfo novelInfo = novelService.findNovelById(id);

        return novelInfo;
    }

    /**
     * 创建一条新的小说信息
     *
     * @param novelInfo 详细信息
     * @return 创建好的小说或者之前存在的小说
     */
    @PostMapping
    public NovelInfo addNovel(@RequestBody NovelInfo novelInfo) {
        log.info("<novel-subscription-fiction>: add novel info -> {}", novelInfo.getNovelName());

        return novelService.addNovel(novelInfo);
    }

    /**
     * 根据 novel_id 删除小说
     *
     * @param id novel_id
     * @return 空的 novel_info
     */
    @DeleteMapping("/{id}")
    public NovelInfo deleteNovel(@PathVariable Long id) {
        log.info("<novel-subscription-fiction>: delete novel info -> {}", id);

        NovelInfo novelInfo = novelService.deleteNovelById(id);

        return novelInfo;
    }
}
