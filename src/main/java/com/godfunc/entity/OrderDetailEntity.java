package com.godfunc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailEntity {

    private Long id;

    private Long orderId;

    private String goodName;

    private Date createTime;
}
