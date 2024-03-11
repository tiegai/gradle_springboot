package org.example.sharding.controller;

import org.example.sharding.mapper.OrdersMapper;
import org.example.sharding.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sharding")
public class ShardingController {

    @Autowired
    private OrdersMapper ordersMapper;

    @GetMapping("/getOrder1")
    public String getOrder1(){
        Orders orders = ordersMapper.selectOne(1);
        return orders.toString();
    }

    @GetMapping("/getOrder2")
    public String getOrder2(){
        Orders orders = ordersMapper.selectOne(2);
        return orders.toString();
    }

    @PostMapping("/addOrder")
    public void addOrder(){
        for (int i = 1; i <=10 ; i++) {
            Orders orders = Orders.builder()
                    .id(i)
                    .customerId(i)
                    .orderType(i)
                    .amount(1000.0*i)
                    .build();
            ordersMapper.insert(orders);
        }
    }


}
