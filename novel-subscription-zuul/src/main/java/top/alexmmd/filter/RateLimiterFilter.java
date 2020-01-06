package top.alexmmd.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import top.alexmmd.exception.RateLimitException;

/**
 * 限流拦截器
 *
 * @author 汪永晖
 */
public class RateLimiterFilter extends ZuulFilter {

    // 每秒创建100个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException();
        }

        return null;
    }
}
