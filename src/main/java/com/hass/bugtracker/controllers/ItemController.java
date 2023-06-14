package com.hass.bugtracker.controllers;

import com.hass.bugtracker.dto.ItemDto;
import com.hass.bugtracker.models.Item;
import com.hass.bugtracker.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/items")
public class ItemController {

    private final ItemServices _itemServices;

    @Autowired
    public ItemController(ItemServices itemServices) { _itemServices = itemServices; }

    @GetMapping("/")
    public List<Item> getItems() { return _itemServices.getItems(); }

    @PostMapping("/")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) {
        try {
            _itemServices.createItem(itemDto);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("The item was created successfully!", HttpStatus.OK);
    }

}
