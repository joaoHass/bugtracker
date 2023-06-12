package com.hass.bugtracker.controllers;

import com.hass.bugtracker.models.Item;
import com.hass.bugtracker.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/items")
public class ItemController {

    private final ItemServices _itemServices;

    @Autowired
    public ItemController(ItemServices itemServices) { _itemServices = itemServices; }

    @GetMapping("/")
    public List<Item> getItems() {return _itemServices.getItems();}

}
