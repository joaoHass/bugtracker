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

    public void createItem(Item item) {
        if (item.getTitle() == null)
            throw new IllegalArgumentException("The item does not contain a title!");

        _repository.save(item);
    }

    public void deleteItem(Integer itemId) throws IllegalArgumentException{
        if (_repository.findById(itemId).isEmpty())
            throw new IllegalArgumentException(String.format("The item with the id %s does not exist!", itemId));

        _repository.deleteById(itemId);
    }
}
