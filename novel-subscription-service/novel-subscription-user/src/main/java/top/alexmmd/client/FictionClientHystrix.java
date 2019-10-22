package top.alexmmd.client;

import org.springframework.stereotype.Component;
import top.alexmmd.domain.Fiction;

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
}
