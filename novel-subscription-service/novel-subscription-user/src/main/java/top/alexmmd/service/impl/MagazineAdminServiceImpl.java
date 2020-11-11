package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.alexmmd.domain.MailEnum;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.bag.CustomerPackage;
import top.alexmmd.domain.bag.ItemsPackage;
import top.alexmmd.domain.bag.OrdersPackage;
import top.alexmmd.domain.bag.UpdateMailPackage;
import top.alexmmd.domain.entity.Category;
import top.alexmmd.domain.entity.Items;
import top.alexmmd.domain.entity.OrderItems;
import top.alexmmd.domain.entity.Orders;
import top.alexmmd.domain.vo.ItemsVo;
import top.alexmmd.domain.vo.OrdersVo;
import top.alexmmd.repository.NovelUserDao;
import top.alexmmd.service.*;
import top.alexmmd.util.MailUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class MagazineAdminServiceImpl implements MagazineAdminService {

    @Value("${admin.customer.password}")
    private String password;

    private final NovelUserDao novelUserDao;

    private final SignUpService signUpService;

    private final ItemsService itemsService;

    private final CategoryService categoryService;

    private final OrdersService ordersService;

    private final OrderItemsService orderItemsService;

    private final MailUtil mailUtil;

    public MagazineAdminServiceImpl(SignUpService signUpService, NovelUserDao novelUserDao, ItemsService itemsService, CategoryService categoryService, OrdersService ordersService, OrderItemsService orderItemsService, MailUtil mailUtil) {
        this.signUpService = signUpService;
        this.novelUserDao = novelUserDao;
        this.itemsService = itemsService;
        this.categoryService = categoryService;
        this.ordersService = ordersService;
        this.orderItemsService = orderItemsService;
        this.mailUtil = mailUtil;
    }

    /**
     * 新增顾客
     *
     * @param customerPackage 顾客信息
     * @return
     */
    @Override
    public RespEntity addNewCustomer(CustomerPackage customerPackage) {
        // 使用后台管理系统注册用户
        return signUpService.signUp(NovelUser.builder()
                .username(customerPackage.getUsername())
                .password(password)
                .email(customerPackage.getEmail())
                .build());
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity deleteCustomer(Integer id) {
        return novelUserDao.deleteById(Long.valueOf(id)) > 0 ? new RespEntity(101, "删除成功") : new RespEntity(500, "删除失败");
    }

    /**
     * 修改用户
     *
     * @param customerPackage
     * @return
     */
    @Override
    public RespEntity updateCustomer(CustomerPackage customerPackage) {
        return novelUserDao.updateByUsername(NovelUser.builder()
                .username(customerPackage.getUsername())
                .email(customerPackage.getEmail())
                .updateTime(new Date())
                .build()) > 0 ? new RespEntity(101, "修改成功") : new RespEntity(500, "修改失败");
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity queryCustomer(Integer id) {
        NovelUser novelUser = novelUserDao.queryById(Long.valueOf(id));
        return new RespEntity(100, "成功查询用户详情", novelUser);
    }

    /**
     * 模糊查询所有用户
     *
     * @param novelUser
     * @return
     */
    @Override
    public RespEntity fuzzySearchCustomer(NovelUser novelUser) {
        List<NovelUser> novelUserList = novelUserDao.queryAll(novelUser);
        return new RespEntity(100, "成功查询所有用户", novelUserList);
    }

    /**
     * 新增杂志
     *
     * @param magazinePackage 杂志信息
     * @return
     */
    @Override
    public RespEntity addMagazine(ItemsPackage magazinePackage) {
        Items items = itemsService.insert(Items.builder()
                .catId(magazinePackage.getCatId())
                .content(magazinePackage.getItemName())
                .itemName(magazinePackage.getItemName())
                .rootCatId(magazinePackage.getRootCatId())
                .onOffStatus(1)
                .sellCounts(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build());
        return items != null ? new RespEntity(101, "新增成功", items) : new RespEntity(500, "新增失败");
    }

    @Override
    public RespEntity deleteMagazine(Integer id) {
        return itemsService.deleteById(id) ? new RespEntity(101, "删除成功") : new RespEntity(500, "删除失败");
    }

    @Override
    public RespEntity updateMagazine(Items items) {
        items.setUpdateTime(new Date());
        Items newItems = itemsService.update(items);
        return new RespEntity(101, "修改成功", newItems);
    }

    @Override
    public RespEntity fuzzySearchMagazine(Items items) {
        List<ItemsVo> itemsVos = itemsService.fuzzySearchMagazine(items);
        return new RespEntity(100, "成功查询所有商品详情", itemsVos);
    }

    @Override
    public RespEntity queryMagazine(Integer id) {
        ItemsVo itemsVo = itemsService.queryDetailById(id);
        return new RespEntity(100, "成功查询商品详情", itemsVo);
    }

    /**
     * 新增类别
     *
     * @param category
     * @return
     */
    @Override
    public RespEntity addCategory(Category category) {
        Category newCategory = categoryService.insert(category);
        return newCategory != null ? new RespEntity(101, "成功新增类别", newCategory) : new RespEntity(500, "新增类别失败");
    }

    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity deleteCategory(Integer id) {
        return categoryService.deleteById(id) ? new RespEntity(101, "删除类别成功") : new RespEntity(500, "删除类别失败");
    }

    /**
     * 修改类别
     *
     * @param category
     * @return
     */
    @Override
    public RespEntity updateCategory(Category category) {
        Category newCategory = categoryService.update(category);
        return newCategory != null ? new RespEntity(101, "成功修改类别", newCategory) : new RespEntity(500, "修改类别失败");
    }

    /**
     * 查询类别
     *
     * @param id
     * @return
     */
    @Override
    public RespEntity queryCategory(Integer id) {
        return new RespEntity(100, "成功查询查询类别", categoryService.queryById(id));
    }

    /**
     * 查询所有类别
     *
     * @return
     */
    @Override
    public RespEntity queryAllCategory(Category category) {
        List<Category> categoryList = categoryService.queryAll(category);
        return new RespEntity(100, "成功查询查询所有类别", categoryList);
    }

    /**
     * 新增订单
     *
     * @param ordersPackage
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RespEntity addOrder(OrdersPackage ordersPackage) {
        List<OrderItems> entities = ordersPackage.getOrderItemsList();
        Orders orders = Orders.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .isDelete(0)
                .payMethod(ordersPackage.getPayMethod())
                .postAmount(ordersPackage.getPostAmount())
                .realPayAmount(ordersPackage.getRealPayAmount())
                .receiverAddress(ordersPackage.getReceiverAddress())
                .receiverMobile(ordersPackage.getReceiverMobile())
                .receiverName(ordersPackage.getReceiverName())
                .totalAmount(ordersPackage.getTotalAmount())
                .userId(ordersPackage.getUserId())
                .build();
        ordersService.insert(orders);
        for (OrderItems orderItem : entities) {
            orderItem.setOrderId(orders.getId());
        }
        orderItemsService.insertBatch(entities);
        return new RespEntity(101, "成功新增订单");
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RespEntity deleteOrder(Integer id) {
        orderItemsService.deleteByOrdersId(id);
        ordersService.deleteById(id);
        return new RespEntity(100, "订单删除成功");
    }

    /**
     * 修改订单
     *
     * @param ordersPackage
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RespEntity updateOrder(OrdersPackage ordersPackage) {
        List<OrderItems> entities = ordersPackage.getOrderItemsList();
        Orders orders = Orders.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .isDelete(0)
                .payMethod(ordersPackage.getPayMethod())
                .postAmount(ordersPackage.getPostAmount())
                .realPayAmount(ordersPackage.getRealPayAmount())
                .receiverAddress(ordersPackage.getReceiverAddress())
                .receiverMobile(ordersPackage.getReceiverMobile())
                .receiverName(ordersPackage.getReceiverName())
                .totalAmount(ordersPackage.getTotalAmount())
                .userId(ordersPackage.getUserId())
                .id(ordersPackage.getId())
                .build();
        ordersService.update(orders);
        orderItemsService.deleteByOrdersId(orders.getId());
        for (OrderItems orderItem : entities) {
            orderItem.setOrderId(orders.getId());
        }
        orderItemsService.insertBatch(entities);
        return new RespEntity(101, "成功修改订单");
    }

    /**
     * 查询订单详情
     *
     * @param id 订单 id
     * @return
     */
    @Override
    public RespEntity queryOrder(Integer id) {
        OrdersVo ordersVo = ordersService.queryOrdersDetail(id);
        return new RespEntity(100, "成功查询订单详情", ordersVo);
    }

    /**
     * 模糊查询所有订单主表信息
     *
     * @param orders
     * @return
     */
    @Override
    public RespEntity queryAllOrder(Orders orders) {
        List<Orders> ordersList = ordersService.queryAllOrder(orders);
        return new RespEntity(100, "成功查询所有订单主表信息", ordersList);
    }

    /**
     * 发送更新邮件
     *
     * @param updateMailPackage
     * @return
     */
    @Override
    public RespEntity sendUpdateMail(UpdateMailPackage updateMailPackage) {
        Integer itemId = updateMailPackage.getItemId();
        String updateContent = updateMailPackage.getUpdateContent();
        String itemName = itemsService.queryById(itemId).getItemName();

        // 查询所有购买了此商品(有效期内)的人
        List<Orders> ordersList = ordersService.queryOrdersByItemId(itemId)
                .stream()
                .filter(orders -> {
                    Date startDate = orders.getCreateTime();
                    return this.isInvalid(startDate);
                })
                .collect(Collectors.toList());
        List<Integer> userIdList = ordersList.stream().map(Orders::getUserId).collect(Collectors.toList());
        log.info("需要给这些客户发送邮件 -> {}", userIdList);
        for (Integer userId : userIdList) {
            // 查询客户资料
            NovelUser novelUser = novelUserDao.queryById(Long.valueOf(userId));
            log.info("客户详细信息 -> {}", novelUser);
            MailInfo mailInfo = new MailInfo();
            mailInfo.setContent(String.format(MailEnum.MAGAZINE.getContent(), novelUser.getUsername(), updateContent));
            mailInfo.setTitle(itemName + "更新");
            mailInfo.setToAddr(novelUser.getEmail());
            mailUtil.sendHtmlMail(mailInfo);
        }
        return new RespEntity(101, "成功发送更新邮件");
    }

    /**
     * 判断购买的商品是否处于有效期内
     *
     * @return
     */
    private boolean isInvalid(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = sdf.format(date);//date-->String
        log.info("开始购买的时间是 -> {}", stringDate);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1); //把日期往后增加一年
        Date deadlineDate = calendar.getTime();
        stringDate = sdf.format(deadlineDate);//date-->String
        log.info("服务截止时间是 -> {}", stringDate);

        Date nowDate = new Date();
        log.info("现在的时间是 -> {}", sdf.format(nowDate));
        return deadlineDate.compareTo(nowDate) >= 0;
    }

}
