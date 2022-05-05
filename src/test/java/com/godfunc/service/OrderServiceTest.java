package com.godfunc.service;

import com.godfunc.entity.OrderDetailEntity;
import com.godfunc.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Test
    public void saveTest() {
        OrderEntity order = new OrderEntity();
        order.setOrderNo(String.valueOf(System.currentTimeMillis()));
        order.setAmount(new BigDecimal("12.22"));
        order.setCreateTime(new Date());
        boolean flag = orderService.save(order);
        if(flag) {
            log.info("save success order={}", order);
        } else {
            log.error("save error order={}", order);
        }
    }

    @Test
    public void saveTest2() {
        OrderEntity order = new OrderEntity();
        order.setOrderNo(String.valueOf(System.currentTimeMillis()));
        order.setAmount(new BigDecimal("12.22"));
        order.setCreateTime(new Date());
        boolean flag = orderService.save(order, "猫");
        if(flag) {
            log.info("save success order={}", order);
        } else {
            log.error("save error order={}", order);
        }
    }

    @Test
    public void saveDetailTest() {
        OrderDetailEntity orderDetail = new OrderDetailEntity();
        orderDetail.setOrderId(1L);
        orderDetail.setCreateTime(new Date());
        orderDetail.setGoodName("毛巾");
        boolean flag = orderDetailService.save(orderDetail);
        if(flag) {
            log.info("save success orderDetail={}", orderDetail);
        } else {
            log.error("save error orderDetail={}", orderDetail);
        }
    }
}
