package top.alexmmd.service;

import top.alexmmd.domain.RespEntity;

/**
 * @author 汪永晖
 */
public interface CustomerService {

    /**
     * 用户订阅小说
     *
     * @param novelId
     * @return
     */
    RespEntity subscription(Long novelId);

    /**
     * 查询所有订阅小说
     *
     * @return
     */
    RespEntity queryAllSubscription();

    /**
     * 取消订阅
     *
     * @param novelId
     * @return
     */
    RespEntity delete(Long novelId);

    /**
     * 检查用户是否已经订阅该小说
     *
     * @param novelId
     * @return
     */
    RespEntity checkSubscription(Long novelId);
}
