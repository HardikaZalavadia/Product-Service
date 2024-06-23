package dev.hardika.EcomProductService.client.orderServiceClient.dtoForOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {
    List<UUID> productIds = new ArrayList<>();
    UUID userId;
    UUID orderId;
    private String address;
}
