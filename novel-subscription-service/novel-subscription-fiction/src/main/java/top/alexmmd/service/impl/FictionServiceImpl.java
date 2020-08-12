package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.ChapterList;
import top.alexmmd.domain.Fiction;
import top.alexmmd.domain.NovelInfo;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.repository.NovelInfoRepository;
import top.alexmmd.service.FictionService;
import top.alexmmd.spider.FictionParse;
import top.alexmmd.util.http.HTTPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class FictionServiceImpl implements FictionService {

    private final String URL_ID = "https://www.biquge.com.cn/book/";

    private final String URL_SEARCH = "https://www.biquge.com.cn/search.php";

    //注入当前对象的代理对象
    @Autowired
    private FictionServiceImpl _this;

    private final NovelInfoRepository novelInfoRepository;

    public FictionServiceImpl(NovelInfoRepository novelInfoRepository) {
        this.novelInfoRepository = novelInfoRepository;
    }

    @Value("${hot.fiction.subscription.size}")
    private Integer size;

    @Override
    public Fiction findFictionById(Long id) {

        //拼装URL，加上ID
        String requestUrl = URL_ID + id.toString() + "/";

        //调用HTTPUtils去获取HTML页面
        String html = null;
        try {
            html = HTTPUtils.doGet(requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把html交给parse去解析
        Fiction fiction = FictionParse.parseFiction(html);

        fiction.setId(id);

        return fiction;
    }

    @Override
    public List<Fiction> searchByKeyword(String keyword) {

        //调用HTTPUtils去获取HTML页面
        String html = null;
        Map<String, Object> map = new HashMap<>();
        map.put("q", keyword);
        try {
            html = HTTPUtils.doGetWithParameter(URL_SEARCH, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把html交给parse去解析
        List<Fiction> fictions = FictionParse.parseSearch(html);

        return fictions;
    }

    /**
     * 查询最近订阅（热榜）
     *
     * @return
     */
    @Override
    public RespEntity hotSubscription() {
        Pageable pageable = PageRequest.of(0, size, Sort.by("updateTime").descending());
        Page<NovelInfo> novelInfoPage = novelInfoRepository.findAll(pageable);
        List<NovelInfo> novelInfoList = novelInfoPage.getContent();
        log.info("查询最近订阅（热榜） -> {}", novelInfoList);
        return new RespEntity(100, "成功查询最近订阅（热榜）", novelInfoList);
    }

    /**
     * 获取图书章节列表
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "chapterList", key = "#id")
    public RespEntity getChapterList(Long id) {
        log.info("获取图书 -> {} 章节列表", id);
        //拼装URL，加上ID
        String requestUrl = URL_ID + id.toString() + "/";

        //调用HTTPUtils去获取HTML页面
        String html = null;
        try {
            html = HTTPUtils.doGet(requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把html交给parse去解析
        List<ChapterList> chapterLists = FictionParse.parseChapterList(html);
        return new RespEntity(100, "成功查询章节目录", chapterLists);
    }

    /**
     * 获取章节具体内容
     *
     * @param novelId
     * @param chapterId
     * @return
     */
    @Override
    public RespEntity getChapterContent(Long novelId, Long chapterId) {
        // 查询所有章节目录
        List<ChapterList> chapterLists = (List<ChapterList>) _this.getChapterList(novelId).getData();

        // 查询缓存的章节内容
        ChapterList chapterList = _this.cacheChapterContent(novelId, chapterId);
        if (chapterList == null) {
            return new RespEntity(500, "暂时没有此章节内容");
        }
        int count = chapterLists.indexOf(chapterList);
        if ((count - 1) >= 0) {
            ChapterList last = chapterLists.get(count - 1);
            chapterList.setLast(last);
        }
        if ((count + 1) < chapterLists.size()) {
            ChapterList next = chapterLists.get(count + 1);
            chapterList.setNext(next);
        }

        return new RespEntity(100, "成功查询章节具体内容", chapterList);
    }

    /**
     * 缓存获取的章节具体内容
     *
     * @param novelId
     * @param chapterId
     * @return
     */
    @Cacheable(value = "chapterContent", key = "#novelId + '::' + #chapterId")
    public ChapterList cacheChapterContent(Long novelId, Long chapterId) {
        log.info("获取图书 -> {} 的章节 -> {} 的具体内容", novelId, chapterId);
        // 查询所有章节目录
        List<ChapterList> chapterLists = (List<ChapterList>) _this.getChapterList(novelId).getData();
        List<ChapterList> chapters = chapterLists.stream()
                .filter(c -> {
                    if (chapterId.equals(c.getChapterId()) && novelId.equals(c.getFictionId())) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        if (chapters != null && chapters.isEmpty()) {
            return null;
        }
        ChapterList chapterList = chapters.get(0);

        //拼装URL，加上ID
        String requestUrl = URL_ID + novelId.toString() + "/" + chapterId.toString() + ".html";

        //调用HTTPUtils去获取HTML页面
        String html = null;
        try {
            html = HTTPUtils.doGet(requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把html交给parse去解析
        String chapterContent = FictionParse.parseChapterContent(html);
        chapterList.setChapterContent(chapterContent);
        return chapterList;
    }
}
