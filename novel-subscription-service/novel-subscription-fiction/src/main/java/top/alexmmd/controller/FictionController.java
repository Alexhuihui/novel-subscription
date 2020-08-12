package top.alexmmd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.Fiction;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.service.FictionService;

import java.util.List;

/**
 * 根据笔趣阁查询小说信息
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/fiction")
public class FictionController {

    private Logger logger = LoggerFactory.getLogger(FictionController.class);

    private final FictionService fictionService;

    public FictionController(FictionService fictionService) {
        this.fictionService = fictionService;
    }

    /**
     * 根据novel_id来获取图书详情
     *
     * @param id novel_id
     * @return 小说详细信息
     */
    @GetMapping("/{id}")
    public Fiction findFiction(@PathVariable("id") Long id) {
        logger.info("要查找的novel_id是" + id);

        Fiction fiction = fictionService.findFictionById(id);

        logger.info("要查找的小说信息是" + fiction.toString());

        return fiction;
    }

    /**
     * 根据novel_id来获取图书详情(返回一个公用对象给前端调用)
     *
     * @param id novel_id
     * @return 小说详细信息
     */
    @GetMapping("/findFictionByFront/{id}")
    public Fiction findFictionByFront(@PathVariable("id") Long id) {
        logger.info("要查找的novel_id是" + id);

        Fiction fiction = fictionService.findFictionById(id);

        logger.info("要查找的小说信息是" + fiction.toString());

        return fiction;
    }

    /**
     * 根据关键字搜索图书
     *
     * @param keyword 关键字
     * @return 图书列表
     */
    @GetMapping("/search")
    public RespEntity getFictions(@RequestParam("keyword") String keyword) {
        logger.info("按照" + keyword + "进行搜索");

        List<Fiction> fictions = fictionService.searchByKeyword(keyword);

        return new RespEntity(100, "成功返回搜索结果", fictions);
    }

    /**
     * 查询最近订阅（热榜）
     *
     * @return 图书列表
     */
    @GetMapping("/hotSubscription")
    public RespEntity hotSubscription() {
        return fictionService.hotSubscription();
    }

    /**
     * 获取图书章节列表
     *
     * @param id novel_id
     * @return 图书列表
     */
    @GetMapping("/getChapterList/{id}")
    public RespEntity getChapterList(@PathVariable("id") Long id) {
        logger.info("获取" + id + "图书章节列表");
        return fictionService.getChapterList(id);
    }

    /**
     * 获取章节具体内容
     *
     * @return 图书列表
     */
    @GetMapping("/getChapterContent")
    public RespEntity getChapterContent(@RequestParam("novelId") Long novelId, @RequestParam("chapterId") Long chapterId) {
        return fictionService.getChapterContent(novelId, chapterId);
    }

}
