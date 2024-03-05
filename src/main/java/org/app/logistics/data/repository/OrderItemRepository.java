package org.app.logistics.data.repository;

import org.app.logistics.data.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    OrderItem findOrderItemByType(String type);
}
