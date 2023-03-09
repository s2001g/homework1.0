package com.tristan.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping("/create")
    public String create(int accountId) {
        OrderService service = new OrderService();
        return service.addOrder(accountId);
        }
}
