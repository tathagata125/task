package com.Tathagata.yummyrest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "products")

public class Product {
    @Id
    private String productId;
    private String productName;
    private int productPrice;

}
