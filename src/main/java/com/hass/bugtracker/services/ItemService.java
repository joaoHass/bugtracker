package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.ItemDomain;
import com.hass.bugtracker.dto.ItemDto;
import com.hass.bugtracker.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemDomain _item;

    @Autowired
    public ItemService(ItemDomain item) { _item = item; }

    public List<ItemDto> getItems() {

        List<Item> items = _item.getItems();
        List<ItemDto> itemsToReturn = new ArrayList<ItemDto>();

        for (int i = 0; i < items.size(); i++) {

            ItemDto itemToReturn = new ItemDto(
                    items.get(i).getTitle(),
                    items.get(i).getDescription(),
                    items.get(i).getDueDate(),
                    items.get(i).isCompleted()

            );

            itemsToReturn.add(itemToReturn);
        }

        return itemsToReturn;
    }

    public void createItem(ItemDto itemDto) throws IllegalArgumentException{
        if (itemDto.title() == null)
            throw new IllegalArgumentException("The item does not contain a title!");

        Item item = new Item();
        item.setTitle(itemDto.title());
        item.setDescription(itemDto.description());
        item.setDueDate(itemDto.dueDate());
        item.setCompleted(itemDto.isCompleted());

        _item.createItem(item);

    }
}
