package app.server.order.impl;

import framework.annotations.http.POST;
import framework.annotations.http.Path;
import framework.annotations.spring.Autowired;
import framework.annotations.spring.Controller;

@Controller
public class OrderController {

    @Autowired(verbose = true)
    OrderService orderService;

    @POST
    @Path("/orderProducts")
    public String postMethod() {
        orderService.placeOrder();
        return "Products have been ordered";
    }
}
