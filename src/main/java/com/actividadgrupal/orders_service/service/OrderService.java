package com.actividadgrupal.orders_service.service;

import com.actividadgrupal.orders_service.dto.OrderRequest;
import com.actividadgrupal.orders_service.dto.StockResponse;
import com.actividadgrupal.orders_service.entity.Order;
import com.actividadgrupal.orders_service.entity.OrderLineItems;
import com.actividadgrupal.orders_service.mapper.OrderLineItemsMapper;
import com.actividadgrupal.orders_service.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(OrderLineItemsMapper::mapToDto)
                .toList();

        order.setOrderLineITemsList(orderLineItems);

        List<Long> productIds =  order.getOrderLineITemsList().stream().map(OrderLineItems::getProductId).toList();

        StockResponse[] stockResponsesArray = webClientBuilder.build().get()
                .uri("http://stocks-service/api/stocks",
                        uriBuilder -> uriBuilder.queryParam("productId", productIds).build())
                .retrieve()
                .bodyToMono(StockResponse[].class)
                .block();

        assert stockResponsesArray != null;
        boolean allProductsInStock = Arrays.stream(stockResponsesArray).allMatch(StockResponse::isInStock);

        log.info(allProductsInStock ? "All products in stock" : "No products in stock");

        if(allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later.");
        }

    }
}
