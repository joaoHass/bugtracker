package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.ItemDomain;
import com.hass.bugtracker.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServices {

    private final ItemDomain _item;

    @Autowired
    public ItemServices(ItemDomain item) { _item = item; }

    public List<Item> getItems() {
        return _item.getItems();
    }
}
