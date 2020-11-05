package top.alexmmd.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Set;

/**
 * @author 汪永晖
 */
@Component
@Slf4j
public class ClearRedisCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 每天凌晨3点清除redis缓存的小说章节信息
     */
    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(cron = "0/1 * * * * ?")
    public void clear() {
        log.info("——————————clear start at {} ——————————", new Date());
        String test = stringRedisTemplate.opsForValue().get("chapterContent::45624::342733");
        String pre = "chapter*";
        Set<String> keys = stringRedisTemplate.keys(pre);
        if (!CollectionUtils.isEmpty(keys)) {
            stringRedisTemplate.delete(keys);
        }
        log.info("——————————clear end at {} ——————————", new Date());
    }
}
