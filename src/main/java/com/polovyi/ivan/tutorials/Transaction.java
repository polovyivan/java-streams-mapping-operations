package com.polovyi.ivan.tutorials;

import java.time.LocalDateTime;
import java.util.List;


public record Transaction(String id,
                          String customerId,
                          List<String> productIds,
                          double totalAmount,
                          LocalDateTime createdAt) {

}


