package top.alexmmd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.Fiction;
import top.alexmmd.service.FictionService;
import top.alexmmd.spider.FictionParse;
import top.alexmmd.util.http.HTTPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 汪永晖
 */
@Service
public class FictionServiceImpl implements FictionService {

    private Logger logger = LoggerFactory.getLogger(FictionServiceImpl.class);

    private final String URL_ID = "https://www.biquge.com.cn/book/";

    private final String URL_SEARCH = "https://www.biquge.com.cn/search.php";

    @Override
    public Fiction findFictionById(Long id) {

        //拼装URL，加上ID
        String requestUrl = URL_ID + id.toString();

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
        map.put("keyword", keyword);
        try {
            html = HTTPUtils.doGetWithParameter(URL_SEARCH, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把html交给parse去解析
        List<Fiction> fictions = FictionParse.parseSearch(html);

        return fictions;
    }
}
