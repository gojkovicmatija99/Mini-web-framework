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
    @Path("app/orderProduct")
    public String postMethod() {
        if (orderService.placeOrder()) {
            return "200 - OK";
        } else {
            return "500 - Internal Server Error";
        }
    }
}
