package com.hass.bugtracker.presentation.item;

import com.hass.bugtracker.presentation.item.dto.ItemDto;
import com.hass.bugtracker.presentation.item.dto.UpdateItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/items")
public class ItemController {

    private final ItemService _itemService;

    @Autowired
    public ItemController(ItemService itemService) { _itemService = itemService; }

    @GetMapping
    public List<ItemDto> getItems() { return _itemService.getItems(); }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto, Authentication authentication) {
        try {
            _itemService.createItem(itemDto, authentication);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("The item was created successfully!", HttpStatus.OK);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<?> updateItem(@RequestBody UpdateItemDto itemDto) {
        try {
            _itemService.updateItem(itemDto);
        } catch(IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("The item was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer itemId) {
        try {
            _itemService.deleteItem(itemId);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("The item was deleted successfully!", HttpStatus.OK);
    }
}
