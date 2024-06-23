package dev.hardika.EcomProductService.client.orderServiceClient;

import dev.hardika.EcomProductService.entity.BaseModel;
import dev.hardika.EcomProductService.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Order extends BaseModel {
    @OneToMany
    private List<UUID> productIds = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private double totalPrice;
    private UUID userId;
    private String address;
}
