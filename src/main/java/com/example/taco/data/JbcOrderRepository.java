package com.example.taco.data;

import com.example.taco.Order;
import org.springframework.stereotype.Repository;

@Repository
public class JbcOrderRepository implements OrderRepository {

    @Override
    public Order save(Order order) {
        return null;
    }
}
