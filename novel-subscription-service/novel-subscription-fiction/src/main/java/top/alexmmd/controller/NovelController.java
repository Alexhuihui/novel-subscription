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

    @PostMapping
    public NovelInfo addNovel(@RequestBody NovelInfo novelInfo) {
        log.info("<novel-subscription-fiction>: add novel info -> {}", novelInfo.getNovelName());

        return novelService.addNovel(novelInfo);
    }
}
