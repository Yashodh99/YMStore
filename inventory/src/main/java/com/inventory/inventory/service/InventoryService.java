package com.inventory.inventory.service;


import com.inventory.inventory.dao.InventoryDao;
import com.inventory.inventory.dto.InventoryDTO;
import com.inventory.inventory.model.Inventory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllItems(){
        List<Inventory> itemList = inventoryDao.findAll();
        return modelMapper.map(itemList, new TypeToken<List<InventoryDTO>>() {}.getType());
    }

    public InventoryDTO addItem(InventoryDTO inventoryDTO){
        inventoryDao.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public InventoryDTO updateItem(InventoryDTO inventoryDTO){
        inventoryDao.save(modelMapper.map(inventoryDTO,Inventory.class));
        return inventoryDTO;
    }

    public String deleteItem(Integer itemId){
        inventoryDao.deleteById(itemId);
        return "Item deleted successfully";
    }

    public InventoryDTO getItemById(Integer itemId){
        Inventory item = inventoryDao.getItemById(itemId);
        return modelMapper.map(item,InventoryDTO.class);
    }



}
