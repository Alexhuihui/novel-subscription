package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.service.CustomerService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户订阅等功能（ADMIN 和 USER角色都能访问）
 *
 * @author 汪永晖
 */
@RestController
@RequestMapping("/customer")
@Slf4j
@Valid
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 用户订阅小说
     *
     * @param novelId
     * @return
     */
    @PostMapping("/subscription/{novelId}")
    public RespEntity subscription(@PathVariable @NotNull Long novelId) {
        return customerService.subscription(novelId);
    }

    /**
     * 查询所有订阅小说
     *
     * @return
     */
    @GetMapping("/subscription")
    public RespEntity queryAllSubscription() {
        return customerService.queryAllSubscription();
    }

    /**
     * 取消订阅
     *
     * @param novelId
     * @return
     */
    @DeleteMapping("/subscription/{novelId}")
    public RespEntity delete(@PathVariable @NotNull Long novelId) {
        return customerService.delete(novelId);
    }

    /**
     * 检查用户是否已经订阅该小说
     *
     * @param novelId
     * @return
     */
    @PostMapping("/isSubscription/{novelId}")
    public RespEntity checkSubscription(@PathVariable @NotNull Long novelId) {
        return customerService.checkSubscription(novelId);
    }
}
