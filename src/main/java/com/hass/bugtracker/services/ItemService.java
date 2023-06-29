package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.ItemDomain;
import com.hass.bugtracker.domain.UserDomain;
import com.hass.bugtracker.dto.ItemDto;
import com.hass.bugtracker.dto.UpdateItemDto;
import com.hass.bugtracker.models.Item;
import com.hass.bugtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemDomain _item;
    private final UserDomain _user;

    @Autowired
    public ItemService(ItemDomain item, UserDomain user) {
        _item = item;
        _user = user;
    }

    public List<ItemDto> getItems() {

        List<Item> items = _item.getItems();
        List<ItemDto> itemsToReturn = new ArrayList<ItemDto>();

        for (Item item : items) {

            ItemDto itemToReturn = new ItemDto(
                    item.getTitle(),
                    item.getDescription(),
                    item.getDueDate(),
                    item.getCreatedBy().getId(),
                    item.isCompleted()

            );

            itemsToReturn.add(itemToReturn);
        }

        return itemsToReturn;
    }

    public void updateItem(UpdateItemDto itemDto) throws IllegalArgumentException {
        _item.updateItem(itemDto);
    }

    public void createItem(ItemDto itemDto, Authentication authentication) throws IllegalArgumentException {
        User loggedUser = _user.findById(((User)authentication.getPrincipal()).getId()).get();

        Item item = new Item();
        item.setTitle(itemDto.title());
        item.setDescription(itemDto.description());
        item.setDueDate(itemDto.dueDate());
        item.setCreatedBy(loggedUser);
        item.setCompleted(itemDto.isCompleted());

        _item.createItem(item);
    }

    public void deleteItem(Integer itemId) throws IllegalArgumentException {
        _item.deleteItem(itemId);
    }
}
