package com.example.itemapi.controller;

import com.example.itemapi.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<Item> items = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item) {

        if (item.getId() == null || item.getName() == null || item.getName().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Item id and name are required");
        }

        items.add(item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {

        for (Item item : items) {
            if (item.getId().equals(id)) {
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
