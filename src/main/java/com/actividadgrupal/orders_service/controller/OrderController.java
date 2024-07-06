package com.actividadgrupal.orders_service.controller;

import com.actividadgrupal.orders_service.dto.OrderRequest;
import com.actividadgrupal.orders_service.entity.Order;
import com.actividadgrupal.orders_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Create order: {}", orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order placed";
    }
}
