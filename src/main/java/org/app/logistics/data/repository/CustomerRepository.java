package org.app.logistics.data.repository;

import org.app.logistics.data.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository <Customer, String> {
   Customer findCustomerByEmail(String email);

}
