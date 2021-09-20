package com.wyt.wytlab.mq.kafka.kimmking;

import com.wyt.wytlab.mq.kafka.Order;

public interface Producer {

    void send(Order order);

    void close();

    // add your interface method here

    // and then implement it

}
