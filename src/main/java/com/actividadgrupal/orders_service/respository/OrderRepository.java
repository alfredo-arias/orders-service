package com.actividadgrupal.orders_service.respository;

import com.actividadgrupal.orders_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
