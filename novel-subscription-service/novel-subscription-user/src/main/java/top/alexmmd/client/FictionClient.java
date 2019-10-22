package top.alexmmd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.alexmmd.domain.Fiction;

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
    Fiction findFiction(@PathVariable Long id);

    /**
     * 从笔趣阁根据关键字来查询图书详情
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/novel-subscription-fiction/fiction/search",
            method = RequestMethod.GET)
    List<Fiction> getFictions(@RequestParam String keyword);
}
