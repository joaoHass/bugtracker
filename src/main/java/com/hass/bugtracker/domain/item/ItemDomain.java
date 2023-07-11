package com.hass.bugtracker.domain.item;

import com.hass.bugtracker.presentation.item.dto.UpdateItemDto;
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

    public void updateItem(UpdateItemDto itemDto) {
        if (itemDto.id() == null)
            throw new IllegalArgumentException("Please provide an item id");

        Item item = _repository.findById(itemDto.id())
                .orElseThrow(() -> new IllegalArgumentException(String.format("An item with id %s does not exist!", itemDto.id())));

        if (itemDto.title() != null)
            item.setTitle(itemDto.title());

        if (itemDto.description() != null)
            item.setDescription(itemDto.description());

        if (itemDto.dueDate() != null)
            item.setDueDate(itemDto.dueDate());

        item.setCompleted(itemDto.isCompleted());
    }
}
