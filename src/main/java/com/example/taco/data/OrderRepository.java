package com.example.taco.data;

import com.example.taco.Order;

public interface OrderRepository {
    Order save(Order order);
}
