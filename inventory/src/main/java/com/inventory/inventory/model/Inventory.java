package com.inventory.inventory.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {

    @Id
    private int inventoryId;
    private int itemId;
    private int productId;
    private int quantity;
}
