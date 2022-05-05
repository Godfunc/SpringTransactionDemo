package com.godfunc.service;

import com.godfunc.entity.OrderDetailEntity;
import com.godfunc.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final JdbcTemplate jdbcTemplate;
    private final OrderDetailService orderDetailService;

    public boolean save(OrderEntity order) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into t_order(order_no, amount, create_time) value (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getOrderNo());
            preparedStatement.setBigDecimal(2, order.getAmount());
            preparedStatement.setDate(3, new Date(order.getCreateTime().getTime()));
            return preparedStatement;
        }, holder);
        if (update == 1 && holder.getKey() != null) {
            order.setId(holder.getKey().longValue());
            return true;
        } else {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(OrderEntity order, String goodName) {
        boolean flag = save(order);
        if (flag) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setOrderId(order.getId());
            orderDetail.setGoodName(goodName);
            orderDetail.setCreateTime(new java.util.Date());
            return orderDetailService.save(orderDetail);
        } else {
            return false;
        }
    }

}
