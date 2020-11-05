package top.alexmmd.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.alexmmd.annotation.AutoIdempotent;
import top.alexmmd.exception.IdempotentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author 汪永晖
 */
@Slf4j
public class IdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${idempotent.timeout}")
    private long timeout;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        Annotation autoIdempotent = method.getAnnotation(AutoIdempotent.class);

        if (autoIdempotent != null) {
            // 获取当前登录用户信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            String methodName = method.getDeclaringClass().getName() + "." + method.getName();
            log.info("当前登录人是{}, 访问的方法名是{}", username, methodName);
            String key = "Idempotent" + username + methodName;

            if (stringRedisTemplate.hasKey(key)) {
                throw new IdempotentException("不要重复请求");
            } else {
                // 把key存入redis中
                log.info("把 key -> {}，存入 redis 中", key);
                stringRedisTemplate.opsForValue().set(key, "1", timeout, TimeUnit.SECONDS);
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
