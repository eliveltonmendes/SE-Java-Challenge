package com.example.seChallenge.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.example.seChallenge.order.dto.OrderDTO;
import com.example.seChallenge.order.dto.OrderResponseDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ControllerAdvice
@RestController
@RequestMapping(value = { "/order" })
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public OrderResponseDTO[] calculateFoodPayment(@RequestBody OrderDTO order) {
        return orderService.calculateOrderDivision(order);
    }
}
