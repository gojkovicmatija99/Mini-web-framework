package app.server.order.impl;

import app.server.order.OrderRepository;
import framework.annotations.spring.Autowired;
import framework.annotations.spring.Bean;
import framework.annotations.spring.Qualifier;
import framework.annotations.spring.Scope;
import framework.annotations.spring.Service;

@Bean(scope = Scope.PROTOTYPE)
public class OrderService {

    @Qualifier("orderRepository")
    @Autowired(verbose = true)
    private OrderRepository orderRepository;

    public boolean placeOrder() {
        return orderRepository.saveOrder();
    }
}
