package top.alexmmd.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.alexmmd.domain.ChapterList;
import top.alexmmd.domain.Fiction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析HTML页面
 * 把结果包装成Fiction和FictionList返回给Service
 *
 * @author 汪永晖
 */
public class FictionParse {

    private static Logger logger = LoggerFactory.getLogger(FictionParse.class);

    private static final String BASE_URL = "https://www.biquge.com.cn";

    /**
     * 解析笔趣阁搜索结果页面
     *
     * @param html service层传来的html页面
     * @return Fiction的集合
     */
    public static List<Fiction> parseSearch(String html) {

        List<Fiction> fictions = new ArrayList<>();

        Document document = Jsoup.parse(html);

        Elements elements = document.select("div[class=result-item result-game-item]");

        for (Element element : elements) {
            fictions.add(fillFiction(element));
        }

        return fictions;
    }

    public static Fiction fillFiction(Element element) {
        Fiction fiction = new Fiction();

        String imgUrl = element.select("img[class=result-game-item-pic-link-img]").first().attr("src");
        String novelName = element.select("a[class=result-game-item-title-link]").first().attr("title");
        String novelUrl = element.select("a[class=result-game-item-title-link]").first().attr("href");
        String novelDesc = element.select("p[class=result-game-item-desc]").first().text();

        Elements elements = element.select("p[class=result-game-item-info-tag]");
        Element authorElement = elements.first();
        String author = authorElement.select("span").last().text();

        String category = elements.get(1).select("span").last().text();

        String updateTime = elements.get(2).select("span").last().text();

        String latestChapter = elements.get(3).select("a").first().text();

        fiction.setImgUrl(imgUrl);
        fiction.setNovelName(novelName);
        fiction.setNovelUrl(novelUrl);
        fiction.setNovelDesc(novelDesc);
        fiction.setAuthor(author);
        fiction.setCategory(category);
        fiction.setUpdateTime(updateTime);
        fiction.setLatestChapter(latestChapter);

        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(novelUrl);

        Long id = Long.valueOf(m.replaceAll("").trim());
        fiction.setId(id);

        return fiction;
    }

    /**
     * 解析笔趣阁图书详情页面
     *
     * @param html service层传来的html页面
     * @return Fiction
     */
    public static Fiction parseFiction(String html) {

        Fiction fiction = new Fiction();

        Document document = Jsoup.parse(html);

        String author = document.select("meta[property=og:novel:author]").first().attr("content");
        String novelDesc = document.select("meta[property=og:description]").first().attr("content");
        String imgUrl = document.select("meta[property=og:image]").first().attr("content");
        String category = document.select("meta[property=og:novel:category]").first().attr("content");
        String novelName = document.select("meta[property=og:novel:book_name]").first().attr("content");
        String novelUrl = document.select("meta[property=og:novel:read_url]").first().attr("content");
        String updateTime = document.select("meta[property=og:novel:update_time]").first().attr("content");
        String latestChapter = document.select("meta[property=og:novel:latest_chapter_name]").first().attr("content");


        fiction.setAuthor(author);
        fiction.setNovelDesc(novelDesc);
        fiction.setImgUrl(imgUrl);
        fiction.setCategory(category);
        fiction.setNovelName(novelName);
        fiction.setNovelUrl(novelUrl);
        fiction.setUpdateTime(updateTime);
        fiction.setLatestChapter(latestChapter);

        return fiction;
    }

    /**
     * 解析笔趣阁图书详情页面, 获取章节目录
     *
     * @param html service层传来的html页面
     * @return Fiction
     */
    public static List<ChapterList> parseChapterList(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("dd");
        List<ChapterList> chapterLists = new ArrayList<>();
        for (Element element : elements) {
            String chapterName = element.select("a").text();
            String attr = element.select("a").attr("href");
            String chapterUrl = BASE_URL + attr;
            String pattern = "\\d{3,}";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(attr);
            List<Long> groups = new ArrayList<>();
            while (m.find()) {
                groups.add(Long.valueOf(m.group()));
            }
            Long fictionId = groups.get(0);
            Long chapterId = groups.get(1);
            ChapterList chapterList = ChapterList.builder()
                    .chapterId(chapterId)
                    .chapterName(chapterName)
                    .chapterUrl(chapterUrl)
                    .fictionId(fictionId)
                    .build();
            chapterLists.add(chapterList);
        }
        return chapterLists;
    }

    /**
     * 解析章节具体内容
     *
     * @param html
     * @return
     */
    public static String parseChapterContent(String html) {
        Document document = Jsoup.parse(html);
        Element element = document.select("div#content").first();
        return element.html();
    }
}
