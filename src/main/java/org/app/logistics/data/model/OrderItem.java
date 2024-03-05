package org.app.logistics.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class OrderItem {
    private String id;
    private String type;
    private String description;
    private String productNumber;
    private String customerId;

}
