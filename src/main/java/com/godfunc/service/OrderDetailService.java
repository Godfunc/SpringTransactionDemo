package com.godfunc.service;

import com.godfunc.entity.OrderDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final JdbcTemplate jdbcTemplate;

    public boolean save(OrderDetailEntity orderDetail) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into t_order_detail(order_id, good_name, create_time) value (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, orderDetail.getOrderId());
            preparedStatement.setString(2, orderDetail.getGoodName());
            preparedStatement.setDate(3, new Date(orderDetail.getCreateTime().getTime()));
            return preparedStatement;
        }, holder);
        if (update == 1 && holder.getKey() != null) {
            orderDetail.setId(holder.getKey().longValue());
            return true;
        } else {
            return false;
        }
    }
}
