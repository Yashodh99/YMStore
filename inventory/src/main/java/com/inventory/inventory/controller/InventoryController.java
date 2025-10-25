package com.inventory.inventory.controller;


import com.inventory.inventory.dto.InventoryDTO;
import com.inventory.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getItems(){
        return inventoryService.getAllItems();
    }

    @GetMapping("/item/{itemId}")
    public InventoryDTO getItemById(@PathVariable Integer itemId){
        return inventoryService.getItemById(itemId);
    }

    @PostMapping
    public InventoryDTO addItem(@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.addItem(inventoryDTO);
    }

    @PutMapping
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping
    public String deleteItem(@PathVariable Integer itemId){
        return inventoryService.deleteItem(itemId);
    }

}
