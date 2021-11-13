package app.server.order.impl;

import app.server.order.OrderRepository;
import framework.annotations.spring.Component;
import framework.annotations.spring.Qualifier;

@Qualifier("orderRepository")
@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public boolean saveOrder() {
        System.out.println("[SERVER] Insert into Orders");
        return true;
    }
}
