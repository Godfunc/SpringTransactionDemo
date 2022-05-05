package com.godfunc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderEntity {

    private Long id;

    private String orderNo;

    private BigDecimal amount;

    private Date createTime;
}
