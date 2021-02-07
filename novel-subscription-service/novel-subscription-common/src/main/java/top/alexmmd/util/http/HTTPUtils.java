package top.alexmmd.util.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * @author 汪永晖
 */
public class HTTPUtils {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * 发起不带参数的get请求
     *
     * @param url 目标url
     * @return 返回的内容HTML网页
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println("response = " + response);
        System.out.println("response.getStatusLine().getStatusCode() = " + response.getStatusLine().getStatusCode());
        System.out.println("response.getEntity() = " + response.getEntity());
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        return null;
    }

    /**
     * 发起携带参数的get请求
     * 利用uriBuilder.setParameter()把参数添加到url中
     * 然后用doGet()获取返回的网页内容
     *
     * @param url 目标url(除去后面的参数)
     * @param map 参数
     * @return 返回的内容HTML网页
     * @throws Exception
     */
    public static String doGetWithParameter(String url, Map<String, Object> map) throws Exception {

        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        return doGet(uriBuilder.build().toString());
    }
}
