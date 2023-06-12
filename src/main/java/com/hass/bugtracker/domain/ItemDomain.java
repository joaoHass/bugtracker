package com.hass.bugtracker.domain;

import com.hass.bugtracker.models.Item;
import com.hass.bugtracker.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDomain {

    private final ItemRepository _repository;

    @Autowired
    public ItemDomain(ItemRepository repository) { _repository = repository;}

    public List<Item> getItems() {
        return _repository.findAll();
    }

}
