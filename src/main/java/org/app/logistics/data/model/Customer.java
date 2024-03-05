package org.app.logistics.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Customer {
    private String id;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String userName;
    private String password;
    private boolean isLocked = true;

}
