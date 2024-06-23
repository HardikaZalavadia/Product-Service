package dev.hardika.EcomProductService.client.orderServiceClient.dtoForOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class OrderResponseDto {
    List<UUID> productIds = new ArrayList<>();
    double totalPrice;
    UUID userId;
    UUID orderId;
}
