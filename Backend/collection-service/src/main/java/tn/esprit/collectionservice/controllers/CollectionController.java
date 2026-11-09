package tn.esprit.collectionservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.collectionservice.entities.Collection;
import tn.esprit.collectionservice.services.CollectionService;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Collection Controller", description = "Endpoints for managing material collections")
public class CollectionController {

    private final CollectionService service;

    @GetMapping
    @Operation(summary = "Get all collections", description = "Retrieves a list of all recorded collections")
    public ResponseEntity<List<Collection>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a collection by ID", description = "Retrieves a specific collection by its ID")
    public ResponseEntity<Collection> getById(@PathVariable Long id) {
        Collection collection = service.getById(id);
        if (collection != null) {
            return ResponseEntity.ok(collection);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new collection", description = "Creates a new collection and validates the material with the Marketplace service")
    public ResponseEntity<Collection> create(@RequestBody Collection c) {
        Collection saved = service.save(c);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a collection", description = "Updates an existing collection by its ID")
    public ResponseEntity<Collection> update(@PathVariable Long id, @RequestBody Collection c) {
        Collection updated = service.update(id, c);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a collection", description = "Deletes a collection by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}