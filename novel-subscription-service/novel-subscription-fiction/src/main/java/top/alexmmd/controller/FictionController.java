package top.alexmmd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.Fiction;
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

    @Autowired
    private FictionService fictionService;

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
     * 根据关键字搜索图书
     * @param keyword 关键字
     * @return 图书列表
     */
    @GetMapping("/search")
    public List<Fiction> getFictions(@RequestParam("keyword") String keyword) {
        logger.info("按照" + keyword + "进行搜索");

        List<Fiction> fictions = fictionService.searchByKeyword(keyword);

        return fictions;
    }
}
