package top.alexmmd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.Fiction;
import top.alexmmd.domain.NovelInfo;

import java.util.List;

/**
 * 通过 Feign 访问小说微服务
 *
 * @author 汪永晖
 */
@FeignClient(value = "novel-subscription-fiction",
        fallback = FictionClientHystrix.class)
public interface FictionClient {

    /**
     * 从笔趣阁根据novel_id来获取图书详情
     *
     * @param id novel_id
     * @return 详细信息
     */
    @RequestMapping(value = "/novel-subscription-fiction/fiction/{id}",
            method = RequestMethod.GET)
    Fiction findFiction(@PathVariable("id") Long id);

    /**
     * 从笔趣阁根据关键字来查询图书详情
     *
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/novel-subscription-fiction/fiction/search",
            method = RequestMethod.GET)
    List<Fiction> getFictions(@RequestParam("keyword") String keyword);

    /**
     * 从数据库中根据 novel_id 来获取图书详情
     *
     * @param id novel_id
     * @return 小说详细信息
     */
    @RequestMapping(value = "/novel-subscription-fiction/novel/{id}",
            method = RequestMethod.GET)
    NovelInfo findNovel(@PathVariable("id") Long id);

    /**
     * 创建一条新的小说信息
     *
     * @param novelInfo 详细信息
     * @return 创建好的小说或者之前存在的小说
     */
    @RequestMapping(value = "/novel-subscription-fiction/novel",
            method = RequestMethod.POST)
    NovelInfo addNovel(@RequestBody NovelInfo novelInfo);

    /**
     * 根据 novel_id 删除小说
     *
     * @param id novel_id
     * @return 空的 novel_info
     */
    @RequestMapping(value = "/novel-subscription-fiction/novel/{id}",
            method = RequestMethod.DELETE)
    NovelInfo deleteNovel(@PathVariable("id") Long id);
}
