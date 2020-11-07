package top.alexmmd.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.alexmmd.domain.RespEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 统一异常拦截
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(value = Exception.class)
    public RespEntity defaultErrorHandler(HttpServletRequest req, Exception e) {
        return new RespEntity(500, e.getMessage());
    }

    /**
     * GET/POST请求方法错误的拦截器
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RespEntity httpRequestMethodHandler(HttpRequestMethodNotSupportedException e) {
        return new RespEntity(500, e.getMessage());
    }


    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        String defaultMessage = null;
        if (e.getBindingResult() != null && e.getBindingResult().getFieldError() != null) {
            defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return new RespEntity(500, defaultMessage);
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public RespEntity handleValidationException(ValidationException e) {
        logger.error(e.getMessage(), e);
        String message = null;
        if (e.getCause() != null) {
            message = e.getCause().getMessage();
        }
        return new RespEntity(500, message);
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RespEntity handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        String message = null;
        if (e.getCause() != null) {
            message = e.getCause().getMessage();
        }
        return new RespEntity(500, message);
    }

    /**
     * 捕获幂等性异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IdempotentException.class)
    public RespEntity idempotentException(IdempotentException e) {
        return new RespEntity(500, e.getMessage());
    }
}
